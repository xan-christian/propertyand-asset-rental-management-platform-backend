package xan.auca.webtech.propertyand_asset_rental_management_platform.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.*;
import java.util.*;
import xan.auca.webtech.propertyand_asset_rental_management_platform.repository.*;
@Service
public class RentalContractService {

@Autowired
    private RentalContractRepository rentalContractRepository;

    public String saveRentalContract(RentalContract rentalContract) {
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
        if (rentalContractRepository.findById(id).isEmpty()) {
            return "The rental contract with that id does not exist";
        }

        rentalContract.setId(id);
        rentalContractRepository.save(rentalContract);
        return "Rental contract updated successfully";
    }

    public String deleteRentalContract(UUID id) 
    {
        if (rentalContractRepository.findById(id).isEmpty()) {
            return "The rental contract with that id does not exist";
        }
        rentalContractRepository.deleteById(id);
        return "Rental contract deleted successfully";
    }
}