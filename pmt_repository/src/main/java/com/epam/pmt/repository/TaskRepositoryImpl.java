package com.epam.pmt.repository;

import com.epam.pmt.domain.Task;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.transaction.Transactional;
import java.util.Collection;

@org.springframework.stereotype.Repository(value = "taskRepository")
@Transactional
@Qualifier(value = "taskRepository")
public class TaskRepositoryImpl extends AbstractRepository<Task> {

    @Override
    public Collection findAll() {
        return null;
    }

    @Override
    public Object findById(int id) {
        Task task = (Task) getSession().get(Task.class, id);

        return task;
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
