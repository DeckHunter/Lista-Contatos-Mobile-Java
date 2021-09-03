package com.example.af_listadecontatos_2021.helper;

import com.example.af_listadecontatos_2021.model.Contatos;

import java.util.List;

public interface IContatoDAO {
    public boolean Salvar(Contatos contatos);
    public boolean Atualizar(Contatos contatos);
    public boolean Deletar(Contatos contatos);
    public List<Contatos> Listar();


}
