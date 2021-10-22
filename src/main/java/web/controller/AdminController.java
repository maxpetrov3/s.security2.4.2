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

	@Autowired
	private RoleService roleService;

	@GetMapping(value = "/admin")
	public String getUsers(ModelMap model) {
		model.addAttribute("users", userService.getAllUsers());
		return "admin";
	}

	@GetMapping(value = "/changeUserData")
	public String getUserData(@RequestParam(name = "userId", required = false) Long userId, ModelMap model) {
		if(userId != null) {
			model.addAttribute("tuser", userService.getUserById(userId));
		}
		model.addAttribute("roles", roleService.getAllRoles());
		model.addAttribute("trole", roleService.getAllRoles());
		return "changeUserData";
	}

	@PostMapping(value = "/admin")
	public String updateUserData(@ModelAttribute User user) {
		userService.updateUser(user);
		return "redirect:/admin";
	}

	@PostMapping(value = "/delete")
	public String deleteUser(@RequestParam(name = "delId") Long id) {
		userService.deleteUserById(id);
		return "redirect:/admin";
	}

}