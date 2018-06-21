package com.example.ruido.ausencias.Pedidos;


import java.io.Serializable;

public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String startdate;
    private String finishdate;
    private String reason;
    private String hours;
    private String comments;
    private String nome;

    public String geId() {
        return id;
    }

    public void setId(String id) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return id;
    }

}