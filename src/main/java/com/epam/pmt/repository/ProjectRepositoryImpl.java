package com.epam.pmt.repository;

import com.epam.pmt.model.Project;
import org.hibernate.Hibernate;

import javax.transaction.Transactional;
import java.util.Collection;

@org.springframework.stereotype.Repository
@Transactional
public class ProjectRepositoryImpl extends AbstractRepository<Project> {

    @Override
    public Collection findAll() {
        return getSession().createCriteria(Project.class).list();
    }

    @Override
    public Project findById(int id) {
        Project project = (Project) getSession().get(Project.class, id);
        Hibernate.initialize(project.getTasks());
        return project;
    }

    @Override
    public boolean save(Object o) {
        return false;
    }

    @Override
    public boolean delete(Object o) {

        getSession().delete(o);

        return false;
    }
}
