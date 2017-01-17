package com.tj.chaersi.nfccheck.adapter;

import android.content.Context;
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
public class CheckDetailAdapter_Data extends BaseAdapter {

    private Context context;
    private ArrayList<PointDetailModel.ListBean> dataArr;

    private ArrayList<HashMap<String,String>> editArr;
    public CheckDetailAdapter_Data(Context context,ArrayList<PointDetailModel.ListBean> dataArr){
        this.context=context;
        this.dataArr=dataArr;

        editArr=new ArrayList<>();
        for(int i=0;i<dataArr.size();i++){
            HashMap<String,String> itemMap=new HashMap<String, String>();
            itemMap.put("position",i+"");
            itemMap.put("editValue","");
            editArr.add(itemMap);
        }
    }

    @Override
    public int getCount() {
        return dataArr.size();
    }

    @Override
    public Object getItem(int position) {
        return dataArr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_type_data, null,false);
            holder=new ViewHolder();
            holder.itemTip= (TextView) convertView.findViewById(R.id.dataTip);
            holder.itemEdit=(EditText) convertView.findViewById(R.id.dataItemEdit);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }

        holder.itemTip.setText(dataArr.get(position).getName()+"：");

        holder.itemEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus==false){
                        EditText edt = (EditText)v.findViewById(R.id.dataItemEdit);
                        HashMap<String,String> itemMap=editArr.get(position);
                        itemMap.put("position",position+"");
                        itemMap.put("editValue",edt.getText().toString());
                }
            }
        });

        holder.itemEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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
        if(position==dataArr.size()-1){
            holder.itemEdit.setImeOptions(EditorInfo.IME_ACTION_DONE);
        }else{
            holder.itemEdit.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        }
        return convertView;
    }
    private class ViewHolder{
        TextView itemTip;
        EditText itemEdit;
    }
}
