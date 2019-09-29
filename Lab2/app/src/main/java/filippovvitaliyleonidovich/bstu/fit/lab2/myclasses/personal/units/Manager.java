package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.staff.Staff;
import lombok.Getter;

@Getter
public class Manager extends Person implements  ISerializable {

    private String role;

    private String birthday;

    private String addr;

    public Manager(final String name, final String surname, final int age, final String addr,
                   final String role, final String birthday) {
        super(name,surname,age);
        this.addr = addr;
        this.birthday = birthday;
        this.role = role;
    }

    public Staff readFromFile(String fileName, Context context) {
       /* FileReader fileReader;
        String json="";
        try {
            int c;
            fileReader=new FileReader(jsonPath);
            while((c=fileReader.read())!=-1)
            {
                json+=(char)c;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Gson gson=new Gson();
        return gson.fromJson(json,new TypeToken<Staff>(){}.getType());*/
        InputStreamReader streamReader = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = context.openFileInput(fileName);
            streamReader = new InputStreamReader(fileInputStream);
            Gson gson = new Gson();
            Staff stuff = gson.fromJson(streamReader, Staff.class);
            return stuff;
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void writeToFile(Context context, Staff stuff,String FILE_NAME)
    {
        /*FileWriter fileWriter = null;
        try {
            Gson gson=new Gson();
            fileWriter=new FileWriter(jsonPath);
            fileWriter.write(gson.toJson(stuff));
        } catch (IOException ex) {
            ex.printStackTrace();
        }*/
        Gson gson = new Gson();
        String jsonString = gson.toJson(stuff);

        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fileOutputStream.write(jsonString.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Staff generarteCourse(int countOfStudents)
    {
        //Integer id, String name, String surname, int age, int year, int mark,Organizations organization
       Staff staff=new Staff();
        /* Random rand=new Random();
        try {
            while(countOfStudents!=0)
            {


                stuff.add(new Student(countOfStudents,
                        RandomStringUtils.randomAlphabetic(10),
                        RandomStringUtils.randomAlphabetic(10),
                        rand.nextInt(40),
                        1970+rand.nextInt(30),
                        rand.nextInt(10),
                        Organization.BSTU
                ));
                countOfStudents--;

            }
        }
        catch (EduException e) {
            e.printStackTrace();
        }*/
        return staff;
    }

    @Override
    public boolean saveInfo() {
        return false;
    }

    @Override
    public Person loadInfo() {
        return null;
    }
}
