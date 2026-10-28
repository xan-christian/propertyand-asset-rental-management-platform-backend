package xan.auca.webtech.propertyand_asset_rental_management_platform.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.*;
import xan.auca.webtech.propertyand_asset_rental_management_platform.service.*;

@RestController 
@RequestMapping (value="/api/property-types")
public class PropertyTypeController {
    @Autowired 
    private PropertyTypeService propertyTypeService;

    @PostMapping (value="/save")
    public ResponseEntity<String> savePropertyType(@RequestBody PropertyType propertyType) {
        String result = propertyTypeService.savePropertyType(propertyType);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    
    }
    @GetMapping (value="/all")
    public ResponseEntity<?> getAllPropertyTypes(){
        return new ResponseEntity<>(propertyTypeService.getAllPropertyTypes(), HttpStatus.OK);
    }

    @GetMapping (value="/{id}")
    public ResponseEntity<?> getPropertyType(@PathVariable UUID id) {
        Optional<PropertyType> propertyType = propertyTypeService.getPropertyTypeById(id);
        if (propertyType.isPresent()) {
            return new ResponseEntity<>(propertyType.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Property type with that ID is not found", HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping (value="/update/{id}")
    public ResponseEntity<?> updatePropertyType(@PathVariable UUID id, @RequestBody PropertyType propertyType){
        String result = propertyTypeService.UpdatePropertyType(id, propertyType);
        if (result.equals("Property type updated successfully!")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
                }
                return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
                
            }
        @DeleteMapping (value="/delete/{id}")
        public ResponseEntity<?> deletePropertyType(@PathVariable UUID id) {
            String result = propertyTypeService.deletePropertyType(id);
            if (result.equals("Property type deleted successfully")) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);

    }
}
