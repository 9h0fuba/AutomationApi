package org.example.utils;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import java.io.File;


public class JsonSchemaValidatorUtil {

    public static void validateResponseSchema(Response response, String schemaFileName) {
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File("src/main/resources/schemas/" + schemaFileName)));
    }
}
