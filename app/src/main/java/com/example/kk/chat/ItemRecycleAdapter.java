package com.example.kk.chat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Saranya GS on 6/11/17.
 */

public class ItemRecycleAdapter extends RecyclerView.Adapter {
    View itemView;
    private ArrayList<chatdomain> serachResult = new ArrayList<>();RecyclerView recycler;
int pos;
Context context;


    public ItemRecycleAdapter(ArrayList<chatdomain> qd, Context context1) {
serachResult=qd;
        this.context=context1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==1)
        {
       itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_user_message_right, parent, false);}
        else if(viewType==2){ itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_user_message_left, parent, false);}
        return new SearchPromoViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder hold, int position) {
        final SearchPromoViewholder holder = (SearchPromoViewholder) hold;
pos=position;
     holder.title.setText(serachResult.get(position).getChatmsg());
        holder.title.setText(serachResult.get(position).getUsername());

    }
/*
    @Override
    public int getItemViewType(int position) {

        *//*if (serachResult.get(position).getUsername().) {

        }*//*
    }*/
    @Override
    public int getItemCount() {
        return serachResult.size();
    }

    class SearchPromoViewholder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;

        public SearchPromoViewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
