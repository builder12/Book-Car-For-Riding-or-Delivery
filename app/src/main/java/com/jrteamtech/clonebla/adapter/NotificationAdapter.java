package com.jrteamtech.clonebla.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jrteamtech.clonebla.R;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private Context context;

    public class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public CardView cardNotification;
        /* access modifiers changed from: private */
        public ImageView imageRemove;
        private ImageView imageUser;
        private TextView tvContent;
        private TextView tvTitle;

        public ViewHolder(@NonNull View view) {
            super(view);
            this.cardNotification = (CardView) view.findViewById(R.id.card_notification);
            this.imageUser = (ImageView) view.findViewById(R.id.image_user);
            this.imageRemove = (ImageView) view.findViewById(R.id.image_remove);
            this.tvTitle = (TextView) view.findViewById(R.id.tv_title);
            this.tvContent = (TextView) view.findViewById(R.id.tv_content);
        }
    }

    public int getItemCount() {
        return 11;
    }

    public NotificationAdapter(Context context2) {
        this.context = context2;
    }

    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_notification, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.cardNotification.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        viewHolder.imageRemove.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
    }
}
