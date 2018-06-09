package com.knoxpo.khyati.criminalintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container , Bundle saveInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_crime_list,container,false);
        mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return  view;
    }
    private void updateUI(){
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private Crime mCrime;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        public CrimeHolder(View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = itemView.findViewById(R.id.crime_title);
            mDateTextView = itemView.findViewById(R.id.crime_date);
            itemView.setOnClickListener(this);
        }
        public void bind(Crime crime)
        {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(),mCrime.getTitle()+"clicked !", Toast.LENGTH_SHORT).show();
        }
    }
    private class SeriousCrime extends CrimeHolder{
        private Button mCallButton;
        private Button mCameraButton;
        public SeriousCrime(View itemView) {
            super(itemView);
            mCallButton = itemView.findViewById(R.id.btn_dial);
            mCameraButton = itemView.findViewById(R.id.btn_camera);

            }
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.btn_dial:
                    //make call;
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:9104088322"));
                    startActivity(callIntent);
                    break;
                case R.id.btn_camera:
                    //open camera;
                     callIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                     startActivity(callIntent);
                default:super.onClick(view);
            }

        }
    }
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>
    {
        private static final int
                TYPE_NORMAL_CRIME = 0,
                TYPE_SERIOUS_CRIME = 1;


        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            switch (viewType) {
                case TYPE_NORMAL_CRIME:
                    View normalView = layoutInflater.inflate(R.layout.list_item_crime, parent, false);
                    return new CrimeHolder(normalView);
                case TYPE_SERIOUS_CRIME:
                    View seriousView = layoutInflater.inflate(R.layout.list_item_serious_crime, parent, false);
                    return new SeriousCrime(seriousView);
                default:
                    throw new RuntimeException("Not a valid view type: " + viewType);
            }

        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }


}
