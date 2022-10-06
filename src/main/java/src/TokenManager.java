package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class TokenManager {

    private static TokenManager instance = null;
    private String token = "";

    private String fileName = "D:\\KCH\\aboutJava\\kako\\JSONParser\\src\\main\\java\\res\\token";

    public static TokenManager getInstance() {
        if (instance == null) {
            instance = new TokenManager();
        }
        return instance;
    }

    public synchronized String createToken(int problemId){
        String token = null;
        String response = Connection.getInstance().start(problemId);


        if(Global.checkResponse(response)){
            saveTokenFile(response);
            token = response;
            response = "200";
        }

        this.token = token;
        return response;
    }
    private void saveTokenFile(String token){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
            bw.write(token);
            bw.flush();

            bw.close();
        } catch (Exception e) {
            System.out.println("token write err");
            e.printStackTrace();
        }
    }

    public String getToken() {
        return this.token;
    }
}
