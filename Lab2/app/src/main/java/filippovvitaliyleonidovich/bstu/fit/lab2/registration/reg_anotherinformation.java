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
import filippovvitaliyleonidovich.bstu.fit.lab2.WorkWithFile;
import filippovvitaliyleonidovich.bstu.fit.lab2.managerpage.ManagerMainInfo;
import filippovvitaliyleonidovich.bstu.fit.lab2.studentpage.studentInfo;

public class reg_anotherinformation extends AppCompatActivity {
    String role;
    String name;
    String surname;
    String birthday;
    String addr;
    DatePicker datePicker;
    SharedPreferences mSettings;
    boolean flag_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_anotherinformation);
        flag_save = true;
        mSettings = getSharedPreferences("param", Context.MODE_PRIVATE);
        getStateFromSharePreferences();
        Intent intent = getIntent();
        role = intent.getStringExtra("role");
        name = intent.getStringExtra("name");
        surname = intent.getStringExtra("surname");
        TextView text = findViewById(R.id.prev_info);
        text.setText("Role: "+role +"\n"+"Name and surname: " + name + " " + surname);
        Log.d("reg_org", "create called");
    }

    public void onClickSave(View view){
        Intent intent;
        if(role.equalsIgnoreCase("manager")){
            intent = new Intent(this, ManagerMainInfo.class);
        }
        else{
            intent = new Intent(this, studentInfo.class);
        }
        datePicker = (DatePicker) findViewById(R.id.edit_birthday);
        birthday = datePicker.getDayOfMonth()+"."+datePicker.getMonth()+"."+datePicker.getYear();
        intent.putExtra("role",role);
        intent.putExtra("name", name);
        intent.putExtra("surname", surname);
        EditText ed_addr = findViewById(R.id.edit_addr);
        addr = ed_addr.getText().toString();
        if(!birthday.isEmpty() && !addr.isEmpty()) {
            intent.putExtra("addr", addr);
            intent.putExtra("birthday", birthday);
            startActivity(intent);
        }
        deleteEbuchiFile();
        flag_save = false;
    }

    private void deleteEbuchiFile(){
        SharedPreferences.Editor editor = mSettings.edit();
        editor.clear();
        editor.apply();
    }

    private boolean saveInfoJSON(){
        return true;
    }

    public void onClickPrev(View view){
        onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d("reg_org", "savestate called");
    }

    @Override
    protected void onStop(){
        super.onStop();
        saveStateInSharePreferences();
        Log.d("reg_org", "stop called");

    }

    @Override
    protected  void onStart(){
        super.onStart();
        Log.d("reg_org", "start called");
    }

    @Override
    protected void onPause(){
        super.onPause();
        saveStateInSharePreferences();
        Log.d("reg_org", "pause called");
    }

    @Override
    protected void onResume(){
        super.onResume();
        getStateFromSharePreferences();
        Log.d("reg_org", "resume called");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("reg_org", "destroy called");
    }

    private void saveStateInSharePreferences(){
        if(flag_save) {
            datePicker = (DatePicker) findViewById(R.id.edit_birthday);
            birthday = datePicker.getDayOfMonth()+"."+datePicker.getMonth()+"."+datePicker.getYear();
            EditText ed_addr = findViewById(R.id.edit_addr);
            addr = ed_addr.getText().toString();
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString("birthday", birthday);
            editor.putString("addr", addr);
            editor.apply();
        }
    }

    private void getStateFromSharePreferences(){
        if(mSettings.contains("addr")) {
            addr = mSettings.getString("addr", "");
            EditText ed_addr = findViewById(R.id.edit_addr);
            ed_addr.setText(addr);
        }
        if(mSettings.contains("birthday")) {
            birthday = mSettings.getString("birthday", "");
            datePicker = (DatePicker) findViewById(R.id.edit_birthday);
            String[] parts = birthday.split("[.]");
            int year =Integer.valueOf(parts[2]) ;
            int month = Integer.valueOf(parts[1]);
            int day = Integer.valueOf(parts[0]);
            datePicker.updateDate(year+1-1,month-1+1,day+1-1);
        }
        if(mSettings.contains("role")) {
            role = mSettings.getString("role", "");
        }
        if(mSettings.contains("name")) {
            name = mSettings.getString("name", "");
        }
        if(mSettings.contains("surname")) {
            surname = mSettings.getString("surname", "");
        }
    }

}
