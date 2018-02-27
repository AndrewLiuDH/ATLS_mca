package com.digihuman.atls_mca.ui;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.digihuman.atls_mca.R;


import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
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
	 * 清除组件显示内容
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
	 * 装载组件显示内容
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
				
				
			}
		});
		
		bt_lan_init.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				String at_string = "AT+000=1\r\n";
				mMainActivity.sendComMessage(at_string);
				clearTextContents();
	
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
//		mLanAnalysisThread = new LanAnalysisThread();//接受数据的线程
//		mLanAnalysisThread.start();
		
		analysis_ok = true;
		
		
		
		mTimerRequest1 = new Timer();
		mTimerRequest1.schedule(new TimerTask(){				
			public void run()
			{
				mHandlerLan.sendEmptyMessage(LAN_REQ_MAC);
			}
		},0,1000);  //延时0ms后执行,1s执行一次


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
