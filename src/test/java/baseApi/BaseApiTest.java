package baseApi;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.example.utils.Config;
import org.testng.annotations.BeforeClass;

public class BaseApiTest {

    public static RequestSpecification requestSpecification ;
    @BeforeClass
    public void setup(){
        RestAssured.baseURI = Config.BASE_URL;

        requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + Config.TOKEN)
                .setContentType("application/json")
                .build();
    }
}
