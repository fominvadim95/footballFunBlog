package ua.nure.fomin.controllers;

import com.backendless.geo.GeoPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.nure.fomin.entities.Place;
import ua.nure.fomin.entities.User;
import ua.nure.fomin.services.PlaceService;
import ua.nure.fomin.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlaceService placeService;


    @RequestMapping(value = "/funBlog", method = RequestMethod.GET)
    public String main(Model model) {
        List<GeoPoint> places = placeService.getAll();
        model.addAttribute("places", places);
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
    public List<String> getAllUsers() {
        return userService.getAll();
    }


    @RequestMapping(value = "/funBlog/feedback", method = RequestMethod.GET)
    public String feedback(Model model) {
        return "feedback";
    }

    @RequestMapping(value = "/funBlog/feedback", method = RequestMethod.POST)
    public String feedback(HttpServletRequest request) {
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");
        userService.sendMessage(subject, message);
        return "redirect:/funBlog";
    }


    @RequestMapping(value = "/funBlog/statistics", method = RequestMethod.GET)
    @ResponseBody
    public int getStatistic() {
        return userService.getStatistics();
    }
}
