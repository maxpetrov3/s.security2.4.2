package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.RoleDao;
import web.model.Role;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public List<Role> getAllRoles() {

        return roleDao.getAllRoles();
    }
}
