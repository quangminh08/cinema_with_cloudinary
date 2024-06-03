package vn.dev.cinema.service;

import vn.dev.cinema.entity.Role;
import vn.dev.cinema.entity.User;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseService<Role> {
	
	@Override
	public Class<Role> clazz() {
		return Role.class;
	}
	
	public Role getRoleByName(String roleName) {
		String sql = "select * from cinimakhoa.tbl_role where name='" + roleName + "'";
		return this.getEntityByNativeSql(sql);
	}
	
	public String checkRoleOfUserById(int userId) {
		Role adminRole = getRoleByName("STAFF");
		List<User> admins = adminRole.getUsers();
		
		for(User admin : admins) {
			if(admin.getId() == userId) {
				return "STAFF";
			}
		}	
		
		
		return "CUSTOMER";
	}
}
