package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Optional;

import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.organization.Organization;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class Person implements Comparable<Person> {

    private String name;

    private Optional<String> surname;

    private int age;

    private int year;

    private Organization org;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Person(final String name, final String surname, final int age, final int year,
                  final String org) {
        this.name = name;
        this.surname=Optional.of(surname);
        this.age = age;
        this.year = year;
        this.org = Organization.valueOf(org);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Person(final String name, final String surname, final int age, final String org) {
        this.name = name;
        this.surname = Optional.of(surname);
        this.age = age;
        this.org = Organization.valueOf(org);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Person(final String name, final String surname, final int age) {
        this.name = name;
        this.surname = Optional.of(surname);
        this.age = age;
    }

    @Override
    public int compareTo(Person person) {
        return 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getSurname() {
        return surname.orElse("");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setSurname(final String surname) {
        this.surname = Optional.of( surname);
    }
}
