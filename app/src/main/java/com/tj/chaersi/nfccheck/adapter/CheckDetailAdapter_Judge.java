package com.tj.chaersi.nfccheck.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.vo.PointDetailModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Chaersi on 17/1/16.
 */
public class CheckDetailAdapter_Judge extends BaseAdapter {

    private Context context;
    private ArrayList<PointDetailModel.ListBean> judgeArr;
    private ArrayList<HashMap<String,Boolean>> chooseArr;
    public CheckDetailAdapter_Judge(Context context,ArrayList<PointDetailModel.ListBean> judgeArr){
        this.context=context;
        this.judgeArr=judgeArr;

        chooseArr=new ArrayList<>();
        for(int i=0;i<judgeArr.size();i++){
            HashMap<String,Boolean> item=new HashMap<>();
            item.put("state",true);
            chooseArr.add(item);
        }
    }

    @Override
    public int getCount() {
        return judgeArr.size();
    }

    @Override
    public Object getItem(int position) {
        return judgeArr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_type_judge, null,false);
            holder=new ViewHolder();
            holder.judgeTip= (TextView) convertView.findViewById(R.id.judgeTip);
            holder.judgeNormal= (TextView) convertView.findViewById(R.id.judgeNormal);
            holder.judgeException= (TextView) convertView.findViewById(R.id.judgeException);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }

        holder.judgeTip.setText(judgeArr.get(position).getName());

        if(chooseArr.get(position).get("state")){
            holder.judgeNormal.setTextColor(Color.WHITE);
            holder.judgeNormal.setBackgroundResource(R.drawable.bg_btn_border_blue);
            holder.judgeException.setTextColor(context.getResources().getColor(R.color.text_grey));
            holder.judgeException.setBackgroundResource(R.drawable.bg_btn_border);
        }else{
            holder.judgeNormal.setTextColor(context.getResources().getColor(R.color.text_grey));
            holder.judgeNormal.setBackgroundResource(R.drawable.bg_btn_border);
            holder.judgeException.setTextColor(Color.WHITE);
            holder.judgeException.setBackgroundResource(R.drawable.bg_btn_border_blue);
        }

        holder.judgeNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!chooseArr.get(position).get("state")){
                    chooseArr.get(position).put("state",true);
                }
                notifyDataSetChanged();
            }
        });

        holder.judgeException.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chooseArr.get(position).get("state")){
                    chooseArr.get(position).put("state",false);
                }
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    private class ViewHolder{
        TextView judgeTip,judgeNormal,judgeException;
    }
}
