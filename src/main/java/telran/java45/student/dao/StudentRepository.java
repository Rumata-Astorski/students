package telran.java45.student.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import telran.java45.student.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {
// Так как Эдд добавил extends CrudRepository, то интерфейс StudentRepository имплементировать не надо, 
// ибо SpringData сам сделает необходимую имплементацию. Проще говоря, класс StudentRepositoryImpl теперь 
// не нужен, поэтому Эдд удалил его из проекта!!!
	// По этой же причине все следующие методы тут тоже можно удалить.
//	Student save(Student student);
//	Optional<Student> findById(int id);
//	void deleteById(int id);
//	Iterable<Student> findAll();
	
	Stream<Student> findByNameIgnoreCase(String name);
	
	@Query("{'scores.?0': {$gt: ?1}}")
	Stream<Student> findByExamAndScoreGreaterThan(String exam, int minScore);

	long countByNameInIgnoreCase(List<String> names);
	
}

