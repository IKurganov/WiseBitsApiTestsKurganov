import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class SpecsBase {
    private RequestSpecification spec;

    private static final String BASE_URI = "http://3.145.97.83:3333/";

    public SpecsBase() {
        spec = given()
                .baseUri(BASE_URI);
    }

    public Response createUser(String username, String email, String password) {
        return given(spec)
                .contentType(ContentType.URLENC)
                .with()
                .formParam("username", username)
                .formParam("email", email)
                .formParam("password",password)
                .log().all()
                .when()
                .post("user/create");
    }

    public Response getUsers() {
        return given(spec)
                .with()
                .log().all()
                .when()
                .get("user/get");
    }
}
