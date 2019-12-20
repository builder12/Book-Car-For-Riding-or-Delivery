package com.jrteamtech.clonebla.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jrteamtech.clonebla.R;
import com.jrteamtech.clonebla.model.CarInfoModel;

import java.util.List;

public class CarInfoAdapter extends RecyclerView.Adapter<CarInfoAdapter.ViewHolder> {

    public String CHANGE_PHOTO = "change_photo";
    public String EDIT = "edit";
    public String DELETE = "delete";

    private Context mContext;
    private List<CarInfoModel> carInfoModelList;
    private LayoutInflater mInflater;
    private ItemClickListener mItemClickListener;
    private ItemMenuClickListener mMenuClickListener;

    // data is passed into the constructor
    public CarInfoAdapter(Context context, List<CarInfoModel> carInfoModelList) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.carInfoModelList = carInfoModelList;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.car_info_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CarInfoModel carInfoModel = carInfoModelList.get(position);
        holder.carInfoTypeImgView.setImageResource(carInfoModel.getImage());
        holder.carInfoMakeView.setText(carInfoModel.getMake());
        holder.carInfoModelView.setText(carInfoModel.getModel());
        holder.carInfoColorView.setText(carInfoModel.getColor_label());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return carInfoModelList.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView carInfoTypeImgView;
        TextView carInfoMakeView, carInfoModelView, carInfoColorView;
        ImageView carInfoOptionBtn;

        ViewHolder(View itemView) {
            super(itemView);
            carInfoTypeImgView = itemView.findViewById(R.id.car_info_type_img);
            carInfoMakeView = itemView.findViewById(R.id.car_info_make_view);
            carInfoModelView = itemView.findViewById(R.id.car_info_model_view);
            carInfoColorView = itemView.findViewById(R.id.car_info_color_view);
            carInfoOptionBtn = itemView.findViewById(R.id.car_info_option_btn);
            itemView.setOnClickListener(this);
            carInfoOptionBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.car_info_option_btn){
                if(mMenuClickListener != null){
                    PopupMenu popupMenu = new PopupMenu(mContext, view);
                    popupMenu.getMenuInflater().inflate(R.menu.car_info_item_menu, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.car_info_item_change_photo:
                                    mMenuClickListener.onItemMenuClick(view, getAdapterPosition(), CHANGE_PHOTO);
                                    break;
                                case R.id.car_info_item_edit:
                                    mMenuClickListener.onItemMenuClick(view, getAdapterPosition(), EDIT);
                                    break;
                                case R.id.car_info_item_delete:
                                    mMenuClickListener.onItemMenuClick(view, getAdapterPosition(), DELETE);
                                    break;
                            }
                            return true;
                        }
                    });

                    popupMenu.show();
                }
            } else {
                if (mItemClickListener != null) mItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    // convenience method for getting data at click position
//    String getItem(int id) {
//        return mData.get(id);
//    }
//
//    // allows clicks events to be caught
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public void setItemMenuClickListener(ItemMenuClickListener menuClickListener){
        this.mMenuClickListener = menuClickListener;
    }
//
//    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface ItemMenuClickListener {
        void onItemMenuClick(View view, int position, String type);
    }
}
