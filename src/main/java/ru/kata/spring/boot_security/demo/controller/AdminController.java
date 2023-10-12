package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin")
    public String mainPage(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "mainPage";
    }

    @GetMapping(value = "/admin/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        return "newUserPage";
    }

    @PostMapping(value = "/admin/new")
    public String createUser(@ModelAttribute("user") User user, @RequestParam List<String> listRoles) {
        Set<Role> roles = new HashSet<>();
        for (String role : listRoles) {
            roles.add(roleService.getRoleByName(role));
        }
        user.setRoles(roles);
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/edit")
    public String editUserForm(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "editPage";
    }

    @PostMapping(value = "/admin/edit")
    public String editUser(@ModelAttribute("user") User user, @RequestParam List<String> listRoles) {
        Set<Role> roles = new HashSet<>();
        for (String role : listRoles) {
            roles.add(roleService.getRoleByName(role));
        }
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/details")
    public String adminPage(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "adminPage";
    }
}