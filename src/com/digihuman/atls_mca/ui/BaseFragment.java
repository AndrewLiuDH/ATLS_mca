package com.digihuman.atls_mca.ui;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import com.digihuman.atls_mca.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android_serialport_api.SerialPort;



public class BaseFragment extends Fragment {

	
	
	
	public Context mContext;
	
	
	
	
	private static final String TAG = "BaseFragment";
	public static String curFragmentTag = MainActivity.curFragmentTag;
	
	public FragmentManager mFragmentManager;
	public FragmentTransaction mFragmentTransaction;
	
	
	
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		Log.d(TAG, "onAttach------");
	}
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
		 this.mContext = getActivity();
		 
		
		Log.d(TAG, "onCreate------");
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		Log.d(TAG, "onCreateView-------");
		return super.onCreateView(inflater, container, savedInstanceState);
		
	}
	
	public static BaseFragment newInstance(Context context,String tag){
		BaseFragment baseFragment =  null;
		if(TextUtils.equals(tag, context.getString(R.string.control_fg))){
			baseFragment = new ControlFragment();
		}else if(TextUtils.equals(tag, context.getString(R.string.status_fg))){
			baseFragment = new StatusFragment();
		}else if(TextUtils.equals(tag, context.getString(R.string.lan_fg))){
			baseFragment = new LanFragment();
		}else if(TextUtils.equals(tag, context.getString(R.string.user_fg))){
			baseFragment = new UserFragment();
		}else if(TextUtils.equals(tag, context.getString(R.string.timerstart_fg))){
			baseFragment = new TimerstartFragment();
		}
		
		return baseFragment;
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	
		Log.d(TAG, "onActivityCreated------");
//		Toast.makeText(mContext, "onActivityCreated",Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d(TAG, "onStart------");
//		Toast.makeText(mContext, "onStart",Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		
		
		
		
		
		
		
		Log.d(TAG, "onResume------");

	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		
		
		
		
		
		super.onPause();
		Log.d(TAG, "onPause------");
//		Toast.makeText(mContext, "onPause",Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d(TAG, "onStop------");
//		Toast.makeText(mContext, "onStop",Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		Log.d(TAG, "onDestroyView------");
//		Toast.makeText(mContext, "onDestroyView",Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		
		
		super.onDestroy();
		Log.d(TAG, "onDestroy------");
//		Toast.makeText(mContext, "onDestroy",Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		Log.d(TAG, "onDetach------");
//		Toast.makeText(mContext, "onDetach",Toast.LENGTH_SHORT).show();
	}
    
    

}

