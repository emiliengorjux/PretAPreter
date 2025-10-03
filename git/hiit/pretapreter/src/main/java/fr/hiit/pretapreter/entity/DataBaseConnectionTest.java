package fr.hiit.pretapreter.entity;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseConnectionTest {
    @Component
    public class DatabaseConnectionTest implements CommandLineRunner {

        private final DataSource dataSource;

        public DatabaseConnectionTest(DataSource dataSource) {
            this.dataSource = dataSource;
        }


        @Bean
        public CommandLineRunner testDatabaseConnection(DataSource dataSource) {
            return args -> {
                try (Connection connection = dataSource.getConnection()) {
                    System.out.println("Connexion à la base de données réussie !");
                    System.out.println("URL: " + connection.getMetaData().getURL());
                } catch (SQLException e) {
                    System.err.println("Erreur de connexion à la base de données : " + e.getMessage());
                    e.printStackTrace();
                }
            };
        }

        @Override
        public void run(String... args) throws Exception {

        }
    }

}

