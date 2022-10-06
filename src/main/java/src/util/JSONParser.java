package src.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import src.data.Pairs;
import src.data.WaitingLine;

import java.util.ArrayList;

public class JSONParser {

    public ArrayList<WaitingLine> getWaitingLines(JSONObject jsonObject) {
        ArrayList<WaitingLine> waitingLines = new ArrayList<>();
        System.out.println(jsonObject);
        try {

        }catch (JSONException e){
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray getMatchJSONArray(ArrayList<Pairs> pairs) {
        JSONArray pairsArray = new JSONArray();
        for(Pairs pair : pairs){
            JSONArray innerPair = new JSONArray();
            innerPair.put(pair.getUser1());
            innerPair.put(pair.getUser2());
            pairsArray.put(innerPair);
        }
        return pairsArray;
    }
}
