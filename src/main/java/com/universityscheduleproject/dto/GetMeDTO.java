package com.universityscheduleproject.dto;

import com.universityscheduleproject.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMeDTO {
    private Long id;
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String major;
    private String phoneNumber;
    private String role;

    public static GetMeDTO fromEntity(Student student) {
        GetMeDTO dto = new GetMeDTO();
        dto.setId(student.getId());
        dto.setStudentId(student.getStudentId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setMajor(student.getMajor());
        dto.setPhoneNumber(student.getPhoneNumber());
        dto.setRole(student.getRole());
        return dto;
    }
}
