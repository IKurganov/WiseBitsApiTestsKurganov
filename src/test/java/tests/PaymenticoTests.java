package tests;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import utils.SpecsBase;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class PaymenticoTests {
    // положил тесты в один класс, но можно было бы разнести в классы по эндпоинту

    SpecsBase specs = new SpecsBase();

    // 1 - Успешная регистрация пользователя с указанием всех необходимых данных
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

    // 2 - Успешное получение корректных данных о зарегистрированных ранее пользователях
    @Test
    public void checkGettingInfoAboutUsers() {

        specs.getUsers()
                .then()
                .log().all()
                .statusCode(200)
                .body(notNullValue());

//        + к кейсам можно добавить проверку на идемпотентность метода GET
//        List<User> users = specs.getUsers()
//                .then()
//                .log().all()
//                .extract().body().jsonPath().getList("$", User.class);
//
    }

    @Test
    public void checkUniquenessOfUsersRegistration() {

        String nonUniqueUsername = "kvakva2";
        String nonUniqueEmail = "dsada@test.com";
        String password = new Faker().internet().password();

        specs.createUser(nonUniqueUsername, nonUniqueEmail, password)
                .then()
                .log().all()
                .statusCode(400)
                .body("message[0]", is("This username is taken. Try another."))  // текст взят из ответа
                .body("success", is(false));
    }
}
