package com.udacity.jdnd.course3.critter.service;


import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;
    public Employee save(Employee employee) {

        // if Employee id is not set create new Employee
       if(employee.getId() == null){return employeeRepository.save(employee);}

       else{
           //check if Employee is found in database
           employeeRepository.findById(employee.getId());
           //if no Exception is thrown, update with save Function
           return employeeRepository.save(employee);
       }
    }

    public Employee findEmployeeById(long employeeId) {
        Optional<Employee> optEmployee = employeeRepository.findById(employeeId);
        if (optEmployee.isPresent()){
            return optEmployee.get();
        }

        else {throw new IllegalArgumentException("Employee with Provided ID could not be found!");}
    }

    public void setAvailabilityByID(long employeeId, Set<DayOfWeek> daysAvailable) {
        Employee employee = this.findEmployeeById(employeeId);
        employee.setDaysAvailable(daysAvailable);
        employeeRepository.save(employee);
    }

    //TODO implement needed methods
}
