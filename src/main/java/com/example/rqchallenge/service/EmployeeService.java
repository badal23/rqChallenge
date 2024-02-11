package com.example.rqchallenge.service;

import static com.example.rqchallenge.response.ResponseCode.RESOURCE_NOT_FOUND;

import com.example.rqchallenge.entity.Employee;
import com.example.rqchallenge.entity.repository.EmployeeRepository;
import com.example.rqchallenge.exception.CustomException;
import com.example.rqchallenge.model.EmployeeHighestSalary;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ObjectMapper objectMapper;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee createEmployee(final Map<String, Object> employeeInput) {

        Employee employee = objectMapper.convertValue(employeeInput,Employee.class);
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(final Long id) {
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
        }else {
          throw CustomException.error(RESOURCE_NOT_FOUND);
        }

    }

    public List<Employee> getEmployeesByNameSearch(final String name){
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    public Employee getEmployeeById(final Long id){
          return employeeRepository.findById(id)
              .orElseThrow(() -> CustomException.error(RESOURCE_NOT_FOUND));

    }

    public EmployeeHighestSalary getHighestSalaryOfEmployees(){
        return employeeRepository.findHighestSalary().map(integer ->
            EmployeeHighestSalary.builder().highestSalary(integer).build())
            .orElseThrow(() -> CustomException.error(RESOURCE_NOT_FOUND));
    }

    public List<String> getTopTenHighestEarningEmployeeNames() {
        return employeeRepository.findTopTenHighestEarningEmployeeNames().stream().limit(10).collect(
            Collectors.toList());
    }

}
