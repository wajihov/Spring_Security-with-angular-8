package five.points.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import five.points.dao.RoleRepository;
import five.points.dao.UserRepository;
import five.points.entities.AppRole;
import five.points.entities.AppUser;

@Service
@Transactional
public class AccountServiceImp implements AccountService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public AppUser saveUser(AppUser user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public AppRole saveRole(AppRole role) {
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		AppRole role = roleRepository.findByRoleName(roleName);
		AppUser user = userRepository.findByUsername(username);
		user.getRoles().add(role);
	}

	@Override
	public List<AppUser> allUser() {
		return userRepository.findAll();
	}

	@Override
	public AppUser findUserByUsername(String userName) {
		return userRepository.findByUsername(userName);
	}

}