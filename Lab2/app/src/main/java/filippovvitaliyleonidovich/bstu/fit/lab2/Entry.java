package filippovvitaliyleonidovich.bstu.fit.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.stream.Stream;

import filippovvitaliyleonidovich.bstu.fit.lab2.enums.PersonInfo;
import filippovvitaliyleonidovich.bstu.fit.lab2.managerpage.ManagerMainInfo;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Manager;
import lombok.var;

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

        final WorkWithFile wf = new WorkWithFile(getFilesDir() + MANAGER_TXT_NAME);
        final String stringFromFile = wf.readFile();

        final Gson gson = new Gson();
        final var findingManager = Stream.of(gson.fromJson(stringFromFile, Manager[].class))
                .filter(manager -> manager.getName().equals(name)
                        && manager.getSurname().equals(surname))
                .findFirst();

        findingManager.ifPresent(manager -> {
            Intent intent = new Intent(this, ManagerMainInfo.class);
            intent.putExtra(PersonInfo.NAME.name(), manager.getName());
            intent.putExtra(PersonInfo.SURNAME.name(), manager.getSurname());
            intent.putExtra(PersonInfo.ROLE.name(), manager.getRole());
            intent.putExtra(PersonInfo.ADDR.name(), manager.getAddr());
            intent.putExtra(PersonInfo.BIRTHDAY.name(), manager.getBirthday());
            startActivity(intent);
        });
    }
}
