package tn.esprit.spring.entity;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "gpg_site")
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "site" , cascade = CascadeType.ALL)
    @JsonIgnore

    private User user;

    @Column(name = "NOM_BOUTIQUE")
    private String shopName;

    @Column(name = "URL_SITE")
    private String url;


    @Column(name = "NOM_TECHNICIEN")
    private String technicianName;


    @Column(name = "DESCRIPTION_SITE")
    private String description;


    @Column(name = "CATEGORIE")
    private String category;

    @Lob
    @Column(name = "LOGO")
    private byte[] profilePic;


    @Column(name = "EMAIL_SITE")
    private String emailSite;

    @Column(name = "TELEPHONE_SITE")
    private String phoneNumberSite;


    @Column(name = "PRENOM_TECHNICIEN")
    private String nameTechnician;

    @Column(name = "ADRESSE_COMPLETE")
    private String completeAddress;

    @Column(name = "SECTEUR_ACTIVITE")
    private String activitySector;


    @Column(name = "CHIFFRE_AFFAIRES")
    private BigDecimal revenue;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public byte[] getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(byte[] profilePic) {
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

    @OneToMany 
    @JsonIgnore

    private List <Transaction> transactions ;
}
