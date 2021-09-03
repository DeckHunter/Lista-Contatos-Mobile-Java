package com.example.af_listadecontatos_2021.model;

import java.io.Serializable;

public class Contatos implements Serializable {

    private Long id;
    private String nomeContato;
    private String ApelidoContato;
    private String NumeroContato;
    private String GrupoContato;
    private String EmailContato;

    public String getApelidoContato() {
        return ApelidoContato;
    }

    public void setApelidoContato(String apelidoContato) {
        ApelidoContato = apelidoContato;
    }

    public String getNumeroContato() {
        return NumeroContato;
    }

    public void setNumeroContato(String numeroContato) {
        NumeroContato = numeroContato;
    }

    public String getGrupoContato() {
        return GrupoContato;
    }

    public void setGrupoContato(String grupoContato) {
        GrupoContato = grupoContato;
    }

    public String getEmailContato() {
        return EmailContato;
    }

    public void setEmailContato(String emailContato) {
        EmailContato = emailContato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }
}
