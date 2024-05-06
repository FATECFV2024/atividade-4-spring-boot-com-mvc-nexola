package com.atv4.atividade4.services;

import com.atv4.atividade4.dtos.EnderecoDTO;
import com.atv4.atividade4.entities.Endereco;
import com.atv4.atividade4.exceptions.ResourceNotFoundException;
import com.atv4.atividade4.repositories.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    @Transactional(readOnly = true)
    public List<EnderecoDTO> findAll() {
        List<Endereco> list = repository.findAll();
        return list.stream().map(x -> new EnderecoDTO(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EnderecoDTO findById(Long id) {
        Optional<Endereco> obj = repository.findById(id);
        Endereco entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new EnderecoDTO(entity);
    }

    @Transactional
    public EnderecoDTO insert(EnderecoDTO dto) {
        Endereco entity = new Endereco();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new EnderecoDTO(entity);
    }

    @Transactional
    public EnderecoDTO update(Long id, EnderecoDTO dto) {
        try {
            Endereco entity = repository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new EnderecoDTO(entity);
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

    private void copyDtoToEntity(EnderecoDTO dto, Endereco entity) {
        entity.setRua(dto.getRua());
        entity.setCidade(dto.getCidade());
        entity.setEstado(dto.getEstado());
        entity.setCep(dto.getCep());
        entity.setNumero(dto.getNumero());
    }
}
