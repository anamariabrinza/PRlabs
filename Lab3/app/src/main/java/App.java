/**
 * Created by Amy on 5/17/2017.
 */
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class App {


    public static void main(String[] args) throws Exception {

        App http = new App();

        System.out.println(" Http GET request");
        http.sendGet();

        System.out.println("\n Http HEAD request");
        http.sendHead();

        System.out.println("\n Send Http POST request");
        http.sendPost();
    }

    // HTTP GET request
    private void sendGet() throws Exception {


        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://httpbin.org/ip");


        HttpResponse response = httpClient.execute(request);
        // Get the response
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        //StringBuffer result = new StringBuffer();

        String line = "";
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }



    }

    // HTTP HEAD request
    private void sendHead() throws Exception {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpHead request = new HttpHead("http://httpbin.org/headers");

        HttpResponse response = httpClient.execute(request);


        Header[] headers = response.getAllHeaders();
        for (Header header : headers) {
            System.out.println("  -> " + header.getName() + ":" + header.getValue());
        }
    }

    // HTTP POST request
    private void sendPost() throws Exception {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://httpbin.org/post");


        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("name", "Ana-Maria"));
        urlParameters.add(new BasicNameValuePair("username", "anamariab"));
        urlParameters.add(new BasicNameValuePair("num", "12345"));


        request.setEntity(new UrlEncodedFormEntity(urlParameters));

        HttpResponse response = httpClient.execute(request);


        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        String line = "";
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }
    }


}