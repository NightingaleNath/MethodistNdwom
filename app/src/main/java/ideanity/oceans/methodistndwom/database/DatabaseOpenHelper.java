package ideanity.oceans.methodistndwom.database;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper extends SQLiteAssetHelper {
    public static final String DATABASE_NAME = "my_hymn.sqlite";
    private static final int DATABASE_VERSION = 1;
    private Context mContext;

    public DatabaseOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mContext = context;
    }

}