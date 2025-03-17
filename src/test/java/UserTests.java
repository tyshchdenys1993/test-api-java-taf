import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pojos.request.CreateUserRequestBody;
import pojos.response.AllUsersResponseBody;
import pojos.response.CreateUserResponseBody;
import pojos.response.UserData;
import pojos.response.UserResponseBody;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static api_services.ReqresService.REQRES_SERVICE;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class UserTests extends BaseTest {

    @Test
    @DisplayName("Get all users count and verify total")
    public void getAllUsersTest() {
        log.info("Get all users by api");
        AllUsersResponseBody allUsersResponseBody =  REQRES_SERVICE.getUserApi().getAllUsers();

        assertThat(allUsersResponseBody.getTotal())
                .as("Total user count is wrong")
                .isEqualTo(12);
    }

    @Test
    @Tag("denys")
    @DisplayName("Get user by specific ID")
    public void getUserByIdTest(){
        log.info("Get user email");
        String expectedUserEmail = REQRES_SERVICE.getUserApi()
                .getAllUsers()
                .getData()
                .stream()
                .filter(userName -> userName.getFirstName().equals("Janet"))
                .findFirst()
                .map(UserData::getEmail)
                .orElseThrow(() -> new RuntimeException("User nor found"));

        log.info("Get user with specific ID");
        UserResponseBody userResponseBody = REQRES_SERVICE.getUserApi().getUserById(2);

        assertThat(userResponseBody.getData().getEmail())
                .isEqualTo(expectedUserEmail);
    }

    @Test
    @DisplayName("Create new user")
    public void createNewUserTest(){
        String todayDate = ZonedDateTime
                .now(ZoneId.of(ZoneOffset.UTC.getId()))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        log.info("Create New user");
        CreateUserRequestBody userBody = CreateUserRequestBody.builder()
                .name("morpheus")
                .job("leader")
                .build();
        CreateUserResponseBody newUser = REQRES_SERVICE.getUserApi().createNewUser(userBody);
        assertThat(newUser.getCreatedAt())
                .as("Today date is wrong")
                .contains(todayDate);
    }
}
