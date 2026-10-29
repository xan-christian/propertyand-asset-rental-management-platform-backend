package xan.auca.webtech.propertyand_asset_rental_management_platform.service;

import xan.auca.webtech.propertyand_asset_rental_management_platform.repository.*;
import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.*;
import org.springframework.beans.factory.annotation.*;
import java.util.*;
import org.springframework.stereotype.*;
    
@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    public String saveTenant(Tenant tenant) {
        tenantRepository.save(tenant);
        return "Tenant saved successfully";
    }

    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    public Optional<Tenant> getTenantById(UUID id) {
        return tenantRepository.findById(id);
    }

    public String updateTenant(UUID id, Tenant tenant) {
        if (tenantRepository.findById(id).isEmpty()) {
            return "The tenant with that id does not exist";
        }
        tenant.setId(id);
        tenantRepository.save(tenant);
        return "Tenant updated successfully";
    }

    public String deleteTenant(UUID id) {
        if (tenantRepository.findById(id).isEmpty()) {
            return "The tenant with that id does not exist";
        }
        tenantRepository.deleteById(id);
        return "Tenant deleted successfully";
    }
}
