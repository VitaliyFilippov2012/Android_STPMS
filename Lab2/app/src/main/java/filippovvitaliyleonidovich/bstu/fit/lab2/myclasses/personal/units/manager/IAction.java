package filippovvitaliyleonidovich.bstu.fit.lab2.myclasses.personal.units;

import android.annotation.TargetApi;
        import android.os.Build;
        import android.util.Log;
        import androidx.annotation.RequiresApi;

        import java.io.File;

public interface IAction {
    void addStuff(String name, Integer maxStudents, Integer maxListeners,
                  File students, File listeners);
    void addStuff(String name, Integer maxStudents, Integer maxListeners);

    default void sendMessage(String msg){
        Log.d("sendMessage", msg);
    }

    static void testVoid(){
        Log.d("testVoid", "Something text..");
    }
}
