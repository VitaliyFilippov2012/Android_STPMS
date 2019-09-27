package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class Listener extends Person implements ISerializable {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Listener(String name, String surname, int age, int year,String org) {
        super(name, surname, age, year,org);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Listener(String name, String surname, int age,String org) {
        super(name, surname, age,org);
    }

    @Override
    public String toString() {
        return "Listener{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean saveInfo() {
        return false;
    }

    @Override
    public Person loadInfo() {
        return null;
    }
}
