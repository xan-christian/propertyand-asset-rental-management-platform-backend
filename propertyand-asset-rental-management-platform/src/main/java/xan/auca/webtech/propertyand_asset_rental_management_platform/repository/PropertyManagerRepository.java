package xan.auca.webtech.propertyand_asset_rental_management_platform.repository;


import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.*;
public interface PropertyManagerRepository extends JpaRepository<PropertyManager, UUID> {

    @Query("SELECT DISTINCT m FROM PropertyManager m JOIN m.propertyTypes t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<PropertyManager> searchByPropertyType(@Param("name") String name);
}