package nu.ist.iosf.entityRepo.entity;

import nu.ist.iosf.entityRepo.utils.DBProps;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SYSTEM_USER_DETAILS")
public class SystemUserDetails extends CommonColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "SYSTEM_USER_ID")
    private SystemUser systemUser;

    @Column(name = "FULL_NAME", nullable = false, length = DBProps.textField)
    private String fullName;

    @Column(name = "FATHERS_NAME", length = DBProps.textField)
    private String fathersName;

    @Column(name = "MOTHERS_NAME", length = DBProps.textField)
    private String mothersName;

    @Column(name = "GENDER", length = DBProps.textField)
    private String gender;

    @Column(name = "NATIONALITY", length = DBProps.textField)
    private String nationality;

    @Column(name = "RELIGION", length = DBProps.textField)
    private String religion;

    @Column(name = "NATIONAL_ID", length = DBProps.textField)
    private String nationalId;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "BIRTH_CERTIFICATE_NO", length = DBProps.textField)
    private String birthCertificateNo;

    @Column(name = "PASSPORT_NO", length = DBProps.textField)
    private String passportNo;

    @Column(name = "EMAIL_ADDRESS", length = DBProps.textField)
    private String emailAddress;

    @Column(name = "CONTACT_NO", length = DBProps.textField)
    private String contactNo;

    @Column(name = "PHOTO")
    private Byte[] photo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBirthCertificateNo() {
        return birthCertificateNo;
    }

    public void setBirthCertificateNo(String birthCertificateNo) {
        this.birthCertificateNo = birthCertificateNo;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(Byte[] photo) {
        this.photo = photo;
    }
}
