package com.desafio.coupon.api.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public abstract class RepositoryBaseTest {

    @PersistenceContext
    protected EntityManager context;

    protected EasyRandom random = new EasyRandom();

    static {
        System.setProperty("user.timezone", "UTC");
    }

    @DynamicPropertySource
    static void configureDatasource(DynamicPropertyRegistry registry) {
        String databaseName = "coupon-api-test-" + UUID.randomUUID();
        registry.add("spring.datasource.url", () -> TestDatabaseConfiguration.buildUrl(databaseName));
        registry.add("spring.datasource.driver-class-name", () -> "org.h2.Driver");
        registry.add("spring.datasource.username", () -> "sa");
        registry.add("spring.datasource.password", () -> "");
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
        registry.add("spring.jpa.show-sql", () -> "false");
    }

    protected void insert(String tableName, String id) {
        var query = String.format("INSERT INTO %s (ID) VALUES('%s');", tableName, id);
        context.createNativeQuery(query).executeUpdate();
    }
}
