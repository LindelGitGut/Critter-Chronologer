package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.NamedQuery;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

   @Query("SELECT s FROM Schedule s join s.pets p WHERE p.id = :petid")
    public List<Schedule> findSchedulesByPetId(Long petid);

   @Query("SELECT s FROM Schedule s join s.employees e WHERE e.id = :employeeid")
    public List<Schedule> findSchedulesByEmployeeId(Long employeeid);

   @Query("SELECT s FROM Schedule s join s.pets p join p.owner o WHERE o.id = :customerid")
   public List<Schedule> findSchedulesCustomerId(Long customerid);
}
