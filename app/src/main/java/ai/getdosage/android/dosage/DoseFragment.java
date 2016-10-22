package ai.getdosage.android.dosage;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by Eskimopies on 19/10/2016.
 */

public class DoseFragment extends android.support.v4.app.Fragment {

    //private Dose mDose;
    private ImageView mImageView;

    public static DoseFragment newInstance() {
        return new DoseFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mDose = new Dose();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.under_construction_placeholder, container, false);

        Drawable placeHolder = getResources().getDrawable(R.drawable.ic_code_white_36dp);

        mImageView = (ImageView) v.findViewById(R.id.code_image);
        mImageView.setImageDrawable(placeHolder);

        return v;
    }

}
