package com.universityscheduleproject.entity;

import com.universityscheduleproject.dto.professor.ProfessorDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;

    public static Professor toEntity(ProfessorDTO professorDTO) {
        Professor professor = new Professor();
        professor.setId(professorDTO.getProfessorId());
        professor.setFirstName(professorDTO.getFirstName());
        professor.setLastName(professorDTO.getLastName());
        professor.setEmail(professorDTO.getEmail());
        professor.setPassword(professorDTO.getPassword());
        professor.setRole(professorDTO.getRole());
        return professor;
    }
}
