package ai.getdosage.android.dosage;

import android.support.v4.app.Fragment;

/**
 * Created by Eskimopies on 20/10/2016.
 */

public class DoseActivity extends SingleFragmentActivity {

    public Fragment createFragment() {
        return new DoseFragment().newInstance();
    }

}
