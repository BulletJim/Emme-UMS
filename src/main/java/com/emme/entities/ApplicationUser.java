package com.emme.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="users")
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="user_id")
    private Integer userId;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(unique = true)
    private String email;

    private String phone;
    @Column(name="dob")
    private Date dob;
    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="user_role_juction",
            joinColumns= {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<Role> authorities;

    public ApplicationUser(Integer userId, String firstName, String lastName, String email, String phone, Date dob, String username, String password, Set<Role> authorities) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public ApplicationUser() {
        this.authorities = new HashSet<>();
    }


    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId){this.userId = userId;}
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
            this.authorities = authorities;
    }
}
