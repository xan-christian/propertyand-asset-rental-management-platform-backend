package xan.auca.webtech.propertyand_asset_rental_management_platform.domain;

import jakarta.persistence.*;
import java.time.*;
import java.util.*;

@Entity
@Table(name = "rent_payment")
public class RentPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private UUID id;
    private String paymentMethod;
    private String reference;
    private int amount;
    private LocalDate paidDate;

    /// many payments can belong to one contract; the contract keeps no list of them
    @ManyToOne
    @JoinColumn(name = "rental_contract_id")
    private RentalContract rentalContract;

    public RentPayment() {
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getPaymentMethod() 
    {
        return paymentMethod; 
    }
    public void setPaymentMethod(String paymentMethod) 
    { 
        this.paymentMethod = paymentMethod; 
    }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }

    public LocalDate getPaidDate() {
        return paidDate; 
    }
    public void setPaidDate(LocalDate paidDate) { this.paidDate = paidDate; }

    public RentalContract getRentalContract() { return rentalContract; }
    public void setRentalContract(RentalContract rentalContract) 
    {
        this.rentalContract = rentalContract; 
    }
}