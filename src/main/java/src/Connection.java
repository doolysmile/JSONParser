package src;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
}
