package ai.getdosage.android.dosage;

import android.support.v4.app.Fragment;

public class DosageListActivity extends SingleFragmentActivity {

    public Fragment createFragment() {
        return new DoseFragment().newInstance();
    }

}
