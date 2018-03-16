package com.digihuman.atls_mca.ui;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.digihuman.atls_mca.R;
import com.digihuman.atls_mca.utils.FileHelper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android_serialport_api.SerialPort;

/**
 * 项目的主Activity，所有的Fragment都嵌入在这里。
 * 
 * @author 
 */
public class MainActivity extends FragmentActivity implements OnClickListener{

	private static final String TAG = "MainActivity";
	 

	public boolean	statusFragmentOn = false;
	public boolean	controlFragmentOn = false;
	public boolean	lanFragmentOn = false;
	public boolean	userFragmentOn = false;
	public boolean	timestartFragmentOn = false;
	
	
	public  String str_mcu_mac1 = "--------";
	
	public  String str_led_mac1 = "--------";
	
	public  String str_fan_mac1 = "--------";
	public  String str_fan_mac2 = "--------";
	public  String str_fan_mac3 = "--------";
	public  String str_fan_mac4 = "--------";
	public  String str_fan_mac5 = "--------";
	public  String str_fan_mac6 = "--------";
	
	public  String str_monitor_mac1 = "--------";
	public  String str_monitor_mac2 = "--------";
	public  String str_monitor_mac3 = "--------";
	public  String str_monitor_mac4 = "--------";
	public  String str_monitor_mac5 = "--------";
	public  String str_monitor_mac6 = "--------";
	
	
	public  String str_o3_mac1 = "--------";


	public  String str_switch_mac1 = "--------";
	public  String str_switch_mac2 = "--------";
	public  String str_switch_mac3 = "--------";
	public  String str_switch_mac4 = "--------";
	public  String str_switch_mac5 = "--------";
	public  String str_switch_mac6 = "--------";
	public  String str_switch_mac7 = "--------";
	public  String str_switch_mac8 = "--------";
	public  String str_switch_mac9 = "--------";
	
	
	
	
	public String str_hcho1_t = "--";
	public String str_hcho2_t = "--";
	public String str_hcho3_t = "--";
	public String str_hcho4_t = "--";
	public String str_hcho5_t = "--";
	public String str_hcho6_t = "--";
	
	public String str_hcho1_h = "--";
	public String str_hcho2_h = "--";
	public String str_hcho3_h = "--";
	public String str_hcho4_h = "--";
	public String str_hcho5_h = "--";
	public String str_hcho6_h = "--";
	
	public String str_hcho1_c = "--";
	public String str_hcho2_c = "--";
	public String str_hcho3_c = "--";
	public String str_hcho4_c = "--";
	public String str_hcho5_c = "--";
	public String str_hcho6_c = "--";
	
	public String str_hcho1_o = "--";
	public String str_hcho2_o = "--";
	public String str_hcho3_o = "--";
	public String str_hcho4_o = "--";
	public String str_hcho5_o = "--";
	public String str_hcho6_o = "--";
	
	public String str_hcho1_ov = "--";
	public String str_hcho2_ov = "--";
	public String str_hcho3_ov = "--";
	public String str_hcho4_ov = "--";
	public String str_hcho5_ov = "--";
	public String str_hcho6_ov = "--";
	
	private boolean hcho1_log;
	private boolean hcho2_log;
	private boolean hcho3_log;
	private boolean hcho4_log;
	private boolean hcho5_log;
	private boolean hcho6_log;
	
	public boolean	bSwitchButtonLed;
	public boolean	bSwitchButtonFan;
	public boolean	bSwitchButtonO3;
	public boolean	bSwitchButtonSwitch1;
	public boolean	bSwitchButtonSwitch2;
	public boolean	bSwitchButtonSwitch3;
	public boolean	bSwitchButtonSwitch4;
	public boolean	bSwitchButtonSwitch5;
	public boolean	bSwitchButtonSwitch6;
	public boolean	bSwitchButtonSwitch7;
	public boolean	bSwitchButtonSwitch8;
	public boolean	bSwitchButtonSwitch9;
	
	
	
	
//	public String filename = "hcho1_data";
	
	private static final int LAN_RCV_000 = 0x00;
	private static final int STATUS_RCV_031 = 0x01;
	private static final int STATUS_RCV_032 = 0x02;
	private static final int STATUS_RCV_033 = 0x03;
	private static final int STATUS_RCV_034 = 0x04;
	private static final int STATUS_RCV_035 = 0x05;
	private static final int STATUS_RCV_036 = 0x06;
	
	private static final int STATUS_RCV_OTHER = 0x0F;
	
	
	private static final int STATUS_REQ_HCHO = 0x10;
	private static final int STATUS_WRITE_LOG = 0x11;
	
	

	
	public  StatusFragment statusFragment;

	private ControlFragment controlFragment;
	
	private LanFragment lanFragment;
	
	private UserFragment userFragment;
	
	private TimerstartFragment timerstartFragment;
	

	private View statusLayout ,controlLayout, lanLayout ,userLayout;

	private ImageView statusImage,controlImage,lanImage,userImage;
	
	private TextView statusText,controlText,lanText,userText;

	/**
	 * 用于对Fragment进行管理
	 */
	private FragmentManager fragmentManager;
	private FragmentTransaction mFragmentTransaction;
//	private SimpleFragment simpleFragment;

