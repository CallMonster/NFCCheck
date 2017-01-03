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

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Chaersi on 16/12/27.
 */
public class CheckPointAdapter extends RecyclerView.Adapter<CheckPointAdapter.ItemViewHolder>{

    private Context context;
    private ArrayList<HashMap<String,String>> arr;
    public CheckPointAdapter(Context context,ArrayList<HashMap<String,String>> arr) {
        this.context = context;
        this.arr=arr;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_checkpoint, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.serialView.setText(arr.get(position).get("serial"));
        holder.pointName.setText(arr.get(position).get("name"));
        holder.checkState.setText(arr.get(position).get("state").equals("1")?"已巡检":"未巡检");
        holder.checkTime.setText(arr.get(position).get("time"));
        holder.checkWorker.setText(arr.get(position).get("worker"));
        holder.position=position;
    }

    @Override
    public int getItemCount() {
        return arr.size();
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
