/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.banco.atividadecassandra.repositorio;

import br.edu.ifpb.banco.atividadecassandra.entidade.Usuario;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.TupleType;
import java.util.ArrayList;
import java.util.List;
import jnr.ffi.Struct.String;

/**
 *
 * @author jose
 */
public class Repositorio {

    private Cluster cluster;

    public Repositorio() {

    }

    private Session init() {
        if (cluster == null) {
            cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        }
        return cluster.connect("atividade");
    }

    public void salvar(Usuario usuario) {
        Session session = init();
        java.lang.String insert = "INSERT INTO usuario (id, nome,telefone) VALUES (:id, :nome, :telefone)";
        PreparedStatement ps = session.prepare(insert);

        TupleType tupleType = cluster.getMetadata().newTupleType(DataType.cint(),
                DataType.text(), DataType.map(DataType.text(), DataType.ascii()));

        BoundStatement bs = ps.bind();
        bs.setInt("id", usuario.getId());
        bs.setString("nome", usuario.getNome());
        bs.setMap("telefone", usuario.getTelefone());
        session.execute(bs);
        fecharConexao(cluster, session);

    }

    public void upDate(Usuario usuario) {
        Session session = init();
        java.lang.String update = "UPDATE usuario id=:id, nome=:nome,telefone=:telefone WHERE id=:id";
        PreparedStatement ps = session.prepare(update);

        TupleType tupleType = cluster.getMetadata().newTupleType(DataType.cint(), DataType.text(), DataType.map(DataType.text(), DataType.ascii()));

        BoundStatement bs = ps.bind();
        bs.setInt("id", usuario.getId());
        bs.setString("nome", usuario.getNome());
        bs.setMap("telefone", usuario.getTelefone());
        session.execute(bs);
        fecharConexao(cluster, session);
    }

    public void delete(int id) {
        Session session = init();

        session.execute("delete FROM usuario where id =" + id);
        fecharConexao(cluster, session);

    }

    public List<Usuario> getTodos() {

        Session session = init();
        ResultSet results = session.execute("SELECT * FROM usuarios");
        List<Usuario> usarios = new ArrayList();

        for (Row row : results) {
            Usuario u = new Usuario(row.getInt("id"), row.getString("nome"), row.getMap("telefome", Integer.class, String.class));
            usarios.add(u);
        }
        fecharConexao(cluster, session);
        return usarios;
    }

    private void fecharConexao(Cluster cluster, Session session) {
        session.close();
        cluster.close();
    }

}
