package src;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    static final String NO_MOVE = "0";
    static final String UP = "1";
    static final String RIGHT = "2";
    static final String DOWN = "3";
    static final String LEFT = "4";
    static final String LOAD = "5";
    static final String SAVE = "6";

    public static HashMap<Integer, Pair> hashMap = new HashMap<>();

    static class Pair {
        int r, c;

        Pair(int a, int b) {
            r = a;
            c = b;
        }
    }

    private static JSONParser jsonParser = new JSONParser();
    public static void main(String[] args) {
        int problemId = 1;

        String response = start(problemId);
        if(response.equals("200")){
            makeMap(5, 5);
            int time = 0;
            while(time < 720){
                System.out.println(time);
                time++;
                ArrayList<Location> locations = locations();
                ArrayList<Truck> trucks = trucks();
                ArrayList<Command> commands = new ArrayList<>();
                ArrayList<Integer> tmp = new ArrayList<>();
                tmp.add(0);
                commands.add(new Command(0, tmp));
                commands.add(new Command(1, tmp));
                commands.add(new Command(2, tmp));
                commands.add(new Command(3, tmp));
                commands.add(new Command(4, tmp));
                simulate(commands);
            }

        }
        System.out.println(score());
    }

    private static String simulate(ArrayList<Command> commands){
        JSONObject jsonObject = Connection.getInstance().simulate(jsonParser.getCommandsJSONArray(commands));

        Simulate simulate = jsonParser.putSimulation(jsonObject);
        return simulate.toString();

    }

    public static void makeMap(int R, int C){
        int index = 0;
        for(int i = 0; i < C; i++) {
            for(int j = R -1 ; j >= 0 ; j--){
                hashMap.put(index++, new Pair(j, i));
            }
        }
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
