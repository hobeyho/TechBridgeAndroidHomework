package com.nickjwpark.techbridgeandroidhomework;

import java.util.Random;

/*
 * Created by Nick on 12/11/15.
 * 로그에 글을 쓰려면 println("글");
*/
public class Homework {
    public static int ch1_1 (int a, int b){
        int c=0;
        println("a="+a);
        println("b="+b);
        c = a+b;
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

    public static String ch2_1 (String a){
        String b = "";
        int count = 0;
        while(count<10){
            b = b + a + " ";
            count++;
        }
        return b;
    }

    public static String ch2_2 (){
        String b = "";
        int count = 1;
        while(count<10){
            b = b + count + " ";
            count++;
        }
        b += 10;
        return b;
    }

    public static String ch2_3(int a){
        String b = "";
        int count = 2;
        while(count<a){
            b = b + count + " ";
            count += 2;
        }
        b += count;
        return b;
    }

    public static String ch2_4(int a){
        String b = "";
        int count = 1;
        while(count < 9){
            b = b + a + "*" + count + "=" + a*count + " ";
            count++;
        }
        b = b + a + "*" + count + "=" + a*count;
        return b;
    }

    public static String ch2_5(){
        String launch="";
        int count = 10;
        while(count>0){
            launch = launch + count + " ";
            count--;
        }
        launch += "Launch!";
        return launch;
    }

    public static int ch2_6(){
        Random myNums;
        myNums = new Random( );
        int num = myNums.nextInt(6)+1;
        return num;
    }

    public static String ch2_7(){
        String b = "";
        Random myNums;
        myNums = new Random( );
        int count = 0;
        int num;
        while(count<5){
            num = myNums.nextInt(45)+1;
            b = b + num + " ";
            count++;
        }
        num = myNums.nextInt(45)+1;
        b += num;
        return b;
    }

    public static String ch2_8(int a){
        String b = "";
        Random myNums;
        myNums = new Random( );
        int temp = 0;
        while(temp != a){
            b = b + temp + " ";
            temp = myNums.nextInt(10);
        }
        b += temp;
        return b;
    }

    public static String ch2_9(double a){
        String b = "";
        int count = 1;
        while (count<5) {
            b = b + Math.pow(a, count) + " ";
            count++;
        }
        b += Math.pow(a,count);
        return b;
    }

    public static double ch2_10(double a, double b){
        double c = 0;
        c = Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
        return c;
    }


    public static void println(String str){
        String txt = (String) QuestionActivity.textLog.getText();
        txt+="\n";
        QuestionActivity.textLog.setText(txt+str);
        QuestionActivity.scrollLog.scrollTo(0, QuestionActivity.scrollLog.getBottom());
    }

}
