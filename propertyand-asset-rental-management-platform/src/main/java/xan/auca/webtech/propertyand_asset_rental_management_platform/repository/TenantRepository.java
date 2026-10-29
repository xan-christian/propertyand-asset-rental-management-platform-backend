package xan.auca.webtech.propertyand_asset_rental_management_platform.repository;

import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.*;


public interface TenantRepository extends JpaRepository<Tenant, UUID> {
    List<Tenant> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    @Query("SELECT DISTINCT c.tenant FROM RentalContract c WHERE c.propertyManager.id = :managerId")
    List<Tenant> findTenantsOfPropertyManager(@Param("managerId") UUID managerId);
}
