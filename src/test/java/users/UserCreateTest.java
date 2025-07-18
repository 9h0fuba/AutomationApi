package users;

import baseApi.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.hamcrest.core.AnyOf;
import org.testng.annotations.Test;

public class UserCreateTest extends BaseApiTest {
    @Test(description = "Verify POST /users returns 201 and valid user created")
    @Severity(SeverityLevel.NORMAL)
    @Epic("User Management")
    @Feature("Create User")
    public void testCreateUserWithValidData() {
        String email = "ghofuba" + System.currentTimeMillis() + "@example.com";

        String requestBody = String.format("""
        {
          "name": "Ghofur Bahtiar",
          "email": "%s",
          "gender": "male",
          "status": "active"
        }
        """, email);

        RestAssured
                .given().spec(requestSpecification)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("name", Matchers.equalTo("Ghofur Bahtiar"))
                .body("email", Matchers.equalTo(email))
                .body("gender", Matchers.equalTo("male"))
                .body("status", Matchers.equalTo("active"));
    }

    @Test(description = "Verify POST /users returns 422 and missing request field")
    @Severity(SeverityLevel.NORMAL)
    @Epic("User Management")
    @Feature("Create User")
    public void testCreateUserWithMissingField() {
        String requestBody = """
        {
          "email": "bob.missing@example.com",
          "gender": "male"
        }
        """;

        RestAssured
                .given().spec(requestSpecification)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(422)
                .body("[0].field", Matchers.equalTo("name"));
    }

    @Test(description = "Verify POST /users returns 422 and invalid request body email")
    @Severity(SeverityLevel.NORMAL)
    @Epic("User Management")
    @Feature("Create User")
    public void testCreateUserWithInvalidEmail() {
        String requestBody = """
        {
          "name": "Invalid Email",
          "email": "invalid-email",
          "gender": "male",
          "status": "active"
        }
        """;

        RestAssured
                .given().spec(requestSpecification)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(422)
                .body("[0].field", Matchers.equalTo("email"));
    }

//    @Test
//    public void testCreateUserSQLInjection() {
//        String requestBody = """
//        {
//          "name": "Robert'); DROP TABLE users;--",
//          "email": "sqlinject@example.com",
//          "gender": "male",
//          "status": "active"
//        }
//        """;
//
//        RestAssured
//                .given().spec(requestSpecification)
//                .body(requestBody)
//                .when()
//                .post("/users")
//                .then()
//                .statusCode(AnyOf.anyOf(Matchers.equalTo(201), Matchers.equalTo(422))) // Ensure it is handled safely
//                .body("name", Matchers.equalTo("Robert'); DROP TABLE users;--"));
//    }
}
