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

@Getter
@Setter
public class Staff {

    private List<Student> studList;

    public Staff(final ArrayList<Student> studList) {
        this.studList = studList;
    }

    public boolean add(final Person person)
    {
        return studList.add((Student) person);
    }

    public boolean remove (final Person item)
    {
        return studList.remove(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public String toString() {
        StringBuilder result= new StringBuilder();
        for(Person person:studList)
        {
            try {
                result.append(person.getName()).append(" ").append(person.getSurname()).append("\n");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result.toString();
    }
    public List<Student> getTopStud()
    {
        Collections.sort(studList, (Comparator<Person>) (person, t1) -> {
            if(person instanceof Student && t1 instanceof Student)
            {
                Integer mark1=((Student) person).getMark();
                Integer mark2=((Student) t1).getMark();
                return mark1.compareTo(mark2);
            }
            return 0;
        });
        return studList.subList(studList.size() - 4,studList.size() - 1);
    }
}
