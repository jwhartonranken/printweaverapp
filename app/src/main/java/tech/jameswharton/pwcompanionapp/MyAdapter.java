package tech.jameswharton.pwcompanionapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList item_name;
    private ArrayList item_desc;
    private ArrayList item_type;


    public MyAdapter(Context context, Activity activity, ArrayList item_name, ArrayList item_desc, ArrayList item_type) {
        this.context = context;
        this.activity = activity;
        this.item_name = item_name;
        this.item_desc = item_desc;
        this.item_type = item_type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.individual_artifact, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvScrollName.setText(String.valueOf(item_name.get(position)));
        if (Integer.parseInt(item_type.get(position).toString()) == 0) {
            holder.ivArtifactType.setImageResource(R.drawable.ring);
        }
        else {
            holder.ivArtifactType.setImageResource(R.drawable.scroll);
        }
        // RecyclerView OnClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SingleItemExpandedActivity.class);
                intent.putExtra("name", String.valueOf(item_name.get(holder.getAdapterPosition())));
                intent.putExtra("desc", String.valueOf(item_desc.get(holder.getAdapterPosition())));
                intent.putExtra("type", String.valueOf(item_type.get(holder.getAdapterPosition())));
                activity.startActivity(intent);
            }
        });

        holder.mainLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("name", String.valueOf(item_name.get(holder.getAdapterPosition())));
                intent.putExtra("desc", String.valueOf(item_desc.get(holder.getAdapterPosition())));
                intent.putExtra("type", String.valueOf(item_type.get(holder.getAdapterPosition())));
                activity.startActivityForResult(intent, 1);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return item_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvScrollName;
        ImageView ivArtifactType;
        ConstraintLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvScrollName = itemView.findViewById(R.id.tvScrollName);
            ivArtifactType = itemView.findViewById(R.id.ivArtifactType);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
