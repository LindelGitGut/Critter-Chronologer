package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Schedule;
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
        return scheduleRepository.findSchedulesByPetId(id);
    }

    public List<Schedule> getSchedulesByEmployeeId(long employeeId) {
        return scheduleRepository.findSchedulesByEmployeeId(employeeId);
    }

    public List<Schedule> getSchedulesCustomerId(long customerId) {
        return scheduleRepository.findSchedulesCustomerId(customerId);
    }
}
