package telran.java45.student.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java45.student.dao.StudentRepository;
import telran.java45.student.dto.ScoreDto;
import telran.java45.student.dto.StudentCreateDto;
import telran.java45.student.dto.StudentInfoDto;
import telran.java45.student.dto.StudentUpdateDto;
import telran.java45.student.dto.exceptions.StudentNotFoundException;
import telran.java45.student.model.Student;

@Service
@RequiredArgsConstructor //аннотация c Required для поля final
public class StudentServiceImpl implements StudentService {

	final StudentRepository studentRepository;
	final ModelMapper modelMapper;

	@Override
	public Boolean addStudent(StudentCreateDto studentCreateDto) {
		if (studentRepository.findById(studentCreateDto.getId()).isPresent()) {
			return false;
		}
//		Student student = new Student(studentCreateDto.getId(), studentCreateDto.getName(),
//				studentCreateDto.getPassword());
		Student student = modelMapper.map(studentCreateDto, Student.class);
		studentRepository.save(student);
		return true;
	}

	@Override
	public StudentInfoDto findStudent(Integer id) {
//		Student student = studentRepository.findById(id).orElse(null);
//		return student == null ? null : new StudentInfoDto(id, student.getName(), student.getScores());
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
//		return new StudentInfoDto(id, student.getName(), student.getScores());	
		return modelMapper.map(student, StudentInfoDto.class);	
	}

	@Override
	public StudentInfoDto removeStudent(Integer id) {
//		Student student = studentRepository.findById(id).orElse(null);
//		if (student != null) {
//			studentRepository.deleteById(id);
//			//using Constructor
//			return new StudentInfoDto(id, student.getName(), student.getScores());
//		}
//		return null;
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
		studentRepository.deleteById(id);
//		return new StudentInfoDto(id, student.getName(), student.getScores());
		return modelMapper.map(student, StudentInfoDto.class);	
	}

	//HW закончить все оставшиеся методы:
	@Override
	public StudentCreateDto updateStudent(Integer id, StudentUpdateDto studentUpdateDto) {
		//TODO Homework
//		Student student = studentRepository.findById(id).orElse(null);
//		if (student == null) {
//			return null;
//		}
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
		if (studentUpdateDto.getName() != null) {
			student.setName(studentUpdateDto.getName());
		}
		if (studentUpdateDto.getPassword() != null) {
			student.setPassword(studentUpdateDto.getPassword());
		}
		//так как мы удалили класс StudentRepositoryImpl, то теперь нужно сохранять update студента в базе данных
		studentRepository.save(student);
		//now using Builder insted of Constructor
//		return StudentCreateDto.builder().id(id).name(student.getName()).password(student.getPassword()).build();
		return modelMapper.map(student, StudentCreateDto.class);	
	}

	@Override
	public Boolean addScore(Integer id, ScoreDto scoreDto) {
		//TODO Homework
//		Student student = studentRepository.findById(id).orElse(null);
//		if (student == null) {
//			return false;
//		}
//		return student.addScore(scoreDto.getExamName(), scoreDto.getScore());
		
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
		boolean newScore = student.addScore(scoreDto.getExamName(), scoreDto.getScore());
		studentRepository.save(student);
		return newScore;

	}

	@Override
	public List<StudentInfoDto> findStudentsByName(String name) {
		//TODO Homework
//		return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
//				.filter(student -> name.equalsIgnoreCase(student.getName()))
//				.map(student -> new StudentInfoDto(student.getId(), student.getName(), student.getScores()))
//				.collect(Collectors.toList());

//		return studentRepository.findByNameIgnoreCase(name)
//				.map(student -> new StudentInfoDto(student.getId(), student.getName(), student.getScores()))
//				.collect(Collectors.toList());
		
		return studentRepository.findByNameIgnoreCase(name)
				.map(student ->  modelMapper.map(student, StudentInfoDto.class))
				.collect(Collectors.toList());

	}

	@Override
	public Long getStudentsNamesQuantity(List<String> names) {
		//TODO Homework
//		return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
//				.filter(student -> names.contains(student.getName()))
//				.count();
		return studentRepository.countByNameInIgnoreCase(names);
	}

	@Override
	public List<StudentInfoDto> getStudentsByExamMinScore(String exam, Integer minScore) {
		//TODO Homework
		return StreamSupport.stream(studentRepository.findAll().spliterator(), false)
				.filter(student -> student.getScores().containsKey(exam) && student.getScores().get(exam) > minScore)
				.map(student -> new StudentInfoDto(student.getId(), student.getName(), student.getScores()))
				.collect(Collectors.toList());
	}

}

