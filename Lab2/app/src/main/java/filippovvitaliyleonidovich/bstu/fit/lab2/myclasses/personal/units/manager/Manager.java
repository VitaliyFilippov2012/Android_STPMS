package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.manager;

import android.util.Log;

import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import filippovvitaliyleonidovich.bstu.fit.lab2.WorkWithFile;
import filippovvitaliyleonidovich.bstu.fit.lab2.WorkWithFileJSON;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.organization.Organization;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Listener;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Person;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Student;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.staff.Staff;

public class Manager extends Person implements IAction{

    private ArrayList<Staff> staffs;

    public Manager(String name,String surname,String addr, Integer yearOfBirthday){
        super(name,surname,addr,yearOfBirthday);
        staffs = new ArrayList<Staff>();
        Log.d("Manager", "Create object Manager");
    }

    public List<Staff> getStuffs() {
        return staffs;
    }

    @Override
    public void addStuff(String name, Integer maxStudents, Integer maxListeners,
                         String students, String listeners){
        WorkWithFile wFListener = new WorkWithFile(listeners);
        WorkWithFile wFStudent = new WorkWithFile(students);
        WorkWithFileJSON<Listener> wfJsonListener = new WorkWithFileJSON<Listener>(wFListener);
        WorkWithFileJSON<Student> wfJsonStudent = new WorkWithFileJSON<Student>(wFStudent);

        ArrayList<Listener> listenerArrayList = wfJsonListener.deserialize(new TypeToken<Listener>(){}.getType());
        ArrayList<Student> studentArrayList = wfJsonStudent.deserialize(new TypeToken<Student>(){}.getType());


        listenerArrayList.sort(Comparator.comparing(obj -> obj.getReit()));
        listenerArrayList.sort(Comparator.comparing(obj -> obj.getName()));
        studentArrayList.sort(Comparator.comparing(obj -> obj.getMark()));
        studentArrayList.sort(Comparator.comparing(obj -> obj.getName()));

        ArrayList<Person> persons = new ArrayList<Person>();
        for (int i = 0; i< maxStudents; i++)
            persons.add((Person)studentArrayList.get(i));

        for (int i = 0; i< maxListeners; i++)
            persons.add((Person)listenerArrayList.get(i));

        Staff stuff = new Staff(name, persons);
        staffs.add(stuff);
    }


    @Override
    public void addStuff(String name, Integer maxStudents, Integer maxListeners){
        // список персон
        ArrayList<Listener> listenerArrayList = new ArrayList<Listener>(Arrays.asList(
                new Listener("Вадим", "Усачев",22,"г.Минск ул.Белорусская 21-705", Organization.BSTU, 2.7,"JAVA"),
                new Listener("Алекссей", "Хваткин", 27,"г.Минск ул.Белорусская 21-305", Organization.BSTU, 3.5,"JAVA"),
                new Listener("Сергей", "Еремнеко", 21,"г.Минск ул.Белорусская 21-205", Organization.BSUIR, 4.1,".NET"),
                new Listener("Вадим", "Сергеев", 23,"г.Минск ул.Белорусская 21-715", Organization.BSU, 1.9,"JAVA"),
                new Listener("Володя", "Усачев", 35,"г.Минск ул.Белорусская 21-405", Organization.BSUIR, 4.,".NET")
        ));

        ArrayList<Student> studentArrayList = new ArrayList<Student>(Arrays.asList(
                new Student("Филипп", "Колотилов", 18,"г.Минск ул.Белорусская 21-705", Organization.BSUIR, 7.8,"JAVA"),
                new Student("Филипп", "Ковтик", 17,"г.Минск ул.Белорусская 21-705", Organization.BSUIR, 6.5,".NET"),
                new Student("Андрей", "Лютик", 19,"г.Минск ул.Белорусская 21-705", Organization.BSTU, 8.,"JAVA"),
                new Student("Артем", "Филатов", 17,"г.Минск ул.Белорусская 21-705", Organization.BSTU, 6.,".NET"),
                new Student("Екатерина", "Кулакова", 18,"г.Минск ул.Белорусская 21-705", Organization.BSTU, 7.1,".NET")
        ));

        // производим сортировку при помощи компаратора
        listenerArrayList.sort(Comparator.comparing(obj -> obj.getReit()));
        listenerArrayList.sort(Comparator.comparing(obj -> obj.getName()));
        studentArrayList.sort(Comparator.comparing(obj -> obj.getMark()));
        studentArrayList.sort(Comparator.comparing(obj -> obj.getName()));

        ArrayList<Person> persons = new ArrayList<Person>();
        for (int i = 0; i< maxStudents; i++)
            persons.add((Person)studentArrayList.get(i));

        for (int i = 0; i< maxListeners; i++)
            persons.add((Person)listenerArrayList.get(i));

        Staff stuff = new Staff(name, persons);
        staffs.add(stuff);
    }

    @Override
    public int compareTo(Person o) {
        return 0;
    }

    // внутренний класс
    class InternalClass{

    }
}
