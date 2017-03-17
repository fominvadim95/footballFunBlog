package ua.nure.fomin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.nure.fomin.entities.User;
import ua.nure.fomin.services.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/funBlog", method = RequestMethod.GET)
    public String main(Model model) {
        return "index";
    }

    @RequestMapping(value = "/funBlog/signUp", method = RequestMethod.GET)
    public String signUpPage(Model model) {
        model.addAttribute(new User());
        return "signUp";
    }

    @RequestMapping(value = "/funBlog/signIn", method = RequestMethod.GET)
    public String signInPage(Model model) {
        model.addAttribute(new User());
        return "signIn";
    }


    @RequestMapping(value = "/funBlog/error", method = RequestMethod.GET)
    public String error(Model model) {
        return "error";
    }


    @RequestMapping(value = "/funBlog/restore", method = RequestMethod.GET)
    public String restore() {
        return "restore";
    }

    @RequestMapping(value = "/funBlog/success", method = RequestMethod.GET)
    public String successRestore() {
        return "successRestore";
    }

    @RequestMapping(value = "/funBlog/signIn", method = RequestMethod.POST)
    public String authorize(@ModelAttribute("user") User user, HttpSession session) {
        if (!userService.signIn(user)) {
            return "redirect:/funBlog/error";
        }
        session.setAttribute("name", user.getName());
        return "redirect:/funBlog";
    }

    @RequestMapping(value = "/funBlog/signUp", method = RequestMethod.POST)
    public String register(@ModelAttribute("user") User user, HttpSession session) {
        if (!userService.signUp(user)) {
            return "redirect:/funBlog/error";
        }
        session.setAttribute("name", user.getName());
        return "redirect:/funBlog";
    }

    @RequestMapping(value = "/funBlog/restore", method = RequestMethod.POST)
    public String restore(@RequestParam String name) {
        if (!userService.restorePassword(name)) {
            return "redirect:/funBlog/error";
        }
        return "redirect:/funBlog/success";
    }

    @RequestMapping(value = "/funBlog/users", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getAllUsers(){
        return userService.getAll();
    }
}
