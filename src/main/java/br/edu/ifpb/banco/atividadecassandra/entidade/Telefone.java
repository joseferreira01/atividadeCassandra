/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.banco.atividadecassandra.entidade;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;

/**
 *
 * @author jose
 */
@UDT(keyspace = "atividade", name = "telefone")
public class Telefone {
    @Field(name = "zip_code")
    private int idUsuario;
    private String numero;

    public Telefone() {
    }

    public Telefone(int idUsuario, String numero) {
        this.idUsuario = idUsuario;
        this.numero = numero;
    }
    

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getId() {
        return idUsuario;
    }

    public void setId(int id) {
        this.idUsuario = id;
    }
    
}
