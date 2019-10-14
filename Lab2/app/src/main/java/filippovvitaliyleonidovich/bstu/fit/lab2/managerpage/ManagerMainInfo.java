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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import filippovvitaliyleonidovich.bstu.fit.lab2.MainActivity;
import filippovvitaliyleonidovich.bstu.fit.lab2.R;
import filippovvitaliyleonidovich.bstu.fit.lab2.StaffInfo;
import filippovvitaliyleonidovich.bstu.fit.lab2.WorkWithFile;
import filippovvitaliyleonidovich.bstu.fit.lab2.WorkWithFileJSON;
import filippovvitaliyleonidovich.bstu.fit.lab2.enums.PersonInfo;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.manager.Manager;

import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.DATE_FORMAT;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.MANAGER_TXT_NAME;
import static java.util.Locale.getDefault;

public class ManagerMainInfo extends AppCompatActivity {

    private String name;
    private String surname;
    private String addr;
    private String birthday;
    private boolean flagSaveButton = false;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main_info);
        Log.d("entry","create");
        setInfo(getIntent());
    }


    private void setInfo(final Intent intent){
        name = intent.getStringExtra(PersonInfo.NAME.name());
        Log.d("entry",name);
        surname = intent.getStringExtra(PersonInfo.SURNAME.name());
        Log.d("entry",surname);

        addr = intent.getStringExtra(PersonInfo.ADDR.name());
        Log.d("entry",addr);

        birthday = intent.getStringExtra(PersonInfo.BIRTHDAY.name());
        Log.d("entry",birthday);

        flagSaveButton = intent.getBooleanExtra("flag",true);

        final TextView textName = findViewById(R.id.name);
        textName.setText(name);
        final TextView textSurname = findViewById(R.id.surname);
        textSurname.setText(surname);
        final TextView textBthd = findViewById(R.id.birthday);
        textBthd.setText(calculateAge(birthday));
        final TextView textAddr = findViewById(R.id.addr);
        textAddr.setText(addr);
        final TableRow buttSave = findViewById(R.id.hidden_row);
        if(flagSaveButton){
            buttSave.setVisibility(View.VISIBLE);
        }
        else{
            buttSave.setVisibility(View.INVISIBLE);
        }
        Log.d("entry","set");
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
        Log.d("main_manager",surname);
        final Manager manager = new Manager(name, surname,addr, Integer.parseInt(birthday.substring(birthday.length() - 4)));
        final WorkWithFile wf = new WorkWithFile(getFilesDir() + MANAGER_TXT_NAME);
        WorkWithFileJSON<Manager> wfJson = new WorkWithFileJSON<Manager>(wf);
        wfJson.saveAsJson(manager);
        Log.d("main_manager","Save manager");
        onBackPressed();
    }

    public void onClickGenerateFromFile(View view){

    }

    public void onClickRandomGenerate(View view){

    }

    public void onClickGoToStaff(View view){
        RadioGroup radioGroup = findViewById(R.id.radio_group_staff);
        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        String staff = radioButton.getText().toString();
        Button button = findViewById(R.id.but_gotostaff);
        button.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, StaffInfo.class);
        intent.putExtra("Staff",staff);
        startActivity(intent);
        button.setVisibility(View.INVISIBLE);
        radioButton.setChecked(false);

    }
}
