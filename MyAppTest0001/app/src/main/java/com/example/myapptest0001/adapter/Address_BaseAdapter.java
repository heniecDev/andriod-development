package com.example.myapptest0001.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapptest0001.R;
import com.example.myapptest0001.Bean.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Address_BaseAdapter extends BaseAdapter {

    public static final class DataWrapper{
        public ImageView imageSrc;
        public TextView name,phone;
        public ImageView tel,sem;
        public CheckBox checkBox;
    }
    private List<Person> mlist;
    private LayoutInflater inflater;
    private DataWrapper dataw;
    private Boolean[] hasChecked;
    private Map<Integer,Boolean> isCheck = new HashMap<Integer, Boolean>();
    Context mContext;


    public Address_BaseAdapter(Context mContext,List<Person> mlist){
        super();
        this.mlist = mlist;
        this.inflater = LayoutInflater.from(mContext);
        this.dataw = new DataWrapper();
        this.mContext = mContext;
        this.hasChecked = new Boolean[getCount()];
        for (int i=0;i<getCount();i++){
            this.hasChecked[i] = false;     //设置为联系人未被勾选
        }
    }
    public Boolean hasChecked(int i){
        return this.hasChecked[i];
    }
    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int i) {
        return mlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        //TODO Auto-generated method stub
        final ViewHolder holder;
        if (view == null){
            final LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.address_items,null);
            holder = new ViewHolder();
            holder.imageSrc = (ImageView)view.findViewById(R.id.address_img);
            holder.name = (TextView)view.findViewById(R.id.address_name);
            holder.phone = (TextView)view.findViewById(R.id.address_phone);
            holder.tel = (ImageView)view.findViewById(R.id.tel);
            holder.sem = (ImageView)view.findViewById(R.id.sem);
            holder.checkBox = (CheckBox)view.findViewById(R.id.address_check);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        holder.imageSrc.setImageResource(mlist.get(i).getImgId());
        holder.name.setText(mlist.get(i).getName());
        holder.phone.setText(mlist.get(i).getPhone());
        // 拨号
        holder.tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tel = mlist.get(i).getPhone();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                //使用 Intent 拨号
                Uri data = Uri.parse("tel:"+ tel);
                intent.setData(data);
                mContext.startActivity(intent);
            }
        });
        //发短信
        holder.sem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tel = mlist.get(i).getPhone();
                // Action用在隐式跳转
                Intent intent = new Intent();
                intent.setAction(intent.ACTION_SENDTO);
                Uri uri =Uri.parse("smsto:"+ tel);
                intent.setData(uri);
                intent.putExtra("sms_body","沉默是今晚的康桥");
                mContext.startActivity(intent);
            }
        });

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,((TextView)view).getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hasChecked[i] = !hasChecked[i];
                String b ;
                b = (hasChecked[i] == true)?"true":"false";
                if (holder.checkBox.isChecked()) {
                    Toast.makeText(mContext, ((TextView) view).getText().toString() + "被点击", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(mContext, ((TextView) view).getText().toString() + "被取消", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
    public Map<Integer,Boolean> getMap(){
        return isCheck;
    }
    static class ViewHolder{
        ImageView imageSrc;
        TextView name,phone;
        ImageView tel,sem;
        CheckBox checkBox;
    }

}

