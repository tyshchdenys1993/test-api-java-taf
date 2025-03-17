package api_services;

import config.EndpointsConfig;
import config.WebConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.NoArgsConstructor;
import pojos.response.resources.AllResourcesResponseBody;

import static io.restassured.RestAssured.given;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class ResourceApi {

    private static final ResourceApi resourceApi = new ResourceApi();

    static ResourceApi getInstance(){
        return resourceApi;
    }

    private RequestSpecification resourceApiSpec = new RequestSpecBuilder()
            .setBaseUri(WebConfig.WEB_CONFIG.getBaseURI())
            .setAccept("application/json")
            .build();

    public AllResourcesResponseBody getAllResources(){
        return given().spec(resourceApiSpec)
                .basePath(EndpointsConfig.ENDPOINTS_CONFIG.getAllResourcesEndpoint())
                .get()
                .then()
                .statusCode(200)
                .extract()
                .response()
                .as(AllResourcesResponseBody.class);
    }
}
