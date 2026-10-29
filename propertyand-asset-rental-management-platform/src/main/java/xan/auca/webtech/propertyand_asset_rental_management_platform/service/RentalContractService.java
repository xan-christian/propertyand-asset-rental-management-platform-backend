package xan.auca.webtech.propertyand_asset_rental_management_platform.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.*;

import java.time.LocalDate;
import java.util.*;
import xan.auca.webtech.propertyand_asset_rental_management_platform.repository.*;

@Service
public class RentalContractService {

@Autowired
    private RentalContractRepository rentalContractRepository;

       public String saveRentalContract(RentalContract rentalContract) {
        // a new contract always starts as PROPOSED,
        rentalContract.setStatus(ContractStatus.PROPOSED);
        if (rentalContract.getStartDate().isBefore(LocalDate.now())) {
            return "Rental contract date cannot be in the past";
        }
        boolean managerIsBusy = rentalContractRepository.existsByPropertyManagerIdAndStartDateAndStatusNot(
                rentalContract.getPropertyManager().getId(), rentalContract.getStartDate(), ContractStatus.TERMINATED);
        if (managerIsBusy) {
            return "Property manager already starts a contract on that date";
        }
        rentalContractRepository.save(rentalContract);
        return "Rental contract saved successfully";
    }

    public List<RentalContract> getAllRentalContracts() {
        return rentalContractRepository.findAll();
    }

    public Optional<RentalContract> getRentalContractById(UUID id) 
    {
        return rentalContractRepository.findById(id);
    }

    public String updateRentalContract(UUID id, RentalContract rentalContract) {
        Optional<RentalContract> found = rentalContractRepository.findById(id);
        if (found.isEmpty()) {
            return "The rental contract with that id does not existt";
        }
        rentalContract.setId(id);
        // //the status can only change through the status endpoint
        rentalContract.setStatus(found.get().getStatus());
        rentalContractRepository.save(rentalContract);
        return "Rental contract updated successfully!";
    }

    @Autowired
    private RentPaymentRepository rentPaymentRepository;

    public String deleteRentalContract(UUID id) {
        Optional<RentalContract> found = rentalContractRepository.findById(id);
        if (found.isEmpty()) {
            return "The rental contract with that id does not exist";
        }
        // a contract with payments keeps its history
        if (rentPaymentRepository.existsByRentalContract(found.get())) {
            return "That rental contract has rent payments, delete them first";
        }
        rentalContractRepository.deleteById(id);
        return "Rental contract deleted successfully";
    }

        public List<RentalContract> getRentalContractsByStatus(ContractStatus status) {
        return rentalContractRepository.findByStatusOrderByStartDateAsc(status);}
    
        public List<RentalContract> getRentalContractsBetween(LocalDate start, LocalDate end) 
        {
        return rentalContractRepository.findByStartDateBetweenOrderByStartDateAsc(start, end);
        }

        public String updateStatus(UUID id, ContractStatus newStatus) {
        Optional<RentalContract> found = rentalContractRepository.findById(id);
        if (found.isEmpty()) {
            return "The rental contract with that id does not exist";
        }
        RentalContract contract = found.get();
        ContractStatus current = contract.getStatus();

        ////only four moves are allowed; everything else stays false
        boolean moveIsAllowed = false;
        if (current == ContractStatus.PROPOSED
                && (newStatus == ContractStatus.SIGNED || newStatus == ContractStatus.TERMINATED)) {
            moveIsAllowed = true;
        }
        if (current == ContractStatus.SIGNED
                && (newStatus == ContractStatus.ACTIVATED || newStatus == ContractStatus.TERMINATED)) {
            moveIsAllowed = true;
        }
        if (!moveIsAllowed) {
            return "Cannot change status from " + current + " to " + newStatus;
        }
        contract.setStatus(newStatus);
        rentalContractRepository.save(contract);
        return "Rental contract status updated successfully";
    }

    public List<Object[]> getStatusReport() 
    {
    return rentalContractRepository.statusReport();
    }
}