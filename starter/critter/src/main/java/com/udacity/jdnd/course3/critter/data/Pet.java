package com.udacity.jdnd.course3.critter.data;
import com.udacity.jdnd.course3.critter.pet.PetType;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
public class Pet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Nationalized
    private String name;

    @Enumerated(EnumType.STRING)
    private PetType petType;

    private LocalDate birthDate;
    private String notes;

    //Pet has one Owner (Customer)
    @ManyToOne(fetch=FetchType.EAGER)
    //@JoinColumn(name = "owner_id")
    private Customer owner;

}
