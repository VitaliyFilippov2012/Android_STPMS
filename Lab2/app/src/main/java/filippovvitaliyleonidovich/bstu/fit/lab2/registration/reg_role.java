package filippovvitaliyleonidovich.bstu.fit.lab2.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import filippovvitaliyleonidovich.bstu.fit.lab2.R;
import filippovvitaliyleonidovich.bstu.fit.lab2.enums.PersonInfo;

public class reg_role extends AppCompatActivity {

    private String role;

    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_role);
        settings = getSharedPreferences("param", Context.MODE_PRIVATE);
        getStateFromSharePreferences();
    }

    public void onClickNext(View view){
        Intent intent = new Intent(this, reg_name.class);
        RadioGroup radioGroup = findViewById(R.id.radio_group_staff);
        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        role = radioButton.getText().toString();
        intent.putExtra(PersonInfo.ROLE.name(),role);
        startActivity(intent);
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
        SharedPreferences.Editor editor = settings.edit();
        RadioGroup radioGroup = findViewById(R.id.radio_group_staff);
        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        role = radioButton.getText().toString();
        editor.putString(PersonInfo.ROLE.name(),role);
        editor.apply();
    }

    private void getStateFromSharePreferences() {
        if (settings.contains(PersonInfo.ROLE.name())) {
            role = settings.getString(PersonInfo.ROLE.name(), "");
            RadioButton radioButton;
            if (role.equalsIgnoreCase("manager")) {
                radioButton = findViewById(R.id.radioButton3);
            } else {
                if (role.equalsIgnoreCase("student")) {
                    radioButton = findViewById(R.id.radioButton1);
                } else {
                    radioButton = findViewById(R.id.radioButton2);
                }
            }
            radioButton.setChecked(true);
        }
    }

}
