package filippovvitaliyleonidovich.bstu.fit.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import filippovvitaliyleonidovich.bstu.fit.lab2.enums.PersonInfo;
import filippovvitaliyleonidovich.bstu.fit.lab2.managerpage.ManagerMainInfo;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.manager.Manager;

import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.MANAGER_TXT_NAME;

public class Entry extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
    }

    public void onClickEntry(View view){
        final EditText ed_name = findViewById(R.id.editText_name);
        final String name = ed_name.getText().toString();

        final EditText ed_surname = findViewById(R.id.editText_surname);
        final String surname = ed_surname.getText().toString();
        Log.d("entry", "ButtDown");

        final WorkWithFile wf = new WorkWithFile(getFilesDir() + MANAGER_TXT_NAME);
        WorkWithFileJSON<Manager> wfJson = new WorkWithFileJSON<Manager>(wf);
        ArrayList<Manager> findManagers = wfJson.deserialize(new TypeToken<Manager>(){}.getType());
        Optional<Manager> manager = Optional.of(getManager(findManagers,name,surname));
        manager.ifPresent(m -> {
            Intent intent = new Intent(this, ManagerMainInfo.class);
            intent.putExtra(PersonInfo.NAME.name(), m.getName());
            intent.putExtra(PersonInfo.SURNAME.name(), m.getSurname());
            intent.putExtra(PersonInfo.ADDR.name(), m.getAddr());
            intent.putExtra(PersonInfo.BIRTHDAY.name(), String.valueOf(m.getYearOfBirthday()));
            intent.putExtra("flag",false);
            Log.d("entry","start");
            startActivity(intent);
       });
    }

    private Manager getManager(ArrayList<Manager> managers,String name, String surname) {
        for(Manager m:managers){
            if (m.getName().equals(name) && m.getSurname().equals(surname)) {
                return m;
            }

        }
        return null;
    }
}
