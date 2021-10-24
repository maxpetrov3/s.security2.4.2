package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;


@Controller
public class AdminController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/admin")
	public String getUsers(ModelMap model) {
		model.addAttribute("users", userService.getAllUsers());
		return "admin";
	}

	@PostMapping(value = "/delete")
	public String deleteUser(@RequestParam(name = "delId") Long id) {
		userService.deleteUserById(id);
		return "redirect:/admin";
	}

}