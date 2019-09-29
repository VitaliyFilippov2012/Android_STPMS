package filippovvitaliyleonidovich.bstu.fit.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import filippovvitaliyleonidovich.bstu.fit.lab2.registration.reg_role;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickSignIn(final View view){
        final Intent intent = new Intent(this, Entry.class);
        startActivity(intent);
    }

    public void onClickSignUp(final View view){
        final Intent intent = new Intent(this, reg_role.class);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }
}
