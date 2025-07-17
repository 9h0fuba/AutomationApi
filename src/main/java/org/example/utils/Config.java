package org.example.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {

    private static final Dotenv dotenv = Dotenv.load();

    public static final String BASE_URL = dotenv.get("API_BASE_URL");
    public static final String TOKEN = dotenv.get("API_TOKEN");
}
