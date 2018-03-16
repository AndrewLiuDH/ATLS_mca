package com.digihuman.atls_mca.ui;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.digihuman.atls_mca.R;


import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class LanFragment extends BaseFragment {

	
	private static String commData = "";
	private static boolean newData = false;
	
	
	private static boolean analysis_ok = false;
	private Timer mTimerRequest1 = new Timer();
	
	private Button bt_lan_request,bt_lan_init;
	
	
	private MainActivity mMainActivity ;
	
//	private LanAnalysisThread mLanAnalysisThread;
	
	
	private TextView txv_mcu_mac1;
	
	private TextView txv_led_mac1;
	
	private TextView txv_fan_mac1;
	private TextView txv_fan_mac2;
	private TextView txv_fan_mac3;
	private TextView txv_fan_mac4;
	private TextView txv_fan_mac5;
	private TextView txv_fan_mac6;
	
	private TextView txv_monitor_mac1;
	private TextView txv_monitor_mac2;
	private TextView txv_monitor_mac3;
	private TextView txv_monitor_mac4;
	private TextView txv_monitor_mac5;
	private TextView txv_monitor_mac6;
	
	private TextView txv_o3_mac1;
	
	private TextView txv_switch_mac1;
	private TextView txv_switch_mac2;
	private TextView txv_switch_mac3;
	private TextView txv_switch_mac4;
	private TextView txv_switch_mac5;
	private TextView txv_switch_mac6;
	private TextView txv_switch_mac7;
	private TextView txv_switch_mac8;
	private TextView txv_switch_mac9;
	
	
	
	
	

	
	
	private static final int LAN_REQ_MAC = 0x10;
	
	
	
	
	public final Handler mHandlerLan = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			Bundle bundle = msg.getData();
			String strArray[] = null;
			switch (msg.what) {
				
	//			loadTextContents();
	//			break;
			
			case LAN_REQ_MAC:
				String at_string = "AT+000?\r\n";
				mMainActivity.sendComMessage(at_string);
				flash_data();
				break;
			default:
				break;
			}
		}		
	};
	
	
	 /**
	 * ��������ʾ����
	 * @param iFlag
	 */
	private void clearTextContents(){
		
		
		
		mMainActivity.str_mcu_mac1 = "--------";
		
		mMainActivity.str_led_mac1 = "--------";
		
		mMainActivity.str_fan_mac1 = "--------";
		mMainActivity.str_fan_mac2 = "--------";
		mMainActivity.str_fan_mac3 = "--------";
		mMainActivity.str_fan_mac4 = "--------";
		mMainActivity.str_fan_mac5 = "--------";
		mMainActivity.str_fan_mac6 = "--------";
		
		mMainActivity.str_monitor_mac1 = "--------";
		mMainActivity.str_monitor_mac2 = "--------";
		mMainActivity.str_monitor_mac3 = "--------";
		mMainActivity.str_monitor_mac4 = "--------";
		mMainActivity.str_monitor_mac5 = "--------";
		mMainActivity.str_monitor_mac6 = "--------";
		
		
		mMainActivity.str_o3_mac1 = "--------";


		mMainActivity.str_switch_mac1 = "--------";
		mMainActivity.str_switch_mac2 = "--------";
		mMainActivity.str_switch_mac3 = "--------";
		mMainActivity.str_switch_mac4 = "--------";
		mMainActivity.str_switch_mac5 = "--------";
		mMainActivity.str_switch_mac6 = "--------";
		mMainActivity.str_switch_mac7 = "--------";
		mMainActivity.str_switch_mac8 = "--------";
		mMainActivity.str_switch_mac9 = "--------";
		flash_data();
		
		
	}
	
	/**
	 * װ�������ʾ����
	 * @param iFlag
	 */
	public void flash_data(){
		
		txv_mcu_mac1.setText(mMainActivity.str_mcu_mac1);
		txv_led_mac1.setText(mMainActivity.str_led_mac1);
		txv_fan_mac1.setText(mMainActivity.str_fan_mac1);
		txv_fan_mac2.setText(mMainActivity.str_fan_mac2);
		txv_fan_mac3.setText(mMainActivity.str_fan_mac3);
		txv_fan_mac4.setText(mMainActivity.str_fan_mac4);
		txv_fan_mac5.setText(mMainActivity.str_fan_mac5);
		txv_fan_mac6.setText(mMainActivity.str_fan_mac6);
		txv_monitor_mac1.setText(mMainActivity.str_monitor_mac1);
		txv_monitor_mac2.setText(mMainActivity.str_monitor_mac2);
		txv_monitor_mac3.setText(mMainActivity.str_monitor_mac3);
		txv_monitor_mac4.setText(mMainActivity.str_monitor_mac4);
		txv_monitor_mac5.setText(mMainActivity.str_monitor_mac5);
		txv_monitor_mac6.setText(mMainActivity.str_monitor_mac6);
		txv_o3_mac1.setText(mMainActivity.str_o3_mac1);
		txv_switch_mac1.setText(mMainActivity.str_switch_mac1);
		txv_switch_mac2.setText(mMainActivity.str_switch_mac2);
		txv_switch_mac3.setText(mMainActivity.str_switch_mac3);
		txv_switch_mac4.setText(mMainActivity.str_switch_mac4);
		txv_switch_mac5.setText(mMainActivity.str_switch_mac5);
		txv_switch_mac6.setText(mMainActivity.str_switch_mac6);
		txv_switch_mac7.setText(mMainActivity.str_switch_mac7);
		txv_switch_mac8.setText(mMainActivity.str_switch_mac8);
		txv_switch_mac9.setText(mMainActivity.str_switch_mac9);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View lanLayout = inflater.inflate(R.layout.lan_layout, container,
				false);


		mMainActivity = (MainActivity) getActivity();

		
		
		bt_lan_request = (Button) lanLayout.findViewById(R.id.Lan_btn_request_lan);
		bt_lan_init = (Button) lanLayout.findViewById(R.id.Lan_btn_init_lan);
		
		txv_mcu_mac1 = (TextView)lanLayout.findViewById(R.id.Lan_txv_mcu_mac1_value);
		
		txv_led_mac1 = (TextView)lanLayout.findViewById(R.id.Lan_txv_led_mac1_value);
		
		txv_fan_mac1 = (TextView)lanLayout.findViewById(R.id.Lan_txv_fan_mac1_value);
		txv_fan_mac2 = (TextView)lanLayout.findViewById(R.id.Lan_txv_fan_mac2_value);
		txv_fan_mac3 = (TextView)lanLayout.findViewById(R.id.Lan_txv_fan_mac3_value);
		txv_fan_mac4 = (TextView)lanLayout.findViewById(R.id.Lan_txv_fan_mac4_value);
		txv_fan_mac5 = (TextView)lanLayout.findViewById(R.id.Lan_txv_fan_mac5_value);
		txv_fan_mac6 = (TextView)lanLayout.findViewById(R.id.Lan_txv_fan_mac6_value);
		
		
		txv_monitor_mac1 = (TextView)lanLayout.findViewById(R.id.Lan_txv_monitor_mac1_value);
		txv_monitor_mac2 = (TextView)lanLayout.findViewById(R.id.Lan_txv_monitor_mac2_value);
		txv_monitor_mac3 = (TextView)lanLayout.findViewById(R.id.Lan_txv_monitor_mac3_value);
		txv_monitor_mac4 = (TextView)lanLayout.findViewById(R.id.Lan_txv_monitor_mac4_value);
		txv_monitor_mac5 = (TextView)lanLayout.findViewById(R.id.Lan_txv_monitor_mac5_value);
		txv_monitor_mac6 = (TextView)lanLayout.findViewById(R.id.Lan_txv_monitor_mac6_value);
		
		txv_o3_mac1 = (TextView)lanLayout.findViewById(R.id.Lan_txv_o3_mac1_value);
		
		
		txv_switch_mac1 = (TextView)lanLayout.findViewById(R.id.Lan_txv_switch_mac1_value);
		txv_switch_mac2 = (TextView)lanLayout.findViewById(R.id.Lan_txv_switch_mac2_value);
		txv_switch_mac3 = (TextView)lanLayout.findViewById(R.id.Lan_txv_switch_mac3_value);
		txv_switch_mac4 = (TextView)lanLayout.findViewById(R.id.Lan_txv_switch_mac4_value);
		txv_switch_mac5 = (TextView)lanLayout.findViewById(R.id.Lan_txv_switch_mac5_value);
		txv_switch_mac6 = (TextView)lanLayout.findViewById(R.id.Lan_txv_switch_mac6_value);
		txv_switch_mac7 = (TextView)lanLayout.findViewById(R.id.Lan_txv_switch_mac7_value);
		txv_switch_mac8 = (TextView)lanLayout.findViewById(R.id.Lan_txv_switch_mac8_value);
		txv_switch_mac9 = (TextView)lanLayout.findViewById(R.id.Lan_txv_switch_mac9_value);

		
		flash_data();
		
		bt_lan_request.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				String at_string = "AT+000?\r\n";
				mMainActivity.sendComMessage(at_string);
				
				
				final ProgressDialog dialog = new ProgressDialog(mMainActivity); 
				//���ý�������񣬷��ΪԲ�Σ���ת�� 
				dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); 
				//����ProgressDialog ���� 
				dialog.setTitle("��ѯZigBee��������"); 
				//����ProgressDialog ��ʾ��Ϣ 
				dialog.setMessage("��ѯ�����С���"); 
				//����ProgressDialog ����ͼ�� 
				dialog.setIcon(android.R.drawable.ic_dialog_map); 
				
				//����ProgressDialog �Ľ������Ƿ���ȷ 
				dialog.setIndeterminate(false); 
				//����ProgressDialog �Ƿ���԰��˻ذ���ȡ�� 
				dialog.setCancelable(true); 
				//��ʾ 
				dialog.show();
				
				Thread thread = new Thread() {
					public void run() {
					try {
						sleep(1500);
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
						}
						dialog.dismiss();// ���򲻿�����䣬��������Ῠ����
					}
					};
					thread.start();

				
				
				
				
			}
		});
		
		bt_lan_init.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				new AlertDialog.Builder(mMainActivity).setTitle("��ȷ��Ҫ��ʼ��Zigbee��������?")  
                .setIcon(android.R.drawable.ic_dialog_info)
                .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {  
                    @Override  
                    public void onClick(DialogInterface arg0, int arg1) {  
                    	
                    	
                    	String at_string = "AT+000=1\r\n";
        				mMainActivity.sendComMessage(at_string);
        				clearTextContents();
        				
        				mMainActivity.editor.putString("control_textview_actor_1", "ִ����1");  
                    	mMainActivity.editor.commit();
                    	mMainActivity.editor.putString("control_textview_actor_2", "ִ����2");  
                    	mMainActivity.editor.commit();
                    	mMainActivity.editor.putString("control_textview_actor_3", "ִ����3");  
                    	mMainActivity.editor.commit();
                    	mMainActivity.editor.putString("control_textview_actor_4", "ִ����4");  
                    	mMainActivity.editor.commit();
                    	mMainActivity.editor.putString("control_textview_actor_5", "ִ����5");  
                    	mMainActivity.editor.commit();
                    	mMainActivity.editor.putString("control_textview_actor_6", "ִ����6");  
                    	mMainActivity.editor.commit();
                    	mMainActivity.editor.putString("control_textview_actor_7", "ִ����7");  
                    	mMainActivity.editor.commit();
                    	mMainActivity.editor.putString("control_textview_actor_8", "ִ����8");  
                    	mMainActivity.editor.commit();
                    	mMainActivity.editor.putString("control_textview_actor_9", "ִ����9");  
                    	mMainActivity.editor.commit();
                    }  
                }).setNegativeButton("ȡ��", null).show();
				
				
				
				
				
            	
	
			}
		});
		
		
		
		return lanLayout;
	}
	
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	
		MainActivity.curFragmentTag = getString(R.string.lan_fg);
		
		clearDataReceived();
//		mLanAnalysisThread = new LanAnalysisThread();//�������ݵ��߳�
//		mLanAnalysisThread.start();
		
		analysis_ok = true;
		
		
		
		mTimerRequest1 = new Timer();
		mTimerRequest1.schedule(new TimerTask(){				
			public void run()
			{
				mHandlerLan.sendEmptyMessage(LAN_REQ_MAC);
			}
		},0,1000);  //��ʱ0ms��ִ��,1sִ��һ��


		mMainActivity.lanFragmentOn = true;
	}
	
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub


		mMainActivity.lanFragmentOn = false;
		mTimerRequest1.cancel();
		
		
		analysis_ok = false;
		
//		if (mLanAnalysisThread!=null) {
//			mLanAnalysisThread.interrupt();
//			mLanAnalysisThread=null;
//		}

		super.onPause();
	}
	public void clearDataReceived(){
		
		commData = "";
		newData = false;
		
	}

}
