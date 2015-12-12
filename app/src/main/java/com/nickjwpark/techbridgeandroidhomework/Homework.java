package com.nickjwpark.techbridgeandroidhomework;

import android.view.View;

/**
 * Created by Nick on 12/11/15.
 */
public class Homework {
    public static int ch1_1 (int a, int b){
        int c=0;
        c = a + b;
//        println(""+c);
        return c;
    }

    public static void println(String str){
        String txt = (String) QuestionActivity.textLog.getText();
        txt+="\n";
        QuestionActivity.textLog.setText(txt+str);
        QuestionActivity.scrollLog.scrollTo(0, QuestionActivity.scrollLog.getBottom());
    }

}
