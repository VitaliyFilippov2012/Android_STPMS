package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.staff;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Person;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Student;
import lombok.Getter;
import lombok.Setter;

public class Staff {
    private String nameStuff;
    private ArrayList<Person> studlist;

    public Stuff (){
        nameStuff = "";
        studlist = new ArrayList<Person>();
        Log.d("Stuff", "Create object Stuff");
    }
    public Stuff(String name,ArrayList<Person> list){
        nameStuff = name;
        studlist = list;
        Log.d("Stuff", "Create object Stuff");
    }

    public List<Person> getStudentsList(){
        return studlist;
    }
    public void setStudentsList(ArrayList<Person> list){
        Log.d("Stuff", "Set new list of persons");
        studlist = list;
    }

    public boolean add (Person item) throws EduException {
        try {
//            if (item == null)
//                throw new EduException();
            Optional.of(item).orElseThrow(() -> new EduException());
        }
        catch (EduException ex) {
            Log.d("", ex.getMessage());
            return false;
        }

        Log.d("Stuff", "Added new item to stuff");
        return studlist.add(item);
    }
    public boolean remove (Person item){
        Log.d("Stuff", "Remove item from  Stuff");
        return studlist.remove(item);
    }

    @Override
    public String toString(){
        return  "Name: " + nameStuff + "\nCount listeners: " + studlist.size();
    }
}
