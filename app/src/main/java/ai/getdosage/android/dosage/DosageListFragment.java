package ai.getdosage.android.dosage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dosage_list, container, false);

        mDoseRecyclerView = (RecyclerView) v.findViewById(R.id.dose_recycler_view);
        mDoseRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return v;
    }

    private void updateUI() {
        DoseDealer doseDealer = DoseDealer.get(getActivity());
        List<Dose> doses = doseDealer.getDoses();

        mAdapter = new DoseAdapter(doses);
        mDoseRecyclerView.setAdapter(mAdapter);
    }

    private class DoseHolder extends RecyclerView.ViewHolder {
        public TextView mTitleTextView;

        public DoseHolder(View itemView) {
            super(itemView);

            mTitleTextView = (TextView) itemView;
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
            View v = layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            return new DoseHolder(v);
        }

        @Override
        public void onBindViewHolder(DoseHolder holder, int position) {
            Dose dose = mDoses.get(position);
            holder.mTitleTextView.setText(dose.getTitle());
        }

        @Override
        public int getItemCount() {
            return mDoses.size();
        }
    }

}
