package com.universitydekanat.api;
import com.universitydekanat.dtos.UserSignupFormCommand;
import com.universitydekanat.dtos.UserDto;
import com.universitydekanat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;



@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = {"/login"})
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @GetMapping(value = {"/logout"})
    public String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:login";
    }

    @GetMapping(value = "/home")
    public String home() {
        return "redirect:dashboard";
    }








    @GetMapping(value = "/signup")
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView("signup");
        modelAndView.addObject("adminSignupFormData", new UserSignupFormCommand());
        return modelAndView;
    }

    @PostMapping(value = "/signup")
    public ModelAndView createNewAdmin(@Valid @ModelAttribute("adminSignupFormData") UserSignupFormCommand userSignupFormCommand, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("signup");
        if (bindingResult.hasErrors()) {
            return modelAndView;
        } else {
            try {
                UserDto newUser = registerUser(userSignupFormCommand);
            } catch (Exception exception) {
                bindingResult.rejectValue("email", "error.adminSignupFormCommand", exception.getMessage());
                return modelAndView;
            }
        }
        return new ModelAndView("login");
    }









    private UserDto registerUser( UserSignupFormCommand adminSignupRequest) {
        UserDto userDto = new UserDto();
                userDto.setEmail(adminSignupRequest.getEmail());
                userDto.setPassword(adminSignupRequest.getPassword());
                userDto.setFirstName(adminSignupRequest.getFirstName());
                userDto.setLastName(adminSignupRequest.getLastName());
                userDto.setAdmin(adminSignupRequest.getIsAdmin().equals("EMPLOYEE"));

        return userService.signup(userDto);
    }
}
