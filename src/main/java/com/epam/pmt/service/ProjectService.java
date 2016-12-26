package com.epam.pmt.service;

import com.epam.pmt.model.Project;
import com.epam.pmt.model.Task;
import com.epam.pmt.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ProjectService {

    private Repository<Project> projectRepository;
    private Repository<Task> taskRepository;

    @Autowired
    public void setProjectRepository(Repository<Project> projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Autowired
    public void setTaskRepository(Repository<Task> taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Project> getProjectsList() {
        Collection<Project> projects = projectRepository.findAll();
        return new ArrayList<>(projects);
    }

    public Project findProgectById(int id) {
        return projectRepository.findById(id);
    }



}
