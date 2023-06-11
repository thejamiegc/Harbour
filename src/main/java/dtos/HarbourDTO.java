package dtos;

import java.util.List;

public class HarbourDTO {
    private Long id;
    private String name;
    private String address;
    private Integer capacity;
    private List<String> boatDTOsAsStrings;
    public HarbourDTO() {
    }

    public HarbourDTO(Long id, String name, String address, Integer capacity) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
    }

    public HarbourDTO(String name, String address, Integer capacity) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<String> getBoatDTOsAsStrings() {
        return boatDTOsAsStrings;
    }

    public void setBoatDTOsAsStrings(List<String> boatDTOsAsStrings) {
        this.boatDTOsAsStrings = boatDTOsAsStrings;
    }

    public void addBoatDTOAsString(String boatDTOAsString) {
        this.boatDTOsAsStrings.add(boatDTOAsString);
    }

    @Override
    public String toString() {
        return "HarbourDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", capacity=" + capacity +
                ", boatDTOsAsStrings=" + boatDTOsAsStrings +
                '}';
    }
}
