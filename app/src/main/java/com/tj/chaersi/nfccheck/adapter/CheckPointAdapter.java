package com.tj.chaersi.nfccheck.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.Utils.DateUtils;
import com.tj.chaersi.nfccheck.Utils.OnClickUtils;
import com.tj.chaersi.nfccheck.impl.OnRecyclerViewListener;
import com.tj.chaersi.nfccheck.vo.CheckPointModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chaersi on 16/12/27.
 */
public class CheckPointAdapter extends RecyclerView.Adapter<CheckPointAdapter.ItemViewHolder>{

    private Context context;
    private ArrayList<CheckPointModel.ListBean> pointArr;
    private HashMap<Integer,Boolean> isChoosed;
    public CheckPointAdapter(Context context,ArrayList<CheckPointModel.ListBean> pointArr) {
        this.context = context;
        this.pointArr=pointArr;
        isChoosed=new HashMap<>();
        for(int i=0;i<pointArr.size();i++){
            isChoosed.put(i,false);
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_checkpoint, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        CheckPointModel.ListBean item=pointArr.get(position);
        holder.serialView.setText((position+1)+"");
        holder.pointName.setText(TextUtils.isEmpty(item.getName())?"-":item.getName());
        holder.checkState.setText(TextUtils.isEmpty(item.getState())?"-":item.getState());
        holder.checkTime.setText(TextUtils.isEmpty(item.getCreateDate()+"")?"-":DateUtils.convertTime(item.getCreateDate()));
        holder.checkWorker.setText(TextUtils.isEmpty(item.getUsername())?"-":item.getUsername());
        holder.position=position;
        if(isChoosed.get(position)){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.parentlayout.setBackgroundColor(context.getResources().getColor(R.color.c_crow,null));
                holder.serialView.setTextColor(context.getResources().getColor(R.color.c_teawhite,null));
                holder.pointName.setTextColor(context.getResources().getColor(R.color.c_teawhite,null));
                holder.checkState.setTextColor(context.getResources().getColor(R.color.c_teawhite,null));
                holder.checkTime.setTextColor(context.getResources().getColor(R.color.c_teawhite,null));
                holder.checkWorker.setTextColor(context.getResources().getColor(R.color.c_teawhite,null));
            }else{
                holder.parentlayout.setBackgroundColor(context.getResources().getColor(R.color.c_crow));
                holder.serialView.setTextColor(context.getResources().getColor(R.color.c_teawhite));
                holder.pointName.setTextColor(context.getResources().getColor(R.color.c_teawhite));
                holder.checkState.setTextColor(context.getResources().getColor(R.color.c_teawhite));
                holder.checkTime.setTextColor(context.getResources().getColor(R.color.c_teawhite));
                holder.checkWorker.setTextColor(context.getResources().getColor(R.color.c_teawhite));
            }
        }else{
            holder.parentlayout.setBackgroundColor(Color.WHITE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.serialView.setTextColor(context.getResources().getColor(R.color.text_grey,null));
                holder.pointName.setTextColor(context.getResources().getColor(R.color.text_grey,null));
                holder.checkState.setTextColor(context.getResources().getColor(R.color.text_grey,null));
                holder.checkTime.setTextColor(context.getResources().getColor(R.color.text_grey,null));
                holder.checkWorker.setTextColor(context.getResources().getColor(R.color.text_grey,null));
            }else{
                holder.serialView.setTextColor(context.getResources().getColor(R.color.text_grey));
                holder.pointName.setTextColor(context.getResources().getColor(R.color.text_grey));
                holder.checkState.setTextColor(context.getResources().getColor(R.color.text_grey));
                holder.checkTime.setTextColor(context.getResources().getColor(R.color.text_grey));
                holder.checkWorker.setTextColor(context.getResources().getColor(R.color.text_grey));
            }
        }
    }

    @Override
    public int getItemCount() {
        return pointArr.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView serialView,pointName,checkState,checkTime,checkWorker;
        public LinearLayout parentlayout;
        public int position;
        public ItemViewHolder(View itemView) {
            super(itemView);
            serialView= (TextView) itemView.findViewById(R.id.serialView);
            pointName= (TextView) itemView.findViewById(R.id.pointName);
            checkState= (TextView) itemView.findViewById(R.id.checkState);
            checkTime= (TextView) itemView.findViewById(R.id.checkTime);
            checkWorker= (TextView) itemView.findViewById(R.id.checkWorker);
            parentlayout= (LinearLayout) itemView.findViewById(R.id.parentLayout);
            parentlayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (OnClickUtils.isFastDoubleClick()) {
                return;
            }
            switch (v.getId()){
                case R.id.parentLayout:
                    resetChooseItem(position);
                    listener.onItemClickListener(position);
                    break;
            }
        }
    }

    /**
     * 重置item选择效果
     * @param position
     */
    private void resetChooseItem(int position){
        for(int i=0;i<pointArr.size();i++){
            isChoosed.put(i,false);
        }
        isChoosed.put(position,true);
        this.notifyDataSetChanged();
    }

    /**
     * 重置选择按钮
     * @param pointArr
     */
    public void notifyItemChoosed(List<CheckPointModel.ListBean> pointArr){
        this.pointArr.clear();
        this.pointArr.addAll(pointArr);
        isChoosed.clear();
        for(int i=0;i< this.pointArr.size();i++){
            isChoosed.put(i,false);
        }
        this.notifyDataSetChanged();
    }

    public static OnRecyclerViewListener listener;
    public void addItemClickListener(OnRecyclerViewListener listener){
        this.listener=listener;
    }

}
