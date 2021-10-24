package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;


import java.util.List;
import java.util.stream.Collectors;

@Controller
public class EditUserController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    private User newUser;


    @GetMapping(value = "/changeUserData")
    public String getUserData(@RequestParam(name = "userId", defaultValue = "0", required = false) Long userId, ModelMap model) {
        List<Role> allRoles = roleService.getAllRoles();
        if (newUser == null) {
            if (userId != 0) {
                newUser = userService.getUserById(userId);
            } else {
                newUser = new User();
                newUser.setAuthorities(allRoles.stream()
                        .filter(x -> x.getRoleName().equalsIgnoreCase("ROLE_USER"))
                        .collect(Collectors.toList()));
            }
        }

        model.addAttribute("tuser", newUser);
        List<Role> uRoles = newUser.getAuthorities();
        for (Role role : uRoles) {
            allRoles = allRoles.stream()
                    .filter(x -> !x.getId().equals(role.getId()))
                    .collect(Collectors.toList());
        }

        model.addAttribute("roles", allRoles);
        return "changeUserData";
    }

    @PostMapping(value = "/saveUserData")
    public String updateUserData(@ModelAttribute User user) {
        newUser.setName(user.getName());
        newUser.setLastName(user.getLastName());
        newUser.setAge(user.getAge());
        newUser.setUsername(user.getUsername());
        userService.updateUser(newUser);
        newUser = null;
        return "redirect:/admin";
    }

    @PostMapping(value = "/addRole")
    public String addNewRole(@RequestParam(name = "newRoleId") Long newRoleId) {
        List<Role> uRoles = newUser.getAuthorities();
        uRoles.add(roleService.getRoleById(newRoleId));
        newUser.setAuthorities(uRoles);

        return "redirect:/changeUserData";
    }

    @PostMapping(value = "/deleteRole")
    public String deleteRole(@RequestParam(name = "roleId") Long roleId) {
        List<Role> uRoles = newUser.getAuthorities();
        uRoles = uRoles.stream()
                .filter(x -> !x.getId().equals(roleId))
                .collect(Collectors.toList());
        newUser.setAuthorities(uRoles);

        return "redirect:/changeUserData";
    }

}
