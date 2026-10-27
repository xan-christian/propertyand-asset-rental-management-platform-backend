package xan.auca.webtech.propertyand_asset_rental_management_platform.domain;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "property")
public class Property {

    @Id 
    @GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

    private int propertyNumber;
    private String name;

    public Property() {
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }public int getPropertyNumber() {
        return propertyNumber;
    }
    public void setPropertyNumber(int propertyNumber) {
        this.propertyNumber = propertyNumber;
    } public String  getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
