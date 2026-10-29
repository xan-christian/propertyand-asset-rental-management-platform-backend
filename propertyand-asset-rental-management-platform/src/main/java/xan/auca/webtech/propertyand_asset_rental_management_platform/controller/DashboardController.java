package xan.auca.webtech.propertyand_asset_rental_management_platform.controller;


import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import xan.auca.webtech.propertyand_asset_rental_management_platform.service.*;


@RestController
@RequestMapping(value = "/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping(value = "/summary")
    public ResponseEntity<?> getSummary() {
        return new ResponseEntity<>(dashboardService.getSummary(), HttpStatus.OK);
    }
}