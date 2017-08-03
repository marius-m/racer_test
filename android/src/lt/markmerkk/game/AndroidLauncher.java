package lt.markmerkk.game;

import android.content.Context;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import lt.markmerkk.app.RacerGame;

public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        // fixme no initializer for android
        initialize(
                new RacerGame(
                        true,
                        "192.168.1.52"
                ),
                config
        );
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }
}
