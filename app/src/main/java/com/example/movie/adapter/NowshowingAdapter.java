package com.example.movie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movie.R;
import com.example.movie.model.nowshowing.NowshowingResultsItem;

import java.util.ArrayList;

public class NowshowingAdapter extends RecyclerView.Adapter<NowshowingAdapter.ViewHolder> {

    private ArrayList<NowshowingResultsItem> nowshowingItems = new ArrayList<>();
    private Context context;
    public Listener listener;

    private static String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w185";

    public NowshowingAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<NowshowingResultsItem> items){
        nowshowingItems.clear();
        nowshowingItems.addAll(items);
        notifyDataSetChanged();

    }
    public interface Listener {
        void onClick(int position);
    }
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public NowshowingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NowshowingAdapter.ViewHolder holder, final int position) {
        ImageView ivThumb;
        TextView tvTitle, tvRate;
        CardView cvItem = holder.cvItem;


        ivThumb = cvItem.findViewById(R.id.itemlist_iv_thumbnail);
        tvTitle = cvItem.findViewById(R.id.itemlist_tv_title);
        tvRate = cvItem.findViewById(R.id.itemlist_tv_rate);

        Glide.with(context).load(BASE_IMAGE_URL + nowshowingItems
                .get(position).getPosterPath())
                .into(ivThumb);

        tvTitle.setText(nowshowingItems.get(position).getTitle());
        tvRate.setText(String.valueOf(nowshowingItems.get(position).getVoteAverage()));

        cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null){
                    listener.onClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return nowshowingItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvItem = (CardView) itemView;
        }
    }
}
