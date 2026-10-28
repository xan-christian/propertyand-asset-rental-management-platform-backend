package xan.auca.webtech.propertyand_asset_rental_management_platform.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.*;
import xan.auca.webtech.propertyand_asset_rental_management_platform.service.*;

@RestController 
@RequestMapping(value ="api/properties")
public class PropertyController {
    @Autowired 
    private PropertyService propertyService;
    @PostMapping (value = "/save")
    public ResponseEntity<?> saveProperty(@RequestBody Property property) {
        String result =propertyService.saveProperty(property);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


@GetMapping (value= "/all")
public ResponseEntity<?> getAllProperties() {
    return new ResponseEntity<>(propertyService.getAllProperties(), HttpStatus.OK);
}
@GetMapping (value = "/{id}")
public ResponseEntity<?> getProperty(@PathVariable UUID id) {
    Optional<Property> property = propertyService.getPropertyById(id);
    if (property.isPresent()) {
        return new ResponseEntity<>(property.get(), HttpStatus.OK);
    } else {
        return new ResponseEntity<>("Sorry! The property with that ID does't exist", HttpStatus.NOT_FOUND);}
    }
        @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateProperty(@PathVariable UUID id, @RequestBody Property property) {
        String result = propertyService.updateProperty(id, property);
        if (result.equals("Property updated successfully")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }


        @DeleteMapping (value = "/delete/{id}")
        public ResponseEntity<?> deleteProperty(@PathVariable UUID id) {
            String result = propertyService.deleteProperty(id);
            if (result.equals("Property deleted successfully")) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
}
