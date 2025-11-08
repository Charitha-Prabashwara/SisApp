package com.example.sisapp;



public class Student {
    private int id;
    private String nic;
    private String firstName;
    private String lastName;
    private String fullName;
    private String nameWithInitials;
    private String addressLineOne;
    private String addressLineTwo;
    private int zipCode;


    public Student(int id,String nic, String firstName, String lastName, String fullName, String nameWithInitials, String addressLineOne, String addressLineTwo, int zipCode){
        this.id = id;
        this.nic = nic;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.nameWithInitials = nameWithInitials;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.zipCode = zipCode;
    }

    public Student(String nic, String firstName, String lastName, String fullName, String nameWithInitials, String addressLineOne, String addressLineTwo, int zipCode){

        this.nic = nic;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.nameWithInitials = nameWithInitials;
        this.addressLineOne = addressLineOne;
        this.addressLineTwo = addressLineTwo;
        this.zipCode = zipCode;
    }




    public int getId() {
        return this.id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getNic() {
        return nic;
    }


    public void setNic(String nic) {
        this.nic = nic;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getFullName() {
        return fullName;
    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getNameWithInitials() {
        return nameWithInitials;
    }


    public void setNameWithInitials(String nameWithInitials) {
        this.nameWithInitials = nameWithInitials;
    }


    public String getAddressLineOne() {
        return addressLineOne;
    }


    public void setAddressLineOne(String addressLineOne) {
        this.addressLineOne = addressLineOne;
    }


    public String getAddressLineTwo() {
        return addressLineTwo;
    }


    public void setAddressLineTwo(String addressLineTwo) {
        this.addressLineTwo = addressLineTwo;
    }


    public int getZipCode() {
        return zipCode;
    }


    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student { ");
        sb.append("id=").append(id)
                .append(", firstName='").append(firstName).append('\'')
                .append(", lastName='").append(lastName).append('\'')
                .append(", fullName='").append(fullName).append('\'')
                .append(", withInitials='").append(nameWithInitials).append('\'')
                .append(", AddressLine1='").append(addressLineOne).append('\'')
                .append(", AddressLine2='").append(addressLineTwo).append('\'')
                .append(", Zipcode='").append(zipCode).append('\'');
        sb.append(" }");
        return sb.toString();
    }




}
