package xan.auca.webtech.propertyand_asset_rental_management_platform.repository;

import org.springframework.data.jpa.repository.*;
import java.util.*;
import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.*;

public interface RentalContractRepository extends JpaRepository<RentalContract, UUID> {

}