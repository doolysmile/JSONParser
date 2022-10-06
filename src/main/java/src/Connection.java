package src;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connection {

    private static Connection instance = null;

    private static String connType = "Content-Type";
    private static String connJson = "application/json";
    private static String connAuth = "Authorization";

    public static Connection getInstance() {
        if(instance == null){
            instance = new Connection();
        }
        return instance;
    }

    public String start(int problemId) {
        HttpURLConnection conn = null;
        JSONObject responseJson = null;
        String response = null;
        try{
            URL url = new URL(Global.BASE_RUL + Global.POST_START + "?problem=" + problemId);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("X-Auth-Token", Global.X_AUTH_TOKEN);
            conn.setRequestProperty(connType, connJson);

            int responseCode = conn.getResponseCode();

            if(responseCode == 200){
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while((line = br.readLine()) != null){
                    sb.append(line);
                }
                responseJson = new JSONObject(sb.toString());
                response = responseJson.getString("auth_key");
                System.out.println(responseJson);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public JSONObject locations() {
        HttpURLConnection conn = null;
        JSONObject responseJson = null;
        try{
            URL url = new URL(Global.BASE_RUL + Global.GET_LOCATIONS);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty(connAuth, TokenManager.getInstance().getToken());
            conn.setRequestProperty(connType, connJson);

            int responseCode = conn.getResponseCode();
            if(Global.checkResponse(String.valueOf(responseCode))){
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null){
                    sb.append(line);
                }
                responseJson = new JSONObject(sb.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return responseJson;
    }

    public JSONObject trucks() {
        HttpURLConnection conn = null;
        JSONObject responseJson = null;
        try{
            URL url = new URL(Global.BASE_RUL + Global.GET_TURCKS);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty(connAuth, TokenManager.getInstance().getToken());
            conn.setRequestProperty(connType, connJson);

            int responseCode = conn.getResponseCode();
            if(Global.checkResponse(String.valueOf(responseCode))){
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null){
                    sb.append(line);
                }
                responseJson = new JSONObject(sb.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return responseJson;
    }

    public JSONObject score() {
        HttpURLConnection conn = null;
        JSONObject responseJson = null;
        try{
            URL url = new URL(Global.BASE_RUL + Global.GET_SCORE);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty(connAuth, TokenManager.getInstance().getToken());
            conn.setRequestProperty(connType, connJson);

            int responseCode = conn.getResponseCode();
            if(Global.checkResponse(String.valueOf(responseCode))){
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null){
                    sb.append(line);
                }
                responseJson = new JSONObject(sb.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return responseJson;
    }

    public JSONObject simulate(JSONArray commandArrays) {
        HttpURLConnection conn = null;
        JSONObject responseJson = null;
        try{
            URL url = new URL(Global.BASE_RUL + Global.PUT_SIMULATE);
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("PUT");
            conn.setRequestProperty(connAuth, TokenManager.getInstance().getToken());
            conn.setRequestProperty(connType, connJson);
            conn.setDoOutput(true);
            conn.setDoInput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            JSONObject commands = new JSONObject();
            commands.put("commands", commandArrays);
            System.out.println("htet");
            System.out.println(commands.toString());
            bw.write(commands.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();
            String res = conn.getResponseMessage();
            System.out.println(res);
            System.out.println(responseCode);

            if(Global.checkResponse(String.valueOf(responseCode))){
                System.out.println("hello");
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null){
                    sb.append(line);
                }
                responseJson = new JSONObject(sb.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(responseJson);
        return responseJson;
    }
}
