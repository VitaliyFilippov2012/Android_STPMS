package filippovvitaliyleonidovich.bstu.fit.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import filippovvitaliyleonidovich.bstu.fit.lab2.enums.PersonInfo;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Listener;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Person;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Student;

import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.STAFF_JAVA_TXT_NAME;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.STAFF_NET_TXT_NANE;

public class StaffInfo extends AppCompatActivity {

    ArrayList<String> person = new ArrayList<String >();
    ArrayList<Student> student = new ArrayList();
    ArrayList<Listener> listener = new ArrayList();
    ArrayAdapter<String> adapter;
    ListView listViewPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_info);
        Intent intent = getIntent();
        String nameStaff = intent.getStringExtra("Staff") == ".NET" ? STAFF_NET_TXT_NANE : STAFF_JAVA_TXT_NAME;
        if(loadPersonInFile(nameStaff)){
            enterListInfoStringPerson();
            addPeopleInListView();
        }

    }

    private void enterListInfoStringPerson(){
        person.clear();
        for(Listener l : listener){
            person.add(l.toString());
        }
        for(Student s : student){
            person.add(s.toString());
        }
    }

    private void addPeopleInListView(){
        listViewPerson = (ListView) findViewById(R.id.listPerson);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, person);
        listViewPerson.setAdapter(adapter);
    }

    private boolean loadPersonInFile(String fileName){
        WorkWithFile wf = new WorkWithFile(getFilesDir()+fileName);
        WorkWithFileJSON<Person> withFileJSON = new WorkWithFileJSON<Person>(wf);
        ArrayList<Person> persons = withFileJSON.deserialize(new TypeToken<Person>(){}.getType());
        if(!sortPeopleByListenerAndStudent(persons)){
            return false;
        }
        return true;
    }

    private boolean sortPeopleByListenerAndStudent(ArrayList<Person> persons){
        student.clear();
        listener.clear();
        try{
            for(Person p: persons){
                if(p.getRole()=="Student"){
                    student.add((Student)p);
                }
                else{
                    listener.add((Listener)p);
                }
            }
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public void onClickTopPeople(View view){

    }

    public void onClickSort(View view){

    }
}
