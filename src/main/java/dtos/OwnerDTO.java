package dtos;

import entities.Owner;

import java.util.ArrayList;
import java.util.List;

public class OwnerDTO {
    private Long id;
    private String name;
    private String address;
    private Integer phone;
    private List<String> boatDTOsAsStrings;

    public OwnerDTO() {
    }

    public OwnerDTO(Long id, String name, String address, Integer phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public OwnerDTO(String name, String address, Integer phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public OwnerDTO(Owner owner) {
        this.id = owner.getId();
        this.name = owner.getName();
        this.address = owner.getAddress();
        this.phone = owner.getPhone();
    }

    public static List<OwnerDTO> getDtos(List<Owner> owners) {
        List<OwnerDTO> ownerDTOS = new ArrayList();
        for (Owner owner : owners) {
            ownerDTOS.add(new OwnerDTO(owner));
        }
        return ownerDTOS;
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

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
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
        return "OwnerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", boatDTOsAsStrings=" + boatDTOsAsStrings +
                '}';
    }
}
