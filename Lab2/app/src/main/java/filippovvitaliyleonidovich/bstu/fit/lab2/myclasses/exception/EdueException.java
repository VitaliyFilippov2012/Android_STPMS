package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.exception;

public class EdueException extends Exception {

    public EduException(){
        Log.d("EduException", "Create exception :(");
    }
    public String getMessage(){
        return "Что-то пошло не так с нашей стороны..";
    }

}
