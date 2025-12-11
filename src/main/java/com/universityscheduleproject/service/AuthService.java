package com.universityscheduleproject.service;

import com.universityscheduleproject.dto.GetMeDTO;
import com.universityscheduleproject.dto.login.LoginRequestDTO;
import com.universityscheduleproject.dto.login.LoginResponseDTO;
import com.universityscheduleproject.dto.professor.GetMeProfDTO;
import com.universityscheduleproject.entity.Professor;
import com.universityscheduleproject.entity.Student;
import com.universityscheduleproject.repository.ProfessorRepository;
import com.universityscheduleproject.repository.StudentRepository;
import com.universityscheduleproject.security.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Optional<Student> student = studentRepository.findByEmail(loginRequestDTO.getEmail());
        if (student.isPresent()) {
            if (!passwordEncoder.matches(loginRequestDTO.getPassword(), student.get().getPassword())) {
                throw new RuntimeException("Invalid password");
            }

            String token = jwtUtils.generateToken(loginRequestDTO.getEmail(), "STUDENT");
            return new LoginResponseDTO(token, "STUDENT");
        }

        Optional<Professor> professor = professorRepository.findByEmail(loginRequestDTO.getEmail());
        if (professor.isPresent()) {
            if (!passwordEncoder.matches(loginRequestDTO.getPassword(), professor.get().getPassword())) {
                throw new RuntimeException("Invalid password");
            }
            String token = jwtUtils.generateToken(loginRequestDTO.getEmail(), "PROFESSOR");
            return new LoginResponseDTO(token, "PROFESSOR");
        }

        return null;
    }

    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (authentication == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        String email = authentication.getName();
        Optional<Student> student = studentRepository.findByEmail(email);
        if (student.isPresent()) {
            GetMeDTO dto = GetMeDTO.fromEntity(student.get());
            return ResponseEntity.ok(dto);
        }

        Optional<Professor> professor = professorRepository.findByEmail(email);
        if (professor.isPresent()) {
            GetMeProfDTO getMeProfDTO = GetMeProfDTO.fromEntity(professor.get());
            return ResponseEntity.ok(getMeProfDTO);
        }
        return ResponseEntity.status(404).body("User not found");
    }
}
