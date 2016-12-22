package com.epam.pmt.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_project_date")
    private Date startDate;

    @Column(name = "ending_project_date")
    private Date endDate;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        // TODO: 12/21/2016 Rewrite this method for own requirements
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // TODO: 12/21/2016 Rewrite this method for own requirements
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Project{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
