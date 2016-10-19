package ai.getdosage.android.dosage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Eskimopies on 19/10/2016.
 */

public class DosageFragment extends android.support.v4.app.Fragment {

    public static DosageFragment newInstance() {
        return new DosageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dosage, container, false);

        return v;
    }

}
