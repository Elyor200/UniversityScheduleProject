package com.universityscheduleproject.dto.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String major;
    private String phoneNumber;

//    public StudentRequestDTO(@JsonProperty("first_name") String firstName,
//                             @JsonProperty("last_name") String lastName,
//                             @JsonProperty("email") String email,
//                             @JsonProperty("major") String major,
//                             @JsonProperty("phone_number") String phoneNumber) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.major = major;
//        this.phoneNumber = phoneNumber;
//    }
}
