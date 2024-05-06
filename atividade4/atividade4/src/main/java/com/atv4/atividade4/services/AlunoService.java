package com.atv4.atividade4.services;

import com.atv4.atividade4.dtos.AlunoDTO;
import com.atv4.atividade4.entities.Aluno;
import com.atv4.atividade4.exceptions.ResourceNotFoundException;
import com.atv4.atividade4.repositories.AlunoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Transactional(readOnly = true)
    public List<AlunoDTO> findAll() {
        List<Aluno> list = repository.findAll();
        return list.stream().map(x -> new AlunoDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AlunoDTO findById(Long id) {
        Optional<Aluno> obj = repository.findById(id);
        Aluno entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new AlunoDTO(entity);
    }

    @Transactional
    public AlunoDTO insert(AlunoDTO dto) {
        Aluno entity = new Aluno();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new AlunoDTO(entity);
    }

    @Transactional
    public AlunoDTO update(Long id, AlunoDTO dto) {
        try {
            Aluno entity = repository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new AlunoDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    private void copyDtoToEntity(AlunoDTO dto, Aluno entity) {
        entity.setNome(dto.getNome());
        entity.setCurso(dto.getCurso());
        entity.setIdade(dto.getIdade());
        entity.setMatricula(dto.getMatricula());
    }
}
