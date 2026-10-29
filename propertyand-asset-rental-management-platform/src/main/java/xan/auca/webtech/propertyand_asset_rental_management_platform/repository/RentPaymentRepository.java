package xan.auca.webtech.propertyand_asset_rental_management_platform.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.RentPayment;

public interface RentPaymentRepository extends JpaRepository<RentPayment, UUID>{
    
    @Query("SELECT p FROM RentPayment p WHERE p.rentalContract.tenant.nationalId = :nationalId ORDER BY p.paidDate DESC")
    List<RentPayment> findPaymentsOfTenant(@Param("nationalId") String nationalId);
}
