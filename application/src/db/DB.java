package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static Connection conn = null; // Objeto de conexão singleton

    // Método para obter uma conexão com o banco de dados
    public static Connection getConnection() {
        if (conn == null) { // Verifica se a conexão já está estabelecida
            try {
                Properties props = loadProperties(); // Carrega as propriedades do arquivo
                String url = props.getProperty("dburl"); // Obtém a URL do banco de dados
                // Estabelece a conexão com o banco de dados usando a URL e as propriedades
                conn = DriverManager.getConnection(url, props);
            }
            catch (SQLException e) {
                // Lança uma exceção personalizada em caso de falha
                throw new DbException(e.getMessage());
            }
        }
        // Retorna a conexão estabelecida
        return conn;
    }

    // Método para fechar a conexão com o banco de dados
    public static void closeConnection() {
        if (conn != null) {
            try {
                // Fecha a conexão se estiver aberta
                conn.close();
            } catch (SQLException e) {
                // Lança uma exceção personalizada em caso de falha ao fechar
                throw new DbException(e.getMessage());
            }
        }
    }

    // Método para carregar as propriedades do banco de dados a partir de um arquivo
    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties(); // Cria um objeto Properties
            props.load(fs); // Carrega as propriedades a partir do fluxo de entrada
            return props; // Retorna as propriedades carregadas
        }
        catch (IOException e) {
            // Lança uma exceção personalizada em caso de erro de I/O
            throw new DbException(e.getMessage());
        }
    }

    // Método para fechar um Statement
    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close(); // Fecha o Statement se não for nulo
            } catch (SQLException e) {
                // Lança uma exceção personalizada em caso de falha ao fechar
                throw new DbException(e.getMessage());
            }
        }
    }

    // Método para fechar um ResultSet
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close(); // Fecha o ResultSet se não for nulo
            } catch (SQLException e) {
                // Lança uma exceção personalizada em caso de falha ao fechar
                throw new DbException(e.getMessage());
            }
        }
    }
}