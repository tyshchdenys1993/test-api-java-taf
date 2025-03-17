package api_services;

import config.EndpointsConfig;
import config.WebConfig;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.NoArgsConstructor;
import pojos.response.users.AllUsersResponseBody;
import pojos.request.CreateUserRequestBody;
import pojos.response.users.CreateUserResponseBody;
import pojos.response.users.UserResponseBody;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserApi {

    private static final UserApi userApi = new UserApi();

    static UserApi getInstance() {
        return userApi;
    }

    private RequestSpecification userApiSpec = new RequestSpecBuilder()
            .setBaseUri(WebConfig.WEB_CONFIG.getBaseURI())
            .setAccept("application/json")
            .build();


    public AllUsersResponseBody getAllUsers() {
        return RestAssured.given()
                .spec(userApiSpec)
                .basePath(EndpointsConfig.ENDPOINTS_CONFIG.getAllUsersEndpoint())
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(AllUsersResponseBody.class);
    }

    public UserResponseBody getUserById(int userId) {
        return RestAssured.given()
                .spec(userApiSpec)
                .basePath(EndpointsConfig.ENDPOINTS_CONFIG.getUserByIdEndpoint())
                .pathParam("userId", userId)
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(UserResponseBody.class);
    }

    public CreateUserResponseBody createNewUser(CreateUserRequestBody createUserRequestBody) {
        return RestAssured.given()
                .spec(userApiSpec)
                .basePath(EndpointsConfig.ENDPOINTS_CONFIG.getAllUsersEndpoint())
                .body(createUserRequestBody)
                .post()
                .then()
                .statusCode(201)
                .extract()
                .response()
                .as(CreateUserResponseBody.class);
    }
}
