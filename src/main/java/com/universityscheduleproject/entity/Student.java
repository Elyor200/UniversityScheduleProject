package com.universityscheduleproject.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Random;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "student_id")
    private String studentId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "major")
    private String major;
    @Column(name = "phone_number")
    private String phoneNumber;

    @PrePersist
    public void prePersist() {
        this.studentId = generateStudentId();
    }

    public String generateStudentId() {
        Random rand = new Random();
        char firstLetter = (char) ('A' +  rand.nextInt(26));
        char secondLetter = (char) ('A' +  rand.nextInt(26));
        int number = rand.nextInt(100000);
        return String.format("%c%c%05d", firstLetter, secondLetter, number);
    }
}
