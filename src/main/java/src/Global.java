package src;

public class Global {

    public static final String BASE_RUL = "https://huqeyhi95c.execute-api.ap-northeast-2.amazonaws.com/prod";

    public static final String POST_START = "/start";
    public static final String GET_WAITING = "/waiting_line";
    public static final String GET_RESULT = "/game_result";
    public static final String GET_USERINFO = "/user_info";
    public static final String PUT_MATCH = "/match";
    public static final String PUT_CHANGE = "/change_grade";
    public static final String GET_SCORE ="/score";
    public static final String X_AUTH_TOKEN = "6981271666c6e99bf854206e0a1ff87c";

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
