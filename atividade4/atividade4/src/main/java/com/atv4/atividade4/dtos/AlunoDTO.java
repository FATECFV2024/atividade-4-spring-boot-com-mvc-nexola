package com.atv4.atividade4.dtos;

import com.atv4.atividade4.entities.Aluno;

public class AlunoDTO {
    private Long id;
    private String nome;
    private String curso;
    private Integer idade;
    private Boolean matricula;

    public AlunoDTO() {
    }

    public AlunoDTO(Aluno entity) {
        id = entity.getId();
        nome = entity.getNome();
        curso = entity.getCurso();
        idade = entity.getIdade();
        matricula = entity.getMatricula();
    }

    public AlunoDTO(Long id, String nome, String curso, Integer idade, Boolean matricula) {
        this.id = id;
        this.nome = nome;
        this.curso = curso;
        this.idade = idade;
        this.matricula = matricula;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCurso() {
        return curso;
    }

    public Integer getIdade() {
        return idade;
    }

    public Boolean getMatricula() {
        return matricula;
    }
}
