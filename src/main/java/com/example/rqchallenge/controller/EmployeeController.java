package com.example.rqchallenge.controller;

import com.example.rqchallenge.entity.Employee;
import com.example.rqchallenge.model.EmployeeHighestSalary;
import com.example.rqchallenge.response.CustomResponse;
import com.example.rqchallenge.response.ResponseBuilderUtil;
import com.example.rqchallenge.response.ResponseCode;
import com.example.rqchallenge.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @Autowired
    private ObjectMapper objectMapper;


    @GetMapping()
    ResponseEntity<CustomResponse<List<Employee>>> getAllEmployees() throws IOException {

        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseBuilderUtil.buildResponse(ResponseCode.SUCCESS, employees);
    }

    @PostMapping("/create")
    ResponseEntity<CustomResponse<Employee>> createEmployee(@RequestBody Map<String, Object> employeeInput){

        Employee employee = employeeService.createEmployee(employeeInput) ;
        return ResponseBuilderUtil.buildResponse(ResponseCode.CREATED, employee);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteEmployeeById(@PathVariable String id){

        employeeService.deleteEmployee(Long.parseLong(id));
        return ResponseBuilderUtil.buildResponse(ResponseCode.DELETED);
    }

    @GetMapping("/search/{searchString}")
    ResponseEntity<CustomResponse<List<Employee>>> getEmployeesByNameSearch(@PathVariable String searchString){
        List<Employee> employees = employeeService.getEmployeesByNameSearch(searchString);
        return ResponseBuilderUtil.buildResponse(ResponseCode.SUCCESS, employees);
    }

    @GetMapping("/{id}")
    ResponseEntity<Employee> getEmployeeById(@PathVariable String id){

      Employee employee = employeeService.getEmployeeById(Long.parseLong(id));
      return ResponseBuilderUtil.buildResponse(ResponseCode.SUCCESS,employee);

    }

    @GetMapping("/highestSalary")
    ResponseEntity<Integer> getHighestSalaryOfEmployees(){

        EmployeeHighestSalary employeeHighestSalary = employeeService.getHighestSalaryOfEmployees();
        return ResponseBuilderUtil.buildResponse(ResponseCode.SUCCESS, employeeHighestSalary);
    }

    @GetMapping("/topTenHighestEarningEmployeeNames")
    ResponseEntity<List<String>> getTopTenHighestEarningEmployeeNames(){

        List<String> employeesName = employeeService.getTopTenHighestEarningEmployeeNames();
        return ResponseBuilderUtil.buildResponse(ResponseCode.SUCCESS,employeesName);

    }
}
