package com.epam.pmt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "status")
public class Status extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "Status{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
