package com.example.rqchallenge.entity.repository;


import com.example.rqchallenge.entity.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContainingIgnoreCase(String searchString);

    @Query("SELECT MAX(e.salary) FROM Employee e")
    Optional<Integer> findHighestSalary();

    @Query("SELECT e.name FROM Employee e ORDER BY e.salary DESC")
    List<String> findTopTenHighestEarningEmployeeNames();
}
