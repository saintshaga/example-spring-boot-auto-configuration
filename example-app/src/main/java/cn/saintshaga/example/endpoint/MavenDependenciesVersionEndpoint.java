package cn.saintshaga.example.endpoint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Component
@Endpoint(id="versions")
public class MavenDependenciesVersionEndpoint {

    @Autowired(required = false)
    private List<Dependency> dependencies;

    @ReadOperation
    public List<Dependency> all() {
        return CollectionUtils.isEmpty(dependencies) ? Collections.emptyList() : dependencies;
    }

    @ReadOperation
    public Dependency version(@Selector String groupId, @Selector String artifactId) {
        if("worksap".equalsIgnoreCase(groupId)) {
            groupId = "com.worksap.company";
        }
        if(!CollectionUtils.isEmpty(dependencies)) {
            for(Dependency dependency : dependencies) {
                if(dependency.getGroupId().equals(groupId)
                        && dependency.getArtifactId().equals(artifactId)) {
                    return dependency;
                }
            }
        }
        return getDependency(groupId, artifactId);
    }

    private Dependency getDependency(String groupId, String artifactId) {
        Dependency dependency = new Dependency(groupId, artifactId, "Unkown version, or dependency not existed!");
        String filePath = "/META-INF/maven/" + groupId + "/" + artifactId + "/pom.properties";
        InputStream in = getClass().getResourceAsStream(filePath);
        if(in == null) {
            return dependency;
        }
        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            return dependency;
        }
        dependency.setVersion(properties.getProperty("version"));
        return dependency;
    }



    @Data
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class Dependency {
        private String groupId;
        private String artifactId;
        private String version;
    }

}
