package filippovvitaliyleonidovich.bstu.fit.lab2.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import filippovvitaliyleonidovich.bstu.fit.lab2.R;
import filippovvitaliyleonidovich.bstu.fit.lab2.WorkWithFile;
import filippovvitaliyleonidovich.bstu.fit.lab2.managerpage.ManagerMainInfo;
import filippovvitaliyleonidovich.bstu.fit.lab2.studentpage.studentInfo;

public class reg_anotherinformation extends AppCompatActivity {
    String role;
    String name;
    String surname;
    String birthday;
    String longBirthday;
    String addr;
    CalendarView mCalendarView;
    SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_anotherinformation);
        mSettings = getSharedPreferences("param", Context.MODE_PRIVATE);
        getStateFromSharePreferences();
        mCalendarView = (CalendarView)findViewById(R.id.edit_birthday);
        longBirthday = String.valueOf(mCalendarView.getDate());
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(CalendarView view, int year,int month, int dayOfMonth) {birthday = String.valueOf(dayOfMonth)+"."+String.valueOf(month)+"."+String.valueOf(year); }});
        Intent intent = getIntent();
        role = intent.getStringExtra("role");
        name = intent.getStringExtra("name");
        surname = intent.getStringExtra("surname");
        EditText ed_role = (EditText)findViewById(R.id.prev_info);
        ed_role.setText("Role: "+role +"\n"+"Name and surname: " + name + " " + surname);
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
        SharedPreferences.Editor  editor = mSettings.edit();
        editor.clear();
        editor.apply();
        WorkWithFile.deleteFile("/data/data/.../shared_prefs/param.xml");
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
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString("role",role);
        editor.putString("name",name);
        editor.putString("surname",surname);
        editor.putString("birthday",birthday);
        editor.putString("lbirthday",longBirthday);
        editor.putString("addr",addr);
        editor.apply();
    }

    private void getStateFromSharePreferences(){
        if(mSettings.contains("addr")) {
            addr = mSettings.getString("addr", "");
            EditText ed_addr = findViewById(R.id.edit_addr);
            ed_addr.setText(addr);
        }
        if(mSettings.contains("birthday")) {
            birthday = mSettings.getString("lbirthday", "");
        }
        if(mSettings.contains("lbirthday")) {
            longBirthday = mSettings.getString("lbirthday", "");
            CalendarView calendarView = findViewById(R.id.edit_birthday);
            calendarView.setDate(Long.valueOf(longBirthday));
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
