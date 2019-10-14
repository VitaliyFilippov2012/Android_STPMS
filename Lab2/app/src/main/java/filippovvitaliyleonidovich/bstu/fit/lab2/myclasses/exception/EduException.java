package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.exception;

import android.util.Log;

public class EduException extends Exception {

    public EduException(){
        Log.d("EduException", "Create exception :(");
    }
    public String getMessage(){
        return "Что-то пошло не так с нашей стороны..";
    }

}
