package filippovvitaliyleonidovich.bstu.fit.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import filippovvitaliyleonidovich.bstu.fit.lab2.registration.reg_role;

import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.LISTENERS_TXT_NAME;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.MANAGER_TXT_NAME;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.STAFF_L_JAVA_TXT_NAME;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.STAFF_L_NET_TXT_NANE;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.STAFF_S_JAVA_TXT_NAME;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.STAFF_S_NET_TXT_NANE;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.STUDENTS_TXT_NAME;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WorkWithFile wfS = new WorkWithFile("/data/user/0/filippovvitaliyleonidovich.bstu.fit.lab2/"+STUDENTS_TXT_NAME);
        createFile(wfS);
        WorkWithFile wfL = new WorkWithFile("/data/user/0/filippovvitaliyleonidovich.bstu.fit.lab2/"+LISTENERS_TXT_NAME);
        createFile(wfL);
        WorkWithFile wfM = new WorkWithFile("/data/user/0/filippovvitaliyleonidovich.bstu.fit.lab2/"+MANAGER_TXT_NAME);
        createFile(wfM);
        WorkWithFile wfLJ = new WorkWithFile("/data/user/0/filippovvitaliyleonidovich.bstu.fit.lab2/"+STAFF_L_JAVA_TXT_NAME);
        createFile(wfLJ);
        WorkWithFile wfLN = new WorkWithFile("/data/user/0/filippovvitaliyleonidovich.bstu.fit.lab2/"+STAFF_L_NET_TXT_NANE);
        createFile(wfLN);
        WorkWithFile wfSJ = new WorkWithFile("/data/user/0/filippovvitaliyleonidovich.bstu.fit.lab2/"+STAFF_S_JAVA_TXT_NAME);
        createFile(wfSJ);
        WorkWithFile wfSN = new WorkWithFile("/data/user/0/filippovvitaliyleonidovich.bstu.fit.lab2/"+STAFF_S_NET_TXT_NANE);
        createFile(wfSN);
    }

    private void createFile(WorkWithFile wf){
        if(!wf.checkFile()){
            wf.createFile();
        }
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

