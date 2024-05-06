package com.atv4.atividade4.dtos;

import com.atv4.atividade4.entities.Endereco;

public class EnderecoDTO {
    private Long id;
    private String rua, cidade, estado, cep;
    private Integer numero;

    public EnderecoDTO() {
    }

    public EnderecoDTO(Endereco entity) {
        id = entity.getId();
        rua = entity.getRua();
        cidade = entity.getCidade();
        estado = entity.getEstado();
        cep = entity.getCep();
        numero = entity.getNumero();
    }

    public EnderecoDTO(Long id, String rua, String cidade, String estado, String cep, Integer numero) {
        this.id = id;
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public String getRua() {
        return rua;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }

    public Integer getNumero() {
        return numero;
    }
}
