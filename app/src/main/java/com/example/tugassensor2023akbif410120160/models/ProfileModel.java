package com.example.tugassensor2023akbif410120160.models;
/**
 * NIM: 10120160
 * Nama : Rendi Julianto
 * Kelas : IF-4
 */
public class ProfileModel {
    private String countrySubdivision;
    private String municipality;
    private String municipalitySubdivision;
    private String country;
    private String freeformAddress;

    public ProfileModel(String countrySubdivision, String municipality, String municipalitySubdivision, String country, String freeformAddress) {
        this.countrySubdivision = countrySubdivision;
        this.municipality = municipality;
        this.municipalitySubdivision = municipalitySubdivision;
        this.country = country;
        this.freeformAddress = freeformAddress;
    }

    public ProfileModel() {
    }

    @Override
    public String toString() {
        return "ProfileModel{" +
                "countrySubdivision='" + countrySubdivision + '\'' +
                ", municipality='" + municipality + '\'' +
                ", municipalitySubdivision='" + municipalitySubdivision + '\'' +
                ", country='" + country + '\'' +
                ", freeformAddress='" + freeformAddress + '\'' +
                '}';
    }

    public String getCountrySubdivision() {
        return countrySubdivision;
    }

    public void setCountrySubdivision(String countrySubdivision) {
        this.countrySubdivision = countrySubdivision;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getMunicipalitySubdivision() {
        return municipalitySubdivision;
    }

    public void setMunicipalitySubdivision(String municipalitySubdivision) {
        this.municipalitySubdivision = municipalitySubdivision;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFreeformAddress() {
        return freeformAddress;
    }

    public void setFreeformAddress(String freeformAddress) {
        this.freeformAddress = freeformAddress;
    }
}
