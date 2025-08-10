
package com.example.student.entities;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.student.enums.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Archievements {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;

     private String awardName;

     private double awardPrice;

     @Enumerated(EnumType.STRING)
     private AwardType awardType;

}