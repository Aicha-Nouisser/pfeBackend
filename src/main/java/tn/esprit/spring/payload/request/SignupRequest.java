package tn.esprit.spring.payload.request;

import java.util.Set;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.spring.entity.UserType;

import java.math.BigDecimal;
import java.util.Set;
import javax.validation.constraints.*;

import static java.util.Objects.*;
import static org.apache.http.util.TextUtils.*;
import javax.validation.constraints.*;

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String postCode;

    private String address;

    private UserType userType;

    private String country;

    private String city;

    private String clientType;

    private String companyName;

    private String paymentEmail;


    //SITE INFO

    private String shopName;

    private String url;

    private String technicianName;

    private String description;

    private String category;

    private MultipartFile profilePic;

    private String emailSite;

    private String phoneNumberSite;

    private String nameTechnician;

    private String completeAddress;

    private String activitySector;

    private BigDecimal revenue;


    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPaymentEmail() {
        return paymentEmail;
    }

    public void setPaymentEmail(String paymentEmail) {
        this.paymentEmail = paymentEmail;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTechnicianName() {
        return technicianName;
    }

    public void setTechnicianName(String technicianName) {
        this.technicianName = technicianName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public MultipartFile getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(MultipartFile profilePic) {
        this.profilePic = profilePic;
    }

    public String getEmailSite() {
        return emailSite;
    }

    public void setEmailSite(String emailSite) {
        this.emailSite = emailSite;
    }

    public String getPhoneNumberSite() {
        return phoneNumberSite;
    }

    public void setPhoneNumberSite(String phoneNumberSite) {
        this.phoneNumberSite = phoneNumberSite;
    }


    public String getNameTechnician() {
        return nameTechnician;
    }

    public void setNameTechnician(String nameTechnician) {
        this.nameTechnician = nameTechnician;
    }

    public String getCompleteAddress() {
        return completeAddress;
    }

    public void setCompleteAddress(String completeAddress) {
        this.completeAddress = completeAddress;
    }

    public String getActivitySector() {
        return activitySector;
    }

    public void setActivitySector(String activitySector) {
        this.activitySector = activitySector;
    }

    public BigDecimal getRevenue() {
        return revenue;
    }

    public void setRevenue(BigDecimal revenue) {
        this.revenue = revenue;
    }

    public void validateRequest() {
        if (isNull(userType)) {
            throw new RuntimeException("TYPE CLIENT EST OBLIGATOIRE");
        }
        //VALIDER les champs obligatoires quelque soit le type

        if (UserType.CLIENT.equals(userType)) {
            validateRequiredField();
        }
        if (UserType.MARCHAND.equals(userType)) {
            validateRequiredField();
            validateRequiredFieldMarchand();
        }


    }


    private void validateRequiredField() {
        //if (isEmpty(firstName) || isEmpty(lastName) || isEmpty(email) || isEmpty(phoneNumber)) {
        if (isEmpty(username) || isEmpty(lastName) || isEmpty(email) || isEmpty(phoneNumber)) {

            throw new RuntimeException("merci de renseigner lec champ obligatoire : (NOM/PRENOM/EMAIL/TELEPHONE)");
        }
    }

    private void validateRequiredFieldMarchand() {
        if (isEmpty(companyName) || isEmpty(paymentEmail) || isEmpty(shopName) || isEmpty(url) || isEmpty(description) || isEmpty(category) || isNull(profilePic) || isNull(phoneNumberSite) || isNull(activitySector)) {
            throw new RuntimeException("merci de renseigner lec champ obligatoire pour le Marchand : (NOM ENTREPRISE,EMAIL PAIEMENT, NOM BOUTIQUE,URL,DESCRIPTION SITE,CATEGORIE,LOGO,EMAIL SITE, TELEPHONE SITE,SECTEUR ACTIVITE)");
        }
    }

}