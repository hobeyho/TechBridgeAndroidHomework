package com.nickjwpark.techbridgeandroidhomework;

import android.view.View;

/**
 * Created by Nick on 12/11/15.
 * 로그에 글을 쓰려면 println("글");
 */
public class Homework {
    public static int ch1_1 (int a, int b){
        int c=0;
        return c;
    }

    public static double ch1_2 (double a, double b){
        double c=0;
        return c;
    }

    public static String ch1_3 (int a){
        String b="";
        return b;
    }

    public static double ch1_4 (int a, int b){
        double c=0;
        return c;
    }

    public static int ch1_5 (double a, double b){
        int c=0;
        return c;
    }

    public static int ch1_6 (int a, int b){
        int c=0;
        return c;
    }

    public static double ch1_7 (double a, double b){
        double c=0;
        return c;
    }

    public static int ch1_8 (int a, int b){
        int c=0;
        return c;
    }

    public static double ch1_9 (double a, double b){
        double c=0;
        return c;
    }


    public static int ch1_10 (int a, int b, int c){
        int d=0;
        return d;
    }

    public static double ch1_11 (double a, double b, double c){
        double d=0;
        return d;
    }

    public static double ch1_12 (int cost, double tax){
        double total=0;
        return total;
    }

    public static double ch1_13 (int radius){
        double circumference=0;
        return circumference;
    }

    public static double ch1_14 (double food, double drink){
        double total=0;
        return total;
    }

    public static double ch1_15 (int food, int people, double tax){
        double total=0;
        return total;
    }


    public static void println(String str){
        String txt = (String) QuestionActivity.textLog.getText();
        txt+="\n";
        QuestionActivity.textLog.setText(txt+str);
        QuestionActivity.scrollLog.scrollTo(0, QuestionActivity.scrollLog.getBottom());
    }

}
