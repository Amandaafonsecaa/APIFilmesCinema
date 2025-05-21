package unifor.br.cinemafilme;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class CinemafilmeApplication {

    public static void main(String[] args) {
        // Carrega as variáveis do arquivo .env
        Dotenv dotenv = Dotenv.load();

        // Define a variável de sistema para o Spring usar no application.properties
        System.setProperty("MONGODB_URI", dotenv.get("MONGODB_URI"));

        // Inicia a aplicação
        var context = SpringApplication.run(CinemafilmeApplication.class, args);

        // Testa a conexão com o MongoDB
        try {
            MongoTemplate mongoTemplate = context.getBean(MongoTemplate.class);
            mongoTemplate.getDb().getName(); // Teste simples
            System.out.println("Conectado ao MongoDB - Banco: " + mongoTemplate.getDb().getName());
        } catch (Exception e) {
            System.err.println("Não conectado ao MongoDB: " + e.getMessage());
        }
    }
}
