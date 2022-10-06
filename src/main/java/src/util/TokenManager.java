package src.util;

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

        if(response.equals("400")){
            System.out.println("400 :: parameter Err ");
        } else if (response.equals("401")) {
            System.out.println("401 :: X-auth-token Err");
        } else if (response.equals("500")) {
            System.out.println("500 :: server err");
        } else {
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
