package cn.forgotdream.httpdns.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpParser {
    private String address;
    public HttpParser(String address){
        this.address = address;
    }
    public String GetIP(){
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://119.29.29.29/d?dn=" + address);
            System.out.println(httpGet.toString());
            response = httpClient.execute(httpGet);
            System.out.println(response.toString());
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity).split(";")[0];
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != response){
                try {
                    response.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            if (null != httpClient){
                try {
                    httpClient.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
