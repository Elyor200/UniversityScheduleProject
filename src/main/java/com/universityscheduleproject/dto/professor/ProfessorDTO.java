package com.universityscheduleproject.dto.professor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.universityscheduleproject.entity.Professor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfessorDTO {
    private Long professorId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;

    public ProfessorDTO(@JsonProperty("professor_id") String professorId,
                        @JsonProperty("first_name") String firstName,
                        @JsonProperty("last_name") String lastName,
                        @JsonProperty("email") String email,
                        @JsonProperty("password") String password,
                        @JsonProperty("role") String role) {
        this.professorId = Long.parseLong(professorId);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static ProfessorDTO fromEntity(Professor professor) {
        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setProfessorId(professor.getId());
        professorDTO.setFirstName(professor.getFirstName());
        professorDTO.setLastName(professor.getLastName());
        professorDTO.setEmail(professor.getEmail());
        professorDTO.setPassword(professor.getPassword());
        professorDTO.setRole(professor.getRole());
        return professorDTO;
    }
}
