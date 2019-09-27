package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.staff;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Person;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Student;

public class Staff {
    private ArrayList<Student> studList=new ArrayList<Student>();

    public Staff() {


    }

    public Staff(ArrayList<Student> studList) {
        this.studList = studList;
    }
    public List<Student> getStudlist(){return studList;}
    public void setStudList(ArrayList<Student> studList)
    {
        this.studList=studList;
    }
    public boolean add(Person item)
    {
        return studList.add((Student) item);
    }

    public boolean remove (Person item)
    {
        return studList.remove(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public String toString() {
        String result="";
        for(Person person:studList)
        {
            try {
                result+=(person.getName()+" "+person.getSurname())+"\n";
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }
    public List<Student> getTopStud()
    {
        Collections.sort(studList, new Comparator<Person>() {

            @Override
            public int compare(Person person, Person t1) {
                if(person instanceof Student && t1 instanceof Student)
                {
                    Integer mark1=((Student) person).getMark();
                    Integer mark2=((Student) t1).getMark();
                    return mark1.compareTo(mark2);
                }
                return 0;
            }
        });
        return (List<Student>) studList.subList(studList.size()-4,studList.size()-1);
    }


}
