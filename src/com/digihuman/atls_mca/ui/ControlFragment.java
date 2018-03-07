package com.digihuman.atls_mca.ui;

import java.util.Timer;
import java.util.TimerTask;

import com.digihuman.atls_mca.R;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ControlFragment extends BaseFragment {
	
	
	private static int progress_saved;
	
	
	private MainActivity mMainActivity ;
	
	
	private SwitchButton mSwitchButtonLed;
	private SwitchButton mSwitchButtonFan;
	private SwitchButton mSwitchButtonO3;
	private SwitchButton mSwitchButtonSwitch1,mSwitchButtonSwitch2,mSwitchButtonSwitch3,mSwitchButtonSwitch4,mSwitchButtonSwitch5,mSwitchButtonSwitch6,mSwitchButtonSwitch7,mSwitchButtonSwitch8,mSwitchButtonSwitch9;

	private SeekBar mSeekBar;//拖动条
	
//	private Button btn_timerstart;
	

	private static final int CONTROL_FLASH = 0x20;
	private Timer mTimerFlash = new Timer();
	
	
	
	public final Handler mHandlerControl = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			Bundle bundle = msg.getData();
			String strArray[] = null;
			switch (msg.what) {
			case CONTROL_FLASH:
				
				flash_data();
				break;
				
			
			default:
				break;
			}
		}		
	};
	
	public void flash_data(){
	

		if(mSwitchButtonO3.getSwitchState() != mMainActivity.bSwitchButtonO3){
			
			mSwitchButtonO3.setSwitchState(mMainActivity.bSwitchButtonO3);
		}
		
		if(mSwitchButtonFan.getSwitchState() != mMainActivity.bSwitchButtonFan){
			
			mSwitchButtonFan.setSwitchState(mMainActivity.bSwitchButtonFan);
		}
	
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View controlLayout = inflater.inflate(R.layout.control_layout,
				container, false);
		
		mMainActivity = (MainActivity) getActivity();
		
		//实例化
		mSeekBar = (SeekBar) controlLayout.findViewById(R.id.led_seekBar);
		
		mSeekBar.setProgress(mMainActivity.sp.getInt("mSeekBarProgress", 159));
//		String at_string = "AT+011=16\r\n";
//		sendMessage(at_string); 
		
        
		mSwitchButtonLed = (SwitchButton) controlLayout.findViewById(R.id.led_onoff_switcher);  
		mSwitchButtonLed.setSwitchState(mMainActivity.sp.getBoolean("mSwitchButtonLed", false));  
		mSwitchButtonLed.setOnSwitchStateChangeListener(new OnSwitchStateChangeListener() {  
		    @Override  
		    public void onSwitchStateChange(boolean state) {  
		        if (state) {  
		        	String at_string = "AT+010=1\r\n";
		        	mMainActivity.sendComMessage(at_string); 
		        } else {  
		        	String at_string = "AT+010=0\r\n";
		        	mMainActivity.sendComMessage(at_string); 
		        }
		        mMainActivity.editor.putBoolean("mSwitchButtonLed", state);
				mMainActivity.editor.commit();
		        
		    }  
		});
		
		mSwitchButtonFan = (SwitchButton) controlLayout.findViewById(R.id.fan_onoff_switcher);  
		mSwitchButtonFan.setSwitchState(mMainActivity.sp.getBoolean("mSwitchButtonFan", false));  
		mSwitchButtonFan.setOnSwitchStateChangeListener(new OnSwitchStateChangeListener() {  
		    @Override  
		    public void onSwitchStateChange(boolean state) {  
		        if (state) {  
		        	
		        	mMainActivity.bSwitchButtonFan = true;
		        	String at_string = "AT+020=1\r\n";
		        	mMainActivity.sendComMessage(at_string);
					at_string = "AT+021=1\r\n";
					mMainActivity.sendComMessage(at_string);
					at_string = "AT+022=1\r\n";
					mMainActivity.sendComMessage(at_string);
					at_string = "AT+023=1\r\n";
					mMainActivity.sendComMessage(at_string);
					at_string = "AT+024=1\r\n";
					mMainActivity.sendComMessage(at_string);
					at_string = "AT+025=1\r\n";
					mMainActivity.sendComMessage(at_string);
					
		        } else {  
		        	mMainActivity.bSwitchButtonFan = false;
		        	String at_string = "AT+020=0\r\n";
		        	mMainActivity.sendComMessage(at_string);
					at_string = "AT+021=0\r\n";
					mMainActivity.sendComMessage(at_string);
					at_string = "AT+022=0\r\n";
					mMainActivity.sendComMessage(at_string);
					at_string = "AT+023=0\r\n";
					mMainActivity.sendComMessage(at_string);
					at_string = "AT+024=0\r\n";
					mMainActivity.sendComMessage(at_string);
					at_string = "AT+025=0\r\n";
					mMainActivity.sendComMessage(at_string);
					
		        }
		        mMainActivity.setSensorDataOfFan(state);
		        mMainActivity.editor.putBoolean("mSwitchButtonFan", state);
				mMainActivity.editor.commit();
		    }  
		});
		
		
		mSwitchButtonO3 = (SwitchButton) controlLayout.findViewById(R.id.o3_onoff_switcher);  
		mSwitchButtonO3.setSwitchState(mMainActivity.sp.getBoolean("mSwitchButtonO3", false));  
		mSwitchButtonO3.setOnSwitchStateChangeListener(new OnSwitchStateChangeListener() {  
		    @Override  
		    public void onSwitchStateChange(boolean state) {  
		        if (state) {  
		        	mMainActivity.bSwitchButtonO3 = true;
		        	String at_string = "AT+040=1\r\n";
		        	mMainActivity.sendComMessage(at_string); 
		        } else {  
		        	mMainActivity.bSwitchButtonO3 = false;
		        	String at_string = "AT+040=0\r\n";
		        	mMainActivity.sendComMessage(at_string); 
		        }
		        mMainActivity.setSensorDataOfO3(state);
		        mMainActivity.editor.putBoolean("mSwitchButtonO3", state);
				mMainActivity.editor.commit();
		    }  
		});
		
		
		mSwitchButtonSwitch1 = (SwitchButton) controlLayout.findViewById(R.id.switch1_onoff_switcher);  
		mSwitchButtonSwitch1.setSwitchState(mMainActivity.sp.getBoolean("mSwitchButtonSwitch1", false));  
		mSwitchButtonSwitch1.setOnSwitchStateChangeListener(new OnSwitchStateChangeListener() {  
		    @Override  
		    public void onSwitchStateChange(boolean state) {  
		        if (state) {  
		        	String at_string = "AT+050=1\r\n";
		        	mMainActivity.sendComMessage(at_string); 
		        } else {  
		        	String at_string = "AT+050=0\r\n";
		        	mMainActivity.sendComMessage(at_string); 
		        }
		        mMainActivity.editor.putBoolean("mSwitchButtonSwitch1", state);
				mMainActivity.editor.commit();
		    }  
		});
		
		
		mSwitchButtonSwitch2 = (SwitchButton) controlLayout.findViewById(R.id.switch2_onoff_switcher);  
		mSwitchButtonSwitch2.setSwitchState(mMainActivity.sp.getBoolean("mSwitchButtonSwitch2", false));  
		mSwitchButtonSwitch2.setOnSwitchStateChangeListener(new OnSwitchStateChangeListener() {  
		    @Override  
		    public void onSwitchStateChange(boolean state) {  
		        if (state) {  
		        	String at_string = "AT+051=1\r\n";
		        	mMainActivity.sendComMessage(at_string); 
		        } else {  
		        	String at_string = "AT+051=0\r\n";
		        	mMainActivity.sendComMessage(at_string); 
		        }
		        mMainActivity.editor.putBoolean("mSwitchButtonSwitch2", state);
				mMainActivity.editor.commit();
		    }  
		});
		
		mSwitchButtonSwitch3 = (SwitchButton) controlLayout.findViewById(R.id.switch3_onoff_switcher);  
		mSwitchButtonSwitch3.setSwitchState(mMainActivity.sp.getBoolean("mSwitchButtonSwitch3", false));  
		mSwitchButtonSwitch3.setOnSwitchStateChangeListener(new OnSwitchStateChangeListener() {  
		    @Override  
		    public void onSwitchStateChange(boolean state) {  
		        if (state) {  
		        	String at_string = "AT+052=1\r\n";
		        	mMainActivity.sendComMessage(at_string); 
		        } else {  
		        	String at_string = "AT+052=0\r\n";
		        	mMainActivity.sendComMessage(at_string); 
		        }
		        mMainActivity.editor.putBoolean("mSwitchButtonSwitch3", state);
				mMainActivity.editor.commit();
		    }  
		});
		
		mSwitchButtonSwitch4 = (SwitchButton) controlLayout.findViewById(R.id.switch4_onoff_switcher);  
		mSwitchButtonSwitch4.setSwitchState(mMainActivity.sp.getBoolean("mSwitchButtonSwitch4", false));  
		mSwitchButtonSwitch4.setOnSwitchStateChangeListener(new OnSwitchStateChangeListener() {  
		    @Override  
		    public void onSwitchStateChange(boolean state) {  
		        if (state) {  
		        	String at_string = "AT+053=1\r\n";
		        	mMainActivity.sendComMessage(at_string); 
		        } else {  
		        	String at_string = "AT+053=0\r\n";
		        	mMainActivity.sendComMessage(at_string); 
		        }
		        mMainActivity.editor.putBoolean("mSwitchButtonSwitch4", state);
				mMainActivity.editor.commit();
		    }  
		});
		
		
		mSwitchButtonSwitch5 = (SwitchButton) controlLayout.findViewById(R.id.switch5_onoff_switcher);  
		mSwitchButtonSwitch5.setSwitchState(mMainActivity.sp.getBoolean("mSwitchButtonSwitch5", false));  
		mSwitchButtonSwitch5.setOnSwitchStateChangeListener(new OnSwitchStateChangeListener() {  
		    @Override  
		    public void onSwitchStateChange(boolean state) {  
		        if (state) {  
		        	String at_string = "AT+054=1\r\n";
		        	mMainActivity.sendComMessage(at_string); 
		        } else {  
		        	String at_string = "AT+054=0\r\n";
		        	mMainActivity.sendComMessage(at_string); 
		        }
		        mMainActivity.editor.putBoolean("mSwitchButtonSwitch5", state);
				mMainActivity.editor.commit();
		    }  
		});
		
		
		mSwitchButtonSwitch6 = (SwitchButton) controlLayout.findViewById(R.id.switch6_onoff_switcher);  
		mSwitchButtonSwitch6.setSwitchState(mMainActivity.sp.getBoolean("mSwitchButtonSwitch6", false));  
		mSwitchButtonSwitch6.setOnSwitchStateChangeListener(new OnSwitchStateChangeListener() {  
		    @Override  
		    public void onSwitchStateChange(boolean state) {  
		        if (state) {  
		        	String at_string = "AT+055=1\r\n";
		        	mMainActivity.sendComMessage(at_string); 
		        } else {  
		        	String at_string = "AT+055=0\r\n";
		        	mMainActivity.sendComMessage(at_string); 
		        }
		        mMainActivity.editor.putBoolean("mSwitchButtonSwitch6", state);
				mMainActivity.editor.commit();
		    }  
		});
		
		mSwitchButtonSwitch7 = (SwitchButton) controlLayout.findViewById(R.id.switch7_onoff_switcher);  
		mSwitchButtonSwitch7.setSwitchState(mMainActivity.sp.getBoolean("mSwitchButtonSwitch7", false));  
		mSwitchButtonSwitch7.setOnSwitchStateChangeListener(new OnSwitchStateChangeListener() {  
		    @Override  
		    public void onSwitchStateChange(boolean state) {  
		        if (state) {  
		        	String at_string = "AT+056=1\r\n";
		        	mMainActivity.sendComMessage(at_string); 
		        } else {  
		        	String at_string = "AT+056=0\r\n";
		        	mMainActivity.sendComMessage(at_string); 
		        }
		        mMainActivity.editor.putBoolean("mSwitchButtonSwitch7", state);
				mMainActivity.editor.commit();
		    }  
		});
		
		
		mSwitchButtonSwitch8 = (SwitchButton) controlLayout.findViewById(R.id.switch8_onoff_switcher);  
		mSwitchButtonSwitch8.setSwitchState(mMainActivity.sp.getBoolean("mSwitchButtonSwitch8", false));  
		mSwitchButtonSwitch8.setOnSwitchStateChangeListener(new OnSwitchStateChangeListener() {  
		    @Override  
		    public void onSwitchStateChange(boolean state) {  
		        if (state) {  
		        	String at_string = "AT+057=1\r\n";
		        	mMainActivity.sendComMessage(at_string); 
		        } else {  
		        	String at_string = "AT+057=0\r\n";
		        	mMainActivity.sendComMessage(at_string); 
		        }
		        mMainActivity.editor.putBoolean("mSwitchButtonSwitch8", state);
				mMainActivity.editor.commit();
		    }  
		});
		
		
		mSwitchButtonSwitch9 = (SwitchButton) controlLayout.findViewById(R.id.switch9_onoff_switcher);  
		mSwitchButtonSwitch9.setSwitchState(mMainActivity.sp.getBoolean("mSwitchButtonSwitch9", false));  
		mSwitchButtonSwitch9.setOnSwitchStateChangeListener(new OnSwitchStateChangeListener() {  
		    @Override  
		    public void onSwitchStateChange(boolean state) {  
		        if (state) {  
		        	String at_string = "AT+058=1\r\n";
		        	mMainActivity.sendComMessage(at_string); 
		        } else {  
		        	String at_string = "AT+058=0\r\n";
		        	mMainActivity.sendComMessage(at_string); 
		        }
		        mMainActivity.editor.putBoolean("mSwitchButtonSwitch9", state);
				mMainActivity.editor.commit();
		    }  
		});
		//给拖动条设置拖动的监听事件
		mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // 拖拽完毕
            	String at_string = null;
            	at_string = String.format("AT+011=%02d\r\n", progress_saved/10+1);
            	mMainActivity.sendComMessage(at_string);
        		mMainActivity.editor.putInt("mSeekBarProgress", progress_saved);
				mMainActivity.editor.commit();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // 开始拖拽时 用户刚好点上去
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser) {
            	progress_saved = progress;
            	

            }
        });
        
        
		mMainActivity = (MainActivity) getActivity();
 /*		
		btn_timerstart = (Button) controlLayout.findViewById(R.id.control_btn_timerstart);
		btn_timerstart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				mMainActivity.setTabSelection(getString(R.string.timerstart_fg));
			}
		});
*/		
		
		return controlLayout;
	}

	
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	
		MainActivity.curFragmentTag = getString(R.string.control_fg);
		mMainActivity.controlFragmentOn = true;
		
		mTimerFlash = new Timer();
		mTimerFlash.schedule(new TimerTask(){				
			public void run()
			{
				mHandlerControl.sendEmptyMessage(CONTROL_FLASH);
			}
		},0,1000);  //延时0ms后执行,1s执行一次
		
		
	}
	
		
		
	@Override
	public void onPause() {
		// TODO Auto-generated method stub

		mMainActivity.controlFragmentOn = false;
		mTimerFlash.cancel();
		super.onPause();
	}
	
}
