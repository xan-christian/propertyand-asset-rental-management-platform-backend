package xan.auca.webtech.propertyand_asset_rental_management_platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.RentPayment;
import xan.auca.webtech.propertyand_asset_rental_management_platform.service.RentPaymentService;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/rent-payments")
public class RentPaymentController {

    @Autowired
    private RentPaymentService rentPaymentService;

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveRentPayment(@RequestParam UUID rentalContractId, @RequestBody RentPayment rentPayment) {
        String result = rentPaymentService.saveRentPayment(rentalContractId, rentPayment);
        if (result.equals("Rent payment saved successfully")) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        if (result.equals("The rental contract with that id does not exist")) {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }
}