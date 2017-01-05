package com.epam.pmt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role extends BaseEntity {

    @Column(name = "access_level")
    private Integer accessLevel;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    public Role() {
    }

    public Integer getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(Integer accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return "Role{" +
                "accessLevel=" + accessLevel +
                ", title='" + title + '\'' +
                '}';
    }
}
