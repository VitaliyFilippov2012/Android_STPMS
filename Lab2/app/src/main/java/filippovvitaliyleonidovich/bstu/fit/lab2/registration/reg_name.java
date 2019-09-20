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

public class reg_name extends AppCompatActivity {

    String role;
    String name;
    String surname;
    SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_name);
        mSettings = getSharedPreferences("param", Context.MODE_PRIVATE);
        getStateFromSharePreferences();
        Intent intent = getIntent();
        role = intent.getStringExtra("role");
        TextView textView = findViewById(R.id.prev_info);
        textView.setText("Role: "+role);
    }

    public void onClickSignIn(View view){
        Intent intent = new Intent(this, reg_anotherinformation.class);
        intent.putExtra("role",role);
        EditText ed_name = (EditText) findViewById(R.id.editText_name);
        EditText ed_surname = (EditText) findViewById(R.id.editText_surname);
        surname = ed_surname.getText().toString();
        name = ed_name.getText().toString();
        if(!name.isEmpty() && !surname.isEmpty()) {
            intent.putExtra("name", name);
            intent.putExtra("surname", surname);
            startActivity(intent);
        }
    }

    public void onClickPrev(View view){
        onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
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
        EditText ed_name = (EditText) findViewById(R.id.editText_name);
        EditText ed_surname = (EditText) findViewById(R.id.editText_surname);
        surname = ed_surname.getText().toString();
        name = ed_name.getText().toString();
        editor.putString("name",name);
        editor.putString("surname",surname);
        editor.apply();
    }

    private void getStateFromSharePreferences(){
        if(mSettings.contains("role")) {
            role = mSettings.getString("role", "");
        }
        if(mSettings.contains("name")) {
            name = mSettings.getString("name", "");
            EditText ed = (EditText)findViewById(R.id.editText_name);
            ed.setText(name);
        }
        if(mSettings.contains("surname")) {
            surname = mSettings.getString("surname", "");
            EditText ed = (EditText)findViewById(R.id.editText_surname);
            ed.setText(surname);
        }
    }

}
