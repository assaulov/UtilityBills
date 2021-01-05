package edu.project.utility_bills.domain;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Table(name = "ub_users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;
    private String lastName;
    @Column (name = "first_name")
    private String firstName;
    @Column (name = "middle_name")
    private String middleName;
    @Column (name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column (name = "passport_seria")
    private String passportSeria;
    @Column (name = "passport_number")
    private String passportNumber;
    @Column (name = "passport_date")
    private LocalDate passportDate;
    @OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "user" )
    private List<Utilities> utilitiesList;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassportSeria() {
        return passportSeria;
    }

    public void setPassportSeria(String passportSeria) {
        this.passportSeria = passportSeria;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public LocalDate getPassportDate() {
        return passportDate;
    }

    public void setPassportDate(LocalDate passportDate) {
        this.passportDate = passportDate;
    }

    public List<Utilities> getUtilitiesList() {
        return utilitiesList;
    }

    public void setUtilitiesList(List<Utilities> utilitiesList) {
        this.utilitiesList = utilitiesList;
    }
}
