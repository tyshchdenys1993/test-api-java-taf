package pojos.response.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pojos.response.users.Support;
import pojos.response.users.UserData;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllResourcesResponseBody {
    private int page;
    @JsonProperty("per_page")
    private int perPage;
    private int total;
    @JsonProperty("total_pages")
    private int totalPages;
    private List<ResourceData> data;
    private Support support;
}
