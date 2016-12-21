package com.epam.pmt.dao;

import com.epam.pmt.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectDaoImpl implements Dao<Project> {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Project> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createNamedQuery("Project.findAll", Project.class).list();
    }

    @Override
    public Project findById(int id) {
        return null;
    }
}
