package com.tahmidu.notes_web_app.util;

import java.util.Random;

public class TokenUtil {

    public static int generateToken(){

        Random random = new Random();
        int digits = 5;
        int min = (int) Math.pow(10,digits-1); // Min = 10000
        int max = (int) Math.pow(10,digits)-1; // Max = 99999
        int token = random.nextInt((max-min)+1); // From 0 inclusive to 100000 exclusive

        // Ensures token is >= 10000
        if(token < Math.pow(10, 4)){
            token += min;
        }

        return token;
    }
}
