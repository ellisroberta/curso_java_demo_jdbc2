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
                conn = DriverManager.getConnection(url, props); // Estabelece a conexão
            }
            catch (SQLException e) {
                throw new DbException(e.getMessage()); // Trata exceções SQL
            }
        }
        return conn; // Retorna a conexão estabelecida
    }

    // Método para fechar a conexão com o banco de dados
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close(); // Fecha a conexão
            } catch (SQLException e) {
                throw new DbException(e.getMessage()); // Trata exceções durante o fechamento
            }
        }
    }

    // Método para carregar as propriedades do banco de dados a partir de um arquivo
    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            Properties props = new Properties();
            props.load(fs); // Carrega as propriedades a partir do fluxo de entrada
            return props; // Retorna as propriedades
        }
        catch (IOException e) {
            throw new DbException(e.getMessage()); // Trata exceções de entrada/saída de arquivo
        }
    }

    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }
}