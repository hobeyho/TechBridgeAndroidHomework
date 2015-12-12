package com.nickjwpark.techbridgeandroidhomework;

/**
 * Created by Nick on 12/11/15.
 */
import java.util.ArrayList;
import java.util.List;

public class Group {

    public String string;
    public final List<String> children = new ArrayList<String>();

    public Group(String string) {
        this.string = string;
    }

}