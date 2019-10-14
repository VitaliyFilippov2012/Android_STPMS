package filippovvitaliyleonidovich.bstu.fit.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import filippovvitaliyleonidovich.bstu.fit.lab2.registration.reg_role;

import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.LISTENERS_TXT_NAME;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.MANAGER_TXT_NAME;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.STAFF_JAVA_TXT_NAME;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.STAFF_NET_TXT_NANE;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.STUDENTS_TXT_NAME;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*WorkWithFile wfS = new WorkWithFile(getFilesDir()+STUDENTS_TXT_NAME);
        createFile(wfS);
        WorkWithFile wfL = new WorkWithFile(getFilesDir()+LISTENERS_TXT_NAME);
        createFile(wfL);
        WorkWithFile wfM = new WorkWithFile(getFilesDir()+MANAGER_TXT_NAME);
        createFile(wfM);
        WorkWithFile wfSJ = new WorkWithFile(getFilesDir()+STAFF_JAVA_TXT_NAME);
        createFile(wfSJ);
        WorkWithFile wfSN = new WorkWithFile(getFilesDir()+STAFF_NET_TXT_NANE);
        createFile(wfSN);*/
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
// manager.addStuff("Java", 3, 3);
//        manager.addStuff("C#", 4, 2,
 //        new File(super.getFilesDir(), "students.txt"),
 //        new File(super.getFilesDir(), "listeners.txt"));

//        List<Stuff> obj = (List<Stuff>) manager.getStuffs();

//        ArrayList<Listener> listenerArrayList = new ArrayList<Listener>(Arrays.asList(
//                new Listener("Иван Чайков", 24 , Organization.ALFA, 2.8),
//                new Listener("Кирилл Смак", 23, Organization.EPAM, 3.1),
//                new Listener("Екатерина Гайло", 22, Organization.ALFA, 4.4),
//                new Listener("Енакентий Сергеев", 23, Organization.LEVERX, 4.),
//                new Listener("Ян Короткий", 31, Organization.ALFA, 3.7)
//        ));
//
//        ArrayList<Student> studentArrayList = new ArrayList<Student>(Arrays.asList(
//                new Student("Филипп Крутовал", 18, Organization.BNTU, 7.8),
//                new Student("Филипп Ковтик", 17, Organization.BNTU, 6.5),
//                new Student("Андрей Хмар", 20, Organization.BSTU, 8.),
//                new Student("Артем Гирдюк", 17, Organization.BSTU, 6.),
//                new Student("Гера Кулакова", 21, Organization.BSTU, 7.1)
//        ));
//
//        WorkWithFile workWithFile = new WorkWithFile();
//        workWithFile.saveAsJson(listenerArrayList, new File(super.getFilesDir(), "listeners.txt"));
//        workWithFile.saveAsJson(studentArrayList, new File(super.getFilesDir(), "students.txt"));
