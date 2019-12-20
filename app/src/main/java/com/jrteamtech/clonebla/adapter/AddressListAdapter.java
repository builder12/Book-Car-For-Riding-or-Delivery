package com.jrteamtech.clonebla.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jrteamtech.clonebla.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.ViewHolder> {

    private Context mContext;
    private List<JSONObject> itemModelList;
    private LayoutInflater mInflater;
    private ItemClickListener mItemClickListener;

    // data is passed into the constructor
    public AddressListAdapter(Context context, List<JSONObject> itemModelList) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.itemModelList = itemModelList;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.book_user_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        JSONObject itemModel = itemModelList.get(position);
        try {
            holder.leave_time_view.setText(itemModel.get("leave_time").toString());
            holder.leave_address_view.setText(itemModel.get("leave_address").toString());
            holder.drop_time_view.setText(itemModel.get("drop_time").toString());
            holder.drop_address_view.setText(itemModel.get("drop_address").toString());
            switch (itemModel.get("leave_walk_type").toString()){
                case "1":
                    holder.leave_walk_img_1.setImageResource(R.drawable.walk_green_icon);
                    break;
                case "2":
                    holder.leave_walk_img_2.setImageResource(R.drawable.walk_yellow_icon);
                    break;
                case "3":
                    holder.leave_walk_img_3.setImageResource(R.drawable.walk_orange_icon);
                    break;
            }
            switch (itemModel.get("drop_walk_type").toString()){
                case "1":
                    holder.drop_walk_img_1.setImageResource(R.drawable.walk_green_icon);
                    break;
                case "2":
                    holder.drop_walk_img_2.setImageResource(R.drawable.walk_yellow_icon);
                    break;
                case "3":
                    holder.drop_walk_img_3.setImageResource(R.drawable.walk_orange_icon);
                    break;
            }
            holder.price_view.setText(itemModel.get("currency").toString() + itemModel.get("price").toString());
            holder.user_name_view.setText(itemModel.getJSONObject("user_info").get("name").toString());
        } catch (JSONException e) {
            Log.e("JSONObject ERROR", e.getLocalizedMessage());
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return itemModelList.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView leave_time_view, drop_time_view;
        TextView leave_address_view, drop_address_view;
        TextView price_view;
        ImageView leave_walk_img_1, leave_walk_img_2, leave_walk_img_3;
        ImageView drop_walk_img_1, drop_walk_img_2, drop_walk_img_3;
        CircleImageView user_avatar_view;
        TextView user_name_view;

        ViewHolder(View itemView) {
            super(itemView);
            leave_time_view = itemView.findViewById(R.id.leave_time_view);
            leave_address_view = itemView.findViewById(R.id.leave_address_view);
            drop_time_view = itemView.findViewById(R.id.drop_time_view);
            drop_address_view = itemView.findViewById(R.id.drop_address_view);
            price_view = itemView.findViewById(R.id.price_view);
            leave_walk_img_1 = itemView.findViewById(R.id.leave_walk_img_1);
            leave_walk_img_2 = itemView.findViewById(R.id.leave_walk_img_2);
            leave_walk_img_3 = itemView.findViewById(R.id.leave_walk_img_3);
            drop_walk_img_1 = itemView.findViewById(R.id.drop_walk_img_1);
            drop_walk_img_2 = itemView.findViewById(R.id.drop_walk_img_2);
            drop_walk_img_3 = itemView.findViewById(R.id.drop_walk_img_3);
            user_avatar_view = itemView.findViewById(R.id.ci_user_avatar);
            user_name_view = itemView.findViewById(R.id.tv_user_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) mItemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
