package com.epam.pmt.repository;

import com.epam.pmt.model.Project;

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
    public Object findById(int id) {
        return null;
    }

    @Override
    public boolean save(Object o) {
        return false;
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }
}
