package xan.auca.webtech.propertyand_asset_rental_management_platform.controller;

import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import xan.auca.webtech.propertyand_asset_rental_management_platform.service.PropertyManagerService;
import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.PropertyManager;

@RestController 
@RequestMapping (value = "/api/property-managers")
public class PropertyManagerController {

    @Autowired
    private PropertyManagerService propertyManagerService;

    @PostMapping(value = "/save")
    public ResponseEntity<?> savePropertyManager(@RequestBody PropertyManager propertyManager) {
        String result = propertyManagerService.savePropertyManager(propertyManager);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllPropertyManagers() {
        return new ResponseEntity<>(propertyManagerService.getAllPropertyManagers(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPropertyManager(@PathVariable UUID id) {

        Optional<PropertyManager> propertyManager = propertyManagerService.getPropertyManagerById(id);
        if (propertyManager.isPresent()) {
            return new ResponseEntity<>(propertyManager.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The property manager with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updatePropertyManager(@PathVariable UUID id, @RequestBody PropertyManager propertyManager) {
        String result = propertyManagerService.updatePropertyManager(id, propertyManager);
        if (result.equals("Property manager updated successfully")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deletePropertyManager(@PathVariable UUID id) {
        String result = propertyManagerService.deletePropertyManager(id);
        if (result.equals("Property manager deleted successfully")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }
}
