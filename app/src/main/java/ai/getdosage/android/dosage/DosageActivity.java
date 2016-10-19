package ai.getdosage.android.dosage;

import android.support.v4.app.Fragment;

public class DosageActivity extends SingleFragmentActivity {

    public Fragment createFragment() {
        return new DosageFragment().newInstance();
    }

}
