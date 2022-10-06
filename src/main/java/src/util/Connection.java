package src.util;

import org.json.JSONArray;
import org.json.JSONObject;
import src.Global;

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
                responseJson = getJsonObject(conn);
                response = responseJson.getString("auth_key");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(responseJson);
        return response;
    }

    public JSONObject waitingLines() {
        HttpURLConnection conn = null;
        JSONObject responseJson = null;

        try{
            URL url = new URL(Global.BASE_RUL + Global.GET_WAITING);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty(connAuth, TokenManager.getInstance().getToken());
            conn.setRequestProperty(connType, connJson);

            int responseCode = conn.getResponseCode();

            if(Global.checkResponse(String.valueOf(responseCode))){
                responseJson = getJsonObject(conn);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseJson;
    }

    public JSONObject match(JSONArray matchJSONArray) {
        HttpURLConnection conn = null;
        JSONObject responseJson = null;

        try{
            URL url = new URL(Global.BASE_RUL + Global.PUT_MATCH);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty(connAuth, TokenManager.getInstance().getToken());
            conn.setRequestProperty(connType, connJson);
            conn.setDoOutput(true);
            conn.setDoInput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            JSONObject pairs = new JSONObject();
            System.out.println(matchJSONArray);
            pairs.put("pairs", matchJSONArray);
            bw.write(pairs.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();

            if(Global.checkResponse(String.valueOf(responseCode))){
                responseJson = getJsonObject(conn);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(responseJson);
        return responseJson;
    }

    private JSONObject getJsonObject(HttpURLConnection conn) throws IOException {
        JSONObject responseJson;
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = "";
        while((line = br.readLine()) != null){
            sb.append(line);
        }
        responseJson = new JSONObject(sb.toString());
        return responseJson;
    }


}
