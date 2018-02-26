package myapplication.example.com.diary;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import myapplication.example.com.entity.Diary;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    private ArrayList<Diary> dataSet;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Title , content , dd_mm_yyyy ,day_time, category;
        ImageView imageViewIcon;

        public MyViewHolder(final View itemView) {
            super(itemView);
            this.Title = (TextView) itemView.findViewById(R.id.title);
            this.content = (TextView) itemView.findViewById(R.id.content);
            this.dd_mm_yyyy = (TextView) itemView.findViewById(R.id.dd_mm_yyyy);
            this.day_time = (TextView) itemView.findViewById(R.id.day_time);
            this.category = (TextView) itemView.findViewById(R.id.category);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.image_star);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(),"Position: " + Integer.toString(getAdapterPosition()),Toast.LENGTH_SHORT).show();
                    int position = getAdapterPosition();
                    Intent intent = new Intent(itemView.getContext(), DiaryActivity.class);
                    Diary diary =  dataSet.get(position);
                    intent.putExtra("diary", diary);
                    itemView.getContext().startActivity(intent);
                }
            });*/
        }
    }

    public CustomAdapter(ArrayList<Diary> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {
        TextView Title = holder.Title;
        TextView content = holder.content;
        TextView dd_mm_yyyy = holder.dd_mm_yyyy;
        TextView day_time = holder.day_time;
        TextView category = holder.category;
        ImageView imageView = holder.imageViewIcon;

        //Title
        Diary diary = dataSet.get(listPosition);

        Title.setText(diary.getTitle());
        content.setText(diary.getContent());
        String dateTime =  diary.getDateTime();
        String[] splitted = dateTime.split("\\s+");
        String dateMonthYear = splitted[0]+ " " + splitted[1]+ " " + splitted[2]+ " " + splitted[3];
        dd_mm_yyyy.setText(dateMonthYear);
        String dayAndTime = splitted[4]+ " " + splitted[5]+ " " + splitted[6];
        day_time.setText(dayAndTime);
        category.setText(diary.getCategory());
        imageView.setImageResource(R.drawable.star);
        //check favorites
        int favarite = diary.getFavorite();
        if(favarite == 0 ){
            //hidden image
                imageView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

}
