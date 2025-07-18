package users;
import baseApi.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class UserDeleteTest extends BaseApiTest {

    @Test(description = "Verify DELETE /users/id returns 204 and valid user deleted")
    @Severity(SeverityLevel.NORMAL)
    @Epic("User Management")
    @Feature("Delete User")
    public void testDeleteUserValidId() {
        String requestBody = """
        {
            "name": "John Doe",
            "email": "johndoe%d@example.com",
            "gender": "male",
            "status": "active"
        }
        """.formatted(System.currentTimeMillis());

        int createdUserId = RestAssured
                .given().spec(requestSpecification)
                .body(requestBody)
                .when().post("/users")
                .then().statusCode(201)
                .extract().path("id");

        RestAssured
                .given().spec(requestSpecification)
                .when().delete("/users/" + createdUserId)
                .then().statusCode(204);
    }

    @Test(description = "Verify DELETE /users/id returns 204 and valid user deleted")
    @Severity(SeverityLevel.NORMAL)
    @Epic("User Management")
    @Feature("Delete User")
    public void testDeleteUserInvalidId() {
        int userId = 99999999;

        RestAssured
                .given().spec(requestSpecification)
                .when()
                .delete("/users/" + userId)
                .then()
                .statusCode(404);
    }
}

