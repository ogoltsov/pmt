package com.epam.pmt.repository;

import com.epam.pmt.model.Status;

import javax.ejb.Startup;
import java.util.Collection;

public class StatusRepositoryImpl extends AbstractRepository<Status> {

    @Override
    public Collection findAll() {
        return getSession().createCriteria(Status.class).list();
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
