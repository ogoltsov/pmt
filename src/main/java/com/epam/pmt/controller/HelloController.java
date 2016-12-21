package com.epam.pmt.controller;

import com.epam.pmt.dao.Dao;
import com.epam.pmt.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloController {

    private Dao<Project> dao;

    @Autowired
    public void setDao(Dao<Project> dao) {
        this.dao = dao;
    }



    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String sayHello(ModelMap model) {
        model.put("name", "Ogoltsov");
        return "index";
    }

}
