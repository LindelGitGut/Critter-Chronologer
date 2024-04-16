package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Customer;
import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.data.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScheduleService {


    @Autowired
    ScheduleRepository scheduleRepository;

    @Autowired
    PetRepository petRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CustomerRepository customerRepository;


    public Schedule save(Schedule schedule){
        if (schedule.getId() != null){
            Optional<Schedule> optSchedule =scheduleRepository.findById(schedule.getId());
            if (optSchedule.isPresent()){return scheduleRepository.save(schedule);}
            else {throw new IllegalArgumentException("Could not find Schedule with provided id!");}
        }
        else { return scheduleRepository.save(schedule);}
    }

    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> getSchedulesByPetId(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()){return scheduleRepository.findSchedulesByPetId(id);}
        else {throw new IllegalArgumentException("Could not find Pet with PetId: " + id);}

    }

    public List<Schedule> getSchedulesByEmployeeId(long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        if (employee.isPresent()){  return scheduleRepository.findSchedulesByEmployeeId(employeeId);}
        else {throw new IllegalArgumentException("Could not find Employee with EmployeeID: " + employeeId);}

    }

    public List<Schedule> getSchedulesCustomerId(long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()){
            return scheduleRepository.findSchedulesCustomerId(customerId);
        }
        else {throw new IllegalArgumentException("Could not find Customer with CustomerID: " + customerId);}
    }
}
