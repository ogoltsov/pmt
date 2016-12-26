package com.epam.pmt.controller;

import com.epam.pmt.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/board/*")
public class AgileBoardController {


    private ProjectService service;

    @Autowired
    public void setService(ProjectService service) {
        this.service = service;
    }

    @RequestMapping(path = "/board/all", method = RequestMethod.GET)
    public String findProject(ModelMap model) {
        model.addAttribute("projects", service.getProjectsList());
        return "agileBoard";
    }

}
