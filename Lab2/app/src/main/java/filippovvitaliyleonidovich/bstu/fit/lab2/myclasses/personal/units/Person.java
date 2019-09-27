package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Optional;

import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.organization.Organization;

public abstract class Person implements Comparable<Person> {
    protected String name;
    Optional<String> surname;
    protected int age;
    protected int year;
    protected Organization org;
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Person( String name, String surname, int age, int year,String org) {
        this.name = name;
        this.surname=Optional.of(surname);
        this.age = age;
        this.year = year;
        this.org = Organization.valueOf(org);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Person(String name, String surname, int age,String org) {
        this.name = name;
        this.surname = Optional.of(surname);
        this.age = age;
        this.org = Organization.valueOf(org);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = Optional.of(surname);
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Person person) {
        return 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getSurname() throws Exception {
        if(surname.isPresent())
        {
            return surname.get();
        }
        else
        {
            throw new Exception("null string");
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setSurname(String surname) {
        this.surname = Optional.of( surname);
    }

    public int getAge() {
        return age;
    }

    public Person() {
    }

    public void setAge(int age) {
        this.age = age;
    }
}
