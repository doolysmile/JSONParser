package src;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONParser {
    public ArrayList<Location> getLocations(JSONObject responseJson){
        ArrayList<Location> locations = new ArrayList<>();
        try{
            JSONArray jsonArray = responseJson.getJSONArray("locations");
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Location location = new Location();
                location.setId(jsonObject.getInt("id"));
                location.setLocated_bikes_count(jsonObject.getInt("located_bikes_count"));

                locations.add(location);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        return locations;
    }

    public ArrayList<Truck> getTrucks(JSONObject responseJson) {
        ArrayList<Truck> trucks = new ArrayList<>();

        try{
            JSONArray trucksArray = responseJson.getJSONArray("trucks");
            for(int i = 0; i < trucksArray.length(); i++){
                JSONObject truck = trucksArray.getJSONObject(i);
                Truck newTruck = new Truck();
                newTruck.setId(truck.getInt("id"));
                newTruck.setLocation_id(truck.getInt("location_id"));
                newTruck.setLoaded_bikes_id(truck.getInt("loaded_bikes_count"));

                trucks.add(newTruck);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
        return trucks;
    }

    public int getScore(JSONObject responseJson){
        System.out.println(responseJson);
        try{
            return responseJson.getInt("score");
        } catch (JSONException e){
            e.printStackTrace();
        }
        return -1;
    }

    public JSONArray getCommandsJSONArray(ArrayList<Command> commandArrayList){
        JSONArray commandArray = new JSONArray();
        for(Command command : commandArrayList){
            commandArray.put(command.getJsonCommandDate());
        }
        return commandArray;
    }

    public Simulate putSimulation(JSONObject responseJson){
        Simulate newSimulate = new Simulate();
        try{
            newSimulate.setStatus(responseJson.getString("status"));
            newSimulate.setTime(responseJson.getInt("time"));
            newSimulate.setTotalDistance(responseJson.getString("distance"));
            newSimulate.setFailRequest(responseJson.getInt("failed_requests_count"));
        } catch (JSONException e){
            e.printStackTrace();
        }
        return newSimulate;
    }
}
