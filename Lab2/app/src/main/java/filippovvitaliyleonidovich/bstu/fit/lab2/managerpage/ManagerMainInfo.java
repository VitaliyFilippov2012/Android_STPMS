package filippovvitaliyleonidovich.bstu.fit.lab2.managerpage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.gson.reflect.TypeToken;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import filippovvitaliyleonidovich.bstu.fit.lab2.MainActivity;
import filippovvitaliyleonidovich.bstu.fit.lab2.R;
import filippovvitaliyleonidovich.bstu.fit.lab2.StaffInfo;
import filippovvitaliyleonidovich.bstu.fit.lab2.WorkWithFile;
import filippovvitaliyleonidovich.bstu.fit.lab2.WorkWithFileJSON;
import filippovvitaliyleonidovich.bstu.fit.lab2.enums.PersonInfo;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.organization.Organization;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Listener;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Student;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.manager.Manager;

import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.DATE_FORMAT;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.LISTENERS_TXT_NAME;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.STUDENTS_TXT_NAME;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.MANAGER_TXT_NAME;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.STAFF_L_JAVA_TXT_NAME;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.STAFF_L_NET_TXT_NANE;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.STAFF_S_JAVA_TXT_NAME;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.STAFF_S_NET_TXT_NANE;
import static java.util.Locale.getDefault;

public class ManagerMainInfo extends AppCompatActivity {

    private String name;
    private String surname;
    private String addr;
    private String birthday;
    private boolean flagSaveButton = false;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main_info);
        setInfo(getIntent());

        radioGroup = (RadioGroup) findViewById(R.id.radio_group_staff);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
            Button button = findViewById(R.id.but_gotostaff);
            if(checkedRadioButton.isChecked()){
                button.setEnabled(true);
            }
            else{
                button.setEnabled(false);

            }
        });
    }


    private void setInfo(final Intent intent){
        name = intent.getStringExtra(PersonInfo.NAME.name());
        surname = intent.getStringExtra(PersonInfo.SURNAME.name());
        addr = intent.getStringExtra(PersonInfo.ADDR.name());
        birthday = intent.getStringExtra(PersonInfo.BIRTHDAY.name());
        flagSaveButton = intent.getBooleanExtra("flag",true);

        final TextView textName = findViewById(R.id.name);
        textName.setText(name);
        final TextView textSurname = findViewById(R.id.surname);
        textSurname.setText(surname);
        final TextView textBthd = findViewById(R.id.birthday);
        textBthd.setText(calculateAge(birthday));
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
        final Manager manager = new Manager(name, surname,addr, Integer.parseInt(birthday.substring(birthday.length() - 4)));
        final WorkWithFile wf = new WorkWithFile(getFilesDir() + MANAGER_TXT_NAME);
        WorkWithFileJSON<Manager> wfJson = new WorkWithFileJSON<Manager>(wf);
        wfJson.saveAsJson(manager);
        Log.d("MyApp","Create manager");
        onBackPressed();
    }

    public void onClickGoToStaff(View view){
        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        String staff = radioButton.getText().toString();
        Intent intent = new Intent(this, StaffInfo.class);
        Log.d("MyApp","GoTo staff");
        intent.putExtra("Staff",staff);
        startActivity(intent);
    }
}
