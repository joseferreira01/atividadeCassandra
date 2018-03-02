/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.banco.atividadecassandra.entidade;

import com.datastax.driver.mapping.annotations.FrozenKey;
import com.datastax.driver.mapping.annotations.FrozenValue;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import java.util.Map;

/**
 *
 * @author jose
 */
@Table(keyspace = "atividade", name = "usuario")
public class Usuario {

    @PartitionKey
    private int id;
    private String nome;
    @FrozenKey
    @FrozenValue
    private Map<String, Telefone> telefone;

    public Usuario(int id, String nome, Map<String, Telefone> telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Map<String, Telefone> getTelefone() {
        return telefone;
    }

    public void setTelefone(Map<String, Telefone> telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", telefone=" + telefone + '}';
    }

}
