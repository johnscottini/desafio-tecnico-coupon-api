package com.desafio.coupon.api.database;

public final class TestDatabaseConfiguration {

    private TestDatabaseConfiguration() {
    }

    public static String buildUrl(String databaseName) {
        return "jdbc:h2:mem:" + databaseName + ";" +
                "DB_CLOSE_DELAY=-1;" +
                "MODE=PostgreSQL;" +
                "NON_KEYWORDS=VALUE,RULE,ENABLED,KEY";
    }
}
