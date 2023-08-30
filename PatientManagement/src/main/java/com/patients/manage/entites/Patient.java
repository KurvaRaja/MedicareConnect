package com.patients.manage.entites;




import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "PatientTable")
public class Patient {



@Id

@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long tokenId;
    @NotNull(message="fill the first name")
private String firstname;
    @NotNull(message="fill the last name")
private String lastname;
    @Email(message="Enter correct email")
private String email;
    @Column(name="RegistrationTime")
    @Temporal(TemporalType.DATE)
private Date date;

public Long getTokenId() {
return tokenId;
}

public void setTokenId(Long tokenId) {
this.tokenId = tokenId;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}




public Date getDate() {
return date;
}

@PrePersist
public void setDate() {
this.date = new Date();
}

public String getFirstname() {
return firstname;
}

public void setFirstname(String firstname) {
this.firstname = firstname;
}

public String getLastname() {
return lastname;
}

public void setLastname(String lastname) {
this.lastname = lastname;
}

public Patient(Long tokenId, String firstname, String lastname, String email, Date date) {
super();
this.tokenId = tokenId;
this.firstname = firstname;
this.lastname = lastname;
this.email = email;
this.date = date;
}

public Patient() {
super();

}

}