package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleService {


    @Autowired
    ScheduleRepository scheduleRepository;


    public void save(Schedule schedule){
        if (schedule.getId() != null){
            Optional<Schedule> optSchedule =scheduleRepository.findById(schedule.getId());
            if (optSchedule.isPresent()){scheduleRepository.save(schedule);}
            else {throw new IllegalArgumentException("Could nit find Schedule with provided id!");}
        }
        else {scheduleRepository.save(schedule);}
    }


}
