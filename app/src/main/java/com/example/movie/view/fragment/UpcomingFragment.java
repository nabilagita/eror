package com.example.movie.view.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movie.R;
import com.example.movie.adapter.UpcomingAdapter;
import com.example.movie.model.upcoming.UpcomingResultsItem;
import com.example.movie.view.viewmodel.UpcomingViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragment extends Fragment {

    private UpcomingAdapter upcomingAdapter;
    private RecyclerView rvUpcoming;
    private UpcomingViewModel upcomingViewModel;

    public UpcomingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        upcomingAdapter = new UpcomingAdapter(getContext());
        upcomingAdapter.notifyDataSetChanged();

        rvUpcoming = view.findViewById(R.id.fragmentupcoming_rv);
        rvUpcoming.setLayoutManager(new GridLayoutManager(getContext(), 2));

        upcomingViewModel = new ViewModelProvider(this).get(UpcomingViewModel.class);
        upcomingViewModel.setUpcomingMovie();
        upcomingViewModel.getUpcoming().observe(this,getUpcoming);

        rvUpcoming.setAdapter(upcomingAdapter);
    }

    private Observer<ArrayList<UpcomingResultsItem>> getUpcoming = new Observer<ArrayList<UpcomingResultsItem>>() {
        @Override
        public void onChanged(ArrayList<UpcomingResultsItem> upcomingResultsItems) {
            if (upcomingResultsItems != null) {
                upcomingAdapter.setData(upcomingResultsItems);
            }
        }
    };
}
