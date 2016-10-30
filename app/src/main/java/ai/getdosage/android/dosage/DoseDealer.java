package ai.getdosage.android.dosage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import ai.getdosage.android.dosage.DoseDbSchema.DoseTable;

/**
 * Created by Eskimopies on 20/10/2016.
 */

public class DoseDealer {

    private static DoseDealer sDoseDealer;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static DoseDealer get(Context context) {
        if (sDoseDealer == null) {
            sDoseDealer = new DoseDealer(context);
        }
        return sDoseDealer;
    }

    private DoseDealer(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new DoseBaseHelper(mContext).getWritableDatabase();

    }

    public void addDose(Dose d) {
        ContentValues values = getContentValues(d);

        mDatabase.insert(DoseTable.NAME, null, values);
    }

    public void deleteDose(Dose d) {
        String whereClause = DoseTable.Cols.UUID + " = ?";
        String[] whereArgs = { d.getId().toString() };
        mDatabase.delete(DoseTable.NAME, whereClause, whereArgs);
    }

    public List<Dose> getDoses() {
        List<Dose> doses = new ArrayList<>();

        DoseCursorWrapper cursor = queryDoses(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                doses.add(cursor.getDose());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return doses;
    }

    public Dose getDose(UUID id) {
        DoseCursorWrapper cursor = queryDoses(
                DoseTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getDose();
        } finally {
            cursor.close();
        }
    }

    public void updateDose(Dose dose) {
        String uuidString = dose.getId().toString();
        ContentValues values = getContentValues(dose);

        mDatabase.update(DoseTable.NAME, values,
                DoseTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    public static ContentValues getContentValues(Dose dose) {
        ContentValues values = new ContentValues();
        values.put(DoseTable.Cols.UUID, dose.getId().toString());
        values.put(DoseTable.Cols.TITLE, dose.getTitle());
        values.put(DoseTable.Cols.DURATION, dose.getDuration());
        values.put(DoseTable.Cols.PRIORITY, dose.getPriority());
        values.put(DoseTable.Cols.DATE, dose.getDueDate().getTime());

        return values;
    }

    private DoseCursorWrapper queryDoses(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                DoseTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new DoseCursorWrapper(cursor);
    }
}
