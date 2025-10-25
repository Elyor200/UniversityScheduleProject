package com.universityscheduleproject.controller;

import com.universityscheduleproject.dto.professor.ProfessorDTO;
import com.universityscheduleproject.dto.professor.ProfessorRequestDTO;
import com.universityscheduleproject.service.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getAllProfessors")
    public ResponseEntity<?> getAllProfessors() {
        List<ProfessorDTO> allProfessors = professorService.getAllProfessors();
        return ResponseEntity.ok(allProfessors);
    }

    @GetMapping("/getProfessorById")
    public ResponseEntity<?> getProfessorById(@RequestParam long id) {
        ProfessorDTO professor = professorService.getProfessorById(id);
        return ResponseEntity.ok(professor);
    }
}
