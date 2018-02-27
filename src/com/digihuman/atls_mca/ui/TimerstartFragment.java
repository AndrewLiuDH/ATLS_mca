package com.digihuman.atls_mca.ui;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


import com.digihuman.atls_mca.R;
import com.digihuman.atls_mca.utils.MyAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager.OnBackStackChangedListener;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class TimerstartFragment extends BaseFragment {

	private static final String TAG = "TimerstartFragment";
	
	private MainActivity mMainActivity ;
	
	
	private TextView now_date;
	private Button OK, openData, openTime,reset;
	private LinearLayout mWeek, odata;
	public static Handler mHandler;
	private RadioGroup mode;
	private RadioButton data, week;
	private CheckBox sun, mon, tue, wed, thu, fri, sat;
	
	private DateFormat fmtDateAndTime = DateFormat.getDateTimeInstance();
	private Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);
	private DatePickerDialog.OnDateSetListener od;

	private TimePickerDialog.OnTimeSetListener ot;

	private DatePickerDialog.OnDateSetListener vvd;

	private TimePickerDialog.OnTimeSetListener vvt;

	private TextView odate, otime;
	
	private ListView listview;
	private MyAdapter adapter;
	
	
	
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View timerstartLayout = inflater.inflate(R.layout.timerstart_layout,
				container, false);
		
		
		
		
		
		
		listview = (ListView) timerstartLayout.findViewById(R.id.listview);
		
		now_date = (TextView) timerstartLayout.findViewById(R.id.now_date);
		int monaa = Calendar.getInstance().get(Calendar.MONTH)+1;
		now_date.setText(Calendar.getInstance().get(Calendar.YEAR)+ " " +getString(R.string.time_year)+ " " +monaa+ " " +getString(R.string.time_month)+ " " +Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+ " " +getString(R.string.time_day) + " ");
		
		OK = (Button) timerstartLayout.findViewById(R.id.ok);
		reset = (Button) timerstartLayout.findViewById(R.id.reset);
		
		mWeek = (LinearLayout) timerstartLayout.findViewById(R.id.weektime);
		odata = (LinearLayout) timerstartLayout.findViewById(R.id.odate);
//		cdata = (LinearLayout) timerstartLayout.findViewById(R.id.cdate);

		openData = (Button) timerstartLayout.findViewById(R.id.kaidate);
		openTime = (Button) timerstartLayout.findViewById(R.id.kaitime);
//		closeDate = (Button) timerstartLayout.findViewById(R.id.guandate);
//		closeTime = (Button) timerstartLayout.findViewById(R.id.guantime);

		odate = (TextView) timerstartLayout.findViewById(R.id.kaidatetv);
		otime = (TextView) timerstartLayout.findViewById(R.id.kaitimetv);

//		cdate = (TextView) timerstartLayout.findViewById(R.id.guandatetv);
//		ctime = (TextView) timerstartLayout.findViewById(R.id.guantimetv);

		mode = (RadioGroup) timerstartLayout.findViewById(R.id.radioGroup2);
		data = (RadioButton) timerstartLayout.findViewById(R.id.data);     //年月日选项，暂不用
		week = (RadioButton) timerstartLayout.findViewById(R.id.week);
