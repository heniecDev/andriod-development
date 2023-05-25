package com.example.myapptest0001.db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapptest0001.Bean.User;
import com.example.myapptest0001.R;

import java.util.List;

public class UserAdapter extends BaseAdapter {

    public final class DataWrapper{
        public TextView name,passwd,sex,edu;
    }
    private List<User> mList;
    private LayoutInflater inflater;
    private UserAdapter.DataWrapper dataw;
    Context mContext;

    public UserAdapter(Context mContext,List<User> mList){
        super();
        this.mList = mList;
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
        this.dataw = new UserAdapter.DataWrapper();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = inflater.inflate(R.layout.user_item,null);
            dataw.name = view.findViewById(R.id.tv_name);
            dataw.passwd = view.findViewById(R.id.tv_passwd);
            dataw.sex = view.findViewById(R.id.tv_sex);
            dataw.edu = view.findViewById(R.id.tv_edu);
            view.setTag(dataw);
        } else {
            dataw = (UserAdapter.DataWrapper)view.getTag();
        }
        dataw.name.setText(mList.get(i).getName());
        dataw.passwd.setText(mList.get(i).getPasswd());
        dataw.sex.setText(mList.get(i).getSex());
        dataw.edu.setText(mList.get(i).getEdu());
        return view;
    }
}
