package com.jtmjinfo.calculonotas.dao;


import com.jtmjinfo.calculonotas.model.Nota;
import com.jtmjinfo.calculonotas.repository.INotaRepository;
import com.jtmjinfo.calculonotas.util.Conexao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotaDao implements INotaRepository {


    @Override
    public void cadastrarNota(Nota nota) {
        try{
            String sql = "insert into nota (p1,trabalho1,trabalho2,media,api,pontosextras,sub,mediafinal,nomealuno) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = Conexao.obterConexao().prepareStatement(sql);
            ps.setDouble(1,nota.getP1());
            ps.setDouble(2,nota.getTrabalho1());
            ps.setDouble(3,nota.getTrabalho2());
            ps.setDouble(4,nota.getMedia());
            ps.setDouble(5,nota.getApi());
            ps.setDouble(6,nota.getPontosExtras());
            ps.setDouble(7,nota.getSub());
            ps.setDouble(8,nota.getMediaFinal());
            ps.setString(9,nota.getNome());

            ps.execute();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Nota> buscarTodos() {
        List<Nota> notas = new ArrayList<>();
        Nota nota = null;

        try{
            String sql = "select * from nota ";
            PreparedStatement ps = Conexao.obterConexao().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                nota = new Nota();

               nota.setNome(rs.getString("nomealuno"));
               nota.setP1(rs.getDouble("p1"));
               nota.setApi(rs.getDouble("api"));
               nota.setTrabalho1(rs.getDouble("trabalho1"));
               nota.setTrabalho2(rs.getDouble("trabalho2"));
               nota.setMedia(rs.getDouble("media"));
               nota.setMediaFinal(rs.getDouble("mediafinal"));
               nota.setPontosExtras(rs.getDouble("pontosextras"));
               nota.setSub(rs.getDouble("sub"));
               nota.setId(rs.getInt("id"));


               notas.add(nota);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return notas;
    }
    @Override
    public Nota buscarPorId(int id) {
        Nota nota = null;
        try{
            String sql = "select * from nota where id=?";
            PreparedStatement ps = Conexao.obterConexao().prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                nota= new Nota();
                nota.setNome(rs.getString("nomealuno"));
                nota.setP1(rs.getDouble("p1"));
                nota.setApi(rs.getDouble("api"));
                nota.setTrabalho1(rs.getDouble("trabalho1"));
                nota.setTrabalho2(rs.getDouble("trabalho2"));
                nota.setMedia(rs.getDouble("media"));
                nota.setMediaFinal(rs.getDouble("mediafinal"));
                nota.setPontosExtras(rs.getDouble("pontosextras"));
                nota.setSub(rs.getDouble("sub"));
                nota.setId(rs.getInt("id"));

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nota;
    }

    @Override
    public void editarNota(Nota nota,int id) {
        try{
            String sql = "update nota set nomealuno=?, p1 =?, trabalho1=?,trabalho2=?," +
                    "media=?,api=?,sub=?,pontosextras=?, mediafinal=? where id=?";
            PreparedStatement ps= Conexao.obterConexao().prepareStatement(sql);
            ps.setString(1,nota.getNome());
            ps.setDouble(2,nota.getP1());
            ps.setDouble(3,nota.getTrabalho1());
            ps.setDouble(4,nota.getTrabalho2());
            ps.setDouble(5,nota.getMedia());
            ps.setDouble(6,nota.getApi());
            ps.setDouble(7,nota.getSub());
            ps.setDouble(8,nota.getPontosExtras());
            ps.setDouble(9,nota.getMediaFinal());
            ps.setInt(10,id);
            ps.executeUpdate();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void deletarNota(int id) {
        try{
            String sql = "delete from nota where id  =?";
            PreparedStatement ps= Conexao.obterConexao().prepareStatement(sql);

            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
