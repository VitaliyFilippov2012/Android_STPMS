package filippovvitaliyleonidovich.bstu.fit.lab2.managerpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import filippovvitaliyleonidovich.bstu.fit.lab2.MainActivity;
import filippovvitaliyleonidovich.bstu.fit.lab2.R;
import filippovvitaliyleonidovich.bstu.fit.lab2.WorkWithFile;
import filippovvitaliyleonidovich.bstu.fit.lab2.enums.PersonInfo;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Manager;

import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.DATE_FORMAT;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.MANAGER_TXT_NAME;
import static java.util.Locale.getDefault;

public class ManagerMainInfo extends AppCompatActivity {

    private String role;

    private String name;

    private String surname;

    private String addr;

    private String birthday;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main_info);
        setInfo(getIntent());
    }

    private void setInfo(final Intent intent){
        role = intent.getStringExtra(PersonInfo.ROLE.name());
        name = intent.getStringExtra(PersonInfo.NAME.name());
        surname = intent.getStringExtra(PersonInfo.SURNAME.name());
        addr = intent.getStringExtra(PersonInfo.ADDR.name());
        birthday = intent.getStringExtra(PersonInfo.BIRTHDAY.name());

        final TextView textName = findViewById(R.id.name);
        textName.setText(name);

        final TextView textSurname = findViewById(R.id.surname);
        textSurname.setText(surname);

        final TextView textBthd = findViewById(R.id.birthday);
        textBthd.setText(calculateAge(birthday.substring(birthday.length() - 4)));

        final TextView textAddr = findViewById(R.id.addr);
        textAddr.setText(addr);
    }

    private String calculateAge(final String year){
        final Date currentDate = new Date();
        final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, getDefault());
        final String dateText = dateFormat.format(currentDate).substring(dateFormat.format(currentDate).length() - 4);
        return String.valueOf(Integer.valueOf(dateText) - Integer.valueOf(year));
    }

    @Override
    public void onBackPressed() {
        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void saveInfo(final View view){
        final Manager manager = new Manager(name, surname, Integer.parseInt(calculateAge(birthday)),
                addr, role, birthday);
        final Gson gson = new Gson();
        final String jsonString = gson.toJson(manager);
        final WorkWithFile wf = new WorkWithFile(getFilesDir() + MANAGER_TXT_NAME);
        if (!jsonString.equals("")) {
            wf.writeFile(jsonString);
        }
    }
}
