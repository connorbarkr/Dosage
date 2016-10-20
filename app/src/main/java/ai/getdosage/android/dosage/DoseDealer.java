package ai.getdosage.android.dosage;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Eskimopies on 20/10/2016.
 */

public class DoseDealer {

    private static DoseDealer sDoseDealer;

    private List<Dose> mDoses;

    public static DoseDealer get(Context context) {
        if (sDoseDealer == null) {
            sDoseDealer = new DoseDealer(context);
        }
        return sDoseDealer;
    }

    private DoseDealer(Context context) {
        mDoses = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Dose dose = new Dose();
            dose.setLocation("Home");
            dose.setTitle("Hit me with your car #" + i);
            dose.setTime(new Date());
            mDoses.add(dose);
        }
    }

    public List<Dose> getDoses() {
        return mDoses;
    }

    public Dose getDose(UUID id) {
        for (Dose dose : mDoses) {
            if (dose.getId() == id) {
                return dose;
            }
        }
        return null;
    }
}
