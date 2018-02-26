package myapplication.example.com.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import myapplication.example.com.diary.CustomAdapter;
import myapplication.example.com.diary.R;
import myapplication.example.com.entity.Diary;
import myapplication.example.com.sqlite.DBManager;

public class FragmentDiary extends Fragment{

    private  RecyclerView.Adapter adapter;
    private  RecyclerView.LayoutManager layoutManager;
    private  RecyclerView recyclerView;
    private  ArrayList<Diary> data;
    private DBManager dbManager;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_diary, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

     /*   layoutManager = new LinearLayoutManager(this);*/
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        dbManager = new DBManager(getActivity());
        dbManager.open();
        data = new ArrayList<Diary>();
        data = dbManager.arrlistFetchDiary();

      //removedItems = new ArrayList<Integer>();
        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public FragmentDiary() {

    }

}
