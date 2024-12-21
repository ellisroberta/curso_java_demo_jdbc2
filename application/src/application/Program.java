package application;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {

    public static void main(String[] args) {
        // Declara variáveis para conexão, instrução e conjunto de resultados
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            // Obtém uma conexão com o banco de dados
            conn = DB.getConnection();

            // Cria um objeto Statement para enviar instruções SQL ao banco de dados
            st = conn.createStatement();

            // Executa uma consulta SQL e armazena o resultado em rs
            rs = st.executeQuery("select * from department");

            // Itera sobre o conjunto de resultados retornado
            while (rs.next()) {
                // Imprime o Id e o Name de cada departamento
                System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
            }
        }
        catch (SQLException e) {
            // Trata exceções SQL imprimindo a stack trace
            e.printStackTrace();
        }
        finally {
            // Garante que os recursos sejam fechados ao final da operação
            DB.closeResultSet(rs); // Fecha o ResultSet
            DB.closeStatement(st); // Fecha o Statement
            DB.closeConnection(); // Fecha a conexão com o banco de dados
        }
    }
}