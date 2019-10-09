package filippovvitaliyleonidovich.bstu.fit.lab2;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class WorkWithFile {
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

    public boolean saveAsJson(Object obj, File file){
        Gson gson = new Gson();
        String json = gson.toJson(obj);

        try{
            FileWriter fileWriter = new FileWriter(file, false);
            fileWriter.append(json);
            fileWriter.flush();
            fileWriter.close();
            return true;
        } catch (IOException e) {
            Log.d("WorkWithFile", "Ошибка записи в файл..");
        }
        return false;
    }
    public Object deserialize(File file, Type type){
        Gson gson = new Gson();
        String str = "";
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((str = br.readLine()) != null) {
                text.append(str);
                text.append('\n');
            }

            Object obj = gson.fromJson(String.valueOf(text), type);
            return obj;

        } catch (FileNotFoundException e) {
            Log.d("WorkWithFile", "Ошибка чтения в файла..");
        } catch (IOException e) {
            Log.d("WorkWithFile", "Ошибка чтения в файла..");
        }

        return null;
    }
}
