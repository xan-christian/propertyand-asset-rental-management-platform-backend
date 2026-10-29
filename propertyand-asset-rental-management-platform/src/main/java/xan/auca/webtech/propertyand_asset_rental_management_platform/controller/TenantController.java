package xan.auca.webtech.propertyand_asset_rental_management_platform.controller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.Tenant;
import xan.auca.webtech.propertyand_asset_rental_management_platform.service.TenantService;
import java.util.*;
    

@RestController
@RequestMapping(value = "/api/tenants")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveTenant(@RequestBody Tenant tenant) 
    {
        String result = tenantService.saveTenant(tenant);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllTenants() {
        return new ResponseEntity<>(tenantService.getAllTenants(), HttpStatus.OK);
    }
    
@GetMapping(value = "/{id}")
    public ResponseEntity<?> getTenant(@PathVariable UUID id) {
        Optional<Tenant> tenant = tenantService.getTenantById(id);
        if (tenant.isPresent()) {
            return new ResponseEntity<>(tenant.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The tenant with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/by-last-name")
    public ResponseEntity<?> getTenantsByLastName(@RequestParam String lastName) {
        return new ResponseEntity<>(tenantService.getTenantsByLastName(lastName), HttpStatus.OK);
    }

    @GetMapping(value = "/of-property-manager/{propertyManagerId}")
    public ResponseEntity<?> getTenantsOfPropertyManager(@PathVariable UUID propertyManagerId) 
    {
        Optional<List<Tenant>> tenants = tenantService.getTenantsOfPropertyManager(propertyManagerId);
        if (tenants.isPresent()) {
            return new ResponseEntity<>(tenants.get(), HttpStatus.OK);}
        return new ResponseEntity<>("The property manager with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateTenant(@PathVariable UUID id, @RequestBody Tenant tenant)
    {
        String result = tenantService.updateTenant(id, tenant);
        if (result.equals("Tenant updated successfully")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteTenant(@PathVariable UUID id) {
        String result = tenantService.deleteTenant(id);
        if (result.equals("Tenant deleted successfully")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }
}
