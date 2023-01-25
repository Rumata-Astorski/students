package telran.java45.student.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
//@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Student not found")
public class StudentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8877536792797063120L;

	public StudentNotFoundException() {}
	
	public StudentNotFoundException(int id) {
		super("Student with id " + id + " not found");
	}
	
}
