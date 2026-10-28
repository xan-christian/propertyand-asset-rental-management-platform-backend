package xan.auca.webtech.propertyand_asset_rental_management_platform.domain;

import jakarta.persistence.*;
import java.util.*;
import java.time.LocalDate;

@Entity 
@Table (name = "rental_contract")
public class RentalContract {
 @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;
    private LocalDate startDate;
    //stored as the  'PROPOSED......' not as an num

    @Enumerated(EnumType.STRING)
    private ContractStatus status;


    private int monthlyRent;
    private int months;

    @ManyToOne @JoinColumn (name = "tenant_id")
    private Tenant tenant;

@ManyToOne
@JoinColumn (name = "property_manager_id")
private PropertyManager propertyManager;

public RentalContract() {
}

public UUID getId() {return id;}
public void setId(UUID id) {this.id = id;}

public LocalDate getStartDate() {return startDate;}
public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;}

public ContractStatus getStatus() {
    return status;
}
public void setStatus(ContractStatus status) {this.status = status;}

public int getMonthlyRent() {return monthlyRent;}
public void setMonthlyRent(int monthlyRent) {
    this.monthlyRent = monthlyRent;}

public int getMonths() {return months;}
public void setMonths(int months) {this.months = months;}

public Tenant getTenant() {
    return tenant;
}
public void setTenant(Tenant tenant) {
    this.tenant = tenant;}

    public PropertyManager getPropertyManager() {
        return propertyManager;
    }
    public void setPropertyManager(PropertyManager propertyManager) {
        this.propertyManager = propertyManager;
    }
}