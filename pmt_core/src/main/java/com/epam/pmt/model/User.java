package com.epam.pmt.model;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        // TODO: 12/22/2016 Rewrite this stuff for my requirements
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // TODO: 12/22/2016 Rewrite this stuff for my requirements
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", role=" + role +
                '}';
    }


}
