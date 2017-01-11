package com.epam.pmt.controller;

import com.epam.pmt.domain.Task;
import com.epam.pmt.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/task/")
public class TaskController {

    private Repository<Task> repository;

    @Autowired
    @Qualifier(value = "taskRepository")
    public void setRepository(Repository<Task> repository) {
        this.repository = repository;
    }

    @RequestMapping(path = "/{id}")
    public String getTask(@PathVariable int id, ModelMap model) {
        Task task = repository.findById(id);
        model.put("task", task);
        return "task/taskPage";
    }


    @RequestMapping(path = "/{id}/delete/")
    public ModelAndView deleteTask(@PathVariable int id, ModelMap model) {
        Task task = repository.findById(id);
        repository.delete(task);
        return new ModelAndView("redirect:/project/" + task.getProject().getId());
    }

}
