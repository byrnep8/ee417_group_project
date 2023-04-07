package com.klassycafe.demo.Entity;

import java.util.List;

// import jakarta.persistence.*;

public class User {

	public User() {
    }

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String username;
    private String password;
    private String firstname;
    private String surname;
    
//    @ManyToMany 
//    @JoinTable( 
//        name = "users_roles", 
//        joinColumns = @JoinColumn(
//          name = "user_id", referencedColumnName = "id"), 
//        inverseJoinColumns = @JoinColumn(
//          name = "role_id", referencedColumnName = "id")) 
    private List<Role> roles;
    
    public Long getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getSurname() {
        return this.surname;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String toString() {
        return "User(id=" + this.getId() + ", email=" + this.getEmail() + ", username=" + this.getUsername() + ", password=" + this.getPassword() + ", firstname=" + this.getFirstname() + ", surname=" + this.getSurname() + ", roles=" + this.getRoles() + ")";
    }
    
}
