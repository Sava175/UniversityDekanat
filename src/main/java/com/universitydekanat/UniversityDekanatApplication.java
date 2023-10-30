package com.universitydekanat;

import com.universitydekanat.dao.UserRoleRepository;
import com.universitydekanat.domain.UserRole;
import com.universitydekanat.dtos.GroupCreateRequest;
import com.universitydekanat.dtos.GroupFullDto;
import com.universitydekanat.dtos.StudentCreateRequestDto;
import com.universitydekanat.dtos.UserDto;
import com.universitydekanat.services.GroupService;
import com.universitydekanat.services.StudentService;
import com.universitydekanat.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UniversityDekanatApplication {

    public static void main(String[] args) {
        SpringApplication.run(UniversityDekanatApplication.class, args);
    }


    @Bean
    CommandLineRunner init(UserRoleRepository roleRepository, UserService userService, GroupService groupService, StudentService studentService) {
        return args -> {

            UserRole employeeRole = roleRepository.findByName("EMPLOYEE");
            if (employeeRole == null) {
                employeeRole = new UserRole();
                employeeRole.setName("EMPLOYEE");
                roleRepository.save(employeeRole);
            }

            UserRole studentRole = roleRepository.findByName("STUDENT");
            if (studentRole == null) {
                studentRole = new UserRole();
                studentRole.setName("STUDENT");
                roleRepository.save(studentRole);
            }

            UserDto employee = new UserDto();
            employee.setEmail("employee@gmail.com");
            employee.setPassword("123456");
            employee.setFirstName("Max");
            employee.setLastName("Pain");
            employee.setAdmin(true);

            userService.signup(employee);


            UserDto student = new UserDto();
            student.setEmail("student@gmail.com");
            student.setPassword("123456");
            student.setFirstName("Alex");
            student.setLastName("Smith");
            student.setAdmin(false);

            userService.signup(student);

            GroupCreateRequest g1 = new GroupCreateRequest();
            g1.setTitle("CI-1302");
            GroupFullDto firstGroup =  groupService.create(g1);

            GroupCreateRequest g2 = new GroupCreateRequest();
            g2.setTitle("PI-1302");
            GroupFullDto secondGroup =   groupService.create(g2);

            GroupCreateRequest g3 = new GroupCreateRequest();
            g3.setTitle("LF-3321");
            GroupFullDto thirdGroup =  groupService.create(g3);

            StudentCreateRequestDto studentCreateRequestDto1 = new StudentCreateRequestDto();
            studentCreateRequestDto1.setPhone("0682684684");
            studentCreateRequestDto1.setEmail("student@gmail.com");
            studentCreateRequestDto1.setFirstName("Nazar");
            studentCreateRequestDto1.setLastName("Mh");
            studentCreateRequestDto1.setAverageGrade(4.5);
            studentCreateRequestDto1.setDateOfBirth("1990-07-30");

            studentService.createStudent(studentCreateRequestDto1, firstGroup.getId());
        };
    }
}
