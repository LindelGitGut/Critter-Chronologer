package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    @Query("SELECT DISTINCT p FROM Employee p Join p.daysAvailable days WHERE :dayOfWeek MEMBER OF days ")
    public List<Employee> findEmployeeByDayOfWeek(DayOfWeek dayOfWeek);
}
