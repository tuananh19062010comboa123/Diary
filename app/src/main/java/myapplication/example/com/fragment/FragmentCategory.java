package myapplication.example.com.fragment;


import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import java.util.ArrayList;

import myapplication.example.com.diary.CustomAdapter;
import myapplication.example.com.diary.R;
import myapplication.example.com.entity.Diary;
import myapplication.example.com.sqlite.DBManager;

public class FragmentCategory extends Fragment {
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ArrayList<Diary> data;
    private DBManager dbManager;

    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(
                R.layout.fragment_categoty, container, false);

        button = (Button) view.findViewById(R.id.buttonShowCustomDialog);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* FragmentCategory();*/
                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.custom);
                dialog.setTitle("Title...");
                recyclerView = (RecyclerView) dialog.findViewById(R.id.my_recycler_view_category);
                recyclerView.setHasFixedSize(true);
                layoutManager = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                dbManager = new DBManager(getActivity());
                dbManager.open();
                data = new ArrayList<Diary>();
                data = dbManager.getDataDiaryFavorites();
                adapter = new CustomAdapter(data);
                recyclerView.setAdapter(adapter);
                dialog.show();
            }
        });
        return view;
    }
}
