package com.udacity.jdnd.course3.critter.data;


import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
public class Employee extends User implements Serializable {

    //Employee has one or more Skills, but EmployeeSkill/daysAvailable is not worth own Entity so added as
    // Simple Element Collection
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;

}
