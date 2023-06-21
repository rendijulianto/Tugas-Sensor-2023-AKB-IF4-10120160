package com.example.tugassensor2023akbif410120160.models;
/**
 * NIM: 10120160
 * Nama : Rendi Julianto
 * Kelas : IF-4
 */
public class RestoModel {
    private String name;
    private String address;
    private String positionLat;
    private String positionLong;

    public RestoModel(String name, String address, String positionLat, String positionLong) {
        this.name = name;
        this.address = address;
        this.positionLat = positionLat;
        this.positionLong = positionLong;
    }

    public RestoModel() {

    }

    @Override
    public String toString() {
        return "RestoModel{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", positionLat=" + positionLat +
                ", positionLong=" + positionLong +
                '}';
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

    public String getPositionLat() {
        return positionLat;
    }

    public void setPositionLat(String positionLat) {
        this.positionLat = positionLat;
    }

    public String getPositionLong() {
        return positionLong;
    }

    public void setPositionLong(String positionLong) {
        this.positionLong = positionLong;
    }
}
