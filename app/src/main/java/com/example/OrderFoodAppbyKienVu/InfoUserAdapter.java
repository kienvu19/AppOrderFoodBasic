package com.example.OrderFoodAppbyKienVu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class InfoUserAdapter extends RecyclerView.Adapter<InfoUserAdapter.UserViewHolder>{
    private final Context context;
    private Activity activity;
    private final ArrayList userid;
    private final ArrayList userten;
    private final ArrayList userdiachi;
    private final ArrayList usersdt;

    public InfoUserAdapter(Context context, Activity activity, ArrayList userid, ArrayList userten, ArrayList userdiachi, ArrayList usersdt){
        this.context = context;
        this.activity = activity;
        this.userid = userid;
        this.userten = userten;
        this.userdiachi = userdiachi;
        this.usersdt = usersdt;
    }

    @NonNull
    @Override
    public InfoUserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.info_user_remember, parent, false);
        return new InfoUserAdapter.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoUserAdapter.UserViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id.setText(String.valueOf(userid.get(position)));
        holder.ten.setText(String.valueOf(userten.get(position)));
        holder.diachi.setText(String.valueOf(userdiachi.get(position)));
        holder.sodienthoai.setText(String.valueOf(usersdt.get(position)));
        //Recyclerview onClickListener
        holder.layoutuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PaymentFood.class);
                intent.putExtra("id_user", String.valueOf(userid.get(position)));
                intent.putExtra("name_user", String.valueOf(userten.get(position)));
                intent.putExtra("add_user", String.valueOf(userdiachi.get(position)));
                intent.putExtra("sdt_user", String.valueOf(usersdt.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
        holder.layoutuser.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.anim_four));
    }

    @Override
    public int getItemCount() {
        return userid.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        TextView id,ten,diachi,sodienthoai;
        RelativeLayout layoutuser;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.saveiduser);
            ten = itemView.findViewById(R.id.savetenuser);
            diachi = itemView.findViewById(R.id.savediachiuser);
            sodienthoai = itemView.findViewById(R.id.savesdtuser);
            layoutuser = itemView.findViewById(R.id.layout_history);
        }
    }
}
