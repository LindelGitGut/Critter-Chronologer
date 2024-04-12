package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.data.Pet;
import com.udacity.jdnd.course3.critter.data.Schedule;
import com.udacity.jdnd.course3.critter.service.EmployeeService;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {


    @Autowired
    EmployeeService employeeService;

    @Autowired
    PetService petService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        throw new UnsupportedOperationException();
    }


    private Schedule convertFromScheduleDto(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);
        if (scheduleDTO.getEmployeeIds() != null){
            List<Employee> employees = new ArrayList<>();
            for (Long employeeid: scheduleDTO.getEmployeeIds()
                 ) {
                employees.add(employeeService.findEmployeeById(employeeid));
            }
            schedule.setEmployees(employees);
        }
        if(scheduleDTO.getPetIds() != null){
            List<Pet> pets = new ArrayList<>();
            for (Long petId: scheduleDTO.getPetIds()
                 ) {
                pets.add(petService.findPetById(petId));
            }
            schedule.setPets(pets);
        }
        return schedule;
    }
    private ScheduleDTO convertToScheduleDto(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        if(schedule.getEmployees() != null){
            List<Long> employeeids = new ArrayList<>();
            for (Employee employee: schedule.getEmployees()
                 ) {
                employeeids.add(employee.getId());
            }
            scheduleDTO.setEmployeeIds(employeeids);
        }

        if(schedule.getPets() != null){
            List<Long> petids = new ArrayList<>();
            for (Pet pet: schedule.getPets()
                 ) {
                petids.add(pet.getId());
            }
            scheduleDTO.setPetIds(petids);
        }
        return scheduleDTO;
    }
}
