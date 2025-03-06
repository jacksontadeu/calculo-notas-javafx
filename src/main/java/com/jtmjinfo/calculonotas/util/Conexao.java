package com.jtmjinfo.calculonotas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexao {
    public static void obterConexao() {
        // connection string
        var url = "jdbc:sqlite:notas.db";

        try (var conn = DriverManager.getConnection(url)) {
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public static void fecharConexao(Connection connection) throws SQLException{
        if (!connection.isClosed() && connection != null){
            connection.close();
            System.out.println("sucesso");
        }



    }



}
