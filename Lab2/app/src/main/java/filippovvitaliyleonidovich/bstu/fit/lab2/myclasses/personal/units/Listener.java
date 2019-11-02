package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units;


import android.util.Log;

import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.organization.Organization;

public class Listener extends Person{

    private Double reit;

    public Listener(String name,String surname, Integer yearOfbirthday, String addr, Organization org, Double reit,String nameStaff,String phone, String email,String messanger) {
        super("Listener",name,surname,addr,yearOfbirthday,org,nameStaff,phone,email,messanger);
        this.reit = reit;
        Log.d("Listener", "Create new object Listener");
    }


    public Double getReit() {
        return reit;
    }
    public void setReit(Double reit) {
        this.reit = reit;
        Log.d("Listener", "Set new value of Reit");
    }

    @Override
    public String toString() {
        try {
            return "Listener{" +
                    "name='" + getName() + ' ' +
                    ", surname='" + getSurname() + ' ' +
                    ", year of birthday=" + getYearOfBirthday() +
                    '}';
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public int compareTo(Person p2) {
        Listener p22 = (Listener)p2;
        return (int)(this.getReit() - p22.getReit());
    }
}
