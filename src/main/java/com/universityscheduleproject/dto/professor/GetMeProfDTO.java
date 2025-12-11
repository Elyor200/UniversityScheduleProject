package com.universityscheduleproject.dto.professor;

import com.universityscheduleproject.entity.Professor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMeProfDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;

    public static GetMeProfDTO fromEntity(Professor professor) {
        GetMeProfDTO dto = new GetMeProfDTO();
        dto.setId(professor.getId());
        dto.setEmail(professor.getEmail());
        dto.setFirstName(professor.getFirstName());
        dto.setLastName(professor.getLastName());
        dto.setRole(professor.getRole());
        return dto;
    }
}
