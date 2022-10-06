package src;

public class Global {

    public static final String BASE_RUL = "https://kox947ka1a.execute-api.ap-northeast-2.amazonaws.com/prod/users";

    public static final String POST_START = "/start";
    public static final String GET_LOCATIONS = "/locations";

    public static final String GET_TURCKS = "/trucks";

    public static final String GET_SCORE = "/score";
    public static final String PUT_SIMULATE = "/simulate";
    public static final String X_AUTH_TOKEN = "9c3355168ff38001e92e48f63ef1fe4d";

    public static boolean checkResponse(String responseCode){
        boolean check = false;
        if(responseCode.equals("400")){
            System.out.println("400 :: parameter Err ");
        } else if (responseCode.equals("401")) {
            System.out.println("401 :: X-auth-token Err");
        } else if (responseCode.equals("500")) {
            System.out.println("500 :: server err");
        } else{
            check = true;
        }
        return check;
    }
}
