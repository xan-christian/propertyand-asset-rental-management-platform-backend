package xan.auca.webtech.propertyand_asset_rental_management_platform.repository;

import org.springframework.data.jpa.repository.*;
import java.util.*;
import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.*;
import java.time.*;
import org.springframework.data.repository.query.*;

public interface RentalContractRepository extends JpaRepository<RentalContract, UUID> {

    List<RentalContract> findByStatusOrderByStartDateAsc(ContractStatus status);

    List<RentalContract> findByStartDateBetweenOrderByStartDateAsc(LocalDate start, LocalDate end);

    boolean existsByPropertyManagerIdAndStartDateAndStatusNot(UUID propertyManagerId, LocalDate startDate, ContractStatus status);

    @Query("SELECT c.status, COUNT(c) FROM RentalContract c GROUP BY c.status")
    List<Object[]> statusReport();

    @Modifying
    @Query("UPDATE RentalContract c SET c.status = :terminated WHERE c.propertyManager.id = :managerId AND c.startDate = :day AND c.status <> :activated")
    int cancelDay(@Param("managerId") UUID managerId, @Param("day") LocalDate day,
        @Param("terminated") ContractStatus terminated, @Param("activated") ContractStatus activated);

}