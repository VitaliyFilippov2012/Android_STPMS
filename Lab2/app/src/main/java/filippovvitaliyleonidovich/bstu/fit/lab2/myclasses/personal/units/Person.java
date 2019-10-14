package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units;

import android.util.Log;
import java.util.Optional;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.organization.Organization;

public abstract class Person implements Comparable<Person> {
    private String name;
    private String addr;
    private Organization organization;
    private Integer yearOfBirthday;
    private String surname;
    private String nameStaff;


    public Person(String name,String surname, Integer yearOfBirthday, String addr, Organization org,String nameStaff){
        this.name = name;
        this.surname = surname;
        this.yearOfBirthday = yearOfBirthday;
        this.organization = org;
        this.addr = addr;
        this.nameStaff = nameStaff;

        Log.d("Person", "Create object Person");
    }
    public Person(String name,String surname, String addr, Integer yearOfBirthday){
        this.name = name;
        this.surname = surname;
        this.addr = addr;
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

    public String getAddr() {
        return addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
        Log.d("Person", "Set new value of Addr");
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

    public String getSurname() {return surname.toString();
    }
    public void setSurname(String surname) {
        this.surname = surname;
        Log.d("Person", "Set new value of surame");
    }
}
