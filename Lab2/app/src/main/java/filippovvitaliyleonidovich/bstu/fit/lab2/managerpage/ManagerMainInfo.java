package filippovvitaliyleonidovich.bstu.fit.lab2.managerpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import filippovvitaliyleonidovich.bstu.fit.lab2.MainActivity;
import filippovvitaliyleonidovich.bstu.fit.lab2.R;
import filippovvitaliyleonidovich.bstu.fit.lab2.WorkWithFile;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Manager;

public class ManagerMainInfo extends AppCompatActivity {
    String role;
    String name;
    String surname;
    String addr;
    String birthday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main_info);
        setInfo(getIntent());
    }

    private void setInfo(Intent intent){
        role = intent.getStringExtra("role");
        name = intent.getStringExtra("name");
        surname = intent.getStringExtra("surname");
        addr = intent.getStringExtra("addr");
        birthday = intent.getStringExtra("birthday");
        TextView textName = findViewById(R.id.name);
        textName.setText(name);
        TextView textSurname = findViewById(R.id.surname);
        textSurname.setText(surname);
        TextView textBthd = findViewById(R.id.birthday);
        textBthd.setText(calculateAge(birthday.substring(birthday.length()-4)));
        TextView textAddr = findViewById(R.id.addr);
        textAddr.setText(addr);
    }

    private String calculateAge(String year){
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
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
        Manager manager = new Manager(name,surname,Integer.parseInt(calculateAge(birthday)),addr,role,birthday);
        Gson gson = new Gson();
        String jsonString = gson.toJson(manager);
        WorkWithFile wf = new WorkWithFile(getFilesDir()+"Manager.txt");
        if(!jsonString.equals("")){
            wf.writeFile(jsonString);
        }
    }
}
