import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import sun.net.www.http.HttpClient;

import java.util.ArrayList;

/**
 * Created by duq on 2017/4/25.
 */
public class TestHttpClinet {
    @Test
    public void test(){
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("http://127.0.0.1:8080/charge/server/sync");
    }
}
