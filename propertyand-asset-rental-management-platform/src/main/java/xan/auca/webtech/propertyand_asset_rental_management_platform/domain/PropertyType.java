package xan.auca.webtech.propertyand_asset_rental_management_platform.domain;
import jakarta.persistence.*;
import java.util.*;

@Entity 
@Table (name = "property_type")
public class PropertyType {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    public PropertyType() {
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
