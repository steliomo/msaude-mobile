package mz.co.msaude.mobile.firebase;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

import javax.inject.Inject;

import mz.co.msaude.mobile.infra.SaudeApplication;

/**
 * Created by Stélio Moiane on 8/2/17.
 */
public class SaudeFirebaseMessagingService extends FirebaseMessagingService {

    @Inject
    EventBus eventBus;

    @Override
    public void onCreate() {
        super.onCreate();
        SaudeApplication application = (SaudeApplication) getApplication();
        application.getComponent().inject(this);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Map<String, String> data = remoteMessage.getData();
        Log.i("Data", data.toString());

        eventBus.post(data);
    }
}
