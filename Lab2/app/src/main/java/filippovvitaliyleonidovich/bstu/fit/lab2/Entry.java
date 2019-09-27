package filippovvitaliyleonidovich.bstu.fit.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import filippovvitaliyleonidovich.bstu.fit.lab2.managerpage.ManagerMainInfo;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Manager;

public class Entry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
    }

    public void onClickEntry(View view){
        EditText ed_name = findViewById(R.id.editText_name);
        EditText ed_surname = findViewById(R.id.editText_surname);
        String name = ed_name.getText().toString();
        String surname = ed_surname.getText().toString();

        WorkWithFile wf = new WorkWithFile(getFilesDir()+"Manager.txt");
        Gson gson = new Gson();
        String stringFromFile = wf.readFile();
        Manager[] managers = gson.fromJson(stringFromFile,Manager[].class);
        for (Manager m: managers) {
            try {
                if(m.getName().equals(name) && m.getSurname().equals(surname)){
                    Intent intent = new Intent(this, ManagerMainInfo.class);
                    intent.putExtra("name",name);
                    intent.putExtra("surname",surname);
                    intent.putExtra("role",m.getRole());
                    intent.putExtra("addr",m.getAddr());
                    intent.putExtra("birthday",m.getBirthday());
                    startActivity(intent);
                    return;
                }
            } catch (Exception e) {
               return;
            }
        }
    }
}
