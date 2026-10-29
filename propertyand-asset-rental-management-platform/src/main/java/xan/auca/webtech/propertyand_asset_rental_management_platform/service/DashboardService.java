package xan.auca.webtech.propertyand_asset_rental_management_platform.service;

import java.time.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import xan.auca.webtech.propertyand_asset_rental_management_platform.domain.*;
import xan.auca.webtech.propertyand_asset_rental_management_platform.repository.*;

@Service
public class DashboardService {

    @Autowired
    private TenantRepository tenantRepository;

    @Autowired
    private PropertyManagerRepository propertyManagerRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private RentalContractRepository rentalContractRepository;

    @Autowired
    private RentPaymentRepository rentPaymentRepository;

    public Map<String, Long> getSummary() {
        // LinkedHashMap keeps the keys in the order we add them
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("tenants", tenantRepository.count());
        summary.put("propertyManagers", propertyManagerRepository.count());
        summary.put("freeProperties", (long) propertyRepository.findFreeProperties().size());
        summary.put("contractsToday", rentalContractRepository.countByStartDate(LocalDate.now()));
        summary.put("proposed", rentalContractRepository.countByStatus(ContractStatus.PROPOSED));
        summary.put("payments", rentPaymentRepository.count());
        return summary;
    }
}
