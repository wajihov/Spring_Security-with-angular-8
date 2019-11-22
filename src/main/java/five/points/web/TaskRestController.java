package five.points.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import five.points.dao.TaskRepository;
import five.points.entities.Tasks;

@RestController
public class TaskRestController {

	@Autowired
	private TaskRepository taskRepository;

	@GetMapping("/tasks")
	public List<Tasks> listTask() {
		return taskRepository.findAll();
	}

	@PostMapping("/tasks")
	public Tasks save(@RequestBody Tasks task) {
		return taskRepository.save(task);
	}
}
