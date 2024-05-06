package com.atv4.atividade4.services;

import com.atv4.atividade4.dtos.NotaDTO;
import com.atv4.atividade4.entities.Nota;
import com.atv4.atividade4.exceptions.ResourceNotFoundException;
import com.atv4.atividade4.repositories.NotaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotaService {

    @Autowired
    private NotaRepository repository;

    @Transactional(readOnly = true)
    public List<NotaDTO> findAll() {
        List<Nota> list = repository.findAll();
        return list.stream().map(x -> new NotaDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public NotaDTO findById(Long id) {
        Optional<Nota> obj = repository.findById(id);
        Nota entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new NotaDTO(entity);
    }

    @Transactional
    public NotaDTO insert(NotaDTO dto) {
        Nota entity = new Nota();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new NotaDTO(entity);
    }

    @Transactional
    public NotaDTO update(Long id, NotaDTO dto) {
        try {
            Nota entity = repository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new NotaDTO(entity);
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

    private void copyDtoToEntity(NotaDTO dto, Nota entity) {
        entity.setNota(dto.getNota());
        entity.setNome_disciplina(dto.getNome_disciplina());
    }
}
