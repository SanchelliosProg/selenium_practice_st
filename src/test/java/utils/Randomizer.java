package utils;

import java.util.Random;

/**
 * Created by Александр on 10.03.2017.
 */
public class Randomizer {
    public static String getRandomEmailAddress (String domain) {
        Random rand = new Random();
        int min = 1000000000;
        int randomNum = rand.nextInt((Integer.MAX_VALUE - min) + 1) + min;
        return randomNum + domain;
    }

    public static int getUSAIndex () {
        Random random = new Random();
        int min = 10000;
        int max = 99999;
        return random.nextInt((max - min) + 1) + min;
    }

    public static int getJustAnyNumber () {
        Random random = new Random();
        int min = 1;
        int max = 99999;
        return random.nextInt((max - min) + 1) + min;
    }
}
