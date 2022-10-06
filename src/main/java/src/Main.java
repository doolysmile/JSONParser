package src;

import org.json.JSONObject;
import src.data.Pairs;
import src.data.UserInfo;
import src.data.WaitingLine;
import src.util.Connection;
import src.util.JSONParser;
import src.util.TokenManager;

import java.util.ArrayList;

public class Main {

    private static JSONParser jsonParser = new JSONParser();
    public static void main(String[] args) {
        int problemId = 1;

        String response = start(problemId);

        if(response.equals("200")){
            ArrayList<Pairs> pairs = new ArrayList<>();
            pairs.add(new Pairs(0, 0));
            pairs.add(new Pairs(0, 0));
            int time = 0;
//            while(time < 540){
//
//            }
            match(pairs);
            ArrayList<WaitingLine> waitingLines = waitingLines();

        }
    }

    private static String start(int problemId){
        System.out.println("===== API Start =====");
        String response = TokenManager.getInstance().createToken(problemId);
        System.out.println("Token : " + TokenManager.getInstance().getToken());
        return response;
    }

    private static ArrayList<WaitingLine> waitingLines() {
        System.out.println("===== API waiting =====");
        JSONObject jsonObject = Connection.getInstance().waitingLines();
        return jsonParser.getWaitingLines(jsonObject);
    }

    private static void match(ArrayList<Pairs> pairs){
        System.out.println("===== API match =====");
        JSONObject jsonObject = Connection.getInstance().match(jsonParser.getMatchJSONArray(pairs));
    }

    private static ArrayList<UserInfo> userInfos(){
        System.out.println("===== User info =====");
        
    }
}
