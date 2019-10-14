package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units.manager;

import android.util.Log;
import java.io.File;

public interface IAction {
    void addStuff(String name, Integer maxStudents, Integer maxListeners,
                  String students, String listeners);
    void addStuff(String name, Integer maxStudents, Integer maxListeners);

    default void sendMessage(String msg){
        Log.d("sendMessage", msg);
    }

    static void testVoid(){
        Log.d("testVoid", "Something text..");
    }
}
