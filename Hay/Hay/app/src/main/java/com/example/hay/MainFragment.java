package com.example.hay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.net.URL;
import java.util.ArrayList;


public class MainFragment extends Fragment {
    ArrayList<Integer> array;
    Myadapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        array=new ArrayList<>();
            array.add(R.drawable.bc);

        return inflater.inflate(R.layout.fragment_main3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recommend = view.findViewById(R.id.recommendview);
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        recommend.setLayoutManager(lm);
        adapter = new Myadapter(getContext(),array);
        recommend.setAdapter(adapter);
    }



}
class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder>{
Context context;
ArrayList<Integer> array;

    public Myadapter(Context context, ArrayList<Integer> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public Myadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recommend_layout,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myadapter.ViewHolder viewHolder, int i) {
viewHolder.roundimg.setImageResource(array.get(i));
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RoundedImageView roundimg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundimg= itemView.findViewById(R.id.recommendimg);


        }
    }
}