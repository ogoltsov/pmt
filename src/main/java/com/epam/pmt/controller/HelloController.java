package com.epam.pmt.controller;

import com.epam.pmt.model.Project;
import com.epam.pmt.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {

    private Repository<Project> repository;

    @Autowired
    public void setRepository(Repository<Project> repository) {
        this.repository = repository;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String sayHello(ModelMap model) {
        model.put("name", "Ogoltsov");
        return "index";
    }
}
