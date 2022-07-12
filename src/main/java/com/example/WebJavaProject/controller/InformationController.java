package com.example.WebJavaProject.controller;

import com.example.WebJavaProject.entity.Category;
import com.example.WebJavaProject.entity.Information;
import com.example.WebJavaProject.entity.User;
import com.example.WebJavaProject.service.CategoryService;
import com.example.WebJavaProject.service.InformationService;
import com.example.WebJavaProject.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/info")
public class InformationController {

    private UserService userService;

    private InformationService informationService;

    private CategoryService categoryService;

    public InformationController(UserService userService, InformationService informationService, CategoryService categoryService) {
        this.userService = userService;
        this.informationService = informationService;
        this.categoryService = categoryService;
    }



    @GetMapping("/list")
    public String listInfo(@AuthenticationPrincipal UserDetails loggedUser,
                           @RequestParam(required = false, name = "sortField") String sortField,
                           @RequestParam(required = false, name ="sortDir") String sortDir,
                           Model theModel,
                           @CookieValue(value = "sortF", defaultValue = "title") String cookieSortField,
                           @CookieValue(value = "sortD", defaultValue = "asc") String cookieSortDir,
                           HttpServletResponse response,
                           @ModelAttribute("selectedUser") User selectedUser){

        String login = loggedUser.getUsername();
        User user = userService.findByLogin(login);
        int id = user.getId();



        if(sortField==null){
            sortField=cookieSortField;
        }
        if(sortDir==null){
            sortDir=cookieSortDir;
        }

        List<Information> informationList = informationService.findAllByUserId(id, sortField, sortDir);

        Cookie cookie = new Cookie("sortF", sortField);
        Cookie cookie2 = new Cookie("sortD", sortDir);

        response.addCookie(cookie);
        response.addCookie(cookie2);

        theModel.addAttribute("sortField", sortField);
        theModel.addAttribute("sortDir", sortDir);
        theModel.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        theModel.addAttribute("informationList", informationList);


        return "users/list-of-info";

    }

    @GetMapping("/addInfo")
    public String showFormForAdd(Model theModel) {

        Information information = new Information();

        List<Category> categoryList = categoryService.findAll();

        theModel.addAttribute("information", information);
        theModel.addAttribute("categoryList", categoryList);

        return "/users/info-form";
    }

    @GetMapping("/updateInfo")
    public String showFormForUpdate(@AuthenticationPrincipal UserDetails loggedUser, @RequestParam("informationId") int theId,
                                    Model theModel) {
        String login = loggedUser.getUsername();
        User user = userService.findByLogin(login);

        Information information = informationService.findById(theId);

        if(user != information.getUser()){
            return "/access-denied";
        }

        List<Category> categoryList = categoryService.findAll();

        theModel.addAttribute("information", information);
        theModel.addAttribute("categoryList", categoryList);

        return "/users/info-form";
    }


    @PostMapping("/save")
    public String saveInformation(@AuthenticationPrincipal UserDetails loggedUser,
                                  @Valid @ModelAttribute("information") Information information,
                                  BindingResult theBindingResult, Model theModel){

        String login = loggedUser.getUsername();
        User user = userService.findByLogin(login);

        if(information.getCreationDate()==null) {
            Date date = new Date();
            information.setCreationDate(date);
        }

        information.setUser(user);



        if (theBindingResult.hasErrors()){
            List<Category> categoryList = categoryService.findAll();
            theModel.addAttribute("categoryList", categoryList);
            return "/users/info-form";
        }

        informationService.save(information);

        return "redirect:/info/list";
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("informationId") int theId) {

        informationService.deleteById(theId);

        return "redirect:/info/list";

    }

    //-----------------------------------------category--------------------------------------------------------

    @GetMapping("/addCategory")
    public String showFormForAddCategory(Model theModel) {

        Category category = new Category();


        theModel.addAttribute("category", category);


        return "/users/category-form";
    }

    @PostMapping("/saveCategory")
    public String saveCategory(@Valid @ModelAttribute("category") Category category,
                                  BindingResult theBindingResult){


        if (theBindingResult.hasErrors()){

            return "/users/category-form";
        }

        categoryService.save(category);

        return "redirect:/info/addInfo";
    }

}
