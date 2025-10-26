package com.universityscheduleproject.dto.student;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.universityscheduleproject.entity.Student;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentResponseDTO {
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String major;

    public StudentResponseDTO(@JsonProperty("student_id") String studentId,
                              @JsonProperty("first_name") String firstName,
                              @JsonProperty("last_name") String lastName,
                              @JsonProperty("email") String email,
                              @JsonProperty("phone_number") String phoneNumber,
                              @JsonProperty("major") String major) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.major = major;
    }

    public static StudentResponseDTO fromEntity(Student student) {
        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setStudentId(student.getStudentId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setPhoneNumber(student.getPhoneNumber());
        dto.setMajor(student.getMajor());
        return dto;
    }
}
