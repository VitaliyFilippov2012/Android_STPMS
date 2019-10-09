package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class Listener extends Person{

    private Double reit;

    public Listener(String name, Integer age, Organization org, Double reit) {
        super(name, age, org);
        this.reit = reit;
        Log.d("Listener", "Create new object Listener");
    }

    public int compare(Listener p1, Listener p2) {
        return (int)(p1.getReit() - p2.getReit());
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
                    ", age=" + getAge() +
                    '}';
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
