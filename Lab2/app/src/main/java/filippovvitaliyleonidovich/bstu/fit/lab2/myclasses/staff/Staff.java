package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.staff;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.exception.EduException;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Person;

public class Staff {
    private String nameStuff;
    private ArrayList<Person> studlist;

    public Staff (String name,ArrayList<Person> studlist){
        this.nameStuff = name;
        this.studlist = studlist;
        Log.d("Stuff", "Create object Stuff");
    }


    public List<Person> getStudentsList(){
        return studlist;
    }
    public void setStudentsList(ArrayList<Person> list){
        Log.d("Stuff", "Set new list of persons");
        studlist = list;
    }

    public boolean add(Person item) throws EduException {
        try {
            Optional.of(item).orElseThrow(() -> new EduException());
        }
        catch (EduException ex) {
            Log.d("", ex.getMessage());
            return false;
        }

        Log.d("Stuff", "Added new item to stuff");
        return studlist.add(item);
    }
    public boolean remove(Person item){
        Log.d("Stuff", "Remove item from  Stuff");
        return studlist.remove(item);
    }

    @Override
    public String toString(){
        return  "Name: " + nameStuff + "\nCount listeners: " + studlist.size();
    }
}
