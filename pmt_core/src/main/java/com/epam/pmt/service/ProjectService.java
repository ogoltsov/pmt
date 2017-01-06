package com.epam.pmt.service;

import com.epam.pmt.domain.Project;
import com.epam.pmt.domain.Status;
import com.epam.pmt.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ProjectService {

    private Repository<Project> projectRepository;

    private Repository<Status> statusRepository;

    @Autowired
    public void setProjectRepository(Repository<Project> projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Autowired
    public void setStatusRepository(Repository<Status> statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Project> getProjectsList() {
        Collection<Project> projects = projectRepository.findAll();
        return new ArrayList<>(projects);
    }

    public Project findProjectById(int id) {
        return projectRepository.findById(id);
    }


    public void deleteProject(int id) {
        projectRepository.delete(projectRepository.findById(id));
    }

    public List<Status> getStatusList() {
        return new ArrayList<>(statusRepository.findAll());
    }
}
