package com.epam.pmt.service;

import com.epam.pmt.domain.Project;
import com.epam.pmt.domain.Status;
import com.epam.pmt.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectService {

    private Repository<Project> projectRepository;

    private Repository<Status> statusRepository;

    @Autowired
    @Qualifier("projectRepository")
    public void setProjectRepository(Repository<Project> projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Autowired
    @Qualifier("statusRepository")
    public void setStatusRepository(Repository<Status> statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Project> getProjectsList() {
        return new ArrayList<>(projectRepository.findAll());
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
