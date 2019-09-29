package filippovvitaliyleonidovich.bstu.fit.lab2.studentpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import filippovvitaliyleonidovich.bstu.fit.lab2.MainActivity;
import filippovvitaliyleonidovich.bstu.fit.lab2.R;
import filippovvitaliyleonidovich.bstu.fit.lab2.WorkWithFile;
import filippovvitaliyleonidovich.bstu.fit.lab2.enums.PersonInfo;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Listener;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Student;

public class studentInfo extends AppCompatActivity {

    private String role;

    private String name;

    private String surname;

    private String addr;

    private String birthday;

    private String organization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudent_info);
        setInfo(getIntent());
    }
    private void setInfo(Intent intent){
        role = intent.getStringExtra(PersonInfo.ROLE.name());
        name = intent.getStringExtra(PersonInfo.NAME.name());
        surname = intent.getStringExtra(PersonInfo.SURNAME.name());
        addr = intent.getStringExtra(PersonInfo.ADDR.name());
        birthday = intent.getStringExtra(PersonInfo.BIRTHDAY.name());
        TextView textRole = findViewById(R.id.role);
        textRole.setText(role);
        TextView textName = findViewById(R.id.name);
        textName.setText(name);
        TextView textSurname = findViewById(R.id.surname);
        textSurname.setText(surname);
        TextView textBthd = findViewById(R.id.birthday);
        textBthd.setText(calculateAge(birthday.substring(birthday.length()-4)));
        TextView textAddr = findViewById(R.id.addr);
        textAddr.setText(addr);
        TextView text_info = findViewById(R.id.text_info);
        //WorkWithFile wf = new WorkWithFile(getFilesDir()+"Person.txt");
        //Log.d("Wread",wf.readFile());

    }

    private String calculateAge(String year){
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate).substring(dateFormat.format(currentDate).length()-4);
        String age = String.valueOf(Integer.valueOf(dateText) - Integer.valueOf(year));
        return age;
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void saveInfo(View view){
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String selected = spinner.getSelectedItem().toString();
        String jsonString = "";
        organization = selected;
        if(role.equals("Student")){
            Student student = new Student(name,surname,Integer.parseInt(calculateAge(birthday)),9,organization);
            Gson gson = new Gson();
            jsonString = gson.toJson(student);
        }
        if(role.equals("listener")) {
            Listener listener = new Listener(name, surname, Integer.parseInt(birthday),organization);
            Gson gson = new Gson();
            jsonString = gson.toJson(listener);
        }
        WorkWithFile wf = new WorkWithFile(getFilesDir()+"Person.txt");
        if(!jsonString.equals("")){
            wf.writeFile(jsonString);
        }
    }
}
