package filippovvitaliyleonidovich.bstu.fit.lab2;

import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {

    public boolean writeToFile(String text,String fileName,File file){
        File f = new File(file + fileName);

        try{
            FileWriter w = new FileWriter(f,true);
            w.write(text);
            w.close();
        }
        catch(IOException e){
            Log.d("io_write",e.getMessage());
            return false;
        }
        return true;
    }

    public String readFile(String fileName,File file) {
        File f =new File(file+fileName);
        StringBuilder builder;
        try{
            FileReader fileReader = new FileReader(f);
            char[] nw=new char[10000];
            fileReader.read(nw);
            builder = new StringBuilder();
            for (int i = 0; i < nw.length; ++i) {
                builder.append(nw[i]);
            }
            fileReader.close();
        }
        catch(IOException e){
            Log.d("io_write",e.getMessage());
            return "";
        }
        return builder.toString();
    }
    public static void deleteFile(String fileName){
        File f = new File(fileName);
        f.delete();
    }
}
