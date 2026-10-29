package xan.auca.webtech.propertyand_asset_rental_management_platform.service;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.*;
import xan.auca.webtech.propertyand_asset_rental_management_platform.repository.*;
@Service
public class PropertyService {
    
    @Autowired 
    private PropertyRepository propertyRepository;
    public String saveProperty(Property property) 
    {
        propertyRepository.save(property);
        return "Property saved successfully!";
    }

    public List<Property> getAllProperties() 
    {
        return propertyRepository.findAll();
    }

    public Optional<Property> getPropertyById(UUID id) {
        return propertyRepository.findById(id);
    }
    
    public String updateProperty(UUID id, Property property) {
        //the record must exist before I overwrite it
        if (propertyRepository.findById(id).isEmpty()){
            return "Property with that ID does not exist";
        }  property.setId(id);
        propertyRepository.save(property);
        return "Property updated successfully!";
    }

        public String deleteProperty(UUID id) 
        { if (propertyRepository.findById(id).isEmpty()){
            return "Property with that ID does not exist";
        }
        propertyRepository.deleteById(id);
        return "Property deleted successfully";
    }

        public List<Property> getFreeProperties() 
        {
        return propertyRepository.findFreeProperties();
    }
}
