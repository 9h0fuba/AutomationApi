package users;

import baseApi.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class UserGetTest extends BaseApiTest {

    @Test(description = "Verify GET /users returns 200 and valid user list")
    @Severity(SeverityLevel.NORMAL)
    @Epic("User Management")
    @Feature("Get User")
    public void testGetUsers(){
        RestAssured
                .given().spec(requestSpecification)
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("size()", Matchers.greaterThan(0));
    }

    @Test(description = "Verify GET /users/id returns 200 and valid user")
    @Severity(SeverityLevel.NORMAL)
    @Epic("User Management")
    @Feature("Get User")
    public void testGetUserByIdValid() {
        int userId = 8012861;

        RestAssured
                .given().spec(requestSpecification)
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(userId));
    }

    @Test(description = "Verify GET /users/id returns 404 and invalid user id")
    @Severity(SeverityLevel.NORMAL)
    @Epic("User Management")
    @Feature("Get User")
    public void testGetUserByIdInvalid() {
        int userId = 99999999;

        RestAssured
                .given().spec(requestSpecification)
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(404);
    }
}
