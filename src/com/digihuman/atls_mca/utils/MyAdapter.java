package com.digihuman.atls_mca.utils;

import java.util.List;

import com.digihuman.atls_mca.R;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class MyAdapter extends BaseAdapter{
	
	private LayoutInflater mInflater;
	Context context;
	Handler myHandler;
	private List<String> list;
	
	public MyAdapter(Context context,List<String> list,Handler mHandler){
		this.context = context;
		mInflater = LayoutInflater.from(context);
		this.list = list;
		myHandler = mHandler;
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.size();
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.book_item_adapter, null);
		}
		convertView.setTag(position);

		TextView sItemTitle =  (TextView) convertView.findViewById(R.id.id);
		sItemTitle.setText(list.get(position));//ÏÔÊ¾ÄÚÈÝ
		
		//É¾³ý
		TextView del = (TextView) convertView.findViewById(R.id.tv_dele);
		
		
		addListener(del,position);
		return  convertView;
	}
	
 	private void addListener(final TextView image ,final int item) {
		// TODO Auto-generated method 
 			image.setOnClickListener(new View.OnClickListener() {
 				@Override
 				public void onClick(View v) {
 //					Utils.MyLog("É¾³ý----onclick:"+item);
 	
 					Message message = myHandler.obtainMessage();
 					message.what=12;
 					Bundle bundle = new Bundle();
 					bundle.putInt("position", item);
 					message.setData(bundle);
 					myHandler.sendMessage(message);
 				}
 			});

 		

 		

 		
			
	}
	
	
}