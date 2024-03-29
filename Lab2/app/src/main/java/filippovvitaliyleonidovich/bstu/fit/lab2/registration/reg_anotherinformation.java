package filippovvitaliyleonidovich.bstu.fit.lab2.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import filippovvitaliyleonidovich.bstu.fit.lab2.R;
import filippovvitaliyleonidovich.bstu.fit.lab2.enums.PersonInfo;
import filippovvitaliyleonidovich.bstu.fit.lab2.managerpage.ManagerMainInfo;
import filippovvitaliyleonidovich.bstu.fit.lab2.studentpage.studentInfo;

import static java.lang.String.format;

public class reg_anotherinformation extends AppCompatActivity {

    private String role;
    private String name;
    private String surname;
    private String birthday;
    private String addr;
    private String phone;
    private String email;
    private String messanger;
    private DatePicker datePicker;
    private SharedPreferences settings;
    private boolean flag_save;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_anotherinformation);

        flag_save = true;
        settings = getSharedPreferences("param", Context.MODE_PRIVATE);
        getStateFromSharePreferences();

        final Intent intent = getIntent();
        role = intent.getStringExtra(PersonInfo.ROLE.name());
        name = intent.getStringExtra(PersonInfo.NAME.name());
        surname = intent.getStringExtra(PersonInfo.SURNAME.name());
        phone = intent.getStringExtra(PersonInfo.Phone.name());
        messanger = intent.getStringExtra(PersonInfo.Messanger.name());
        email = intent.getStringExtra(PersonInfo.Email.name());

        final TextView text = findViewById(R.id.prev_info);
        text.setText(format("Role: %s\nName and surname: %s %s", role, name, surname));
    }

    public void onClickSave(final View view){
        final Intent intent;
        if(role.equalsIgnoreCase("manager")){
            intent = new Intent(this, ManagerMainInfo.class);
        }
        else{
            intent = new Intent(this, studentInfo.class);
        }
        datePicker = findViewById(R.id.edit_birthday);
        birthday = String.valueOf(datePicker.getYear());
        intent.putExtra(PersonInfo.ROLE.name(),role);
        intent.putExtra(PersonInfo.NAME.name(), name);
        intent.putExtra(PersonInfo.SURNAME.name(), surname);
        intent.putExtra(PersonInfo.Email.name(), email);
        intent.putExtra(PersonInfo.Phone.name(), phone);
        intent.putExtra(PersonInfo.Messanger.name(), messanger);
        intent.putExtra("flag", "0");
        final EditText ed_addr = findViewById(R.id.edit_addr);
        addr = ed_addr.getText().toString();
        if (!birthday.isEmpty() && !addr.isEmpty()) {
            intent.putExtra(PersonInfo.ADDR.name(), addr);
            intent.putExtra(PersonInfo.BIRTHDAY.name(), birthday.substring(birthday.length()-4));
            startActivity(intent);
        }
        deleteFileSet();
        flag_save = false;
    }

    private void deleteFileSet(){
        final SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.apply();
    }


    public void onClickPrev(View view){
        onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onStop(){
        super.onStop();
        saveStateInSharePreferences();
    }

    @Override
    protected  void onStart(){
        super.onStart();
    }

    @Override
    protected void onPause(){
        super.onPause();
        saveStateInSharePreferences();
    }

    @Override
    protected void onResume(){
        super.onResume();
        getStateFromSharePreferences();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    private void saveStateInSharePreferences(){
        if(flag_save) {
            datePicker = findViewById(R.id.edit_birthday);
            birthday = datePicker.getDayOfMonth()+"."+datePicker.getMonth()+"."+datePicker.getYear();
            EditText ed_addr = findViewById(R.id.edit_addr);
            addr = ed_addr.getText().toString();
            SharedPreferences.Editor editor = settings.edit();
            editor.putString(PersonInfo.BIRTHDAY.name(), birthday);
            editor.putString(PersonInfo.ADDR.name(), addr);
            editor.apply();
        }
    }

    private void getStateFromSharePreferences(){
        if(settings.contains(PersonInfo.ADDR.name())) {
            addr = settings.getString(PersonInfo.ADDR.name(), "");
            EditText ed_addr = findViewById(R.id.edit_addr);
            ed_addr.setText(addr);
        }
        if(settings.contains(PersonInfo.BIRTHDAY.name())) {
            birthday = settings.getString(PersonInfo.BIRTHDAY.name(), "");
            datePicker = (DatePicker) findViewById(R.id.edit_birthday);
            String[] parts = birthday.split("[.]");
            int year =Integer.valueOf(parts[2]) ;
            int month = Integer.valueOf(parts[1]);
            int day = Integer.valueOf(parts[0]);
            datePicker.updateDate(year+1-1,month-1+1,day+1-1);
        }
        if(settings.contains(PersonInfo.ROLE.name())) {

            role = settings.getString(PersonInfo.ROLE.name(), "");
        }
        if(settings.contains(PersonInfo.NAME.name())) {
            name = settings.getString(PersonInfo.NAME.name(), "");
        }
        if(settings.contains(PersonInfo.SURNAME.name())) {
            surname = settings.getString(PersonInfo.SURNAME.name(), "");
        }
    }

}
