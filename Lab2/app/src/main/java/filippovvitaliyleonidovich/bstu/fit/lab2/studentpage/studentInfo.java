package filippovvitaliyleonidovich.bstu.fit.lab2.studentpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import filippovvitaliyleonidovich.bstu.fit.lab2.MainActivity;
import filippovvitaliyleonidovich.bstu.fit.lab2.R;

public class studentInfo extends AppCompatActivity {
    String role;
    String name;
    String surname;
    String addr;
    String birthday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudent_info);
        setInfo(getIntent());
    }
    private void setInfo(Intent intent){
        role = intent.getStringExtra("role");
        name = intent.getStringExtra("name");
        surname = intent.getStringExtra("surname");
        addr = intent.getStringExtra("addr");
        birthday = intent.getStringExtra("birthday");
        TextView textRole = findViewById(R.id.role);
        textRole.setText(role);
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
}
