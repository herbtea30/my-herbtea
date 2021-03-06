package com.herbtea.myherbtea.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 20, unique = true)
    private String userId;
    private String password;
    private String name;
    private String email;
    private String address;

    public boolean matchPassword(String newPassword) {
        if(newPassword == null) {
            return false;
        }
        return newPassword.equals(this.password);
    }

    public boolean matchId(Long newId) {
        if(newId == null) {
            return false;
        }
        return newId.equals(this.id);
    }

    public String getName() {
        return name;
    }
    
    public String getAddress() { 
        return address;
    }
   
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\''+
                '}';
    }

    public void update(User updateUser) {
        this.password = updateUser.password;
        this.name = updateUser.name;
        this.email = updateUser.email;
        this.address = updateUser.address;
    }
}
