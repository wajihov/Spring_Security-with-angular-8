package five.points.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import five.points.entities.Tasks;

public interface TaskRepository extends JpaRepository<Tasks, Long> {

}
