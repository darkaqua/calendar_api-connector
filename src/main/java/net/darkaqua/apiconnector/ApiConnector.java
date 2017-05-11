package net.darkaqua.apiconnector;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Pablo on 22/04/17.
 */
public class ApiConnector {

    private String url;

    private String client_id;
    private String client_token;

    public ApiConnector(String ip, int port){
        this.url = "http://" + ip + ":" + port + "/";
    }

    public void auth(String client_id, String client_token){
        this.client_id = client_id;
        this.client_token = client_token;
    }

    public void GET(String path, JSONObject jsonObject, Request request){
        doRequest("GET", path, jsonObject, request);
    }

    public void POST(String path, JSONObject jsonObject, Request request){
        doRequest("POST", path, jsonObject, request);
    }

    public void DELETE(String path, JSONObject jsonObject, Request request){
        doRequest("DELETE", path, jsonObject, request);
    }

    public void PUT(String path, JSONObject jsonObject, Request request){
        doRequest("PUT", path, jsonObject, request);
    }

    private void doRequest(final String method, final String path, final JSONObject jsonObject, final Request request){
        new Thread(new Runnable() {
            public void run() {
                try {
                    String params = jsonObject == null || jsonObject.length() == 0 ? null : jsonObject.toString();

                    request.Response(getRequest(path, method, params));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private Object getRequest(String path, String method, String params) throws Exception{
        URL url = new URL(this.url + path);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        System.out.println(url.toString());
        if(client_id != null && client_token != null){
            conn.setRequestProperty("client_id", client_id);
            conn.setRequestProperty("client_token", client_token);
        }
        if(params != null){
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            OutputStreamWriter wr= new OutputStreamWriter(conn.getOutputStream());
            wr.write(params);
            wr.flush();
            wr.close();
        }

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        StringBuilder result = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        String strResult = result.toString();

        return strResult.startsWith("{") ? new JSONObject(strResult) : new JSONArray(strResult);
    }

}