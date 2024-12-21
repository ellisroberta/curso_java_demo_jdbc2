package db;

public class DbException extends RuntimeException {

    private static final long serialVersionUID = 1L; // Número de versão serial

    public DbException(String message) {
        super(message); // Chama o construtor da superclasse com a mensagem
    }
}

/**
 * serialVersionUID: Este campo é utilizado para identificar a versão de uma classe durante a serialização.
 Quando uma classe é serializada, o serialVersionUID é gravado junto com os dados. Se a classe for modificada (por exemplo,
 adicionando ou removendo campos), o serialVersionUID deve ser alterado para indicar que a versão da classe mudou.
 Isso ajuda a evitar problemas de compatibilidade ao tentar desserializar um objeto que foi salvo com uma versão diferente da classe.

 * Construtor: O construtor da classe chama o construtor da classe pai (RuntimeException) passando a mensagem de erro.
 Isso permite que a exceção carregue uma descrição do erro que pode ser útil para depuração.

 * RuntimeException: RuntimeException é uma exceção que pode ser lançada em tempo de execução.

 * A classe DbException é uma subclasse de RuntimeException, o que significa que é uma exceção não verificada.

 * Isso permite que os métodos que lançam essa exceção não precisem declará-la na lista de exceções.
 **/