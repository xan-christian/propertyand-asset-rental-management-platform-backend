package xan.auca.webtech.propertyand_asset_rental_management_platform.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.RentPayment;

public interface RentPaymentRepository extends JpaRepository<RentPayment, UUID>{
    
}
