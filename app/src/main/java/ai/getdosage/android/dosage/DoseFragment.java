package ai.getdosage.android.dosage;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by Eskimopies on 19/10/2016.
 */

public class DoseFragment extends android.support.v4.app.Fragment {

    private Dose mDose;
    private EditText mEditText;
    private TextView mLocation;
    private TextView mDuration;

    public static DoseFragment newInstance() {
        return new DoseFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID doseId = (UUID) getActivity().getIntent().getSerializableExtra(DoseActivity.EXTRA_DOSE_ID);
        mDose = DoseDealer.get(getActivity()).getDose(doseId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dose, container, false);

        mLocation = (TextView) v.findViewById(R.id.dose_location);
        mLocation.setText(mDose.getLocation());

        mDuration = (TextView) v.findViewById(R.id.dose_duration);
        mDuration.setText(mDose.getDuration() + " minutes");

        mEditText = (EditText) v.findViewById(R.id.dose_title);
        mEditText.setText(mDose.getTitle());
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // intentional blank
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mDose.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // intentional blank
            }
        });

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();

        DoseDealer.get(getActivity()).updateDose(mDose);
    }

}
