package ai.getdosage.android.dosage;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

import static android.support.v7.appcompat.R.styleable.MenuItem;

/**
 * Created by Eskimopies on 19/10/2016.
 */

public class DoseFragment extends android.support.v4.app.Fragment {

    private static final String ARG_DOSE_ID = "dose_id";

    private Dose mDose;
    private EditText mEditText;
    private TextView mLocation;
    private TextView mDuration;

    public static DoseFragment newInstance(UUID doseId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DOSE_ID, doseId);

        DoseFragment fragment = new DoseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static DoseFragment newInstance() {
        return new DoseFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID doseId = (UUID) getArguments().getSerializable(ARG_DOSE_ID);
        mDose = DoseDealer.get(getActivity()).getDose(doseId);
        setHasOptionsMenu(true);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_dose, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_delete:
                DoseDealer.get(getActivity()).deleteDose(mDose);
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        DoseDealer.get(getActivity()).updateDose(mDose);
    }

}
