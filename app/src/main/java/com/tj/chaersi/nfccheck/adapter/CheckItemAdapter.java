package com.tj.chaersi.nfccheck.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.Utils.OnClickUtils;
import com.tj.chaersi.nfccheck.impl.OnRecyclerViewListener;

/**
 * Created by Chaersi on 16/12/26.
 */
public class CheckItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public enum ITEM_TYPE {
        ITEM_TYPE_PAYITEM, ITEM_TYPE_PAYITEMBLANK
    }

    private Context context;
    private String[] indexTitleArr;
    private int[] indexTagArr;

    public CheckItemAdapter(Context context) {
        this.context = context;
        indexTitleArr = context.getResources().getStringArray(R.array.indexTitleArr);
        indexTagArr = new int[]{
                R.mipmap.ic_index_one, R.mipmap.ic_index_two,
                R.mipmap.ic_index_three, R.mipmap.ic_index_four,
                R.mipmap.ic_index_five
        };
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_PAYITEM.ordinal()) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_index, null);
            return new ItemViewHolder(view);
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.item_blank, null);
            return new BlankItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).itenName.setText(indexTitleArr[position]);
            ((ItemViewHolder) holder).itemIcon.setImageResource(indexTagArr[position]);
            ((ItemViewHolder) holder).position=position;
        }else if(holder instanceof BlankItemViewHolder){

        }
    }

    @Override
    public int getItemCount() {
        if(indexTitleArr.length%3==1){
            return indexTitleArr.length+2;
        }else if(indexTitleArr.length%3==2){
            return indexTitleArr.length+1;
        }else{
            return indexTitleArr.length;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position<indexTitleArr.length){
            return ITEM_TYPE.ITEM_TYPE_PAYITEM.ordinal();
        }else{
            return ITEM_TYPE.ITEM_TYPE_PAYITEMBLANK.ordinal();
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView itemIcon;
        public TextView itenName;
        public LinearLayout parentLayout;
        public int position;
        public ItemViewHolder(View itemView) {
            super(itemView);
            parentLayout= (LinearLayout) itemView.findViewById(R.id.parentLayout);
            itenName= (TextView) itemView.findViewById(R.id.itenName);
            itemIcon= (ImageView) itemView.findViewById(R.id.itemIcon);
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

    public class BlankItemViewHolder extends RecyclerView.ViewHolder{
        public BlankItemViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static OnRecyclerViewListener listener;
    public void addItemClickListener(OnRecyclerViewListener listener){
        this.listener=listener;
    }


}
