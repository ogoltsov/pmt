package com.epam.pmt.repository;

import com.epam.pmt.domain.Status;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.transaction.Transactional;
import java.util.Collection;

@org.springframework.stereotype.Repository
@Transactional
@Qualifier("statusRepository")
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
