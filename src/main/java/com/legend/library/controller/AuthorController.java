package com.legend.library.controller;

import com.legend.library.model.Author;
import com.legend.library.model.Member;
import com.legend.library.service.AuthorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/list")
    public String getAllTheAuthors(Model model, String filterText) {

        List<Author> authors;
        if(filterText == null || filterText.isEmpty()){
            authors = authorService.findAll();
        }else {
            authors = authorService.findAuthorsByFilterText(filterText);
        }

        model.addAttribute("authors", authors);
        model.addAttribute("filterText", filterText);

        return "author/list-authors";
    }

    @GetMapping("/add")
    public String showFormForAdd(Model model) {

        Author author = new Author();
        model.addAttribute("author", author);
        model.addAttribute("modalTitle", "Add new Author" );

        return "author/add-author";
    }

    @GetMapping("/update")
    public String showFormForUpdate(@RequestParam("authorId") int theId, Model model) {

        Author author = authorService.findById(theId);
        model.addAttribute("author", author);
        model.addAttribute("modalTitle", "Update Author" );

        return "author/add-author";
    }

    @PostMapping("/save")
    public String saveAuthor(@ModelAttribute("author") Author author, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        String message = "";
        String title = "";
        try{
            authorService.addAuthor(author);
            message = "Author is successfully added";
            title="Success";
        } catch (Exception e) {
            if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                message = "Author with that first and last name already exist";
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
