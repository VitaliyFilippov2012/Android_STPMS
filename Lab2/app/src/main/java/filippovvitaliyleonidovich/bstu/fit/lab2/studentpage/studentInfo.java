package filippovvitaliyleonidovich.bstu.fit.lab2.studentpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import filippovvitaliyleonidovich.bstu.fit.lab2.MainActivity;
import filippovvitaliyleonidovich.bstu.fit.lab2.R;
import filippovvitaliyleonidovich.bstu.fit.lab2.WorkWithFile;
import filippovvitaliyleonidovich.bstu.fit.lab2.WorkWithFileJSON;
import filippovvitaliyleonidovich.bstu.fit.lab2.enums.PersonInfo;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.organization.Organization;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Listener;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Person;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Student;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.DATE_FORMAT;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.LISTENERS_TXT_NAME;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.STUDENTS_TXT_NAME;

public class studentInfo extends AppCompatActivity {

    private String role;
    private String name;
    private String surname;
    private String addr;
    private String birthday;
    private Organization organization;
    private String compareType;
    WorkWithFile wf;
    TextView text_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudent_info);
        setInfo(getIntent());
    }
    private void setInfo(Intent intent){
        role = intent.getStringExtra(PersonInfo.ROLE.name());
        TextView compareType = findViewById(R.id.text_compareType);
        text_info = findViewById(R.id.text_info);
        if(role.equals("Student")){
            compareType.setText(R.string.text_mark);
            wf = new WorkWithFile(getFilesDir()+STUDENTS_TXT_NAME);
            loadInfo(new TypeToken<ArrayList<Student>>(){}.getType());
        }
        else {
            compareType.setText(R.string.text__rating);
            wf = new WorkWithFile(getFilesDir()+LISTENERS_TXT_NAME);
            loadInfo(new TypeToken<ArrayList<Listener>>(){}.getType());
        }
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
    }

    private void loadInfo(Type type){
        WorkWithFileJSON<Person> workWithFileJSON = new WorkWithFileJSON<Person>(wf);
        ArrayList<Person> arrayList = workWithFileJSON.deserialize(type);
        String str = "NOTNING";

        for(Person p:arrayList ){
            str = str + p.toString()+"\n";
        }

        text_info.setText(str);
    }
    private String calculateAge(String year){
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
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
        Spinner org = (Spinner) findViewById(R.id.org);
        String selected_org = org.getSelectedItem().toString();
        Spinner staff = (Spinner) findViewById(R.id.org);
        String selected_staff = staff.getSelectedItem().toString();
        EditText compareType = findViewById(R.id.compareType);
        WorkWithFileJSON<Person> wfJson = new WorkWithFileJSON<Person>(wf);

        if(selected_org.equals("BSU")){
            organization = Organization.BSU;
        }
        else{
            if(selected_org.equals("BSTU")){
                organization = Organization.BSTU;
            }
            else{
                organization = Organization.BSUIR;
            }
        }
        if(role.equals("Student")){
            Student student = new Student(name,surname,Integer.parseInt(calculateAge(birthday)),addr,organization,Double.valueOf(compareType.getText().toString()),selected_staff);

            wfJson.saveAsJson(student);
        }
        if(role.equals("listener")) {
            Listener listener = new Listener(name, surname, Integer.parseInt(birthday),addr,organization,Double.valueOf(compareType.getText().toString()),selected_staff);
            wfJson.saveAsJson(listener);
        }

    }
}
