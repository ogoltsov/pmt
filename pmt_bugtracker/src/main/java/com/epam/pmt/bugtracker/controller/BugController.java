package com.epam.pmt.bugtracker.controller;


import com.epam.pmt.domain.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/project2/*")
public class BugController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String sayHello(ModelMap model) {
        List<Project> projects = new ArrayList<>();
        model.put("name", "Ogoltsov BugCenter Controller");
        return "index";
    }

}
