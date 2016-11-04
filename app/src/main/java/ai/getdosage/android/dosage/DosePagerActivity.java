package ai.getdosage.android.dosage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

/**
 * Created by Eskimopies on 04/11/2016.
 */

public class DosePagerActivity extends FragmentActivity {

    private static final String EXTRA_DOSE_ID = "ai.getdosage.android.dosage.dose_id";

    private ViewPager mViewPager;
    private List<Dose> mDoses;

    public static Intent newIntent(Context packageContext, UUID doseId) {
        Intent intent = new Intent(packageContext, DosePagerActivity.class);
        intent.putExtra(EXTRA_DOSE_ID, doseId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dose_pager);

        UUID doseId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_DOSE_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_dose_view_pager);

        mDoses = DoseDealer.get(this).getDoses();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Dose dose = mDoses.get(position);
                return DoseFragment.newInstance(dose.getId());
            }

            @Override
            public int getCount() {
                return mDoses.size();
            }
        });

        for (int i = 0; i < mDoses.size(); i++) {
            if (mDoses.get(i).getId().equals(doseId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

}
