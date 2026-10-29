package xan.auca.webtech.propertyand_asset_rental_management_platform.service;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.ContractStatus;
import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.RentPayment;
import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.RentalContract;
import java.util.UUID;
import xan.auca.webtech.propertyand_asset_rental_management_platform.repository.RentPaymentRepository;
import xan.auca.webtech.propertyand_asset_rental_management_platform.repository.RentalContractRepository;
import java.util.Optional;


@Service
public class RentPaymentService {

    @Autowired
    private RentPaymentRepository rentPaymentRepository;

    @Autowired
    private RentalContractRepository rentalContractRepository;

    public String saveRentPayment(UUID rentalContractId, RentPayment rentPayment) {
        Optional<RentalContract> found = rentalContractRepository.findById(rentalContractId);
        if (found.isEmpty()) {
            return "The rental contract with that id does not exist";
        }
        if (found.get().getStatus() != ContractStatus.ACTIVATED) {
            return "Rent payments are only for ACTIVATED rental contracts";
        }
        // the service, not the client, decides the date and the link
        rentPayment.setPaidDate(LocalDate.now());
        rentPayment.setRentalContract(found.get());
        rentPaymentRepository.save(rentPayment);
        return "Rent payment saved successfully";
    }
}
