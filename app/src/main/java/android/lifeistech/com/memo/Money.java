package android.lifeistech.com.memo;

import io.realm.RealmObject;

public class Money extends RealmObject{

    static int INCOME=1;
    static int OUT=-1;

    public int okane;
    public String time;
    public String memo;
    public int type;
}
