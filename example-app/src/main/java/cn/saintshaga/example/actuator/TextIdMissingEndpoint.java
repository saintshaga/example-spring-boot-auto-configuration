package cn.saintshaga.example.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by huang on 19-1-15.
 */
@Component
@Endpoint(id = "missingTextIds")
public class TextIdMissingEndpoint {

    private Map<String, String> missingTextIds = new ConcurrentHashMap<>();

    @ReadOperation
    public Map<String, String> missingText() {
        missingTextIds.put("test", "test");
        return missingTextIds;
    }
}
