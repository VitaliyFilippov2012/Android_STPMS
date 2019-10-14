package filippovvitaliyleonidovich.bstu.fit.lab2;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Person;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.manager.Manager;

public class WorkWithFile{
    File file;

    public WorkWithFile(String fileName) {
        this.file = new File(fileName);
    }

    public boolean createFile() {
        try {
            file.createNewFile();
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean deleteFile() {
        file.delete();
        return !checkFile();
    }

    public boolean checkFile(){
        if(file.exists()) {
            return true;
        }
        else{
            return false;
        }
    }

    public boolean writeFile(String text) {
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(text);
            fileWriter.close();
        }
        catch(IOException e){
            return false;
        }
        return true;
    }

    public String readFile() {
        StringBuilder builder;
        try{
            FileReader fileReader = new FileReader(file);
            FileInputStream fi = new FileInputStream(file);
            char[] nw =new char[fi.available()];
            fileReader.read(nw);
            builder = new StringBuilder();
            for (int i = 0; i < nw.length; ++i) {
                builder.append(nw[i]);
            }

            fileReader.close();
        }
        catch (Exception e) {
            return "Error";
        }
        return builder.toString();
    }

    public boolean isExistsString(String text){
        return readFile().contains(text);
    }

    public String getStringAbout(String partText){
        String textFromFile = readFile();
        if(!textFromFile.contains(partText)){
            return "";
        }
        String[] arrayStringFile =  textFromFile.split("\n\r");
        for (String str: arrayStringFile) {
            if(str.contains(partText)){
                return str;
            }
        }
        return "";
    }



}
