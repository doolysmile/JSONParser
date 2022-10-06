package src;

import src.util.TokenManager;

public class Main {
    public static void main(String[] args) {
        int problemId = 1;

        String response = start(problemId);

    }

    private static String start(int problemId){
        System.out.println("===== API Start =====");
        String response = TokenManager.getInstance().createToken(problemId);
        System.out.println("Token : " + TokenManager.getInstance().getToken());
        return response;
    }
}
