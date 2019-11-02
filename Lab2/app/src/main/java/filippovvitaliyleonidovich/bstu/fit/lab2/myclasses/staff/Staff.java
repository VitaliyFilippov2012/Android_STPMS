package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.staff;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import filippovvitaliyleonidovich.bstu.fit.lab2.R;
import filippovvitaliyleonidovich.bstu.fit.lab2.WorkWithFile;
import filippovvitaliyleonidovich.bstu.fit.lab2.WorkWithFileJSON;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.exception.EduException;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Listener;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Person;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Student;

import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.LISTENERS_TXT_NAME;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.STUDENTS_TXT_NAME;

public class Staff {

    private static ArrayList<String> persons;
    private static ArrayList<Listener> listeners;
    private static ArrayList<Student> students;
    static {
        persons = new ArrayList<String>();
        listeners = new ArrayList<Listener>();
        students = new ArrayList<Student>();
        loadFromFile();
    }
    public static ArrayList<Student> getStudents(){
        return students;
    }
    public static ArrayList<Listener> getListeners(){
        return listeners;
    }
    public static ArrayList<String> getPersons(){ return persons; }

    public static ArrayList<Student> getStudents(String nameStaff){
        ArrayList<Student> studentsOnStaff = new ArrayList<>();
        for (Student s:students) {
            if(s.getStaff().equalsIgnoreCase(nameStaff)){
                studentsOnStaff.add(s);
            }
        }
        return studentsOnStaff;
    }

    public static ArrayList<String> getPersons(String nameStaff){
        ArrayList<String> personsOnStaff = new ArrayList<>();
        for (String s:persons) {
            if(s.contains(nameStaff)){
                personsOnStaff.add(s);
            }
        }
        return personsOnStaff;
    }

    public static ArrayList<Listener> getListeners(String nameStaff){
        ArrayList<Listener> listenersOnStaff = new ArrayList<>();
        for (Listener l:listeners) {
            if(l.getStaff().equalsIgnoreCase(nameStaff)){
                listenersOnStaff.add(l);
            }
        }
        return listenersOnStaff;
    }

    public static Student getStudent(String person){
        for (Student s: students) {
            Person p = (Person)s;
            if(p.toString().equalsIgnoreCase(person)){
                return s;
            }
        }
        return null;
    }
    public static Listener getListener(String person){
        for (Listener l: listeners) {
            Person p = (Person)l;
            if(p.toString().equalsIgnoreCase(person)){
                return l;
            }
        }
        return null;
    }


    private static void loadFromFile(){
            WorkWithFile wfS = new WorkWithFile("/data/user/0/filippovvitaliyleonidovich.bstu.fit.lab2/"+STUDENTS_TXT_NAME);
            WorkWithFileJSON<Student> workWithFileJSONS = new WorkWithFileJSON<Student>(wfS);
            ArrayList<Student> arrayListS = workWithFileJSONS.deserialize(new TypeToken<Student>(){}.getType());
            setStudents(arrayListS,false);
            for(Person p : arrayListS ){
                persons.add(p.toString());
            }

            WorkWithFile wfL = new WorkWithFile("/data/user/0/filippovvitaliyleonidovich.bstu.fit.lab2/"+LISTENERS_TXT_NAME);
            WorkWithFileJSON<Listener> workWithFileJSONL = new WorkWithFileJSON<Listener>(wfL);
            ArrayList<Listener> arrayListL = workWithFileJSONL.deserialize(new TypeToken<Listener>(){}.getType());
            setListeners(arrayListL,false);
            for(Person p : arrayListL){
                persons.add(p.toString());
            }
            Log.d("MyApp","loadFromFile");
    }

    public static void setStudents(ArrayList<Student> list,boolean r){
        if(!r){
            students.addAll(list);
            return;
        }
        students.clear();
        WorkWithFile wf = new WorkWithFile("/data/user/0/filippovvitaliyleonidovich.bstu.fit.lab2/"+STUDENTS_TXT_NAME);
        wf.createFile();
        WorkWithFileJSON<Student> wfJson = new WorkWithFileJSON<Student>(wf);
        for(Student s:list){
            wfJson.saveAsJson(s);
        }
        Log.d("MyApp", "Set new list of students");
    }

    public static void setListeners(ArrayList<Listener> list,boolean r){
        if(!r){
            listeners.addAll(list);
            return;
        }
        listeners.clear();
        WorkWithFile wf = new WorkWithFile("/data/user/0/filippovvitaliyleonidovich.bstu.fit.lab2/"+LISTENERS_TXT_NAME);
        wf.createFile();
        WorkWithFileJSON<Listener> wfJson = new WorkWithFileJSON<Listener>(wf);
        for(Listener l:list){
            wfJson.saveAsJson(l);
        }
        Log.d("MyApp", "Set new list of listeners");
    }

    public static void setPersons(ArrayList<Person> list){
        for (Person p: list) {
            persons.add(p.toString());
        }
        Log.d("MyApp", "Set new list of persons");
    }

    public static void add(Person item,String role){
        if(role.equals("Student")){
            Student student = (Student)item;
            WorkWithFile wf = new WorkWithFile("/data/user/0/filippovvitaliyleonidovich.bstu.fit.lab2/"+STUDENTS_TXT_NAME);
            WorkWithFileJSON<Student> wfJson = new WorkWithFileJSON<Student>(wf);
            wfJson.saveAsJson(student);
            students.add(student);
            Log.d("MyApp","Create new Student");
        }
        if(role.equals("Listener")) {
            Listener listener = (Listener)item;
            WorkWithFile wf = new WorkWithFile("/data/user/0/filippovvitaliyleonidovich.bstu.fit.lab2/"+LISTENERS_TXT_NAME);
            WorkWithFileJSON<Listener> wfJson = new WorkWithFileJSON<Listener>(wf);
            wfJson.saveAsJson(listener);
            listeners.add(listener);
            Log.d("MyApp" ,"Create new Listener");
        }
        Log.d("Stuff", "Added new item to stuff");
        persons.add(item.toString());
    }

    public static void remove(String person){
        if(person.contains("Listener")){
            ArrayList<Listener>  newListeners = new ArrayList<>();
            for(Listener l:listeners){
                Person p = (Person)l;
                if(!p.toString().equalsIgnoreCase(person)){
                    newListeners.add(l);
                }
            }
            setListeners(newListeners,true);
        }
        else{
            ArrayList<Student>  newStudents = new ArrayList<>();
            for(Student s:students){
                Person p = (Person)s;
                if(!p.toString().equalsIgnoreCase(person)){
                    newStudents.add(s);
                }
            }
            setStudents(newStudents,true);
        }
        listeners.clear();
        students.clear();
        persons.clear();
        loadFromFile();
    }

    @Override
    public String toString(){
        return "\nCount students: " + students.size()+ "\nCount listeners: " + listeners.size();
    }
}
