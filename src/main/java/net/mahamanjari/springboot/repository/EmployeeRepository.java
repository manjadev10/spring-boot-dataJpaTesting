package net.mahamanjari.springboot.repository;

import net.mahamanjari.springboot.module.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> findByEmail(String email);

    @Query("select e from Employee e where e.firstName = ?1 and e.lastName = ?2")
    Employee findByJPQL(String firstName, String lastName);

    @Query("select e from Employee e where e.firstName = :first and e.lastName = :last")
    Employee findByJPQLNamed(@Param("first") String firstName, @Param("last") String lastName);

    @Query(value = "select * from employees e where e.first_name = ?1 and e.last_name = ?2",nativeQuery = true)
    Employee findByNativeSql(String firstName,String lastName);
}
