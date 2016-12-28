package com.epam.pmt.controller;

import com.epam.pmt.model.Project;
import com.epam.pmt.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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
        return "projectList";
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public String findProject(@PathVariable(name = "id", required = true) int id, ModelMap model) {
        Project project = service.findProjectById(id);
        model.addAttribute("project", project);
        return "projectPage";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteProject(@PathVariable(name = "id", required = true) int id, ModelMap model) {
        service.deleteProject(id);
        return new ModelAndView("redirect:/project/");
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String saveProject(@Valid Project project, BindingResult result, ModelMap model) {
        return null;
    }


}
