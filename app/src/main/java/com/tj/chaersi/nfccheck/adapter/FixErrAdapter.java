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
import com.tj.chaersi.nfccheck.vo.FixErrModel;

import java.util.List;

/**
 * Created by Chaersi on 17/1/17.
 */
public class FixErrAdapter extends RecyclerView.Adapter<FixErrAdapter.ItemViewHolder>{

    private Context context;
    private List<FixErrModel.ListBean> fixErrArr;
    public FixErrAdapter(Context context,List<FixErrModel.ListBean> fixErrArr){
        this.context=context;
        this.fixErrArr=fixErrArr;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_fixerr, null);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.position=position;

        if("1".equals(fixErrArr.get(position).getOverTime())){
            holder.fixState.setText("已过期");
        }else{
            holder.fixState.setText("");
        }

        holder.fixName.setText(fixErrArr.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return fixErrArr.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView fixName,fixState;
        public LinearLayout parentLayout;
        public int position;
        public ItemViewHolder(View itemView) {
            super(itemView);
            parentLayout= (LinearLayout) itemView.findViewById(R.id.parentLayout);
            fixName= (TextView) itemView.findViewById(R.id.fixName);
            fixState= (TextView) itemView.findViewById(R.id.fixState);
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
