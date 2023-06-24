package com.example.loudalarm.Games.MathTrainerGame;

import java.util.Random;

public class Problem {

    private int result;
    private int level = 1;
    private final Random random = new Random();

    public static int getRandom(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }

    public int getResult() {
        return result;
    }

    public int getNotResult() {
        return result + getRandom(-4, 3);
    }

    public String getProblem(int a, int b) {
        String sign;
        switch (getRandom(0, 2)) {
            case 1:
                sign = getRandomSignSqr();
                break;
            case 0:
                sign = getRandomSign();
                break;
            default:
                return random.nextBoolean() ? getRandomSign() : getRandomSignSqr();
        }
        switch (sign) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "/":
                if (b == 0) {
                    if (random.nextBoolean())
                        b = getRandom(1, 50);
                    else b = getRandom(-50, -1);
                }
                result = (int) a / b;
                break;
            case "*":
                result = a * b;
                break;
        }
        return a + " " + sign + " " + b + " =";
    }


    public String getProblem(int a, int b, int c){
        String first_str = getProblem(a, b);
        int first_res = result;
        return  getProblem(first_res, c);
    }


    private String getRandomSign() {
        return random.nextBoolean() ? "-" : "+";
    }

    private String getRandomSignSqr() {
        return random.nextBoolean() ? "/" : "*";

    }
}



