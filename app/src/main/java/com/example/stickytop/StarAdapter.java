package com.example.stickytop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @Author: Jack Ou
 * @CreateDate: 2020/9/7 21:57
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/9/7 21:57
 * @UpdateRemark: 更新说明
 */
public class StarAdapter extends RecyclerView.Adapter<StarAdapter.StarHolder> {

    private List<Star> stars;

    public StarAdapter(List<Star> stars) {
        this.stars = stars;
    }

    @NonNull
    @Override
    public StarAdapter.StarHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rv_item_star, null);
        return new StarHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StarAdapter.StarHolder holder, int position) {
        Star star = stars.get(position);
        holder.starName.setText(star.getName());
    }

    @Override
    public int getItemCount() {
        return stars == null ? 0 : stars.size();
    }

    public static class StarHolder extends RecyclerView.ViewHolder {
        private TextView starName;

        public StarHolder(@NonNull View itemView) {
            super(itemView);
            starName = itemView.findViewById(R.id.tv_star);
        }
    }

    public boolean isGroupHeader(int position) {
        if (position == 0){
            return true;
        } else {
            String currentGroupName = getGroupName(position);
            if (!currentGroupName.equals(getGroupName(position -1))){
                return true;
            }
        }
        return false;
    }

    public String getGroupName(int position){
        return stars.get(position).getGroup();
    }
}
