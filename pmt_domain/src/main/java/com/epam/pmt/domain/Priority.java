package com.epam.pmt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "priority_level")
public class Priority extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "level")
    private int level;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
        return "Priority{" +
                "title='" + title + '\'' +
                ", level=" + level +
                '}';
    }
}
