package five.points.service;

import java.util.List;

import five.points.entities.AppRole;
import five.points.entities.AppUser;

public interface AccountService {

	public AppUser saveUser(AppUser user);

	public AppRole saveRole(AppRole role);

	public void addRoleToUser(String username, String roleName);

	public List<AppUser> allUser();

	public AppUser findUserByUsername(String userName);

}
