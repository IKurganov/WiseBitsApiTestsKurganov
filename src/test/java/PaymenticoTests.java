import com.github.javafaker.Faker;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.is;

public class PaymenticoTests {
    SpecsBase specs = new SpecsBase();

    @Test
    public void checkRegistrationOfUser() {

        String username = new Faker().name().username();
        String email = new Faker().internet().emailAddress();
        String password = new Faker().internet().password();

        specs.createUser(username, email, password)
                .then()
                .log().all()
                .statusCode(200)
                .body("message", is("User Successully created"))  // текст взят из ответа
                .body("details.username", is(username))
                .body("details.email", is(email));
    }
}
