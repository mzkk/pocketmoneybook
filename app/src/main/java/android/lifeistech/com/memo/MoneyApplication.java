package android.lifeistech.com.memo;

import android.app.Application;

import io.realm.Realm;

public class MoneyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(getApplicationContext());
    }
}