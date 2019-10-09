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

public class Manager extends Person implements IAction{
    private ArrayList<Stuff> stuffs;

    public Manager(String name,Integer yearOfBirthday,Organization org){
        super(name,yearOfBirthday);
        stuffs = new ArrayList<Stuff>();
        Log.d("Manager", "Create object Manager");
    }


    public List<Stuff> getStuffs() {
        return stuffs;
    }

    // Добавление из JSON
    public void addStuff(String name, Integer maxStudents, Integer maxListeners,
                         File students, File listeners){
        WorkWithFile workWithFile = new WorkWithFile();
        ArrayList<Listener> listenerArrayList =(ArrayList<Listener>) workWithFile.deserialize(
                listeners, new TypeToken<ArrayList<Listener>>(){}.getType());
        ArrayList<Student> studentArrayList = (ArrayList<Student>) workWithFile.deserialize(
                students, new TypeToken<ArrayList<Student>>(){}.getType());

        listenerArrayList.sort(Comparator.comparing(obj -> obj.getReit()));
        listenerArrayList.sort(Comparator.comparing(obj -> obj.getName()));
        studentArrayList.sort(Comparator.comparing(obj -> obj.getMark()));
        studentArrayList.sort(Comparator.comparing(obj -> obj.getName()));

        ArrayList<Person> persons = new ArrayList<Person>();
        for (int i = 0; i< maxStudents; i++)
            persons.add((Person)studentArrayList.get(i));

        for (int i = 0; i< maxListeners; i++)
            persons.add((Person)listenerArrayList.get(i));

        Stuff stuff = new Stuff(name, persons);
        stuffs.add(stuff);


    }
    // Генерация
    public void addStuff(String name, Integer maxStudents, Integer maxListeners){
        // список персон
        ArrayList<Listener> listenerArrayList = new ArrayList<Listener>(Arrays.asList(
                new Listener("Вадим Усачев", 25 , Organization.ALFA, 2.7),
                new Listener("Алекссей Хваткин", 27, Organization.EPAM, 3.5),
                new Listener("Сергей Еремнеко", 21, Organization.ALFA, 4.1),
                new Listener("Вадим Сергеев", 23, Organization.LEVERX, 1.9),
                new Listener("Володя Усачев", 35, Organization.ALFA, 4.)
        ));

        ArrayList<Student> studentArrayList = new ArrayList<Student>(Arrays.asList(
                new Student("Филипп Колотилов", 18, Organization.BNTU, 7.8),
                new Student("Филипп Ковтик", 17, Organization.BNTU, 6.5),
                new Student("Андрей Лютик", 19, Organization.BSTU, 8.),
                new Student("Артем Филатов", 17, Organization.BSTU, 6.),
                new Student("Екатерина Кулакова", 18, Organization.BSTU, 7.1)
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

        Stuff stuff = new Stuff(name, persons);
        stuffs.add(stuff);
    }

    // внутренний класс
    class InternalClass{

    }
}
