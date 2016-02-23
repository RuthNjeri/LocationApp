package com.example.waiganjo.locationexample;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Waiganjo on 11/17/2015.
 */
public class Utility {
    public static void saveToAFile(   Context context,final String fileContents, String fileName) {

        try {
            FileWriter out = new FileWriter(new File(context.getFilesDir(), fileName));
            out.write(fileContents);
            out.close();
        } catch (IOException e) {
            Log.e("Data", e.getMessage());
        }
    }

    public static String readFromFile( Context context ,String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(new File(context.getFilesDir(), fileName)));
            while ((line = in.readLine()) != null) stringBuilder.append(line);

        } catch (FileNotFoundException e) {
            Log.e("Data", e.getMessage());
        } catch (IOException e) {
            Log.e("Data", e.getMessage());
        }

        return stringBuilder.toString();
    }
}
