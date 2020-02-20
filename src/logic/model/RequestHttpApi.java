package logic.model;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestHttpApi {

    public String sendRequest(String url) throws IOException {
		OkHttpClient client = new OkHttpClient();
    	Request request = new Request.Builder()
    			.url(url)
    			.build();

    	Response response = client.newCall(request).execute();
    	
    	return response.body().string();
    }

}
