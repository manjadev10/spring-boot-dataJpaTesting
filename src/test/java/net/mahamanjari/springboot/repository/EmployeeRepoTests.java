package net.mahamanjari.springboot.repository;

import net.mahamanjari.springboot.module.Employee;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepoTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    //Junit test for save employee operation
    //@DisplayName("Junit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){

        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Kumar")
                .email("ramesh.kumar@gmail.com")
                .build();

        //when - action or behavious that we are going to test
        Employee savedEmployee = employeeRepository.save(employee);

        //then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }

    //JUnit test for get all employees
    @Test
    public void givenSaveEmployees_whenFindAll_thenEmployeesList(){

        //given - precondition or setup
        Employee employee1 = Employee.builder()
                .firstName("Ramesh")
                .lastName("Kumar")
                .email("ramesh.kumar@gmail.com")
                .build();

        Employee employee2 = Employee.builder()
                .firstName("John")
                .lastName("Modi")
                .email("john.kumar@gmail.com")
                .build();

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        //when - action or behavious that we are going to test
        List<Employee> employeeList = employeeRepository.findAll();

        //then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    //JUnit test for find employee by id
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject(){

        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Kumar")
                .email("ramesh.kumar@gmail.com")
                .build();
        employeeRepository.save(employee);

        //when - action or behaviours that we are going to test
        Employee result = employeeRepository.findById(1l).get();

        //then - verify the output
        assertThat(result).isNotNull();
    }


    //JUnit test for find employee by email
    @Test
    public void givenEmployeeSaved_whenFindByEmail_thenReturnEmployeeObject(){

        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Kumar")
                .email("ramesh.kumar@gmail.com")
                .build();
        employeeRepository.save(employee);

        //when - action or behaviours that we are going to test
        Employee result = employeeRepository.findByEmail("ramesh.kumar@gmail.com").get();

        //then - verify the output
        assertThat(result).isNotNull();

    }

    //JUnit test for Updating employee object
    @Test
    public void givenSavedEmployee_whenUpdate_thenReturnUpdateEmployeeObject(){

        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Kumar")
                .email("ramesh.kumar@gmail.com")
                .build();
        employeeRepository.save(employee);


        //when - action or behaviour that we are going to test
        Employee savedEmp = employeeRepository.findById(1l).get();
        savedEmp.setEmail("newMail@gmail.com");
        Employee empUpdated = employeeRepository.save(employee);



        //then - verify the output
        assertThat(empUpdated.getEmail()).isEqualTo("newMail@gmail.com");

    }


    //JUnit test for
    @Test
    public void givenEmployee_whenDelete_thenDeleteEmployee(){

        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Kumar")
                .email("ramesh.kumar@gmail.com")
                .build();
        employeeRepository.save(employee);

        //when - action or behaviour that we are going to test
        employeeRepository.delete(employee);
        Optional<Employee> empOut = employeeRepository.findById(1l);

        //then - verify the output
        assertThat(empOut).isEmpty();

    }

    @Test
    public void givenEmployee_whenFindByNamesUsingJPQl_thenReturnEmploye(){

        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Kumar")
                .email("ramesh.kumar@gmail.com")
                .build();
        employeeRepository.save(employee);

        //when - action or behaviour that we are going to test
        Employee empOut = employeeRepository.findByJPQL("Ramesh","Kumar");

        //then - verify the output
        assertThat(empOut).isNotNull();

    }

    @Test
    public void givenEmployee_whenFindByNamesUsingJPQlNamed_thenReturnEmploye(){

        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Kumar")
                .email("ramesh.kumar@gmail.com")
                .build();
        employeeRepository.save(employee);

        //when - action or behaviour that we are going to test
        Employee empOut = employeeRepository.findByJPQLNamed("Ramesh","Kumar");

        //then - verify the output
        assertThat(empOut).isNotNull();

    }


    @Test
    public void givenEmployee_whenFindByNativeSql_thenReturnEmploye(){

        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Kumar")
                .email("ramesh.kumar@gmail.com")
                .build();
        employeeRepository.save(employee);

        //when - action or behaviour that we are going to test
        Employee empOut = employeeRepository.findByNativeSql("Ramesh","Kumar");

        //then - verify the output
        assertThat(empOut).isNotNull();

    }




}
