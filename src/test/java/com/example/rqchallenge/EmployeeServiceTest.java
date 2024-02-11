package com.example.rqchallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.rqchallenge.entity.Employee;
import com.example.rqchallenge.exception.CustomException;
import com.example.rqchallenge.model.EmployeeHighestSalary;
import com.example.rqchallenge.service.EmployeeService;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeServiceTest {
    @Autowired
    private EmployeeService employeeService;

    void TestCreateEmployee(String name, long salary,int age){
        Map<String, Object> employeeInput1 = Map.of( "name", name, "salary", salary,"age",age);

        Employee employee = employeeService.createEmployee(employeeInput1);

        assertNotNull(employee.getId());
        assertEquals(employee.getName(), name);
    }


    void TestDeleteEmployee(Long id){
        employeeService.deleteEmployee(id);
    }


    @Test
    void TestGetEmployeesByNameSearch(){

        TestCreateEmployee("John", 5000,34);
        TestCreateEmployee("Mick", 7000,24);
        TestCreateEmployee("Mick Tesla", 8000,14);
        TestCreateEmployee("Mick2", 8000,14);
        TestCreateEmployee("Mick3", 9000,14);
        TestCreateEmployee("Mick4", 10000,14);
        TestCreateEmployee("Mick5", 1000,14);
        TestCreateEmployee("Mick6", 2000,14);

        TestCreateEmployee("Mick7", 4000,14);
        TestCreateEmployee("Mick8", 11000,14);
        TestCreateEmployee("John Tesla", 12000,14);
        TestCreateEmployee("Tesla Tesla", 15000,14);

        // will fetch all employees
        List<Employee> result = employeeService.getAllEmployees();
        assertNotNull(result.size());
        assertEquals(result.size(), 12);

        // delete one employee
        employeeService.deleteEmployee(result.get(0).getId());

        // will fetch all employees
        result = employeeService.getAllEmployees();
        assertNotNull(result.size());
        assertEquals(result.size(), 11);

        Employee employee = employeeService.getEmployeeById(result.get(0).getId());

        assertNotNull(employee);
        assertEquals(employee.getId(), result.get(0).getId());

        List<Employee> employeeByName  = employeeService.getEmployeesByNameSearch("Mick");
        assertNotNull(employeeByName.size());
        assertEquals(employeeByName.size(), 9);

        EmployeeHighestSalary employeeHighestSalary = employeeService.getHighestSalaryOfEmployees();

        assertNotNull(employeeHighestSalary);
        assertEquals(employeeHighestSalary.getHighestSalary(), 15000);

        List<String> top10EmployeeName = employeeService.getTopTenHighestEarningEmployeeNames();

        assertNotNull(top10EmployeeName);
        assertEquals(top10EmployeeName.size(), 10);
        assertEquals(top10EmployeeName.get(0), "Tesla Tesla");
        assertEquals(top10EmployeeName.get(9), "Mick6");

        //Delete all employees
        result.stream().forEach(employeeTest ->  TestDeleteEmployee(employeeTest.getId()));

        List<Employee> employeeListAfterDelete = employeeService.getAllEmployees();
        assertNotNull(employeeListAfterDelete.size());
        assertEquals(employeeListAfterDelete.size(), 0);

        // Now delete employee we should get exception
        assertThrows(CustomException.class, () -> TestDeleteEmployee(10l));

        // Now search based on id
        assertThrows(CustomException.class, () -> employeeService.getEmployeeById(10l));

        assertThrows(CustomException.class, () -> employeeService.getHighestSalaryOfEmployees());



    }

}
