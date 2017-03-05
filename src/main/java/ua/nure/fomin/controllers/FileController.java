package ua.nure.fomin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.nure.fomin.services.FileService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@Controller
public class FileController {

    @Autowired
    private FileService fileService;


    @RequestMapping(value = "/funBlog/createFolder", method = RequestMethod.GET)
    public String createFolder() {
        return "createFolder";
    }


    @RequestMapping(value = "/funBlog/createFolder", method = RequestMethod.POST)
    public String createFolder(@RequestParam String name, HttpSession httpSession) {
        fileService.createFolder(name, (String) httpSession.getAttribute("name"));
        return "redirect:/funBlog";
    }


    @RequestMapping(value = "/funBlog/removeFile", method = RequestMethod.GET)
    public String removeFile() {
        return "removeFile";
    }


    @RequestMapping(value = "/funBlog/removeFile", method = RequestMethod.POST)
    public String removeFile(@RequestParam String name, HttpSession httpSession) {
        fileService.removeFile(name, (String) httpSession.getAttribute("name"));
        return "redirect:/funBlog";
    }


    @RequestMapping(value = "/funBlog/readFolder", method = RequestMethod.GET)
    public String readFolder() {
        return "readFolderForm";
    }


    @RequestMapping(value = "/funBlog/readFolder", method = RequestMethod.POST)
    public String readFolder(@RequestParam String name, HttpSession httpSession, Model model) {
        Map<String, String> files = fileService.getFiles(name, (String) httpSession.getAttribute("name"));
        model.addAttribute("files", files);
        return "readFolder";
    }


    @RequestMapping(value = "/funBlog/uploadFile", method = RequestMethod.GET)
    public String uploadFile() {
        return "uploadFile";
    }


    @RequestMapping(value = "/funBlog/uploadFile", method = RequestMethod.POST)
    public String uploadFile(@RequestParam(name = "name") String name, @RequestParam(name = "file") MultipartFile file, HttpSession httpSession) {
        byte[] bytes = null;
        if (!file.isEmpty()) {
            try {
                bytes = file.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileService.uploadFile(file.getOriginalFilename(), name, (String) httpSession.getAttribute("name"), bytes);
        }
        return "redirect:/funBlog";
    }


    @RequestMapping(value = "/funBlog/downloadFile", method = RequestMethod.GET)
    public String downloadFile() {
        return "downloadFile";
    }


    @RequestMapping(value = "/funBlog/downloadFile", method = RequestMethod.POST)
    public String downloadFile(@RequestParam String name, HttpSession httpSession) {
        String url = fileService.downloadFile(name, (String) httpSession.getAttribute("name"));
        return "redirect:" + url;
    }
}
