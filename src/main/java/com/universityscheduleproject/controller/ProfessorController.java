package com.universityscheduleproject.controller;

import com.universityscheduleproject.dto.professor.ProfessorDTO;
import com.universityscheduleproject.dto.professor.ProfessorRequestDTO;
import com.universityscheduleproject.service.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/professor")
public class ProfessorController {
    private ProfessorService professorService;

    @PostMapping("/create-professor")
    public ResponseEntity<?> createProfessor(@RequestBody ProfessorRequestDTO requestDTO) {
        ProfessorDTO professor = professorService.createProfessor(requestDTO);
        return ResponseEntity.ok(professor);
    }
}
