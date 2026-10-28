package xan.auca.webtech.propertyand_asset_rental_management_platform.domain;

import jakarta.persistence.*;
import java.util.*;


@Entity
@Table (name = "tenant")
public class Tenant {
    @Id 
@GeneratedValue (strategy = GenerationType.UUID)
    private UUID id;

private String firstName;
private String lastName;

@Column (unique = true)
private String nationalId;
private String phone;
private String email;

public Tenant() {
}
public UUID getId() {return id;
}
public void setId(UUID id){ this.id = id;}

public String getFirstName() {return firstName;}
public void setFirstName(String firstName) {this.firstName = firstName;}

public String getLastName() {return lastName;}
public void setLastName(String lastName) {this.lastName = lastName;}

public String getNationalId() {return nationalId;}
public void setNationalId(String nationalId) {this.nationalId = nationalId;}
public String getPhone() {return phone;}
public void setPhone(String phone) {this.phone = phone;}

public String getEmail() {return email;}
public void setEmail(String email) {this.email = email;}
}