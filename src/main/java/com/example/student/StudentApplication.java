package com.example.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.student.entities.Archievements;
import com.example.student.entities.Course;
import com.example.student.entities.Student;
import com.example.student.entities.StudentInfo;
import com.example.student.enums.AwardType;
import com.example.student.repositories.ArchievementsRepos;
import com.example.student.repositories.CourseRepos;
import com.example.student.repositories.StudentRepository;
import com.github.javafaker.Faker;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@SpringBootApplication
@EnableJpaAuditing
public class StudentApplication {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ArchievementsRepos archievementsRepos;

	@Autowired
	private CourseRepos courseRepos;

	public static void main(String[] args) {
		SpringApplication.run(StudentApplication.class, args);
	}

	@PostConstruct
	public void addFakeData() {
		// Truncate tables
		// studentRepository.doTruncate();
		// courseRepos.doTruncate();
		// archievementsRepos.doTruncateRelationaltable();
		// archievementsRepos.doTruncate();

		// Faker faker = new Faker();
		// Random random = new Random();

		// setArchievements();
		// setTheCourses();

		// for (int i = 1; i <= 50; i++) {
		// 	Student student = new Student();
		// 	student.setStudentId(1000 + i);

		// 	// 3️⃣ Fresh list of achievements per student
		// 	List<Archievements> studentArch = new ArrayList<>();
		// 	studentArch.add(archList.get(random.nextInt(archList.size())));
		// 	studentArch.add(archList.get(random.nextInt(archList.size())));
		// 	student.setArchievements(studentArch);

		// 	List<Course> assignedCourses = new ArrayList<>();
		// 	Course course = new Course();
		// 	course.setCourseCode(faker.code().asin());
		// 	course.setSemester(String.valueOf(random.nextInt(1,8)));
		// 	assignedCourses.add(course);
		// 	student.setCourse(assignedCourses);
		// 	course.setStudent(student);
			

		// 	// 5️⃣ Student Info
		// 	StudentInfo info = new StudentInfo();
		// 	info.setName(faker.name().fullName());
		// 	info.setDob(faker.date().birthday(18, 25).toString());
		// 	info.setAddress(faker.address().fullAddress());
		// 	info.setStudent(student);
		// 	student.setStudentInfo(info);
		// 	studentRepository.save(student);
		// 	courseRepos.save(course);
		// }

	}

	public List<Archievements> setArchievements(){
		List<Archievements> archievementsList = new ArrayList<>();
		Faker faker = new Faker();
		for(int i=0;i<20;i++){
			Archievements archievements = new Archievements();
			archievements.setAwardName(faker.name().title());
			archievements.setAwardType(AwardType.getRandomType());
			archievements.setAwardPrice(faker.number().randomDouble(5, 1000, 10000));
			archievementsList.add(archievements);
		}
		archievementsRepos.saveAll(archievementsList);
		return archievementsList;
	}

	public List<Course> setTheCourses(){
		List<Course> courses = new ArrayList<>();
		Faker faker = new Faker();
		Random random = new Random();
		IntStream.range(0, 20).forEach(i->{
			Course course = new Course();
			course.setCourseCode(faker.code().asin());
			course.setSemester(String.valueOf(random.nextInt(1,8)));
			courses.add(course);
		});
		courseRepos.saveAll(courses);
		return courses;
	}
}
