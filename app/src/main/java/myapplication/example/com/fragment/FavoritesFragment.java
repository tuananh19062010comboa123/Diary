package myapplication.example.com.fragment;


        import android.app.Fragment;
        import android.app.ProgressDialog;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
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

public class FavoritesFragment extends Fragment{

    private  RecyclerView.Adapter adapters;
    private  RecyclerView.LayoutManager layoutManagers;
    private  RecyclerView recyclerVies;
    private ArrayList<Diary> datas;
    private DBManager dbManagers;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_favorites, container, false);
        recyclerVies = (RecyclerView) view.findViewById(R.id.my_recycler_view_fragmentFavorites);
        recyclerVies.setHasFixedSize(true);

        /* layoutManager = new LinearLayoutManager(this); */
        layoutManagers = new LinearLayoutManager(getActivity());
        recyclerVies.setLayoutManager(layoutManagers);
        recyclerVies.setItemAnimator(new DefaultItemAnimator());

        dbManagers = new DBManager(getActivity());
        dbManagers.open();
        datas = new ArrayList<Diary>();
        datas = dbManagers.getDataDiaryFavorites();

        //removedItems = new ArrayList<Integer>();
        adapters = new CustomAdapter(datas);
        recyclerVies.setAdapter(adapters);
        return view;
    }
    public FavoritesFragment() {
    }
}
