package ai.getdosage.android.dosage;

import android.widget.TextView;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Eskimopies on 20/10/2016.
 */

public class Dose {

    private UUID mId;
    private Date mDueDate;
    private String mPriority;
    private String mTitle;
    private String mDuration;

    public Dose() {
        mId = UUID.randomUUID();
        mDueDate = new Date();
    }

    public Dose(UUID id) {
        mId = id;
    }

    public UUID getId() {
        return mId;
    }

    public Date getDueDate() {
        return mDueDate;
    }

    public void setDueDate(Date dueDate) {
        mDueDate = dueDate;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDuration() {
        return mDuration;
    }

    public void setDuration(String duration) {
        mDuration = duration;
    }

    public String getPriority() {
        return mPriority;
    }

    public void setPriority(String priority) {
        mPriority = priority;
    }
}
