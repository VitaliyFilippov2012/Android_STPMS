package filippovvitaliyleonidovich.bstu.fit.lab2.studentpage;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.reflect.TypeToken;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import filippovvitaliyleonidovich.bstu.fit.lab2.MainActivity;
import filippovvitaliyleonidovich.bstu.fit.lab2.R;
import filippovvitaliyleonidovich.bstu.fit.lab2.WorkWithFile;
import filippovvitaliyleonidovich.bstu.fit.lab2.WorkWithFileJSON;
import filippovvitaliyleonidovich.bstu.fit.lab2.enums.PersonInfo;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.organization.Organization;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Listener;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Person;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.Student;
import filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.staff.Staff;

import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.DATE_FORMAT;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.LISTENERS_TXT_NAME;
import static filippovvitaliyleonidovich.bstu.fit.lab2.constants.Basic.STUDENTS_TXT_NAME;

public class studentInfo extends AppCompatActivity{

    private String role;
    private String name;
    private String surname;
    private String addr;
    private String birthday;
    private Organization organization;
    private String phone;
    private String email;
    private String messanger;
    WorkWithFile wf;
    private static final int CAMERA_REQUEST = 1;
    private ImageView imageView;
    private Uri outputFileUri;
    private String org;
    private String nameStaff;
    private String reit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudent_info);
        Log.d("MyApp","CreateActivity");
        setInfo(getIntent());
        imageView = findViewById(R.id.imageView);
    }
    private void setInfo(Intent intent){
        role = intent.getStringExtra(PersonInfo.ROLE.name());
        name = intent.getStringExtra(PersonInfo.NAME.name());
        surname = intent.getStringExtra(PersonInfo.SURNAME.name());
        addr = intent.getStringExtra(PersonInfo.ADDR.name());
        birthday = intent.getStringExtra(PersonInfo.BIRTHDAY.name());
        phone = intent.getStringExtra(PersonInfo.Phone.name());
        messanger = intent.getStringExtra(PersonInfo.Messanger.name());
        email = intent.getStringExtra(PersonInfo.Email.name());
        Log.d("MyApp","Get intent key");

        TextView textPhone = findViewById(R.id.phone);
        textPhone.setText(phone);
        TextView textEmail = findViewById(R.id.email);
        textEmail.setText(email);
        TextView textMessanger = findViewById(R.id.messanger);
        textMessanger.setText(messanger);
        TextView textRole = findViewById(R.id.role);
        textRole.setText(role);
        TextView textName = findViewById(R.id.name);
        textName.setText(name);
        TextView textSurname = findViewById(R.id.surname);
        textSurname.setText(surname);
        TextView textBthd = findViewById(R.id.birthday);
        textBthd.setText(birthday);
        TextView textAddr = findViewById(R.id.addr);
        textAddr.setText(addr);
        Log.d("MyApp","Set key");

        String flag = intent.getStringExtra("flag");
        if(flag.equalsIgnoreCase("1")){
            addInfoAboutPersonFromIntent(intent);
        }
        else{
            loadInfo();
        }
    }

    private void addInfoAboutPersonFromIntent(Intent intent){
        org = intent.getStringExtra(PersonInfo.Organization.name());
        nameStaff = intent.getStringExtra(PersonInfo.Staff.name());
        reit = intent.getStringExtra("Reit");

        Spinner organiz = (Spinner) findViewById(R.id.org);
        if(org.equalsIgnoreCase("BSU")){
            organiz.setSelection(0);
        }
        else{
            if(org.equalsIgnoreCase("BSTU")){
                organiz.setSelection(1);
            }
            else{
                organiz.setSelection(2);
            }
        }
        Spinner staff = (Spinner) findViewById(R.id.staff);
        if(nameStaff.equalsIgnoreCase(".Net")){
            staff.setSelection(0);
        }
        else{
            staff.setSelection(1);
        }
        EditText compareType = findViewById(R.id.compareType);
        compareType.setText(reit);
        organiz.setEnabled(false);
        staff.setEnabled(false);
    }

    private void loadInfo(){
        addPeopleInListView(Staff.getPersons());
    }

    private void addPeopleInListView(ArrayList<String> arrayList){
        if(arrayList!= null){
            ListView listViewPerson = (ListView) findViewById(R.id.listPerson);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, arrayList);
            listViewPerson.setAdapter(adapter);
        }
    }

    private String calculateAge(String year){
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        String dateText = dateFormat.format(currentDate).substring(dateFormat.format(currentDate).length()-4);
        String age = String.valueOf(Integer.valueOf(dateText) - Integer.valueOf(year));
        return age;
    }
    @Override
    public void onBackPressed() {
        if(reit == null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else{
            super.onBackPressed();
        }
    }

    public void onClickPhone(View view){
        String toDial="tel:"+phone;
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(toDial)));
    }

    public void onClickEmail(View view){
        Intent emailIntent = new Intent(Intent.ACTION_SEND,Uri.parse(email));
        emailIntent.setType("text/html");
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Hello");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<small>"+email.toString()+"</small>"));
        startActivity(Intent.createChooser(emailIntent, "Email:"));
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    public void onClickMessanger(View view){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(messanger)));
    }

    public void saveInfo(View view){
        Spinner org = (Spinner) findViewById(R.id.org);
        String selected_org = org.getSelectedItem().toString();
        Spinner staff = (Spinner) findViewById(R.id.staff);
        String selected_staff = staff.getSelectedItem().toString();
        EditText compareType = findViewById(R.id.compareType);

        if(selected_org.equals("BSU")){
            organization = Organization.BSU;
        }
        else{
            if(selected_org.equals("BSTU")){
                organization = Organization.BSTU;
            }
            else{
                organization = Organization.BSUIR;
            }
        }
        if(role.equals("Student")){
            Student student = new Student(name,surname,Integer.parseInt(calculateAge(birthday)),addr,organization,Double.valueOf(compareType.getText().toString()),selected_staff,phone,email,messanger);
            Staff.add(student,role);
        }
        if(role.equals("Listener")) {
            Listener listener = new Listener(name, surname, Integer.parseInt(birthday),addr,organization,Double.valueOf(compareType.getText().toString()),selected_staff,phone,email,messanger);
            Staff.add(listener,role);
        }
        onBackPressed();
    }


    public void onClick(View view) {
        saveFullImage();
        Log.d("MyApp","1");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("MyApp","5-1");

        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            // Проверяем, содержит ли результат маленькую картинку
            if (data != null) {
                if (data.hasExtra("data")) {
                    Log.d("MyApp","5-2");

                    Bitmap thumbnailBitmap = data.getParcelableExtra("data");
                    // Какие-то действия с миниатюрой
                    imageView.setImageBitmap(thumbnailBitmap);
                    Log.d("MyApp","5-3");

                }
            } else {
                // Какие-то действия с полноценным изображением,
                // сохраненным по адресу outputFileUri
                Log.d("MyApp","5-4");

                imageView.setImageURI(outputFileUri);
            }
        }
    }

    private void saveFullImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        /*File file = new File(Environment.getExternalStorageDirectory(),"test.jpg");
        Log.d("MyApp","2");
        try{
            file.createNewFile();
        }
        catch (IOException e){

        }*/
        //outputFileUri = Uri.fromFile(file);
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        //startActivityForResult(intent, CAMERA_REQUEST);
        Log.d("MyApp","6");
        startActivity(intent);
    }
}
