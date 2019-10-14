package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units;


import android.util.Log;

import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.organization.Organization;

public class Listener extends Person{

    private Double reit;

    public Listener(String name,String surname, Integer yearOfbirthday, String addr, Organization org, Double reit,String nameStaff) {
        super("Listener",name,surname,yearOfbirthday,addr,org,nameStaff);
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
                    "name='" + getName() + '\'' +
                    ", surname='" + getSurname() + '\'' +
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
