import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent("com.example.session15.TEST");
                intent.putExtra("message","hello broad cast receiver");
                LocalBroadcastManager.getInstance(App.this).sendBroadcast(intent);
            }
        },5000);
    }
}