//		day = (RadioButton) timerstartLayout.findViewById(R.id.day);

		sun = (CheckBox) timerstartLayout.findViewById(R.id.sun);
		mon = (CheckBox) timerstartLayout.findViewById(R.id.mon);
		tue = (CheckBox) timerstartLayout.findViewById(R.id.tue);
		wed = (CheckBox) timerstartLayout.findViewById(R.id.wed);
		thu = (CheckBox) timerstartLayout.findViewById(R.id.thu);
		fri = (CheckBox) timerstartLayout.findViewById(R.id.fri);
		sat = (CheckBox) timerstartLayout.findViewById(R.id.sat);
		
		mMainActivity = (MainActivity) getActivity();
		
		
		mMainActivity.editor.putString("MODE", "3");
		mMainActivity.editor.commit();

		readtime();// 显示上次选择的开关机时间
	
		if (mMainActivity.sp.getString("SUN", "").equals("")
				|| mMainActivity.sp.getString("SUN", "").equals("0")) {
			sun.setChecked(false);
		} else {
			sun.setChecked(true);
		}

		if (mMainActivity.sp.getString("MON", "").equals("")
				|| mMainActivity.sp.getString("MON", "").equals("0")) {
			mon.setChecked(false);
		} else {
			mon.setChecked(true);
		}

		if (mMainActivity.sp.getString("TUE", "").equals("")
				|| mMainActivity.sp.getString("TUE", "").equals("0")) {
			tue.setChecked(false);
		} else {
			tue.setChecked(true);
		}

		if (mMainActivity.sp.getString("WED", "").equals("")
				|| mMainActivity.sp.getString("WED", "").equals("0")) {
			wed.setChecked(false);
		} else {
			wed.setChecked(true);
		}

		if (mMainActivity.sp.getString("THU", "").equals("")
				|| mMainActivity.sp.getString("THU", "").equals("0")) {
			thu.setChecked(false);
		} else {
			thu.setChecked(true);
		}

		if (mMainActivity.sp.getString("FRI", "").equals("")
				|| mMainActivity.sp.getString("FRI", "").equals("0")) {
			fri.setChecked(false);
		} else {
			fri.setChecked(true);
		}

		if (mMainActivity.sp.getString("SAT", "").equals("")
				|| mMainActivity.sp.getString("SAT", "").equals("0")) {
			sat.setChecked(false);
		} else {
			sat.setChecked(true);
		}

		sun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					mMainActivity.editor.putString("SUN", "1");
					mMainActivity.editor.commit();
				} else {
					mMainActivity.editor.putString("SUN", "0");
					mMainActivity.editor.commit();
				}
			}
		});

		mon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					mMainActivity.editor.putString("MON", "1");
					mMainActivity.editor.commit();
				} else {
					mMainActivity.editor.putString("MON", "0");
					mMainActivity.editor.commit();
				}
			}
		});

		tue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					mMainActivity.editor.putString("TUE", "1");
					mMainActivity.editor.commit();
				} else {
					mMainActivity.editor.putString("TUE", "0");
					mMainActivity.editor.commit();
				}
			}
		});

		wed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					mMainActivity.editor.putString("WED", "1");
					mMainActivity.editor.commit();
				} else {
					mMainActivity.editor.putString("WED", "0");
					mMainActivity.editor.commit();
				}
			}
		});

		thu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					mMainActivity.editor.putString("THU", "1");
					mMainActivity.editor.commit();
				} else {
					mMainActivity.editor.putString("THU", "0");
					mMainActivity.editor.commit();
				}
			}
		});

		fri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					mMainActivity.editor.putString("FRI", "1");
					mMainActivity.editor.commit();
				} else {
					mMainActivity.editor.putString("FRI", "0");
					mMainActivity.editor.commit();
				}
			}
		});

		sat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					mMainActivity.editor.putString("SAT", "1");
					mMainActivity.editor.commit();
				} else {
					mMainActivity.editor.putString("SAT", "0");
					mMainActivity.editor.commit();
				}
			}
		});

		mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
					
				if (checkedId == R.id.data) {
					mMainActivity.editor.putString("MODE", "1");
					mMainActivity.editor.commit();
					mWeek.setVisibility(View.GONE);
					odata.setVisibility(View.VISIBLE);
	//				cdata.setVisibility(View.GONE);
				} else if (checkedId == R.id.week) {
					mMainActivity.editor.putString("MODE", "2");
					mMainActivity.editor.commit();
					mWeek.setVisibility(View.VISIBLE);
					odata.setVisibility(View.GONE);
//					cdata.setVisibility(View.GONE);
				}
			}
		});
		


		if (mMainActivity.sp.getString("MODE", "").equals("1")) {
			data.setChecked(true);
			mWeek.setVisibility(View.GONE); // 首次启动选择绘制UI
			odata.setVisibility(View.VISIBLE);
//			cdata.setVisibility(View.VISIBLE);
		} else {						
			week.setChecked(true);
			mWeek.setVisibility(View.VISIBLE);
			odata.setVisibility(View.GONE);
//			cdata.setVisibility(View.GONE);
		}

		if (mMainActivity.sp.getInt("OYEAR", 0) != 0 || mMainActivity.sp.getInt("CYEAR", 0) != 0) {
			readtime(); // 显示选择的时间
		}

		
		
		mHandler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				switch (msg.what) {
				case 1:
				//	Utils.MyLog("关机时间:"+sp.getInt("CHOUR", 0)+":"+sp.getInt("CMINUTE", 0)+"-->开机时间:"+sp.getInt("OHOUR", 0)+":"+sp.getInt("OMINUTE", 0));
					//保存设置的定时开关机列表
					int times = mMainActivity.sp.getInt("time", 0)+1;
					
					if(times > 3){
						
						Toast.makeText(mContext, "最多只能添加3个定时启动时间！",Toast.LENGTH_SHORT).show();
						return;
					}
					
					String string_time = mMainActivity.sp.getInt("OHOUR", 0)+"/"+mMainActivity.sp.getInt("OMINUTE", 0);
					
					
					if (mMainActivity.sp.getString("MODE", "").equals("1")) {
						
						string_time = "1/"+string_time+"/"+mMainActivity.sp.getInt("OYEAR", 0)+"/"+mMainActivity.sp.getInt("OMONTH", 0)+"/"+mMainActivity.sp.getInt("ODAY", 0);
						
					} else if (mMainActivity.sp.getString("MODE", "").equals("2")) {
						
						if((mMainActivity.sp.getString("SUN", "").equals("")|| mMainActivity.sp.getString("SUN", "").equals("0"))
								&& (mMainActivity.sp.getString("SUN", "").equals("")|| mMainActivity.sp.getString("SUN", "").equals("0"))
								&& (mMainActivity.sp.getString("MON", "").equals("")|| mMainActivity.sp.getString("MON", "").equals("0"))
								
								&& (mMainActivity.sp.getString("TUE", "").equals("")|| mMainActivity.sp.getString("TUE", "").equals("0"))
								&& (mMainActivity.sp.getString("WED", "").equals("")|| mMainActivity.sp.getString("WED", "").equals("0"))
								
								&& (mMainActivity.sp.getString("THU", "").equals("")|| mMainActivity.sp.getString("THU", "").equals("0"))
								&& (mMainActivity.sp.getString("FRI", "").equals("")|| mMainActivity.sp.getString("FRI", "").equals("0"))
								
								
								&& (mMainActivity.sp.getString("SAT", "").equals("")|| mMainActivity.sp.getString("SAT", "").equals("0"))){
							
							Toast.makeText(mContext, "请先选择具体是星期几！",Toast.LENGTH_SHORT).show();
							return;
						}
						
						string_time = "2/"+string_time+"/"+mMainActivity.sp.getString("SUN", "")+"/"+mMainActivity.sp.getString("MON", "")+"/"+mMainActivity.sp.getString("TUE", "")+"/"+mMainActivity.sp.getString("WED", "")+"/"+mMainActivity.sp.getString("THU", "")+"/"+mMainActivity.sp.getString("FRI", "")+"/"+mMainActivity.sp.getString("SAT", "");
					}
					
					mMainActivity.editor.putInt("time", times);
					mMainActivity.editor.putString(""+times, string_time);
					mMainActivity.editor.commit();
					
					Showlistview();//列表显示设置的定时开关机
					
//					Intent it = new Intent("android.timezone.setagan");
//					sendBroadcast(it);

//					Toast.makeText(mContext, "The switch machine time is written successfully!",sToast.LENGTH_SHORT).show();
					break;

				case 2:
					odate.setText(mMainActivity.sp.getInt("OYEAR", 0) + getString(R.string.time_year)
							+ mMainActivity.sp.getInt("OMONTH", 0) + getString(R.string.time_month)
							+ mMainActivity.sp.getInt("ODAY", 0) + getString(R.string.time_day));
					break;

				case 3:
					otime.setText(mMainActivity.sp.getInt("OHOUR", 0) +  getString(R.string.time_hour)
							+ mMainActivity.sp.getInt("OMINUTE", 0) + getString(R.string.time_minute));
					break;

				case 4:
//					cdate.setText(mMainActivity.sp.getInt("CYEAR", 0) + getString(R.string.time_year)
//							+ mMainActivity.sp.getInt("CMONTH", 0) +  getString(R.string.time_month)
//							+ mMainActivity.sp.getInt("CDAY", 0) +  getString(R.string.time_day));
					break;

				case 5:
//					ctime.setText(mMainActivity.sp.getInt("CHOUR", 0) + getString(R.string.time_hour)
//							+ mMainActivity.sp.getInt("CMINUTE", 0) + getString(R.string.time_minute));
//					break;
				case 12://删除
					
					int position = msg.getData().getInt("position")+1;//要删除的项
					
					List<String> list = new ArrayList<String>();
					int time = mMainActivity.sp.getInt("time", 0);
					for(int i=1;i<=time;i++){
						if(i!=position){
							list.add(mMainActivity.sp.getString(""+i, ""));
						}
						mMainActivity.editor.remove(""+i);
					}
					mMainActivity.editor.putInt("time", list.size());
					
					//清除
					for(int i=0;i<list.size();i++){
						mMainActivity.editor.putString(""+(i+1), list.get(i));
					}
					mMainActivity.editor.commit();
					Showlistview();
					break;
					
				case 13:
					
					int timeAll = mMainActivity.sp.getInt("time", 0);
					for(int i=1;i<=timeAll;i++){
						mMainActivity.editor.remove(""+i);
					}
					
					mMainActivity.editor.putInt("time",0);
					
					mMainActivity.editor.commit();
					Showlistview();
					
					break;

				}
			};
		};

		OK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
					Message msg = mHandler.obtainMessage(1);
					mHandler.sendMessage(msg);
			}
		});
		
		reset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				reset();
				
			}
		});

		openData.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new DatePickerDialog(mContext, od, mMainActivity.sp.getInt("OYEAR",
						2014), mMainActivity.sp.getInt("OMONTH", 0) - 1 < 0 ? 0 : mMainActivity.sp.getInt(
						"OMONTH", 0) - 1, mMainActivity.sp.getInt("ODAY",
						Calendar.DAY_OF_MONTH)).show();
			}
		});

		openTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new TimePickerDialog(mContext, ot, mMainActivity.sp.getInt("OHOUR",
						Calendar.HOUR_OF_DAY), mMainActivity.sp.getInt("OMINUTE",
						Calendar.MINUTE), true).show();
				
			}
		});
