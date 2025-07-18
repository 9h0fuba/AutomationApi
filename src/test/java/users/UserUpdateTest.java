package users;

import baseApi.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class UserUpdateTest extends BaseApiTest {

    @Test(description = "Verify PUT /users/id returns 200 and valid user updated")
    @Severity(SeverityLevel.NORMAL)
    @Epic("User Management")
    @Feature("Update User")
    public void testUpdateUserWithValidData() {
        String requestBody = """
        {
            "name": "Ghofur Bahtiar",
            "email": "ghofuba%d@example.com",
            "gender": "male",
            "status": "active"
        }
        """.formatted(System.currentTimeMillis());

        int userId = RestAssured
                .given().spec(requestSpecification)
                .body(requestBody)
                .when().post("/users")
                .then().statusCode(201)
                .extract().path("id");

        RestAssured
                .given().spec(requestSpecification)
                .body(requestBody)
                .when()
                .put("/users/" + userId)
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo("Ghofur Bahtiar"));
    }

    @Test(description = "Verify PUT /users/id returns 404 and invalid body request email")
    @Severity(SeverityLevel.NORMAL)
    @Epic("User Management")
    @Feature("Update User")
    public void testUpdateUserWithInvalidEmail() {
        String invalidEmailRequest = """
        {
            "email": "Invalid-email"
        }""";

        String requestBody = """
        {
            "name": "John Doe",
            "email": "johndoe%d@example.com",
            "gender": "male",
            "status": "active"
        }
        """.formatted(System.currentTimeMillis());

        int userId = RestAssured
                .given().spec(requestSpecification)
                .body(requestBody)
                .when().post("/users")
                .then().statusCode(201)
                .extract().path("id");

        RestAssured
                .given().spec(requestSpecification)
                .body(invalidEmailRequest)
                .when()
                .put("/users/" + userId)
                .then()
                .statusCode(422)
                .body("[0].field", Matchers.equalTo("email"))
                .body("[0].message", Matchers.equalTo("is invalid"));
    }
}

