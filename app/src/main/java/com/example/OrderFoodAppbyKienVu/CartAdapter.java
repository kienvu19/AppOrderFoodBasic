package com.example.OrderFoodAppbyKienVu;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private final Context context;
    private Activity activity;
    private final ArrayList food_id;
    private final ArrayList food_title;
    private final ArrayList food_sl;
    private final ArrayList food_price;

    Locale localeVN = new Locale("vi", "VN");
    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);

    public CartAdapter(Context context, Activity activity, ArrayList food_id, ArrayList food_title, ArrayList food_sl, ArrayList food_price) {
        this.context = context;
        this.activity = activity;
        this.food_id = food_id;
        this.food_title = food_title;
        this.food_sl = food_sl;
        this.food_price = food_price;
    }
    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cartlist, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        holder.food_id_txt.setText(String.valueOf(food_id.get(position)));
        holder.food_title_txt.setText(String.valueOf(food_title.get(position)));
        holder.food_sl_txt.setText(String.valueOf(food_sl.get(position)));
//        holder.food_price_txt.setText(String.valueOf(food_price.get(position)));
        holder.food_price_txt.setText(currencyVN.format(food_price.get(position)));
        //Recyclerview onClickListener
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateDeleteActivity.class);
                intent.putExtra("id", String.valueOf(food_id.get(position)));
                intent.putExtra("title", String.valueOf(food_title.get(position)));
                intent.putExtra("sl", String.valueOf(food_sl.get(position)));
                intent.putExtra("price", String.valueOf(food_price.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
        holder.layout.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.anim_four));
    }

    @Override
    public int getItemCount() {
        return food_id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView food_id_txt, food_title_txt, food_sl_txt, food_price_txt;
        RelativeLayout layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            food_id_txt = itemView.findViewById(R.id.id_txt);
            food_title_txt = itemView.findViewById(R.id.title_txt);
            food_sl_txt = itemView.findViewById(R.id.sl_txt);
            food_price_txt = itemView.findViewById(R.id.price_txt);
            layout = itemView.findViewById(R.id.layout_cart);
        }
    }
}