/*
		closeDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new DatePickerDialog(mContext, cd, mMainActivity.sp.getInt("CYEAR",
						2014), mMainActivity.sp.getInt("CMONTH", 0) - 1 < 0 ? 0 : mMainActivity.sp.getInt(
						"CMONTH", 0) - 1, mMainActivity.sp.getInt("CDAY",
						Calendar.DAY_OF_MONTH)).show();
			}
		});

		closeTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new TimePickerDialog(mContext, ct, mMainActivity.sp.getInt("CHOUR",
						Calendar.HOUR_OF_DAY), mMainActivity.sp.getInt("CMINUTE",
						Calendar.MINUTE), true).show();
			}
		});
*/
		od = new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				mMainActivity.editor.putInt("OYEAR", year);
				mMainActivity.editor.putInt("OMONTH", monthOfYear + 1);
				mMainActivity.editor.putInt("ODAY", dayOfMonth);
				mMainActivity.editor.commit();
				Message msg = mHandler.obtainMessage(2);
				mHandler.sendMessage(msg);
			}
		};

		ot = new TimePickerDialog.OnTimeSetListener() {

			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				mMainActivity.editor.putInt("OHOUR", hourOfDay);
				mMainActivity.editor.putInt("OMINUTE", minute);
				mMainActivity.editor.commit();
				Message msg = mHandler.obtainMessage(3);
				mHandler.sendMessage(msg);
			}
		};

		
		
		
		
		
	
		
		return timerstartLayout;
	}

