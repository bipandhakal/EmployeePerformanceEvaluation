package com.syntech.model;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author bipan
 */
@Entity
@Table(name = "employee")
public class Employee implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @NotNull(message = "FirstName should not be null")
    @Size(min = 3, message = "FirstName should be minimum 3 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "LastName should not be null")
    @Size(min = 3, message = "LastName should be minimum 3 characters")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "join_date", nullable = false)
    private Date joinDate;

//    @NotNull(message = "Phone number is required")
    @Size(max = 10)
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number should be 10 digits number")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

//    @NotNull(message = "Email is required")
    @Email
    @Pattern(regexp = "^[a-zA-Z0-9.]+@[a-zA-Z0-9]+.[a-zA-Z]+$", message = "Email should be valid email")
    @Column(name = "email", nullable = false)
    private String email;

    public Employee() {

    }

    public Employee(Long id, String firstName, String lastName, Date joinDate, String phoneNumber, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.joinDate = joinDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
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

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + Objects.hashCode(this.firstName);
        hash = 43 * hash + Objects.hashCode(this.lastName);
        hash = 43 * hash + Objects.hashCode(this.joinDate);
        hash = 43 * hash + Objects.hashCode(this.phoneNumber);
        hash = 43 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.joinDate, other.joinDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", joinDate=" + joinDate + ", phoneNumber=" + phoneNumber + ", email=" + email + '}';
    }
}
