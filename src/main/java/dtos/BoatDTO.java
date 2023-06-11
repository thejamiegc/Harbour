package dtos;

import entities.Boat;

import java.util.ArrayList;
import java.util.List;

public class BoatDTO {
    private Long id;
    private String name;
    private String make;
    private String brand;
    private String image;
    private Long harbourId;
    private List<String> ownerDTOsAsStrings;

    public BoatDTO() {
    }

    public BoatDTO(Long id, String name, String make, String brand, String image, Long harbourId) {
        this.id = id;
        this.name = name;
        this.make = make;
        this.brand = brand;
        this.image = image;
        this.harbourId = harbourId;
    }

    public BoatDTO(String name, String make, String brand, String image) {
        this.name = name;
        this.make = make;
        this.brand = brand;
        this.image = image;
    }

    public BoatDTO(Boat boat) {
        this.id = boat.getId();
        this.name = boat.getName();
        this.make = boat.getMake();
        this.brand = boat.getBrand();
        this.image = boat.getImage();
        this.harbourId = boat.getHarbour().getId();
    }

    public static List<BoatDTO> getDtos(List<Boat> boats) {
        List<BoatDTO> boatDTOS = new ArrayList();
        for (Boat boat : boats) {
            boatDTOS.add(new BoatDTO(boat));
        }
        return boatDTOS;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getHarbourId() {
        return harbourId;
    }

    public void setHarbourId(Long harbourId) {
        this.harbourId = harbourId;
    }

    public List<String> getOwnerDTOsAsStrings() {
        return ownerDTOsAsStrings;
    }

    public void setOwnerDTOsAsStrings(List<String> ownerDTOsAsStrings) {
        this.ownerDTOsAsStrings = ownerDTOsAsStrings;
    }

    public void addOwnerDTOAsString(String ownerDTOAsString) {
        this.ownerDTOsAsStrings.add(ownerDTOAsString);
    }

    @Override
    public String toString() {
        return "BoatDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", make='" + make + '\'' +
                ", brand='" + brand + '\'' +
                ", image='" + image + '\'' +
                ", harbourId=" + harbourId +
                ", ownerDTOsAsStrings=" + ownerDTOsAsStrings +
                '}';
    }
}
