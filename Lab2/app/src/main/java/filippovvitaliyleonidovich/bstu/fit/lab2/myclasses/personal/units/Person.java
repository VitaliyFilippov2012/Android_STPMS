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
    private String role;
    private String nameStaff;
    private String phone;
    private String email;
    private String messanger;


    public Person(String role,String name,String surname, Integer yearOfBirthday, String addr, Organization org,String nameStaff){
        this(role,name,surname,addr,yearOfBirthday);
        this.organization = org;
        this.addr = addr;
        this.nameStaff = nameStaff;
    }

    public Person(String role,String name,String surname, String addr, Integer yearOfBirthday){
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.addr = addr;
        this.yearOfBirthday = yearOfBirthday;
        Log.d("Person", "Create object Person");
    }

    public Person(String role,String name,String surname, String addr, Integer yearOfBirthday, Organization org,String nameStaff,String phone, String email, String messanger){
        this(role,name,surname,yearOfBirthday,addr,org,nameStaff);
        this.email = email;
        this.messanger = messanger;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        Log.d("Person", "Set new value of Name");
    }

    public String getRole() {
        return role;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
        Log.d("Person", "Set new value of Phone");
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
        Log.d("Person", "Set new value of email");
    }

    public String getMessanger() {
        return messanger;
    }
    public void setMessanger(String messanger) {
        this.messanger = messanger;
        Log.d("Person", "Set new value of Messanger");
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

    public String getStaff() {return nameStaff.toString();
    }

    @Override
    public String toString(){
        return "Staff " + this.getStaff() +"\n" + this.getRole() + "\nName: "+this.getName()+" Surname: " + this.getSurname();
    }
}
