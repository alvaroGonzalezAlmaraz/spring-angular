package org.wasabiweb.employee.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.wasabiweb.employee.exceptions.UserNotFoundException;
import org.wasabiweb.employee.model.Employee;
import org.wasabiweb.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return (List<Employee>) employeeRepository.findAll();
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findEmployeeById(id).orElseThrow(
                () -> new UserNotFoundException("El empleado con id nº: " + " no se encuentra en la base de datos"));
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
