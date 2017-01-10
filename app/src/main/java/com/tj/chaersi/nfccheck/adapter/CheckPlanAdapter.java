package com.tj.chaersi.nfccheck.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.Utils.OnClickUtils;
import com.tj.chaersi.nfccheck.impl.OnRecyclerViewListener;
import com.tj.chaersi.nfccheck.vo.CheckPlanModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chaersi on 16/12/29.
 */
public class CheckPlanAdapter extends RecyclerView.Adapter<CheckPlanAdapter.ItemViewHolder> {

    private Context context;
    private List<CheckPlanModel.ListBean> checkArr;
    public CheckPlanAdapter(Context context,List<CheckPlanModel.ListBean> checkArr){
        this.context=context;
        this.checkArr=checkArr;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_checkplan, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.position=position;
        holder.planPointName.setText(checkArr.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return checkArr.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView planPointName;
        public LinearLayout parentLayout;
        public int position;
        public ItemViewHolder(View itemView) {
            super(itemView);
            planPointName= (TextView) itemView.findViewById(R.id.planPointName);
            parentLayout= (LinearLayout) itemView.findViewById(R.id.parentLayout);
            parentLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (OnClickUtils.isFastDoubleClick()) {
                return;
            }
            switch (v.getId()){
                case R.id.parentLayout:
                    listener.onItemClickListener(position);
                    break;
            }
        }
    }

    public static OnRecyclerViewListener listener;
    public void addItemClickListener(OnRecyclerViewListener listener){
        this.listener=listener;
    }

}
