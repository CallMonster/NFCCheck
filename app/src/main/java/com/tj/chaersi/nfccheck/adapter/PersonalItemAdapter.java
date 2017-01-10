package com.tj.chaersi.nfccheck.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.base.BaseApplication;

/**
 * Created by Chaersi on 16/12/26.
 */
public class PersonalItemAdapter extends BaseAdapter {

    private Context context;
    private String[] titleArr;
    private int[] tagImgArr;

    public PersonalItemAdapter(Context context) {
        this.context = context;
        titleArr = context.getResources().getStringArray(R.array.personalTitleArr);
        tagImgArr = new int[]{
                R.mipmap.ic_tag_personal_one,
                R.mipmap.ic_tag_personal_two,
                R.mipmap.ic_tag_personal_three
        };
    }

    @Override
    public int getCount() {
        return titleArr.length;
    }

    @Override
    public Object getItem(int position) {
        return titleArr[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_personal, null,false);
            holder=new ViewHolder();
            holder.itemIcon=(ImageView) convertView.findViewById(R.id.itemIcon);
            holder.itemName=(TextView) convertView.findViewById(R.id.itemName);
            holder.versionName=(TextView) convertView.findViewById(R.id.versionName);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }

        holder.itemIcon.setImageResource(tagImgArr[position]);
        holder.itemName.setText(titleArr[position]);
        if(position==1){
            holder.versionName.setText("版本："+BaseApplication.instance.checkAppVersion());
        }else{
            holder.versionName.setText("");
        }
        return convertView;
    }

    private class ViewHolder{
        ImageView itemIcon;
        TextView itemName;
        TextView versionName;
    }

}
