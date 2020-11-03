package com.example.az_cx_day06.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.az_cx_day06.R;
import com.example.az_cx_day06.bean.FuLiBean;
import com.example.az_cx_day06.myinterface.SetOnItemClick;

import java.util.List;

public class MyRcyAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<FuLiBean.ResultsBean> list;
//    private SetOnItemClick setOnItemClick;
//
//    public void setSetOnItemClick(SetOnItemClick setOnItemClick) {
//        this.setOnItemClick = setOnItemClick;
//    }

    private final static int TYPE_ZERO = 0;
    private final static int TYPE_OME = 1;

    public MyRcyAdapter(Context context, List<FuLiBean.ResultsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        //注意下标的类型判断
        if (position %2== 0) {
            return TYPE_ZERO;
        }else {
            return TYPE_OME;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ZERO) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.rcy_item, parent, false);
            ViewHolder1 viewHolder1 = new ViewHolder1(inflate);
            return viewHolder1;
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.rcyone_item, parent, false);
            ViewHolder2 viewHolder2 = new ViewHolder2(view);
            return viewHolder2;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int type = getItemViewType(position);
        if (type == TYPE_ZERO) {
            ViewHolder1 holder1 = (ViewHolder1) holder;
            Glide.with(context).load(list.get(position).getUrl()).into(holder1.rcy_img);
            holder1.tv_text.setText(list.get(position).getDesc());


        }else {
            ViewHolder2 holder2 = (ViewHolder2) holder;
            Glide.with(context).load(list.get(position).getUrl()).into(holder2.rcy_img1);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.setItemOnClick(position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (itemLongClickListener != null) {
                    itemLongClickListener.setItemLongClick(position);
                }
                //设置true防止误操作单击
                return false;
            }
        });

    }

    public interface setOnItemClickListener {
        void setItemOnClick(int postion);
    }

    private setOnItemClickListener onItemClickListener;

    public void setSetOnItemClickListener(setOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface setItemLongClickListener {
        void setItemLongClick(int postion);
    }

    private setItemLongClickListener itemLongClickListener;

    public void setSetItemLongClickListener(setItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static
    class ViewHolder1 extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView rcy_img;
        public TextView tv_text;
        public ViewHolder1(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.rcy_img = (ImageView) rootView.findViewById(R.id.rcy_img);
            this.tv_text = (TextView) rootView.findViewById(R.id.tv_text);
        }

    }

    public static
    class ViewHolder2 extends RecyclerView.ViewHolder{
        public View rootView;
        public ImageView rcy_img1;

        public ViewHolder2(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.rcy_img1 = (ImageView) rootView.findViewById(R.id.rcy_img1);
        }

    }
}
