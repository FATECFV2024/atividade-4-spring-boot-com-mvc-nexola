package com.atv4.atividade4.controllers;

import com.atv4.atividade4.dtos.NotaDTO;
import com.atv4.atividade4.services.NotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/notas")
public class NotaController {

    @Autowired
    private NotaService service;

    @GetMapping
    public ResponseEntity<List<NotaDTO>> findAll() {
        List<NotaDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<NotaDTO> findById(@PathVariable Long id) {
        NotaDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<NotaDTO> insert(@RequestBody NotaDTO dto) {
        dto = service.insert(dto);
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<NotaDTO> update(@PathVariable Long id, @RequestBody NotaDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

