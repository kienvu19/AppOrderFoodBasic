package com.example.OrderFoodAppbyKienVu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

@RequiresApi(api = Build.VERSION_CODES.N)
public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder>{
    private List<Food> mlistFoods;

    Locale localeVN = new Locale("vi", "VN");
    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

    @SuppressLint("NotifyDataSetChanged")
    public void setFilteredList(List<Food> filteredList){
        this.mlistFoods = filteredList;
        notifyDataSetChanged();
    }
    Context context;
    public FoodAdapter(Context context,List<Food> mlistFoods){
        this.mlistFoods = mlistFoods;
        this.context = context;
    }
    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food, parent,false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        final Food food = mlistFoods.get(position);
        if(food == null){
            return;
        }
        holder.imgFood.setImageResource(food.getImage());
        holder.tvName.setText(food.getName());
//        holder.tvGia.setText(String.valueOf(food.getGia()));
        holder.tvGia.setText(currencyVN.format(food.getGia()));
        holder.tvGt.setText(food.getGioithieu());
        holder.layout_item.setOnClickListener(view -> onClickGoToDetail(food));
        holder.layout_item.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.anim_four));
    }
    private void onClickGoToDetail(Food food){
        Intent intent = new Intent(context, InfoFoodCart1.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("imgFood", food);
        bundle.putSerializable("nameFood", food);
        bundle.putSerializable("priceFood", food);
        bundle.putSerializable("detailFood",food);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if(mlistFoods!=null){
            return mlistFoods.size();
        }
        return 0;
    }

    public static class FoodViewHolder extends RecyclerView.ViewHolder{
        private final RelativeLayout layout_item;
        private final CircleImageView imgFood;
        private final TextView tvName;
        private final TextView tvGia;
        private final TextView tvGt;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            layout_item = itemView.findViewById(R.id.layout_item);
            imgFood = itemView.findViewById(R.id.img_food);
            tvName = itemView.findViewById(R.id.tv_nameFood);
            tvGia = itemView.findViewById(R.id.tv_gia);
            tvGt = itemView.findViewById(R.id.tv_gt);
        }

    }
}
