package com.example.dethimau;

import android.content.Context ;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NguyenVanAn_Adapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Contact_An> list;
    private ArrayList<Contact_An> arrayList = null;

    public NguyenVanAn_Adapter(Context context, int layout, List<Contact_An> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
        this.arrayList = new ArrayList<>();
        arrayList.addAll(list);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder {
        TextView txtId, txtName, txtPhone;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_layout, null);
            holder.txtId = view.findViewById(R.id.textId);
            holder.txtName = view.findViewById(R.id.textName);
            holder.txtPhone = view.findViewById(R.id.textSDT);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Contact_An dv = list.get(i);
        holder.txtId.setText(dv.getId()+ "");
        holder.txtName.setText(dv.getName());
        holder.txtPhone.setText(dv.getPhoneNum());
        return view;
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        list.clear();
        if (charText.length() == 0) {
            list.addAll(arrayList);
        }
        else
        {
            for (Contact_An wp : arrayList) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    list.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
}
