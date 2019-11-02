package filippovvitaliyleonidovich.bstu.fit.lab2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

import filippovvitaliyleonidovich.bstu.fit.lab2.enums.PersonInfo;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Listener;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Person;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Student;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.staff.Staff;
import filippovvitaliyleonidovich.bstu.fit.lab2.registration.reg_role;
import filippovvitaliyleonidovich.bstu.fit.lab2.studentpage.studentInfo;

public class StaffInfo extends AppCompatActivity{

    ArrayList<String> person = new ArrayList<String >();
    ArrayList<Student> student = new ArrayList<Student>();
    ArrayList<Listener> listener = new ArrayList<Listener>();
    ArrayAdapter<String> adapter;
    String nameStaff;
    ListView listViewPerson;
    int position = -1;
    ClipData clipData;
    ClipboardManager clipboardManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_info);
        Intent intent = getIntent();
        clipboardManager=(ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
        nameStaff = intent.getStringExtra("Staff");
        listViewPerson = (ListView) findViewById(R.id.listPerson);
        RefreshPerson();
        Log.d("MyApp","Initialize listView");
        listViewPerson.setOnItemClickListener((parent,v,position,id)-> {
            String selectedItem = person.get(position);
            Log.d("MyApp","Go to: "+ selectedItem);
            goToStudentPage(selectedItem);
        });

        listViewPerson.setOnItemLongClickListener((parent,v,pos,id)-> {
            position = pos;
            PopupMenu popup = new PopupMenu(this, v);
            popup.setOnMenuItemClickListener(this::onContextItemSelected);
            popup.inflate(R.menu.context_menu);
            popup.show();
            return true;
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        View view = null;

        switch (item.getItemId()) {
            case R.id.copy:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("Attention").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onClickCopy();
                    }
                }).setMessage("Copy");
                AlertDialog dialog2 = builder2.create();
                dialog2.show();
                return true;

            case R.id.delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Attention").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onClickDelete();
                    }
                }).setMessage("Now you try to delete any user");
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    private void onClickCopy(){
        String copyString = person.get(position);
        clipData = ClipData.newPlainText("text",copyString);
        clipboardManager.setPrimaryClip(clipData);
        Toast.makeText(getApplicationContext(),"Text Copied ", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),copyString, Toast.LENGTH_SHORT).show();

    }

    private void onClickDelete(){
        Staff.remove(person.get(position));
        RefreshPerson();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.find :
                LayoutInflater li = LayoutInflater.from(this);
                View promptsView = li.inflate(R.layout.prompt, null);
                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(this);
                mDialogBuilder.setView(promptsView);
                final EditText userInput = (EditText) promptsView.findViewById(R.id.input_text);
                mDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Ok",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        onClickFind(userInput.getText().toString());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = mDialogBuilder.create();
                alertDialog.show();
                return true;
            case R.id.new_p:
                Intent intent = new Intent(this, reg_role.class);
                startActivity(intent);
                return true;
            case R.id.sort:
                onClickSort();
                return true;
            case R.id.top:
                onClickTopPeople();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToStudentPage(String person){
        final Intent intent = new Intent(this, studentInfo.class);
        if(person.contains("Listener")){
            Listener l = Staff.getListener(person);
            if(l == null)
                return;
            intent.putExtra("Reit",String.valueOf(l.getReit()));
            intent.putExtra(PersonInfo.ROLE.name(),l.getRole());
            intent.putExtra(PersonInfo.NAME.name(), l.getName());
            intent.putExtra(PersonInfo.SURNAME.name(),l.getSurname());
            intent.putExtra(PersonInfo.Email.name(), l.getEmail());
            intent.putExtra(PersonInfo.Phone.name(), l.getPhone());
            intent.putExtra(PersonInfo.Messanger.name(), l.getMessanger());
            intent.putExtra(PersonInfo.ADDR.name(),l.getAddr());
            intent.putExtra(PersonInfo.BIRTHDAY.name(), l.getYearOfBirthday());

            intent.putExtra(PersonInfo.Organization.name(), l.getOrganization().name());
            intent.putExtra(PersonInfo.Staff.name(), l.getStaff());

        }
        else{
            Student s = Staff.getStudent(person);
            if(s == null)
                return;
            intent.putExtra("Reit",String.valueOf(s.getMark()));
            intent.putExtra(PersonInfo.ROLE.name(),s.getRole());
            intent.putExtra(PersonInfo.NAME.name(), s.getName());
            intent.putExtra(PersonInfo.SURNAME.name(),s.getSurname());
            intent.putExtra(PersonInfo.Email.name(), s.getEmail());
            intent.putExtra(PersonInfo.Phone.name(), s.getPhone());
            intent.putExtra(PersonInfo.Messanger.name(), s.getMessanger());
            intent.putExtra(PersonInfo.ADDR.name(),s.getAddr());
            intent.putExtra(PersonInfo.BIRTHDAY.name(), s.getYearOfBirthday());
            intent.putExtra(PersonInfo.Organization.name(), s.getOrganization().name());
            intent.putExtra(PersonInfo.Staff.name(), s.getStaff());
        }
        intent.putExtra("flag", "1");
        startActivity(intent);
    }

    private void enterListInfoStringPerson(){
        for(Listener l : listener){
            person.add(l.toString());
        }
        for(Student s : student){
            person.add(s.toString());
        }
    }

    private void addPeopleInListView(ArrayList<String> person){
        if(person!= null){
        adapter = new ArrayAdapter<String>(this, R.layout.rowlayout,R.id.label,person);
            listViewPerson.setAdapter(adapter);
        }
        Log.d("MyApp","Add to listView");
    }

    private boolean loadPerson(String nameStaff){
        try {
            person.clear();
            student.clear();
            listener.clear();
            student.addAll(Staff.getStudents(nameStaff));
            listener.addAll(Staff.getListeners(nameStaff));
            person.addAll(Staff.getPersons(nameStaff));
        }
        catch (Exception e){
            return false;
        }
        return true;
    }


    public void onClickTopPeople(){
        student.sort((Student o1,Student o2) -> (int)(o2.getMark().intValue()-o1.getMark().intValue()));
        listener.sort((Listener o1, Listener o2) -> (int)(o2.getYearOfBirthday()-o1.getYearOfBirthday()));
        ArrayList<String> p = new ArrayList<String>();
        for(int i = 0; i<3 && i<student.size();i++){
            p.add(student.get(i).toString());
        }
        for(int i = 0; i<3 && i<listener.size();i++){
            p.add(listener.get(i).toString());
        }
        addPeopleInListView(p);
    }

    public void onClickSort(){
        student.sort((Student o1,Student o2) -> (int)(o2.getMark().intValue()-o1.getMark().intValue()));
        listener.sort((Listener o1, Listener o2) -> (int)(o2.getYearOfBirthday()-o1.getYearOfBirthday()));
        person.clear();
        enterListInfoStringPerson();
        addPeopleInListView(person);
    }

    public void onClickFind(String str){
        ArrayList<String> p = new ArrayList<>();
        for(String s: person){
            if(s.toLowerCase().contains(str.toLowerCase())){
                p.add(s);
            }
        }
        addPeopleInListView(p);
    }

    private void RefreshPerson(){
        if(loadPerson(nameStaff)){
            enterListInfoStringPerson();
            addPeopleInListView(person);
        }
    }
}
