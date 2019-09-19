package filippovvitaliyleonidovich.bstu.fit.lab2.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import filippovvitaliyleonidovich.bstu.fit.lab2.Entry;
import filippovvitaliyleonidovich.bstu.fit.lab2.MainActivity;
import filippovvitaliyleonidovich.bstu.fit.lab2.R;

public class reg_role extends AppCompatActivity {

    String role;
    SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_role);
        mSettings = getSharedPreferences("param", Context.MODE_PRIVATE);
        
        getStateFromSharePreferences();
        RadioButton radioButton;
        if(role.equalsIgnoreCase("manager")) {
            radioButton = findViewById(R.id.radioButton3);
        }
        else{
            if(role.equalsIgnoreCase("student")){
                radioButton = findViewById(R.id.radioButton1);
            }
            else{
                radioButton = findViewById(R.id.radioButton2);
            }
        }
        Log.d("reg_role",role);
        radioButton.setChecked(true);
    }

    public void onClickNext(View view){
        Intent intent = new Intent(this, reg_name.class);
        RadioGroup radioGroup = findViewById(R.id.radio_group_role);
        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        String role = radioButton.getText().toString();
        if(role != null){
            intent.putExtra("role",role);
            startActivity(intent);
        }

    }

    public void onClickPrev(View view){
        onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d("reg_role", "savestate called");
    }

    @Override
    protected void onStop(){
        super.onStop();
        saveStateInSharePreferences();
        Log.d("reg_role", "stop called");

    }

    @Override
    protected  void onStart(){
        super.onStart();
        Log.d("reg_role", "start called");
    }

    @Override
    protected void onPause(){
        super.onPause();
        saveStateInSharePreferences();
        Log.d("reg_role", "pause called");
    }

    @Override
    protected void onResume(){
        super.onResume();
        getStateFromSharePreferences();
        Log.d("reg_role", "resume called");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("reg_role", "destroy called");
    }

    private void saveStateInSharePreferences(){
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString("role",role);
        editor.apply();
    }

    private void getStateFromSharePreferences(){
        if(mSettings.contains("role")) {
            role = mSettings.getString("role", "");
        }
    }
}
