package src;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

//    public static HashMap<Integer, Pair> hashMap = new HashMap<>();
    private static JSONParser jsonParser = new JSONParser();
    public static void main(String[] args) {
        int problemId = 1;

        String response = start(problemId);
        if(response.equals("200")){
            ArrayList<Location> locations = locations();
            ArrayList<Truck> trucks = trucks();
            for(Location l : locations){
//                System.out.println(l);
            }
            for(Truck t : trucks){
                System.out.println(t);
            }
        }

        System.out.println(score());
    }

    private static int score() {
        JSONObject jsonObject = Connection.getInstance().score();
        return jsonParser.getScore(jsonObject);
    }


    private static String start(int problemId){
        System.out.println("===== API Start =====");
        String response = TokenManager.getInstance().createToken(problemId);
        System.out.println("Token : " + TokenManager.getInstance().getToken());
        return response;
    }

    private static ArrayList<Location> locations() {
        JSONObject jsonObject = Connection.getInstance().locations();
        return jsonParser.getLocations(jsonObject);
    }

    private static ArrayList<Truck> trucks() {
        JSONObject jsonObject = Connection.getInstance().trucks();
        return jsonParser.getTrucks(jsonObject);
    }
}
