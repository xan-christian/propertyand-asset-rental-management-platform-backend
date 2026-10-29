package xan.auca.webtech.propertyand_asset_rental_management_platform.service;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.*;
import xan.auca.webtech.propertyand_asset_rental_management_platform.repository.*;


@Service 
public class PropertyTypeService {
    @Autowired 
    private PropertyTypeRepository propertyTypeRepository;
    
    public String savePropertyType(PropertyType propertyType) {
        propertyTypeRepository.save(propertyType);
        return "Property type saved successfully!";
    }

    public List<PropertyType> getAllPropertyTypes() {
        return propertyTypeRepository.findAll();
    }
    public Optional<PropertyType> getPropertyTypeById(UUID id) {
        return propertyTypeRepository.findById(id);
    }

    public String UpdatePropertyType(UUID id, PropertyType propertyType) {
        //the record must exist before we overwrite it
        if (propertyTypeRepository.findById(id).isEmpty()){
            return "Property with that ID does not exist";
        }
        propertyType.setId(id);
        propertyTypeRepository.save(propertyType);
        return "Property type updated successfully!";
    }

    public String deletePropertyType(UUID id) {
        if (propertyTypeRepository.findById(id).isEmpty()){
            return "Property type with that ID does not exist";
        }
        propertyTypeRepository.deleteById(id);
        return "Property type deleted successfully";
    }
        public List<PropertyType> getUnusedPropertyTypes() 
        {
        return propertyTypeRepository.findUnusedPropertyTypes();
    }
}

