package com.example.appoder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Ban_Adapter extends RecyclerView.Adapter<Ban_Adapter.BanViewHolder> {
    private Context context;
    private List<Ban> mListBan;

    public Ban_Adapter(Context context) {
        this.context = context;
    }

    public void setData(List<Ban> mban){
        this.mListBan=mban;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ban,parent,false);
        return new BanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BanViewHolder holder, int position) {

        Ban ban = mListBan.get(position);
        if(ban == null ){
            return;
        }
        holder.txttenban.setText(ban.getTenBan());
        



    }

    @Override
    public int getItemCount() {
        if(mListBan != null){
            return mListBan.size();
        }
        return 0;
    }

    public class BanViewHolder extends RecyclerView.ViewHolder {

        private TextView txttenban;

        public BanViewHolder(@NonNull View itemView) {
            super(itemView);
            txttenban= itemView.findViewById(R.id.txtTenBan);
        }
    }

}
