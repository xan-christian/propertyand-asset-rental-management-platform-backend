package xan.auca.webtech.propertyand_asset_rental_management_platform.service;


import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import java.util.*;
import org.springframework.transaction.annotation.*;
import xan.auca.webtech.propertyand_asset_rental_management_platform.repository.PropertyManagerRepository;
import xan.auca.webtech.propertyand_asset_rental_management_platform.repository.PropertyTypeRepository;
import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.PropertyManager;
import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.PropertyType;


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

        public List<PropertyManager> getPropertyManagersByPropertyType(String name) {
        return propertyManagerRepository.searchByPropertyType(name);
    }
        @Autowired
    private PropertyTypeRepository propertyTypeRepository;

    @Transactional
    public String addPropertyType(UUID propertyManagerId, UUID propertyTypeId) {
        Optional<PropertyManager> foundManager = propertyManagerRepository.findById(propertyManagerId);
        if (foundManager.isEmpty()) {
            return "The property manager with that id does not exist";
        }
        Optional<PropertyType> foundType = propertyTypeRepository.findById(propertyTypeId);
        if (foundType.isEmpty()) {
            return "That property type does not exist";
        }
        PropertyManager manager = foundManager.get();
        PropertyType wanted = foundType.get();

        // compare by id: objects loaded in different calls are not the same instance
        for (PropertyType owned : manager.getPropertyTypes()) {
            if (owned.getId().equals(wanted.getId())) {
                return "The property manager already has that property type";
            }
        }
        manager.getPropertyTypes().add(wanted);
        propertyManagerRepository.save(manager);
        return "Property type added to the property manager";
    }
}

