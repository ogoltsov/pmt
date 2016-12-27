package com.epam.pmt.controller;

import com.epam.pmt.model.Project;
import com.epam.pmt.model.Status;
import com.epam.pmt.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/project/*")
public class ProjectController {

    private ProjectService service;

    @Autowired
    public void setService(ProjectService service) {
        this.service = service;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String findProject(ModelMap model) {
        model.addAttribute("projects", service.getProjectsList());
        return "agileBoard";
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String findProject(@PathVariable(name = "id", required = true) int id, ModelMap model) {
        Project project = service.findProjectById(id);
        model.addAttribute("project", project);
        return "projectPage";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public String deleteProject(@PathVariable(name = "id", required = true) int id, ModelMap model) {
        service.deleteProject(id);
        return null;
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String saveProject(@Valid Project project, BindingResult result, ModelMap model) {
        return null;
    }

    @ModelAttribute("statusList")
    public List<Status> initializeSections() {
        return service.getStatusList();
    }


}
