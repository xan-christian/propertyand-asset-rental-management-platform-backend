package xan.auca.webtech.propertyand_asset_rental_management_platform.service;


import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import java.util.*;

import xan.auca.webtech.propertyand_asset_rental_management_platform.repository.*;
import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.*;


@Service 
public class PropertyManagerService {
    @Autowired
    private PropertyManagerRepository propertyManagerRepository;


    public String savePropertyManager(PropertyManager propertyManager) {
        propertyManagerRepository.save(propertyManager);
        return "Property manager saved successfully";
    }

    public List<PropertyManager> getAllPropertyManagers() {
        return propertyManagerRepository.findAll();
    }

    public Optional<PropertyManager> getPropertyManagerById(UUID id) {
        return propertyManagerRepository.findById(id);
    }

    public String updatePropertyManager(UUID id, PropertyManager propertyManager) {
        if (propertyManagerRepository.findById(id).isEmpty()) {
            return "The property manager with that id does not exist";
        }
        propertyManager.setId(id);
        propertyManagerRepository.save(propertyManager);
        return "Property manager updated successfully";
    }

    public String deletePropertyManager(UUID id) {
        if (propertyManagerRepository.findById(id).isEmpty()) {
            return "The property manager with that id does not exist";
        }
        propertyManagerRepository.deleteById(id);
        return "Property manager deleted successfully";
    }
}

