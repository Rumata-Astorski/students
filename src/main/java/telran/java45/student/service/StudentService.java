package telran.java45.student.service;

import java.util.List;

import telran.java45.student.dto.ScoreDto;
import telran.java45.student.dto.StudentCreateDto;
import telran.java45.student.dto.StudentInfoDto;
import telran.java45.student.dto.StudentUpdateDto;

public interface StudentService {
	Boolean addStudent(StudentCreateDto studentCreateDto);

	StudentInfoDto findStudent(Integer id);

	StudentInfoDto removeStudent(Integer id);

	StudentCreateDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto);

	Boolean addScore(Integer id, ScoreDto scoreDto);

	List<StudentInfoDto> findStudentsByName(String name);

	Long getStudentsNamesQuantity(List<String> names);

	List<StudentInfoDto> getStudentsByExamMinScore(String exam, Integer minScore);
}
