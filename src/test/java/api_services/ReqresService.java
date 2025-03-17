package api_services;

import lombok.Getter;

@Getter
public enum ReqresService {
    REQRES_SERVICE;

    private final UserApi userApi = UserApi.getInstance();
    private final ResourceApi resourceApi = ResourceApi.getInstance();
}
