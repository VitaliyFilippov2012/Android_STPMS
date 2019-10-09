package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Optional;

import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.organization.Organization;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public abstract class Person implements Comparable<Person> {
    private String name;
    private Organization organization;
    private Integer yearOfBirthday;
    private Optional<String> surname;

    public Person(String name, Integer yearOfBirthday, Organization org){
        this.name = name;
        this.yearOfBirthday = yearOfBirthday;
        this.organization = org;
        Log.d("Person", "Create object Person");
    }
    public Person(String name, Integer yearOfBirthday){
        this.name = name;
        this.yearOfBirthday = yearOfBirthday;
        Log.d("Person", "Create object Person");
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        Log.d("Person", "Set new value of Name");
    }

    public Organization getOrganization() {
        return organization;
    }
    public void setOrganization(Organization organization) {
        this.organization = organization;
        Log.d("Person", "Set new value of Organization");
    }

    public Integer getYearOfBirthday() {
        return yearOfBirthday;
    }
    public void setYear(Integer yearOfBirthday) {
        this.yearOfBirthday = yearOfBirthday;
        Log.d("Person", "Set new value of year birthday");
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
        Log.d("Person", "Set new value of surame");
    }
}
