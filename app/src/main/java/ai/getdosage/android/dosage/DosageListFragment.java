package ai.getdosage.android.dosage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Eskimopies on 20/10/2016.
 */

public class DosageListFragment extends Fragment {

    private RecyclerView mDoseRecyclerView;
    private DoseAdapter mAdapter;

    public static DosageListFragment newInstance() {
        return new DosageListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dosage_list, container, false);

        mDoseRecyclerView = (RecyclerView) v.findViewById(R.id.dose_recycler_view);
        mDoseRecyclerView.addItemDecoration(new DividerDoseDecoration(getActivity(), DividerDoseDecoration.VERTICAL_LIST));
        mDoseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_dosage_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_new_dose:
                Dose dose = new Dose();
                DoseDealer.get(getActivity()).addDose(dose);
                Intent intent = DoseActivity.newIntent(getActivity(), dose.getId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUI() {
        DoseDealer doseDealer = DoseDealer.get(getActivity());
        List<Dose> doses = doseDealer.getDoses();

        if (mAdapter == null) {
            mAdapter = new DoseAdapter(doses);
            mDoseRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setDoses(doses);
            mAdapter.notifyDataSetChanged();
        }
    }

    private class DoseHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mLocationTextView;
        private TextView mDurationTextView;
        private TextView mDateTextView;
        private Dose mDose;

        public DoseHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_dose_title_text_view);
            mLocationTextView = (TextView) itemView.findViewById(R.id.list_item_dose_location_text_view);
            mDurationTextView = (TextView) itemView.findViewById(R.id.list_item_dose_duration_text_view);
        }

        public void bindDose(Dose dose) {
            mDose = dose;
            mTitleTextView.setText(mDose.getTitle());
            mLocationTextView.setText(mDose.getLocation());
            mDurationTextView.setText(mDose.getDuration());
        }

        @Override
        public void onClick(View view) {
            Dose dose = mDose;
            Intent intent = DoseActivity.newIntent(getActivity(), dose.getId());
            startActivity(intent);
        }
    }

    private class DoseAdapter extends RecyclerView.Adapter<DoseHolder> {
        private List<Dose> mDoses;

        public DoseAdapter(List<Dose> doses) {
            mDoses = doses;
        }

        @Override
        public DoseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.list_item_dose, parent, false);
            return new DoseHolder(v);
        }

        @Override
        public void onBindViewHolder(DoseHolder holder, int position) {
            Dose dose = mDoses.get(position);
            holder.bindDose(dose);
        }

        @Override
        public int getItemCount() {
            return mDoses.size();
        }

        public void setDoses(List<Dose> doses) {
            mDoses = doses;
        }
    }
}
