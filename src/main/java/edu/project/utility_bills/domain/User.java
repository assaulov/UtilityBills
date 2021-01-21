package edu.project.utility_bills.domain;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import edu.project.utility_bills.view.LocalDateStringConverter;
import edu.project.utility_bills.view.StringLocalDateConverter;

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
    @Column(name = "last_name")
    private String lastName;
    @Column (name = "first_name")
    private String firstName;
    @Column (name = "middle_name")
    private String middleName;
    @Column (name = "date_of_birth")
    @JsonSerialize(converter = LocalDateStringConverter.class)
    @JsonDeserialize(converter = StringLocalDateConverter.class)
    private LocalDate dateOfBirth;
    @Column (name = "pseudo_name")
    private String pseudoName;
    @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "user")
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

    public String getPseudoName() {
        return pseudoName;
    }

    public void setPseudoName(String pseudoName) {
        this.pseudoName = pseudoName;
    }

    public List<Utilities> getUtilitiesList() {
        return utilitiesList;
    }

    public void setUtilitiesList(List<Utilities> utilitiesList) {
        this.utilitiesList = utilitiesList;
    }
}
