package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units;

import android.os.Build;

import androidx.annotation.RequiresApi;

public class Listener extends Person implements ISerializable {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Listener(final String name, final String surname, final int age, final int year,
                    final String org) {
        super(name, surname, age, year, org);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public Listener(final String name, final String surname, final int age, final String org) {
        super(name, surname, age, org);
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

    @Override
    public boolean saveInfo() {
        return false;
    }

    @Override
    public Person loadInfo() {
        return null;
    }
}
