package xan.auca.webtech.propertyand_asset_rental_management_platform.repository;

import java.util.*;
import org.springframework.data.jpa.repository.*;
import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.*;


public interface PropertyRepository extends JpaRepository<Property, UUID> {
    @Query("SELECT p FROM Property p WHERE NOT EXISTS (SELECT m FROM PropertyManager m WHERE m.property = p) ORDER BY p.propertyNumber")
    List<Property> findFreeProperties();
    Optional<Property> findByPropertyNumber(int propertyNumber);
}
