package com.universitydekanat.api;

import com.universitydekanat.dtos.GroupCreateRequest;
import com.universitydekanat.dtos.GroupFullDto;
import com.universitydekanat.dtos.StudentCreateRequestDto;
import com.universitydekanat.dtos.StudentSearchDto;
import com.universitydekanat.services.GroupService;
import com.universitydekanat.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ApiController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private StudentService studentService;


    @GetMapping(value = {"/groups"})
    public ModelAndView groups() {
        ModelAndView modelAndView = new ModelAndView("groups");
        modelAndView.addObject("groupCreateRequest", new GroupCreateRequest());
        modelAndView.addObject("groups", groupService.getAll());

        return modelAndView;
    }


    @GetMapping(value = {"/student/groups"})
    public ModelAndView groupsForStudent() {
        ModelAndView modelAndView = new ModelAndView("student-groups");
        modelAndView.addObject("groupCreateRequest", new GroupCreateRequest());
        modelAndView.addObject("groups", groupService.getAll());

        return modelAndView;
    }

    @PostMapping(value = {"/student/groups/search"})
    public ModelAndView groupsForStudentSearch(@ModelAttribute("groupCreateRequest") GroupCreateRequest groupCreateRequest) {
        ModelAndView modelAndView = new ModelAndView("student-groups");
        modelAndView.addObject("groupCreateRequest", new GroupCreateRequest());
        modelAndView.addObject("groups", groupService.getAllByTitle(groupCreateRequest.getTitle()));

        return modelAndView;
    }

    @GetMapping(value = {"/student/groups/{id}"})
    public ModelAndView groupForStudent(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("student-group");
        modelAndView.addObject("student", new StudentSearchDto());

        modelAndView.addObject("group", groupService.getById(id));

        return modelAndView;
    }

    @PostMapping(value = {"/student/group/{id}/search"})
    public ModelAndView groupForStudentSearch(@PathVariable("id") int id, @ModelAttribute("student") StudentSearchDto studentSearchDto) {
        ModelAndView modelAndView = new ModelAndView("student-group");
        modelAndView.addObject("student", new StudentSearchDto());
        modelAndView.addObject("group", groupService.getByIdAndLastName(id, studentSearchDto.getLastName()));

        return modelAndView;
    }

    @PostMapping(value = {"/groups"})
    public String createGroup(@ModelAttribute("groupCreateRequest") GroupCreateRequest groupCreateRequest) {
        ModelAndView modelAndView = new ModelAndView("groups");

        groupService.create(groupCreateRequest);


        return "redirect:groups";
    }

    @GetMapping(value = {"/groups/delete/{id}"})
    public String deleteGroup(@PathVariable("id") int id) {
        groupService.deleteById(id);
        return "redirect:/groups";
    }

    @GetMapping(value = {"/groups/{id}"})
    public ModelAndView groups(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("group");

        modelAndView.addObject("group", groupService.getById(id));

        return modelAndView;
    }


    @GetMapping(value = {"/groups/{id}/student/add"})
    public ModelAndView openAddStudentPage(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("student");

        modelAndView.addObject("group", groupService.getById(id));
        modelAndView.addObject("student", new StudentCreateRequestDto());

        return modelAndView;
    }

    @PostMapping(value = {"/groups/{id}/student/add"})
    public String addStudent(@PathVariable("id") int id, @ModelAttribute("student") StudentCreateRequestDto studentCreateRequestDto) {
        studentService.createStudent(studentCreateRequestDto, id);
        return "redirect:/groups/" + id;
    }

    @GetMapping(value = {"/groups/{id}/student/{sid}/delete"})
    public String deleteStudent(@PathVariable("id") int id, @PathVariable("sid") int sid) {

        studentService.deleteById(id, sid);
        return "redirect:/groups/" + id;
    }


}
