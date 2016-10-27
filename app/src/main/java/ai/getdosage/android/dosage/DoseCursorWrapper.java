package ai.getdosage.android.dosage;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import ai.getdosage.android.dosage.DoseDbSchema.DoseTable;

/**
 * Created by Eskimopies on 27/10/2016.
 */

public class DoseCursorWrapper extends CursorWrapper {

    public DoseCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Dose getDose() {
        String uuidString = getString(getColumnIndex(DoseTable.Cols.UUID));
        String title = getString(getColumnIndex(DoseTable.Cols.TITLE));
        String duration = getString(getColumnIndex(DoseTable.Cols.DURATION));
        String location = getString(getColumnIndex(DoseTable.Cols.LOCATION));

        Dose dose = new Dose(UUID.fromString(uuidString));
        dose.setTitle(title);
        dose.setDuration(duration);
        dose.setLocation(location);

        return dose;
    }

}
