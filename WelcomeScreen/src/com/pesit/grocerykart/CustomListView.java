package com.pesit.grocerykart;

import java.util.List;


import  android.app.Activity;
import android.content.Context;
import android.graphics.Color;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;




public class CustomListView extends ArrayAdapter<GroceryList> {
 
    Context context;
    List<GroceryList> lt;
    
    public CustomListView(Context context, int resourceId,
            List<GroceryList> items) {
        super(context, resourceId, items);
        this.context = context;
        this.lt=items;
    }
 
    /*private view holder class*/
    private class ViewHolder {
        TextView txtName;
        TextView txtQuantity;
        TextView txtPrice;
        TextView txtDistributor;
        
    }
 
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder holder = null;
        GroceryList groc = getItem(position);
 
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.listitems, null);
            mInflater.inflate(R.layout.listitems, null);
            holder = new ViewHolder();
            holder.txtName = (TextView) convertView.findViewById(R.id.name);
            holder.txtQuantity = (TextView) convertView.findViewById(R.id.quantity);
            holder.txtPrice = (TextView) convertView.findViewById(R.id.price);
            holder.txtDistributor = (TextView) convertView.findViewById(R.id.distributor);
            convertView.setTag(holder);
            
        } 
        else
        	holder=((ViewHolder)convertView.getTag());
        
        
         ViewHolder vh = (ViewHolder)convertView.getTag();
        vh.txtName.setTextColor(Color.BLACK);
        vh.txtName.setText(groc.getName());
        vh.txtQuantity.setTextColor(Color.BLACK);
        vh.txtQuantity.setText(String.valueOf(groc.getQuantity()));
        vh.txtPrice.setTextColor(Color.BLACK);
        vh.txtPrice.setText(String.valueOf(groc.getPrice()));
        vh.txtDistributor.setTextColor(Color.BLACK);
        vh.txtDistributor.setText(groc.getDistributor());
      
        return convertView;
    }
}