	public static  String curFragmentTag;
	
	
	/**
	 * 串口通讯相关
	 */
//	private CommMain mCommMain;
	private SerialPort mSerialPort = null;
	private InputStream mInputStream;
	private OutputStream mOutputStream;
	private ReadThread mReadThread;
	private static String commData = "";
	
	
	public Calendar cal;
	public String year;
	public String month;
	public String day;
	public String hour;
	public String minute;
	public String second;
	public String week;
	private Timer mTimer1s = new Timer();
	private Timer mTimerWriteLog = new Timer();
	
	
	/**
	 * 暂存的状态或配置
	 */
	SharedPreferences sp;
	SharedPreferences.Editor editor;
	
	
	/**
	 * 联网相关https
	 */
	RequestQueue mQueue;
	
	
	
	
	
	public final Handler mHandlerCom = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			Bundle bundle = msg.getData();
			String strArray[] = null;
			switch (msg.what) {
			
			case LAN_RCV_000:
				String lan000 = bundle.getString("LAN_000");
				strArray = lan000.split(",");
//				Toast.makeText(getApplicationContext(), "AT"+lan000,Toast.LENGTH_SHORT).show();

				str_mcu_mac1 = (strArray[1].length() == 8)?strArray[1]:str_mcu_mac1;
				
				str_led_mac1 = (strArray[2].length() == 8)?strArray[2]:str_led_mac1;
			
				
				str_fan_mac1 = (strArray[3].length() == 8)?strArray[3]:str_fan_mac1;
			
				
				str_fan_mac2 = (strArray[4].length() == 8)?strArray[4]:str_fan_mac2;
			
				
				str_fan_mac3 = (strArray[5].length() == 8)?strArray[5]:str_fan_mac3;
			
				
				str_fan_mac4 = (strArray[6].length() == 8)?strArray[6]:str_fan_mac4;
			
				
				str_fan_mac5 = (strArray[7].length() == 8)?strArray[7]:str_fan_mac5;
			
				
				str_fan_mac6 = (strArray[8].length() == 8)?strArray[8]:str_fan_mac6;
			
				
				str_monitor_mac1 = (strArray[9].length() == 8)?strArray[9]:str_monitor_mac1;
			
				
				str_monitor_mac2 = (strArray[10].length() == 8)?strArray[10]:str_monitor_mac2;
			
				
				str_monitor_mac3 = (strArray[11].length() == 8)?strArray[11]:str_monitor_mac3;
			
				
				str_monitor_mac4 = (strArray[12].length() == 8)?strArray[12]:str_monitor_mac4;
			
				
				str_monitor_mac5 = (strArray[13].length() == 8)?strArray[13]:str_monitor_mac5;
			
				
				str_monitor_mac6 = (strArray[14].length() == 8)?strArray[14]:str_monitor_mac6;
			
				
				str_o3_mac1 = (strArray[15].length() == 8)?strArray[15]:str_o3_mac1;
			
				
				str_switch_mac1 = (strArray[16].length() == 8)?strArray[16]:str_switch_mac1;
			
				
				str_switch_mac2 = (strArray[17].length() == 8)?strArray[17]:str_switch_mac2;
			
				
				str_switch_mac3 = (strArray[18].length() == 8)?strArray[18]:str_switch_mac3;
			
				
				str_switch_mac4 = (strArray[19].length() == 8)?strArray[19]:str_switch_mac4;
			
				
				str_switch_mac5 = (strArray[20].length() == 8)?strArray[20]:str_switch_mac5;
			
				
				str_switch_mac6 = (strArray[21].length() == 8)?strArray[21]:str_switch_mac6;
			
				
				str_switch_mac7 = (strArray[22].length() == 8)?strArray[22]:str_switch_mac7;
			
				
				str_switch_mac8 = (strArray[23].length() == 8)?strArray[23]:str_switch_mac8;
			
				
				str_switch_mac9 = (strArray[24].length() == 8)?strArray[24]:str_switch_mac9;		
				break;
				
			case STATUS_RCV_031:
				String status031 = bundle.getString("STATUS_031");
				
				
				strArray = status031.split(",");
				
				str_hcho1_t = strArray[1];
				str_hcho1_h = strArray[2];
				str_hcho1_c = strArray[3];
				str_hcho1_o = strArray[4];
				str_hcho1_ov = strArray[5];
				
				if(hcho1_log){
					hcho1_log = false;
					
					/*						
					FileHelper fHelper = new FileHelper(getApplicationContext());
		            String filedetail = hour+":"+minute+"    "+status031;
		            
	            
		            try {
		                fHelper.saveAdd(filename, filedetail);
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
					 */
				}
				
                
				break;
				
			case STATUS_RCV_032:
				String status032 = bundle.getString("STATUS_032");
				strArray = status032.split(",");
				
				str_hcho2_t = strArray[1];
				str_hcho2_h = strArray[2];
				str_hcho2_c = strArray[3];
				str_hcho2_o = strArray[4];
				str_hcho2_ov = strArray[5];
				
				if(hcho2_log){
					hcho2_log = false;
					
					/*					
					FileHelper fHelper = new FileHelper(getApplicationContext());
		            String filedetail = hour+":"+minute+"    "+status032;
		            

		            try {
		                fHelper.saveAdd(filename, filedetail);
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
					 */
				}
				
				break;
				
			case STATUS_RCV_033:
				String status033 = bundle.getString("STATUS_033");
				strArray = status033.split(",");
				
				str_hcho3_t = strArray[1];
				str_hcho3_h = strArray[2];
				str_hcho3_c = strArray[3];
				str_hcho3_o = strArray[4];
				str_hcho3_ov = strArray[5];
				
				
				break;
			
			case STATUS_RCV_034:
				String status034 = bundle.getString("STATUS_034");
				strArray = status034.split(",");
				
				str_hcho4_t = strArray[1];
				str_hcho4_h = strArray[2];
				str_hcho4_c = strArray[3];
				str_hcho4_o = strArray[4];
				str_hcho4_ov = strArray[5];
				
				
				break;
				
			case STATUS_RCV_035:
				String status035 = bundle.getString("STATUS_035");
				strArray = status035.split(",");
				
				str_hcho5_t = strArray[1];
				str_hcho5_h = strArray[2];
				str_hcho5_c = strArray[3];
				str_hcho5_o = strArray[4];
				str_hcho5_ov = strArray[5];
				
				
				break;
			
			case STATUS_RCV_036:
				String status036 = bundle.getString("STATUS_036");
				strArray = status036.split(",");
				
				str_hcho6_t = strArray[1];
				str_hcho6_h = strArray[2];
				str_hcho6_c = strArray[3];
				str_hcho6_o = strArray[4];
				str_hcho6_ov = strArray[5];
				
				
				
				break;
				
				
			case STATUS_RCV_OTHER:
				String statusother = bundle.getString("STATUS_OTHER");
				break;
				
			case STATUS_REQ_HCHO:
				String at_string = "AT+030?\r\n";
				sendComMessage(at_string);
				
				getSensorDataOfO3();
				getSensorDataOfFan();
				postSensorData();
				break;
				
			case STATUS_WRITE_LOG:
				
				hcho1_log = true;
				hcho2_log = true;
				hcho3_log = true;
				hcho4_log = true;
				hcho5_log = true;
				hcho6_log = true;
				
				
				
				break;
			default:
				break;
			}
		}		
	};
	
	
	
	
	public void postSensorData() {  
		
		
		
		String url = "http://api.tlink.io/tlink_interface/api/device/createDataPonit.htm";
		
		
		
		
		JSONArray data = new JSONArray();
		
		//甲醛1
		JSONObject jsonObject1 = new JSONObject();
        try {
			jsonObject1.put("sensorsId","200128988");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			jsonObject1.put("value",str_hcho1_c);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

      //臭氧1
        JSONObject jsonObject2 = new JSONObject();
        try {
			jsonObject2.put("sensorsId","200128989");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			jsonObject2.put("value",str_hcho1_o);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
      //温度1
        JSONObject jsonObject3 = new JSONObject();
        try {
			jsonObject3.put("sensorsId","200128990");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			jsonObject3.put("value",str_hcho1_t);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
      //湿度1
        JSONObject jsonObject4 = new JSONObject();
        try {
			jsonObject4.put("sensorsId","200128991");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			jsonObject4.put("value",str_hcho1_h);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        
        
        
        
        
        
      //甲醛2
		JSONObject jsonObject5 = new JSONObject();
        try {
			jsonObject5.put("sensorsId","200129432");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			jsonObject5.put("value",str_hcho2_c);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

      //臭氧2
        JSONObject jsonObject6 = new JSONObject();
        try {
			jsonObject6.put("sensorsId","200129433");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			jsonObject6.put("value",str_hcho2_o);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
      //温度2
        JSONObject jsonObject7 = new JSONObject();
        try {
			jsonObject7.put("sensorsId","200129434");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			jsonObject7.put("value",str_hcho2_t);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
      //湿度2
        JSONObject jsonObject8 = new JSONObject();
        try {
			jsonObject8.put("sensorsId","200129435");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			jsonObject8.put("value",str_hcho2_h);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
      //臭氧1电压值
        JSONObject jsonObject9 = new JSONObject();
        try {
			jsonObject9.put("sensorsId","200130059");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			jsonObject9.put("value",str_hcho1_ov);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
      //臭氧2电压值
        JSONObject jsonObject10 = new JSONObject();
        try {
			jsonObject10.put("sensorsId","200130060");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			jsonObject10.put("value",str_hcho2_ov);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
		data.put(jsonObject1);
		data.put(jsonObject2);
		data.put(jsonObject3);
		data.put(jsonObject4);
		data.put(jsonObject5);
		data.put(jsonObject6);
		data.put(jsonObject7);
		data.put(jsonObject8);
		data.put(jsonObject9);
		data.put(jsonObject10);
		
		
		
		
		
		
		 JSONObject params = new JSONObject();
		 try {
			params.put("deviceNo","EL0X8XLZI62IUX88");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			params.put("sensorDatas",data);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url,params, new Response.Listener<JSONObject>() {  
	        @Override  
	        public void onResponse(JSONObject response) {  
	            //responseText.setText(response.toString());
	       /* 	
	        	try {
					Toast.makeText(getApplicationContext(), ("flag:"+response.get("flag")+"  msg:"+response.get("msg")), Toast.LENGTH_SHORT).show();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			*/	
	        }  
	    }, new Response.ErrorListener() {  
	        @Override  
	        public void onErrorResponse(VolleyError error) {  
	            //responseText.setText(error.getMessage());  
	        	//Toast.makeText(getApplicationContext(), "非正常接收", Toast.LENGTH_SHORT).show();
	        }  
	    });
	    
//		RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
//	    Toast.makeText(getApplicationContext(), params.toString(), Toast.LENGTH_SHORT).show();
	    
		mQueue.add(jsonObjectRequest);
	}
	
	
	
	
	public void postSensorconfig() {  
		
		
		
		String url = "http://api.tlink.io/tlink_interface/api/device/getDeviceByNo";
		
		
		 JSONObject params = new JSONObject();
		 try {
			params.put("deviceNo","EL0X8XLZI62IUX88");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url,params, new Response.Listener<JSONObject>() {  
	        @Override  
	        public void onResponse(JSONObject response) {  
	            //responseText.setText(response.toString());
	        	
	        	try {
	        		//String msg = "flag:"+response.get("flag")+"  msg:"+response.get("msg");
	        		String msg = ""+response.get("flag");
	        		
	        		if(msg.equals("00")){
	        			msg = "flag:"+msg+"  msg:"+response.get("msg");
	        			
	        			msg+=response.getJSONArray("deviceItems").get(0).toString();
	        			//msg+=response.getJSONArray("deviceItems").get(1).toString();
	        			//msg+=response.getJSONArray("deviceItems").get(2).toString();
	        			//msg+=response.getJSONArray("deviceItems").get(3).toString();
	        			//msg+=response.getJSONArray("deviceItems").get(4).toString();
	        			//msg+=response.getJSONArray("deviceItems").get(5).toString();
	        			//msg+=response.getJSONArray("deviceItems").get(6).toString();
	        			//msg+=response.getJSONArray("deviceItems").get(7).toString();
	        			
	        		}else{
	        			msg = "flag:"+msg+"  msg:"+response.get("msg");
	        		}
	        		
	        		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
	        }  
	    }, new Response.ErrorListener() {  
	        @Override  
	        public void onErrorResponse(VolleyError error) {  
	            //responseText.setText(error.getMessage());  
	        	//Toast.makeText(getApplicationContext(), "非正常接收",Toast.LENGTH_SHORT).show();
	        }  
	    });
	    
//		RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
		mQueue.add(jsonObjectRequest);
	}


	public void getSensorDataOfO3() {  
		
		
		
		String url = "http://api.tlink.io/tlink_interface/api/device/datapoint.htm";
		
		
		
		
		
		
		 JSONObject params = new JSONObject();
		 try {
			params.put("deviceId","200020210");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			params.put("sensorId","200134499");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url,params, new Response.Listener<JSONObject>() {  
	        @Override  
	        public void onResponse(JSONObject response) {  
	            //responseText.setText(response.toString());
	        	//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
	        	try {
	        		String msg = ""+response.get("flag");
	        		
	        		if(msg.equals("00")){
	        			
	        			
	        			msg = ""+response.get("switcher");
	        			if(msg.equals("1")){
	        				if(bSwitchButtonO3 == false){
		        				String at_string = "AT+040=1\r\n";
		    		        	sendComMessage(at_string); 
		    		        	editor.putBoolean("mSwitchButtonO3", true);
		    		        	
		    		        	bSwitchButtonO3 = true;
	    		        	}
	        			}else{
	        				
	        				if(bSwitchButtonO3 == true){
		        				String at_string = "AT+040=0\r\n";
		    		        	sendComMessage(at_string);
		    		        	editor.putBoolean("mSwitchButtonO3", false);
		    		        	
		    		        	bSwitchButtonO3 = false;
	    		        	}
	        			}
	        			editor.commit();
	        			
	        		}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	        }  
	    }, new Response.ErrorListener() {  
	        @Override  
	        public void onErrorResponse(VolleyError error) {  
	            //responseText.setText(error.getMessage());  
	        	//Toast.makeText(getApplicationContext(), "非正常接收", Toast.LENGTH_SHORT).show();
	        }  
	    });
	    
	//	RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
	//    Toast.makeText(getApplicationContext(), params.toString(), Toast.LENGTH_SHORT).show();
	    
		mQueue.add(jsonObjectRequest);
	}
	
	public void setSensorDataOfO3(boolean sw) {
		
		String url = "http://api.tlink.io/tlink_interface/api/device/createDataPonit.htm";
		
		
		
		
		JSONArray data = new JSONArray();
		
		//臭氧发生器开关
		JSONObject jsonObject1 = new JSONObject();
	    try {
			jsonObject1.put("sensorsId","200134499");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
	    	if(sw){
	    		jsonObject1.put("switcher","1");
			}else{
				jsonObject1.put("switcher","0");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
		data.put(jsonObject1);
		
		
		
		
		
		
		 JSONObject params = new JSONObject();
		 try {
			params.put("deviceNo","EL0X8XLZI62IUX88");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			params.put("sensorDatas",data);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url,params, new Response.Listener<JSONObject>() {  
	        @Override  
	        public void onResponse(JSONObject response) {  
	            //responseText.setText(response.toString());
	       /* 	
	        	try {
					Toast.makeText(getApplicationContext(), ("flag:"+response.get("flag")+"  msg:"+response.get("msg")), Toast.LENGTH_SHORT).show();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			*/	
	        }  
	    }, new Response.ErrorListener() {  
	        @Override  
	        public void onErrorResponse(VolleyError error) {  
	            //responseText.setText(error.getMessage());  
	        	//Toast.makeText(getApplicationContext(), "非正常接收", Toast.LENGTH_SHORT).show();
	        }  
	    });
	    
	//	RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
	//    Toast.makeText(getApplicationContext(), params.toString(), Toast.LENGTH_SHORT).show();
	    
		mQueue.add(jsonObjectRequest);
		
	}
	
	
	public void getSensorDataOfFan() {  
		
		
		
		String url = "http://api.tlink.io/tlink_interface/api/device/datapoint.htm";
		
		
		
		
		
		
		 JSONObject params = new JSONObject();
		 try {
			params.put("deviceId","200020210");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			params.put("sensorId","200134500");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url,params, new Response.Listener<JSONObject>() {  
	        @Override  
	        public void onResponse(JSONObject response) {  
	            //responseText.setText(response.toString());
	        	
	        	try {
	        		String msg = ""+response.get("flag");
	        		
	        		if(msg.equals("00")){
	        			
	        			
	        			msg = ""+response.get("switcher");
	        			if(msg.equals("1")){
	        				if(bSwitchButtonFan == false){
		        				String at_string = "AT+020=1\r\n";
		    		        	sendComMessage(at_string);
		    		        	at_string = "AT+021=1\r\n";
		    		        	sendComMessage(at_string);
		    		        	at_string = "AT+022=1\r\n";
		    		        	sendComMessage(at_string);
		    		        	at_string = "AT+023=1\r\n";
		    		        	sendComMessage(at_string);
		    		        	at_string = "AT+024=1\r\n";
		    		        	sendComMessage(at_string);
		    		        	at_string = "AT+025=1\r\n";
		    		        	sendComMessage(at_string);
		    		        	editor.putBoolean("mSwitchButtonFan", true);
		    		        	
		    		        	bSwitchButtonFan = true;
	    		        	}
	        			}else{
	        				if(bSwitchButtonFan == true){
		        				String at_string = "AT+020=0\r\n";
		    		        	sendComMessage(at_string);
		    		        	at_string = "AT+021=0\r\n";
		    		        	sendComMessage(at_string);
		    		        	at_string = "AT+022=0\r\n";
		    		        	sendComMessage(at_string);
		    		        	at_string = "AT+023=0\r\n";
		    		        	sendComMessage(at_string);
		    		        	at_string = "AT+024=0\r\n";
		    		        	sendComMessage(at_string);
		    		        	at_string = "AT+025=0\r\n";
		    		        	sendComMessage(at_string);
		    		        	editor.putBoolean("mSwitchButtonFan", false);
		    		        	
		    		        	bSwitchButtonFan = false;
	    		        	}
	        			}
	        			editor.commit();
	        			
	        		}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	        }  
	    }, new Response.ErrorListener() {  
	        @Override  
	        public void onErrorResponse(VolleyError error) {  
	            //responseText.setText(error.getMessage());  
	        	//Toast.makeText(getApplicationContext(), "非正常接收", Toast.LENGTH_SHORT).show();
	        }  
	    });
	    
	//	RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
	//    Toast.makeText(getApplicationContext(), params.toString(), Toast.LENGTH_SHORT).show();
	    
		mQueue.add(jsonObjectRequest);
	}
	
	
	
	public void setSensorDataOfFan(boolean sw) {
		
		String url = "http://api.tlink.io/tlink_interface/api/device/createDataPonit.htm";
		
		
		
		
		JSONArray data = new JSONArray();
		
		//臭氧发生器开关
		JSONObject jsonObject1 = new JSONObject();
	    try {
			jsonObject1.put("sensorsId","200134500");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
	    	if(sw){
	    		jsonObject1.put("switcher","1");
			}else{
				jsonObject1.put("switcher","0");
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
		data.put(jsonObject1);
		
		
		
		
		
		
		 JSONObject params = new JSONObject();
		 try {
			params.put("deviceNo","EL0X8XLZI62IUX88");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			params.put("sensorDatas",data);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url,params, new Response.Listener<JSONObject>() {  
	        @Override  
	        public void onResponse(JSONObject response) {  
	            //responseText.setText(response.toString());
	       /* 	
	        	try {
					Toast.makeText(getApplicationContext(), ("flag:"+response.get("flag")+"  msg:"+response.get("msg")), Toast.LENGTH_SHORT).show();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			*/	
	        }  
	    }, new Response.ErrorListener() {  
	        @Override  
	        public void onErrorResponse(VolleyError error) {  
	            //responseText.setText(error.getMessage());  
	        	//Toast.makeText(getApplicationContext(), "非正常接收", Toast.LENGTH_SHORT).show();
	        }  
	    });
	    
	//	RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());
	//    Toast.makeText(getApplicationContext(), params.toString(), Toast.LENGTH_SHORT).show();
	    
		mQueue.add(jsonObjectRequest);
		
	}


	
	
	
	
	
	
	
	
	
	
	private class ReadThread extends Thread {
	
		@Override
		public void run() {
			int size = 0;
			byte[] buffer = new byte[1000];
			
			super.run();
			
			while(!isInterrupted()) {
///*				
				try {
					
					if (mInputStream == null) return;
					size = mInputStream.read(buffer);
					if (size > 0) {
						onDataReceived(buffer, size);
					}
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
//*/				
				try {
					Thread.sleep(50);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			
		}
	}

	private SerialPort getSerialPort() throws SecurityException, IOException,
		InvalidParameterException {
///*
	if (mSerialPort == null) {
		
			mSerialPort = new SerialPort("/dev/ttyS1", 9600, 0);
		
	}
//*/
	return mSerialPort;
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
	    
		sp = this.getSharedPreferences("share_fortimezone", 0);
		editor = sp.edit();
		
		mQueue = Volley.newRequestQueue(getApplicationContext());
		
		// 初始化布局元素
		initViews();
		fragmentManager = getSupportFragmentManager();
		// 第一次启动时默认选中第一个tab
		setCurrentFragment();
		
		
		
		
		
///*		
		try {
			
			
			mSerialPort = getSerialPort();
			mInputStream = mSerialPort.getInputStream();
			mOutputStream = mSerialPort.getOutputStream();
			
			mReadThread = new ReadThread();//接受数据的线程
			mReadThread.start();

		} catch (InvalidParameterException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, "打开串口失败，无效参数",Toast.LENGTH_SHORT).show();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, "打开串口失败，权限问题",Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, "打开串口失败，IO异常",Toast.LENGTH_SHORT).show();
		}
		
//*/		
		hcho1_log = false;
		hcho2_log = false;
		hcho3_log = false;
		hcho4_log = false;
		hcho5_log = false;
		hcho6_log = false;
		
		
		
		mTimer1s = new Timer();
		mTimer1s.schedule(new TimerTask(){				
			public void run()
			{
				
				
	/*			
				if(TextUtils.equals(getString(R.string.status_fg), curFragmentTag)){
					
					statusFragment.flash_data();
					
				}else if(TextUtils.equals(getString(R.string.lan_fg), curFragmentTag)){
					
					lanFragment.flash_data();
				}
	*/

	
				mHandlerCom.sendEmptyMessage(STATUS_REQ_HCHO);
				
				
				
		        
			}
		},0,1000);  //延时0ms后执行,1s执行一次
		
		
		mTimerWriteLog = new Timer();
		mTimerWriteLog.schedule(new TimerTask(){				
			public void run()
			{
				mHandlerCom.sendEmptyMessage(STATUS_WRITE_LOG);
				
				
				cal = Calendar.getInstance();
		        cal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		        
		        
		        year = String.format("%04d",cal.get(Calendar.YEAR));
		        month = String.format("%02d",cal.get(Calendar.MONTH)+1);
		        day = String.format("%02d",cal.get(Calendar.DATE));
		        if (cal.get(Calendar.AM_PM) == 0)
		            hour = String.format("%02d",cal.get(Calendar.HOUR));
		        else
		            hour = String.format("%02d",cal.get(Calendar.HOUR)+12);
		        minute = String.format("%02d",cal.get(Calendar.MINUTE));
		        second = String.format("%02d",cal.get(Calendar.SECOND));
		        week = String.format("%d",cal.get(Calendar.DAY_OF_WEEK));
		        
		        
		        if(cal.get(Calendar.SECOND) == 0){
			        
			        String at_string = "AT+060="+year+month+day+hour+minute+week+"\r\n";
			        
			        sendComMessage(at_string);
		        }
			}
		},0,60000);  //延时0ms后执行,60s执行一次
		
		
		bSwitchButtonLed = sp.getBoolean("mSwitchButtonLed", false);
		bSwitchButtonFan = sp.getBoolean("mSwitchButtonFan", false);
		bSwitchButtonO3 = sp.getBoolean("mSwitchButtonO3", false);
		bSwitchButtonSwitch1 = sp.getBoolean("mSwitchButtonSwitch1", false);
		bSwitchButtonSwitch2 = sp.getBoolean("mSwitchButtonSwitch2", false);
		bSwitchButtonSwitch3 = sp.getBoolean("mSwitchButtonSwitch3", false);
		bSwitchButtonSwitch4 = sp.getBoolean("mSwitchButtonSwitch4", false);
		bSwitchButtonSwitch5 = sp.getBoolean("mSwitchButtonSwitch5", false);
		bSwitchButtonSwitch6 = sp.getBoolean("mSwitchButtonSwitch6", false);
		bSwitchButtonSwitch7 = sp.getBoolean("mSwitchButtonSwitch7", false);
		bSwitchButtonSwitch8 = sp.getBoolean("mSwitchButtonSwitch8", false);
		bSwitchButtonSwitch9 = sp.getBoolean("mSwitchButtonSwitch9", false);
		
	}

	/**
	 * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
	 */
	private void initViews() {
		statusLayout = findViewById(R.id.status_layout);
		controlLayout = findViewById(R.id.control_layout);
		lanLayout = findViewById(R.id.lan_layout);
		userLayout = findViewById(R.id.user_layout);
		statusImage = (ImageView) findViewById(R.id.status_image);
		controlImage = (ImageView) findViewById(R.id.control_image);
		lanImage = (ImageView) findViewById(R.id.lan_image);
		userImage = (ImageView) findViewById(R.id.user_image);
		statusText = (TextView) findViewById(R.id.status_text);
		controlText = (TextView) findViewById(R.id.control_text);
		lanText = (TextView) findViewById(R.id.lan_text);
		userText = (TextView) findViewById(R.id.user_text);
		statusLayout.setOnClickListener(this);
		controlLayout.setOnClickListener(this);
		lanLayout.setOnClickListener(this);
		userLayout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.status_layout:
			// 当点击了消息tab时，选中第1个tab
			setTabSelection(getString(R.string.status_fg));
			break;
		case R.id.control_layout:
			// 当点击了联系人tab时，选中第2个tab
			setTabSelection(getString(R.string.control_fg));
			break;
		case R.id.lan_layout:
			// 当点击了动态tab时，选中第3个tab
			setTabSelection(getString(R.string.lan_fg));
			break;
		case R.id.user_layout:
			// 当点击了设置tab时，选中第4个tab
			setTabSelection(getString(R.string.timerstart_fg));
			break;
		default:
			break;
		}
	}

	private void setCurrentFragment(){
		
		Resources resource = (Resources) getBaseContext().getResources();  
		ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.text_seclected);
		
		
		clearSelection();
		mFragmentTransaction = fragmentManager.beginTransaction();
		
		lanImage.setImageResource(R.drawable.lan_selected);
		lanText.setTextColor(csl);
		
		if (lanFragment == null) {
			// 如果MessageFragment为空，则创建一个并添加到界面上
			lanFragment = new LanFragment();
			//transaction.add(0, messageFragment);
			mFragmentTransaction.add(R.id.content, lanFragment,getString(R.string.lan_fg));
			
			commitTransactions();
		}
		curFragmentTag = getString(R.string.lan_fg);
	}
	
	
	/**
	 * 根据传入的tag参数来设置选中的tab页。
	 * 
	 * @param tag
	 *          
	 */
	public  void setTabSelection(String tag) {
		
		
		Resources resource = (Resources) getBaseContext().getResources();  
		ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.text_seclected);
		
		// 每次选中之前先清楚掉上次的选中状态
		clearSelection();
		// 开启一个Fragment事务
		mFragmentTransaction = fragmentManager.beginTransaction();
		Log.e("setTagSele", "currentTag"+curFragmentTag+"-----tag----"+tag);
		 if(TextUtils.equals(tag, getString(R.string.status_fg))){
			// 当点击了消息tab时，改变控件的图片和文字颜色
			 statusImage.setImageResource(R.drawable.status_selected);
			 statusText.setTextColor(csl);
			
			if (statusFragment == null) {
				statusFragment = new StatusFragment();
			} 

			
		}else if(TextUtils.equals(tag, getString(R.string.control_fg))){
			controlImage.setImageResource(R.drawable.control_selected);
			controlText.setTextColor(csl);
			Log.e(TAG, "contact");
			if (controlFragment == null) {
				controlFragment = new ControlFragment();
			} 

			
		}else if(TextUtils.equals(tag, getString(R.string.lan_fg))){
			lanImage.setImageResource(R.drawable.lan_selected);
			lanText.setTextColor(csl);
			if (lanFragment == null) {
				lanFragment = new LanFragment();
			}
			
		}else if(TextUtils.equals(tag, getString(R.string.user_fg))){
			userImage.setImageResource(R.drawable.user_selected);
			userText.setTextColor(csl);
			if (userFragment == null) {
				userFragment = new UserFragment();
			}
		}else if(TextUtils.equals(tag, getString(R.string.timerstart_fg))){
			userImage.setImageResource(R.drawable.user_selected);
			userText.setTextColor(csl);
			
			if (timerstartFragment == null) {
				timerstartFragment = new TimerstartFragment();
			} 
			
		}
		 switchFragment(tag);
		 
	}

	public  void switchFragment(String tag){
		if(TextUtils.equals(tag, curFragmentTag)){
			Log.e("switchFragment", "curTag == tag");
			return;
		}
		
		if(curFragmentTag != null){
			detachFragment(getFragment(curFragmentTag));
			
		}
		attachFragment(R.id.content,getFragment(tag),tag);
		curFragmentTag = tag;
		Log.e(" after switchFrag", "currenttag--->"+curFragmentTag);
		commitTransactions();
	} 
	
	private void detachFragment(Fragment f){
		
		if(f != null && !f.isDetached()){
			Log.d("detachFragment-->", f.getTag());
			ensureTransaction();
			mFragmentTransaction.detach(f);
		}
	}
	
	private FragmentTransaction ensureTransaction( ){
		if(mFragmentTransaction == null){
			mFragmentTransaction = fragmentManager.beginTransaction();
			mFragmentTransaction
			.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			
		}
		return mFragmentTransaction;
		
	}
	
	private void attachFragment(int layout, Fragment f,String tag){
		if(f != null){
			if(f.isDetached()){
				ensureTransaction();
				mFragmentTransaction.attach(f);
				
			}else if(!f.isAdded()){
				ensureTransaction();
				mFragmentTransaction.add(layout,f, tag);
			}
		}
	}
	
	private void commitTransactions(){
		if (mFragmentTransaction != null && !mFragmentTransaction.isEmpty()) {
			mFragmentTransaction.commit();
			mFragmentTransaction = null;
		}
	}
	private Fragment getFragment(String tag){
		
		Fragment f = fragmentManager.findFragmentByTag(tag);
		
		if(f == null){
//			Toast.makeText(getApplicationContext(), "fragment == null", Toast.LENGTH_SHORT).show();
			
			f = BaseFragment.newInstance(getApplicationContext(), tag);
		}
		return f;
		
	}
	
	
	/**
	 * 清除掉所有的选中状态。
	 */
	private void clearSelection() {
		statusImage.setImageResource(R.drawable.status_unselected);
		statusText.setTextColor(Color.parseColor("#82858b"));
		controlImage.setImageResource(R.drawable.control_unselected);
		controlText.setTextColor(Color.parseColor("#82858b"));
		lanImage.setImageResource(R.drawable.lan_unselected);
		lanText.setTextColor(Color.parseColor("#82858b"));
		userImage.setImageResource(R.drawable.user_unselected);
		userText.setTextColor(Color.parseColor("#82858b"));
	}
	
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		mTimer1s.cancel();
		
		mTimerWriteLog.cancel();
		
		try {
			if (mReadThread!=null) {
				mReadThread.interrupt();
				mReadThread=null;
			}
			if (mInputStream!=null) {
				mInputStream.close();
				mInputStream=null;
			}
			if (mOutputStream!=null) {
				mOutputStream.flush();
				mOutputStream.close();
				mOutputStream=null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (mSerialPort != null) {
			mSerialPort.close();
			mSerialPort = null;
		}
		
	}
	
	
	
	public void SendComCmd(byte[] _cmd) {
///*
		try {
			mOutputStream.write(_cmd);//发送数据
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//*/
	}
    
    
    /**
	 * 向串口发送数据
	 * @param message:数据
	 */
	public int sendComMessage(String message){
		// Check that we're actually connected before trying anything
//        if (mBtService.getState() != BluetoothService.STATE_CONNECTED) {
//            Toast.makeText(mContext, R.string.not_connected, Toast.LENGTH_SHORT).show();
//            return 0;
//        }

        // Check that there's actually something to send
        if (message.length() > 0) {
            // Get the message bytes and tell the BluetoothChatService to write
        	//String outString = ShareFunctional.bytesToHexString(message.getBytes());
        	//wait for receive data over.
        	
        	byte[] send = message.getBytes();
///*        	
        	try {
    			mOutputStream.write(send);//发送数据
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
//*/          
            
            //开启等待
 //       	TimeoutThread mTimeoutThread = new TimeoutThread();
 //      	mTimeoutThread.start();    	         
             
            return 1;
        }else{
        	return 0;
        }
	}
	
	protected  void onDataReceived(byte[] buffer, int size) {
		
	
		int index;
		String temp_string;
		String rcv_string = new String(buffer,0,size);
		commData += rcv_string;

		while(commData.indexOf("\r\n") != -1){

			index = commData.indexOf("\r\n");
			temp_string = commData.substring(0,index+2);
			commData = commData.substring(index+2);
			
			String strArray[] = temp_string.split(",");
			Message msg = null;
			String sResponseType;
			
			//模块实时状态信息
			if ((strArray[0].equals("AT+000")) && (strArray.length >= 25))
			{
				
				String ReceiveBuffer = temp_string;
				msg = mHandlerCom.obtainMessage(LAN_RCV_000);
				sResponseType = "LAN_000";
				
				//发送消息，更新UI
				Bundle bundle = new Bundle();
				bundle.putString(sResponseType, ReceiveBuffer);
				msg.setData(bundle);
				mHandlerCom.sendMessage(msg);
			}
			
			
			//模块实时状态信息
			if ((strArray[0].equals("AT+031")) && (strArray.length >= 5))
			{
				
				String ReceiveBuffer = temp_string;
				msg = mHandlerCom.obtainMessage(STATUS_RCV_031);
				sResponseType = "STATUS_031";
				
				//发送消息，更新UI
				Bundle bundle = new Bundle();
				bundle.putString(sResponseType, ReceiveBuffer);
				msg.setData(bundle);
				mHandlerCom.sendMessage(msg);
			}else if ((strArray[0].equals("AT+032")) && (strArray.length >= 5))
			{
				
				String ReceiveBuffer = temp_string;
				msg = mHandlerCom.obtainMessage(STATUS_RCV_032);
				sResponseType = "STATUS_032";
				
				//发送消息，更新UI
				Bundle bundle = new Bundle();
				bundle.putString(sResponseType, ReceiveBuffer);
				msg.setData(bundle);
				mHandlerCom.sendMessage(msg);
			}else if ((strArray[0].equals("AT+033")) && (strArray.length >= 5))
			{
				
				String ReceiveBuffer = temp_string;
				msg = mHandlerCom.obtainMessage(STATUS_RCV_033);
				sResponseType = "STATUS_033";
				
				//发送消息，更新UI
				Bundle bundle = new Bundle();
				bundle.putString(sResponseType, ReceiveBuffer);
				msg.setData(bundle);
				mHandlerCom.sendMessage(msg);
			}else if ((strArray[0].equals("AT+034")) && (strArray.length >= 5))
			{
				
				String ReceiveBuffer = temp_string;
				msg = mHandlerCom.obtainMessage(STATUS_RCV_034);
				sResponseType = "STATUS_034";
				
				//发送消息，更新UI
				Bundle bundle = new Bundle();
				bundle.putString(sResponseType, ReceiveBuffer);
				msg.setData(bundle);
				mHandlerCom.sendMessage(msg);
			}else if ((strArray[0].equals("AT+035")) && (strArray.length >= 5))
			{
				
				String ReceiveBuffer = temp_string;
				msg = mHandlerCom.obtainMessage(STATUS_RCV_035);
				sResponseType = "STATUS_035";
				
				//发送消息，更新UI
				Bundle bundle = new Bundle();
				bundle.putString(sResponseType, ReceiveBuffer);
				msg.setData(bundle);
				mHandlerCom.sendMessage(msg);
			}else if ((strArray[0].equals("AT+036")) && (strArray.length >= 5))
			{
				
				String ReceiveBuffer = temp_string;
				msg = mHandlerCom.obtainMessage(STATUS_RCV_036);
				sResponseType = "STATUS_036";
				
				//发送消息，更新UI
				Bundle bundle = new Bundle();
				bundle.putString(sResponseType, ReceiveBuffer);
				msg.setData(bundle);
				mHandlerCom.sendMessage(msg);
			}else
			{
				
				String ReceiveBuffer = temp_string;
				msg = mHandlerCom.obtainMessage(STATUS_RCV_OTHER);
				sResponseType = "STATUS_OTHER";
				
				//发送消息，更新UI
				Bundle bundle = new Bundle();
				bundle.putString(sResponseType, ReceiveBuffer);
				msg.setData(bundle);
				mHandlerCom.sendMessage(msg);
			}
		}
		

	}
	
	
	/**
	 * 获取串口数据
	 */
	public synchronized static String getCommData(){
		return commData;
	}
	
	/**
	 * 设置接收串口数据
	 * @param value
	 */
	public synchronized static void setCommData(String value){
		commData = value;
	}
	
	public void clearDataReceived(){
		
		commData = "";
		
	}

}
