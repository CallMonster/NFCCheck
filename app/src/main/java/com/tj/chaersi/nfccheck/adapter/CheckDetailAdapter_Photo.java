package com.tj.chaersi.nfccheck.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tj.chaersi.nfccheck.R;
import com.tj.chaersi.nfccheck.Utils.ImageLoadUtils;
import com.tj.chaersi.nfccheck.impl.OnAdapterListener;
import com.tj.chaersi.nfccheck.impl.OnRecyclerViewListener;
import com.tj.chaersi.nfccheck.vo.PointDetailModel;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Chaersi on 17/1/17.
 */
public class CheckDetailAdapter_Photo extends BaseAdapter {

    private Context context;
    private ArrayList<PointDetailModel.ListBean> photoArr;

    private ArrayList<HashMap<String,String>> itemAArr;
    private ArrayList<HashMap<String,String>> itemBArr;
    private ArrayList<HashMap<String,String>> itemCArr;
    public CheckDetailAdapter_Photo(Context context,ArrayList<PointDetailModel.ListBean> photoArr){
        this.context=context;
        this.photoArr=photoArr;

        itemAArr=new ArrayList<>();
        itemBArr=new ArrayList<>();
        itemCArr=new ArrayList<>();
        for(int i=0;i<photoArr.size();i++){
            HashMap<String,String> itemA=new HashMap<>();
            itemA.put("imgpath","");
            itemA.put("tag","A");
            itemAArr.add(itemA);

            HashMap<String,String> itemB=new HashMap<>();
            itemB.put("imgpath","");
            itemB.put("tag","B");
            itemBArr.add(itemB);

            HashMap<String,String> itemC=new HashMap<>();
            itemC.put("imgpath","");
            itemC.put("tag","C");
            itemCArr.add(itemC);
        }
    }

    @Override
    public int getCount() {
        return photoArr.size();
    }

    @Override
    public Object getItem(int position) {
        return photoArr.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_type_photo, null,false);
            holder=new ViewHolder();
            holder.itemTip= (TextView) convertView.findViewById(R.id.photoTip);
            holder.imgABtn=(ImageView) convertView.findViewById(R.id.imgABtn);
            holder.imgBBtn=(ImageView) convertView.findViewById(R.id.imgBBtn);
            holder.imgCBtn=(ImageView) convertView.findViewById(R.id.imgCBtn);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }
        PointDetailModel.ListBean item=photoArr.get(position);
        holder.itemTip.setText(item.getName());
        holder.imgABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemViewClickListener(position,"A");
            }
        });
        if(TextUtils.isEmpty(itemAArr.get(position).get("imgpath"))){
            holder.imgABtn.setImageResource(R.mipmap.ic_add_photo);
        }else{
            if(ImageLoadUtils.pathToBitmap(itemAArr.get(position).get("imgpath"))!=null){
                holder.imgABtn.setImageBitmap(ImageLoadUtils.pathToBitmap(itemAArr.get(position).get("imgpath")));
            }else{
                holder.imgABtn.setImageResource(R.mipmap.ic_add_photo);
            }
        }

        holder.imgBBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemViewClickListener(position,"B");
            }
        });
        if(TextUtils.isEmpty(itemBArr.get(position).get("imgpath"))){
            holder.imgBBtn.setImageResource(R.mipmap.ic_add_photo);
        }else{
            if(ImageLoadUtils.pathToBitmap(itemBArr.get(position).get("imgpath"))!=null){
                holder.imgBBtn.setImageBitmap(ImageLoadUtils.pathToBitmap(itemBArr.get(position).get("imgpath")));
            }else{
                holder.imgBBtn.setImageResource(R.mipmap.ic_add_photo);
            }
        }

        holder.imgCBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemViewClickListener(position,"C");
            }
        });
        if(TextUtils.isEmpty(itemCArr.get(position).get("imgpath"))){
            holder.imgCBtn.setImageResource(R.mipmap.ic_add_photo);
        }else{
            if(ImageLoadUtils.pathToBitmap(itemCArr.get(position).get("imgpath"))!=null){
                holder.imgCBtn.setImageBitmap(ImageLoadUtils.pathToBitmap(itemCArr.get(position).get("imgpath")));
            }else{
                holder.imgCBtn.setImageResource(R.mipmap.ic_add_photo);
            }
        }
        return convertView;
    }

    private class ViewHolder{
        TextView itemTip;
        ImageView imgABtn,imgBBtn,imgCBtn;
    }

    public void setImagePath(int position,String path_TAG,String imgPath){
        HashMap<String,String> item=new HashMap<>();
        item.put("tag",path_TAG);
        item.put("imgpath",imgPath);
        if("A".equals(path_TAG)){
            itemAArr.set(position,item);
        }else if("B".equals(path_TAG)){
            itemBArr.set(position,item);
        }else if("C".equals(path_TAG)){
            itemCArr.set(position,item);
        }
        notifyDataSetChanged();
    }

    public ArrayList<HashMap<String,String>> getItemAArr(){
        return itemAArr;
    }

    public static OnAdapterListener listener;
    public void addItemViewClickListener(OnAdapterListener listener){
        this.listener=listener;
    }

}
