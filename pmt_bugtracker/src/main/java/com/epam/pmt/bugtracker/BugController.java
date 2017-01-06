package com.epam.pmt.bugtracker;

@Controller
@RequestMapping("/project/*")
public class BugController {

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

}
