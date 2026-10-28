package xan.auca.webtech.propertyand_asset_rental_management_platform.domain;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table (name = "property_manager")

public class PropertyManager {
@Id 
@GeneratedValue (strategy = GenerationType.UUID)
private UUID id;
private String firstName;
private String lastName;
private String phone;

@ManyToOne //many managers can point to one property; the column may be null if the manager is not assigned to any property
@JoinColumn (name = "property_id")
private Property property;

@ManyToMany 
@JoinTable (name = "property_manager_property_type")
private List<PropertyType> propertyTypes = new ArrayList<>();


public PropertyManager() {
} 
public UUID getId() {
    return id;
}
public void setId(UUID id) {
    this.id = id;
}
public String getFirstName() {
    return firstName;
}

public void setFirstName(String firstName) {
    this.firstName = firstName;
}
public String getLastName() {
    return lastName;

} public void setLastName(String lastName) {
    this.lastName = lastName;
}

public String getPhone() {
    return phone;}

public void setPhone(String phone) {
    this.phone = phone;}

public Property getProperty() {
    return property;}

public void setProperty(Property property) {
    this.property = property;}

public List<PropertyType> getPropertyTypes() {
    return propertyTypes;
}
public void setPropertyTypes(List<PropertyType> propertyTypes) {
    this.propertyTypes = propertyTypes;}

    

}
