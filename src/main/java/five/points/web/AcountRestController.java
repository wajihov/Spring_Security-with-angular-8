package five.points.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import five.points.entities.AppUser;
import five.points.service.AccountService;

@RestController
public class AcountRestController {

	@Autowired
	AccountService accountService;

	@PostMapping("/register")
	public AppUser register(@RequestBody RegisterForm userForm) {

		if (!userForm.getPassword().equals(userForm.getRepassword()))
			throw new RuntimeException("You must confirm your password");

		AppUser user = accountService.findUserByUsername(userForm.getUsername());
		if (user != null)
			throw new RuntimeException("this user exist");

		AppUser appUser = new AppUser();
		appUser.setUsername(userForm.getUsername());
		appUser.setPassword(userForm.getPassword());

		accountService.saveUser(appUser);
		accountService.addRoleToUser(userForm.getUsername(), "USER");

		return appUser;
	}

}
