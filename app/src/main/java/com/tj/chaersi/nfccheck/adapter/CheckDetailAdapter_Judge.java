package com.tj.chaersi.nfccheck.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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
    private ArrayList<HashMap<String,String>> chooseArr;
    public CheckDetailAdapter_Judge(Context context,ArrayList<PointDetailModel.ListBean> judgeArr){
        this.context=context;
        this.judgeArr=judgeArr;

        chooseArr=new ArrayList<>();
        for(int i=0;i<judgeArr.size();i++){
            HashMap<String,String> item=new HashMap<>();
            item.put("state","1");
            item.put("remark","");
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
            holder.judgeEdit= (EditText) convertView.findViewById(R.id.judgeEdit);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }

        holder.judgeTip.setText(judgeArr.get(position).getName());

        if("1".equals(chooseArr.get(position).get("state"))){
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
                if("0".equals(chooseArr.get(position).get("state"))){
                    chooseArr.get(position).put("state","1");
                }
                notifyDataSetChanged();
            }
        });

        holder.judgeException.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("1".equals(chooseArr.get(position).get("state"))){
                    chooseArr.get(position).put("state","0");
                }
                notifyDataSetChanged();
            }
        });


        holder.judgeEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus==false){
                    EditText edt = (EditText)v.findViewById(R.id.dataItemEdit);
                    HashMap<String,String> itemMap=chooseArr.get(position);
                    itemMap.put("remark",edt.getText().toString());
                }
            }
        });

        holder.judgeEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    EditText edt = (EditText)v.findViewById(R.id.dataItemEdit);
                    edt.clearFocus();
                    InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return false;
            }
        });

        if(position==chooseArr.size()-1){
            holder.judgeEdit.setImeOptions(EditorInfo.IME_ACTION_DONE);
        }else{
            holder.judgeEdit.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        }
        return convertView;
    }

    private class ViewHolder{
        TextView judgeTip,judgeNormal,judgeException;
        EditText judgeEdit;
    }
}
