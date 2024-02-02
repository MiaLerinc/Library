package com.legend.library.controller;

import com.legend.library.model.Publisher;
import com.legend.library.service.PublisherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Controller
@RequestMapping("/publishers")
public class PublisherController {

    private PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/list")
    public String getAllThePublishers(Model model) {
        List<Publisher> publishers = publisherService.findAll();

        model.addAttribute("publishers", publishers);
        return "publisher/list-publishers";
    }

    @GetMapping("/add")
    public String showFormForAdd(Model model) {

        Publisher publisher = new Publisher();
        model.addAttribute("publisher", publisher);
        model.addAttribute("modalTitle", "Add new Publisher" );

        return "publisher/add-publisher";
    }

    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam("publisherId") int theId, Model model) {

        Publisher publisher = publisherService.findById(theId);
        model.addAttribute("publisher", publisher);
        model.addAttribute("modalTitle", "Update Publisher" );

        return "publisher/add-publisher";
    }

    @PostMapping("/save")
    public String savePublisher(@ModelAttribute("publisher") Publisher publisher, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        String message = "";
        String title = "";
        try{
            publisherService.addPublisher(publisher);
            message = "Publisher is successfully added";
            title="Success";
        } catch (Exception e) {
            if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                message = "Publisher with that name already exist";
            } else {
                message = "Something went wrong";
            }
            title = "Error";
        }
        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("title", title);

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
}
