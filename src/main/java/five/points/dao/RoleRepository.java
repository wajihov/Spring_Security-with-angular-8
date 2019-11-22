package five.points.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import five.points.entities.AppRole;

public interface RoleRepository extends JpaRepository<AppRole, Long> {

	public AppRole findByRoleName(String roleName);
}
