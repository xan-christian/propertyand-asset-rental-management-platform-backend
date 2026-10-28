package xan.auca.webtech.propertyand_asset_rental_management_platform.domain;
import jakarta.persistence.*;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
@Table (name = "property_type")
public class PropertyType {

    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    //teh register is kept by property manager, so the property type does not need to know about the property manager.
    @ManyToMany (mappedBy = "propertyTypes")
    @JsonIgnore private List<PropertyManager> managers = new ArrayList<>();
    //jsonIgnore keeps the JSON from looping (type -> manager -> type -> manager -> type -> and so n)


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

    public List<PropertyManager> getManagers() {
        return managers;
    }
public void setManagers(List<PropertyManager> managers){
    this.managers  =managers;
}
}
