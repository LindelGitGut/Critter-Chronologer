package com.udacity.jdnd.course3.critter.service;


import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeDTO;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
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

    public List<Employee> getAvailableEmloyeesForService(Set<EmployeeSkill> skills, LocalDate date) {
        DayOfWeek dayOfWeek= date.getDayOfWeek();
        System.out.println("Debug DayOfWeek: " +dayOfWeek);
        List<Employee> employees =  employeeRepository.findEmployeeByDayOfWeek(dayOfWeek);

        for (Employee employee:employees
             ) {
            if (!employee.getSkills().containsAll(skills)){employees.remove(employee);}
        }
        return employees;
    }


}
