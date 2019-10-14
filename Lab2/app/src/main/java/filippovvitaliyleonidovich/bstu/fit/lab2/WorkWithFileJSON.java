package filippovvitaliyleonidovich.bstu.fit.lab2;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.manager.Manager;

public class WorkWithFileJSON<T>{

    private WorkWithFile wf;

    public WorkWithFileJSON(WorkWithFile workWithFile){
        this.wf = workWithFile;
    }

    public ArrayList<T> deserialize(Type type){
        Gson gson = new Gson();
        String str = "";
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(wf.file));

            while ((str = br.readLine()) != null) {
                text.append(str);
                text.append('\n');
            }
            Log.d("entry",String.valueOf(text));
            String[] findStrings = String.valueOf(text).split("\n");
            ArrayList<T> findObj = new ArrayList<T>();
            for (String s: findStrings) {
                findObj.add((T)gson.fromJson(String.valueOf(s), type));
            }

            return findObj;

        } catch (FileNotFoundException e) {
            Log.d("WorkWithFile", "Ошибка чтения в файла..");
        } catch (IOException e) {
            Log.d("WorkWithFile", "Ошибка чтения в файла..");
        }
        return null;
    }

    public boolean saveAsJson(T obj){
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        try{
            FileWriter fileWriter = new FileWriter(wf.file, true);
            fileWriter.append(json);
            fileWriter.append("\n");
            fileWriter.flush();
            fileWriter.close();
            return true;
        } catch (IOException e) {
            Log.d("WorkWithFile", "Ошибка записи в файл..");
        }
        return false;
    }
}
