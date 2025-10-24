package com.universityscheduleproject.dto.professor;

import com.universityscheduleproject.entity.Professor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorRequestDTO {
    private String firstName;
    private String lastName;
    private String email;

    public static ProfessorRequestDTO fromEntity(Professor professor) {
        ProfessorRequestDTO professorDTO = new ProfessorRequestDTO();
        professorDTO.setFirstName(professor.getFirstName());
        professorDTO.setLastName(professor.getLastName());
        professorDTO.setEmail(professor.getEmail());
        return professorDTO;
    }
}
