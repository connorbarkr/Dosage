package ai.getdosage.android.dosage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ai.getdosage.android.dosage.DoseDbSchema.DoseTable;

/**
 * Created by Eskimopies on 27/10/2016.
 */

public class DoseBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "doseBase.db";


    public DoseBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DoseTable.NAME + "(" +
        " _id integer primary key autoincrement, " +
        DoseTable.Cols.UUID + ", " +
        DoseTable.Cols.TITLE + ", " +
        DoseTable.Cols.DURATION + ", " +
        DoseTable.Cols.LOCATION + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