private void Showlistview(){//liuyanjun
		
		
		listview.setVisibility(View.VISIBLE);
		List<String> list = new ArrayList<String>();
		int time = mMainActivity.sp.getInt("time", 0);
		String string = "";
		String sendString = null;
	//	sendMessage(String.format("AT+069=%d\r\n",time));
		
		for(int i=1;i<=3;i++){
			if(i <= time){
				string = mMainActivity.sp.getString(""+i, "");
				sendString = String.format("AT+06%d=1/", i);
				sendString += (string+ "\r\n");
								
	
				if(string.split("/")[0].equals("1")){
					list.add(i+" "+this.getString(R.string.kjsj)+":"+string.split("/")[1]+":"+string.split("/")[2]+" "+this.getString(R.string.kjrq)+":"+string.split("/")[3]+"-"+string.split("/")[4]+"-"+string.split("/")[5]);
				}else if(string.split("/")[0].equals("2")){
					String temp = "";
					if(string.split("/")[4].equals("1")){
						
						temp += "周一";
					}
					if(string.split("/")[5].equals("1")){
						
						temp += "周二";
					}
					
					if(string.split("/")[6].equals("1")){
						
						temp += "周三";
					}
	
					if(string.split("/")[7].equals("1")){
						
						temp += "周四";
					}
	
					
					if(string.split("/")[8].equals("1")){
						
						temp += "周五";
					}
	
					if(string.split("/")[9].equals("1")){
						
						temp += "周六";
					}
					
					if(string.split("/")[3].equals("1")){
						
						temp += "周天";
					}
	
					list.add(i+" "+this.getString(R.string.kjsj)+":"+string.split("/")[1]+":"+string.split("/")[2]+" "+this.getString(R.string.kjrq)+":每周的  "+temp);
				}

			}else{
				
				sendString = String.format("AT+06%d=0/\r\n",i);
			}
			mMainActivity.sendComMessage(sendString);
			
		//	if(string.length()>0){
//					list.add(i+" "+this.getString(R.string.gjsj)+":"+string.split("/")[0]+":"+string.split("/")[1]+"  "+this.getString(R.string.kjsj)+":"+string.split("/")[2]+":"+string.split("/")[3]);
		//	}
			
		}
			
		adapter = new MyAdapter(mMainActivity, list,mHandler);
		listview.setAdapter(adapter);
		
		
	}

	public double timeZone(){
		TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT);
		System.out.println(TimeZone.getDefault().getID());
		String string = TimeZone.getDefault().getID();
		
		//switch (TimeZone.getDefault().getID()) {
		if("Pacific/Midway".equals(string))return -11;
		if("Pacific/Honolulu".equals(string))return -10;
		if("GMT-9:00".equals(string))return -9;
		if("America/Anchorage".equals(string))return -8;
		if("America/Los_Angeles".equals(string))return -7;
		if("America/Tijuana".equals(string))return -7;
		if("America/Phoenix".equals(string))return -7;
		if("America/Chihuahua".equals(string))return -7;
		if("America/Denver".equals(string))return -6;
		if("America/Costa_Rica".equals(string))return -6;
		if("America/Mexico_City".equals(string))return -6;
		if("America/Regina".equals(string))return -6;
		if("America/Chicago".equals(string))return -5;
		if("America/Bogota".equals(string))return -5;
		if("America/Caracas".equals(string))return -4.5;
		if("America/New_York".equals(string))return -4;
		if("America/Barbados".equals(string))return -4;
		if("America/Manaus".equals(string))return -4;
		if("America/Santiago".equals(string))return -4;
		if("America/Sao_Paulo".equals(string))return -3;
		if("America/Argentina/Buenos_Aires".equals(string))return -3;
		if("America/Godthab".equals(string))return -3;
		if("America/Montevideo".equals(string))return -3;
		if("America/St_Johns".equals(string))return -2.5;
		if("Atlantic/South_Georgia".equals(string))return -2;
		if("Atlantic/Azores".equals(string))return -1;
		if("Atlantic/Cape_Verde".equals(string))return -1;
		if("Africa/Casablanca".equals(string))return 0;
		if("Europe/London".equals(string))return 0;
		if("Europe/Amsterdam".equals(string))return 1;
		if("Europe/Belgrade".equals(string))return 1;
		if("Europe/Brussels".equals(string))return 1;
		if("Europe/Sarajevo".equals(string))return 1;
		if("Africa/Brazzaville".equals(string))return 1;
		if("Africa/Windhoek".equals(string))return 2;
		if("Asia/Amman".equals(string))return 2;
		if("Europe/Athens".equals(string))return 2;
		if("Asia/Beirut".equals(string))return 2;
		if("Africa/Cairo".equals(string))return 2;
		if("Europe/Helsinki".equals(string))return 2;
		if("Asia/Jerusalem".equals(string))return 2;
		if("Africa/Harare".equals(string))return 2;
		if("Europe/Minsk".equals(string))return 3;
		if("Asia/Baghdad".equals(string))return 3;
		if("Asia/Kuwait".equals(string))return 3;
		if("Africa/Nairobi".equals(string))return 3;
		if("Asia/Tehran".equals(string))return 3.5;
		if("Europe/Moscow".equals(string))return 4;
		if("Asia/Baku".equals(string))return 4;
		if("Asia/Tbilisi".equals(string))return 4;
		if("Asia/Yerevan".equals(string))return 4;
		if("Asia/Dubai".equals(string))return 4;
		if("Asia/Kabul".equals(string))return 4.5;
		if("Asia/Karachi".equals(string))return 5;
		if("Asia/Oral".equals(string))return 5;
		if("Asia/Calcutta".equals(string))return 5.5;
		if("Asia/Colombo".equals(string))return 5.5;
		if("Asia/Katmandu".equals(string))return 5.75;
		if("Asia/Yekaterinburg".equals(string))return 6;
		if("Asia/Almaty".equals(string))return 6;
		if("Asia/Rangoon".equals(string))return 6.5;
		if("Asia/Bangkok".equals(string))return 7;
	//	case "GMT+08:00" :
	//		return 8;
		if("Asia/Irkutsk".equals(string))return 9;
		if("Asia/Seoul".equals(string))return 9;
		if("Asia/Tokyo".equals(string))return 9;
		if("Australia/Darwin".equals(string))return 9.5;
		if("Asia/Yakutsk".equals(string))return 10;
		if("Australia/Brisbane".equals(string))return 10;
		if("Pacific/Guam".equals(string))return 10;
		if("Australia/Adelaide".equals(string))return 10.5;
		if("Australia/Hobart".equals(string))return 11;
		if("Australia/Sydney".equals(string))return 11;
		if("Asia/Vladivostok".equals(string))return 11;
		if("Pacific/Majuro".equals(string))return 12;
		if("Asia/Magadan".equals(string))return 12;
		if("Pacific/Fiji".equals(string))return 12;
		if("Pacific/Auckland".equals(string))return 13;	
		if("Pacific/Tongatapu".equals(string))return 13;	
		else return 8;
		
	}
	
	
	
	
	
	// 显示开关机时间
	public void readtime() {
	
		odate.setText(mMainActivity.sp.getInt("OYEAR", 0) + " " + getString(R.string.time_year) + " " + mMainActivity.sp.getInt("OMONTH", 0)
		+ " " + getString(R.string.time_month) + mMainActivity.sp.getInt("ODAY", 0) + getString(R.string.time_day));
		otime.setText(mMainActivity.sp.getInt("OHOUR", 6) + " " + getString(R.string.time_hour)  + " " +mMainActivity.sp.getInt("OMINUTE", 0)
		+ "  " + getString(R.string.time_minute));
		
	}
	
	public String stringCreate(int num) {
		if (num == 0) {
			return "00";
		} else if (0 < num && num < 10) {
			return "0" + num;
		} else {
			return num + "";
		}
	}

	public String dataCreate(int num) {
		if (num == 0) {
			return "0000";
		} else {
			return num + "";
		}
	}
	
	private void reset(){   //清除数据永不关机
//		Intent it = new Intent("android.time.zone.clean");
//		sendBroadcast(it);
	/*	
		week.setChecked(true);
		sun.setChecked(false);
		mon.setChecked(false);
		wed.setChecked(false);
		fri.setChecked(false);
		tue.setChecked(false);
		sat.setChecked(false);
		thu.setChecked(false);
		
		mMainActivity.editor.putString("SUN", "0");
		mMainActivity.editor.putString("MON", "0");
		mMainActivity.editor.putString("WED", "0");
		mMainActivity.editor.putString("FRI", "0");
		mMainActivity.editor.putString("TUE", "0");
		mMainActivity.editor.putString("SAT", "0");
		mMainActivity.editor.putString("THU", "0");
		mMainActivity.editor.commit();
	*/	
		Message msg = mHandler.obtainMessage(13);
		mHandler.sendMessage(msg);
	}

	
	
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		Log.e(TAG, "onDeatch======>");
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		Log.e(TAG, "onAttach======>");
	}
	
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	
		MainActivity.curFragmentTag = getString(R.string.timerstart_fg);
		
		//罗列设置的 开关机列表
		Showlistview();

		mMainActivity.timestartFragmentOn = true;
		
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub

		mMainActivity.timestartFragmentOn = false;

		super.onPause();
	}
	
	
}
