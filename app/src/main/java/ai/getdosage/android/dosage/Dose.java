package ai.getdosage.android.dosage;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Eskimopies on 20/10/2016.
 */

public class Dose {

    private UUID mId;
    private Date mTime;
    private String mLocation;

    public Dose() {
        mId = UUID.randomUUID();
    }

    public UUID getId() {
        return mId;
    }

    public Date getTime() {
        return mTime;
    }

    public void setTime(Date time) {
        mTime = time;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }
}
