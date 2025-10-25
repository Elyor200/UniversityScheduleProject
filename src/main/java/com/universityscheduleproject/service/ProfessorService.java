package com.universityscheduleproject.service;

import com.universityscheduleproject.dto.professor.ProfessorDTO;
import com.universityscheduleproject.dto.professor.ProfessorRequestDTO;
import com.universityscheduleproject.entity.Professor;
import com.universityscheduleproject.repository.ProfessorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfessorService {
    private ProfessorRepository professorRepository;

    public ProfessorDTO createProfessor(ProfessorRequestDTO requestDTO) {
        Optional<Professor> optionalProfessor = professorRepository.findByEmailAndFirstNameAndLastName
                (requestDTO.getEmail(), requestDTO.getFirstName(), requestDTO.getLastName());
        if (optionalProfessor.isPresent()) {
            throw new RuntimeException("Professor already exists!");
        }

        ProfessorDTO professorDTO = new ProfessorDTO();
        professorDTO.setEmail(requestDTO.getEmail());
        professorDTO.setFirstName(requestDTO.getFirstName());
        professorDTO.setLastName(requestDTO.getLastName());

        Professor professor = Professor.toEntity(professorDTO);
        professorRepository.save(professor);
        return professorDTO;
    }

    public List<ProfessorDTO> getAllProfessors() {
        List<ProfessorDTO> professorDTOList = new ArrayList<>();
        List<Professor> professorList = professorRepository.findAll();
        for (Professor professor : professorList) {
            ProfessorDTO professorDTO = ProfessorDTO.fromEntity(professor);
            professorDTOList.add(professorDTO);
        }
        return  professorDTOList;
    }

    public ProfessorDTO getProfessorById(Long id) {
        Optional<Professor> optionalProfessor = professorRepository.findById(id);
        if (!optionalProfessor.isPresent()) {
            throw new RuntimeException("Professor not found!");
        }
        return ProfessorDTO.fromEntity(optionalProfessor.get());
    }
}
