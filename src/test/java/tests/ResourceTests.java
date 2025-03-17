package tests;

import api_services.ReqresService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojos.response.resources.AllResourcesResponseBody;
import pojos.response.resources.ResourceData;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
public class ResourceTests extends BaseTest {

    @Test
    @DisplayName("Get All resources")
    public void getAllResourceTest() {
        log.info("Get All resource");

        AllResourcesResponseBody allResource = ReqresService.REQRES_SERVICE.getResourceApi().getAllResources();

        String colorName = allResource.getData()
                .stream()
                .filter(resourceData -> resourceData.getColor().equals("#C74375"))
                .findFirst()
                .map(ResourceData::getName)
                .orElseThrow(() -> new RuntimeException("Color is not exist"));

        assertThat(colorName)
                .as("ColorName is wrong")
                .isEqualTo("fuchsia rose");
    }
}
