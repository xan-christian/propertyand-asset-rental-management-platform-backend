package xan.auca.webtech.propertyand_asset_rental_management_platform.controller;

import java.time.LocalDate;
import java.util.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;

import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.ContractStatus;
import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.RentalContract;
import xan.auca.webtech.propertyand_asset_rental_management_platform.service.RentalContractService;
import org.springframework.data.domain.*;


    
@RestController
@RequestMapping(value = "/api/rental-contracts")
public class RentalContractController {

    @Autowired
    private RentalContractService rentalContractService;

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveRentalContract(@RequestBody RentalContract rentalContract) {
        String result = rentalContractService.saveRentalContract(rentalContract);
        if (result.equals("Rental contract saved successfully")) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        if (result.equals("Property manager already starts a contract on that date")) {
            return new ResponseEntity<>(result, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllRentalContracts() {
        return new ResponseEntity<>(rentalContractService.getAllRentalContracts(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getRentalContract(@PathVariable UUID id) {
        Optional<RentalContract> rentalContract = rentalContractService.getRentalContractById(id);
        if (rentalContract.isPresent()) {
            return new ResponseEntity<>(rentalContract.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("The rental contract with that id does not exist", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/by-status")
    public ResponseEntity<?> getByStatus(@RequestParam ContractStatus status) {
        return new ResponseEntity<>(rentalContractService.getRentalContractsByStatus(status), HttpStatus.OK);
    }

    @GetMapping(value = "/between")
    public ResponseEntity<?> getRentalContractsBetween(@RequestParam String start, @RequestParam String end) {
        LocalDate from = LocalDate.parse(start);
        LocalDate to = LocalDate.parse(end);
        return new ResponseEntity<>(rentalContractService.getRentalContractsBetween(from, to), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateRentalContract(@PathVariable UUID id, @RequestBody RentalContract rentalContract) {
        String result = rentalContractService.updateRentalContract(id, rentalContract);
        if (result.equals("Rental contract updated successfully")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

       @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteRentalContract(@PathVariable UUID id) {
        String result = rentalContractService.deleteRentalContract(id);
        if (result.equals("Rental contract deleted successfully")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        if (result.equals("That rental contract has rent payments, delete them first")) {
            return new ResponseEntity<>(result, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    
    @PatchMapping(value = "/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable UUID id, @RequestParam ContractStatus status) {
        String result = rentalContractService.updateStatus(id, status);
        if (result.equals("Rental contract status updated successfully")) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        if (result.equals("The rental contract with that id does not exist")) {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/stats/by-status")
    public ResponseEntity<?> getStatusReport() {
        return new ResponseEntity<>(rentalContractService.getStatusReport(), HttpStatus.OK);
    }

    @PatchMapping(value = "/cancel-day")
    public ResponseEntity<?> cancelDay(@RequestParam UUID propertyManagerId, @RequestParam String date) {
        String result = rentalContractService.cancelDay(propertyManagerId, LocalDate.parse(date));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

        @GetMapping(value = "/page")
    public ResponseEntity<?> getRentalContractsPage(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int size,
                                                    @RequestParam(defaultValue = "startDate,desc") String sort) {
        // split "startDate,desc" into the field and the direction
        String[] parts = sort.split(",");
        Sort.Direction direction = Sort.Direction.ASC;
        if (parts.length > 1) {
            direction = Sort.Direction.fromString(parts[1]);
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, parts[0]));
        return new ResponseEntity<>(rentalContractService.getRentalContractsPage(pageable), HttpStatus.OK);
    }
}
