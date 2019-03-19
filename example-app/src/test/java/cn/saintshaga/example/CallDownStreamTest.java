package cn.saintshaga.example;

import cn.saintshaga.example.config.XRayConfiguration;
import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.entities.TraceID;
import com.amazonaws.xray.proxies.apache.http.HttpClientBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        XRayConfiguration.class
})
public class CallDownStreamTest {

    @Test
    public void callDownStream() throws Exception {
        TraceID traceID = new TraceID();
        System.out.println("TraceId:" + traceID.toString());
        AWSXRay.beginSegment("DynamoDb-test", traceID, null);
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().setRecorder(AWSXRay.getGlobalRecorder()).build();
            HttpGet httpGet = new HttpGet("http://localhost:8080/text");
            CloseableHttpResponse response = httpClient.execute(httpGet);
            try {
                byte[] bytes = new byte[100];
                response.getEntity().getContent().read(bytes);
                String content = new String(bytes);
                System.out.println(content);
                EntityUtils.consume(response.getEntity());
            } finally {
                response.close();
            }
        } finally {
            AWSXRay.endSegment();
        }

    }
}
