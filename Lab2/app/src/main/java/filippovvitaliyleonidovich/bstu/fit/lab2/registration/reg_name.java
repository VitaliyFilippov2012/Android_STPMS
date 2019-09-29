package filippovvitaliyleonidovich.bstu.fit.lab2.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import filippovvitaliyleonidovich.bstu.fit.lab2.R;
import filippovvitaliyleonidovich.bstu.fit.lab2.enums.PersonInfo;

public class reg_name extends AppCompatActivity {

    private String role;

    private String name;

    private String surname;

    private SharedPreferences mSettings;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_name);
        mSettings = getSharedPreferences("param", Context.MODE_PRIVATE);
        getStateFromSharePreferences();
        Intent intent = getIntent();
        role = intent.getStringExtra(PersonInfo.ROLE.name());
        TextView textView = findViewById(R.id.prev_info);
        textView.setText("Role: "+role);
    }

    public void onClickSignIn(final View view){
        final Intent intent = new Intent(this, reg_anotherinformation.class);
        intent.putExtra(PersonInfo.ROLE.name(),role);
        final EditText ed_name = findViewById(R.id.editText_name);
        final EditText ed_surname = findViewById(R.id.editText_surname);
        surname = ed_surname.getText().toString();
        name = ed_name.getText().toString();
        if(!name.isEmpty() && !surname.isEmpty()) {
            intent.putExtra(PersonInfo.NAME.name(), name);
            intent.putExtra(PersonInfo.SURNAME.name(), surname);
            startActivity(intent);
        }
    }

    public void onClickPrev(final View view){
        onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(final Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d("reg_name", "savestate called");
    }

    @Override
    protected void onStop(){
        super.onStop();
        saveStateInSharePreferences();
        Log.d("reg_name", "stop called");
    }

    @Override
    protected  void onStart(){
        super.onStart();
        Log.d("reg_name", "start called");
    }

    @Override
    protected void onPause(){
        super.onPause();
        saveStateInSharePreferences();
        Log.d("reg_name", "pause called");
    }

    @Override
    protected void onResume(){
        super.onResume();
        getStateFromSharePreferences();
        Log.d("reg_name", "resume called");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("reg_name", "destroy called");

    }
    private void saveStateInSharePreferences(){
        SharedPreferences.Editor editor = mSettings.edit();
        EditText ed_name = findViewById(R.id.editText_name);
        EditText ed_surname = findViewById(R.id.editText_surname);
        surname = ed_surname.getText().toString();
        name = ed_name.getText().toString();
        editor.putString(PersonInfo.NAME.name(),name);
        editor.putString(PersonInfo.SURNAME.name(),surname);
        editor.apply();
    }

    private void getStateFromSharePreferences(){
        if(mSettings.contains(PersonInfo.ROLE.name())) {
            role = mSettings.getString(PersonInfo.ROLE.name(), "");
        }
        if(mSettings.contains(PersonInfo.NAME.name())) {
            name = mSettings.getString(PersonInfo.NAME.name(), "");
            EditText ed = findViewById(R.id.editText_name);
            ed.setText(name);
        }
        if(mSettings.contains(PersonInfo.SURNAME.name())) {
            surname = mSettings.getString(PersonInfo.SURNAME.name(), "");
            EditText ed = findViewById(R.id.editText_surname);
            ed.setText(surname);
        }
    }

}
