/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.banco.atividadecassandra;

import br.edu.ifpb.banco.atividadecassandra.repositorio.Repositorio;
import br.edu.ifpb.banco.atividadecassandra.entidade.Usuario;
import com.google.common.collect.HashBiMap;
import com.sun.jndi.toolkit.ctx.HeadTail;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import br.edu.ifpb.banco.atividadecassandra.entidade.Telefone;

/**
 *
 * @author jose
 */
public class Main {

    private static Repositorio repositorio = new Repositorio();

    public static void main(String[] args) {
        Map<String, Telefone> tel1 = new HashMap<>();
        Telefone telefone= new Telefone(1,"2234-23454");
        Telefone telefone2= new Telefone(1,"2234-4666");
        Telefone telefone3= new Telefone(1,"2234-667");
        tel1.put("1",telefone);
        Map<String, Telefone> tel2 = new HashMap<>();
        
        tel2.put("2", telefone2);
        Map<Integer, String> tel3 = new HashMap<>();
        
        tel3.put(Integer.parseInt("1"), "66666-1111");
        Usuario usuario1 = new Usuario(1, "pedro", tel1);
        Usuario usuario2 = new Usuario(2, "maria", tel2);
      
        salvar(usuario1);
        salvar(usuario2);
        upDate(usuario1);
        listar().forEach(u -> System.out.println());
        System.err.println("===================== ************ =====================");
        System.err.println("deletando usuario com id 1");
        delete(1);
        listar().forEach(u -> System.out.println());
    }

    private static void salvar(Usuario usuario) {
        repositorio.salvar(usuario);
    }

    private static void upDate(Usuario usuario) {
        repositorio.upDate(usuario);
    }

    private static void delete(int id) {
        repositorio.delete(id);
    }

    private static List<Usuario> listar() {
        return repositorio.getTodos();
    }

}
