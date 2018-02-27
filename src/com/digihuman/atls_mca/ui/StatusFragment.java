package com.digihuman.atls_mca.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.listener.LineChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.digihuman.atls_mca.R;
import com.digihuman.atls_mca.utils.FileHelper;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class StatusFragment extends BaseFragment {

	
	private static final String TAG = "StatusFragment";
	
	private MainActivity mMainActivity ;
	
	//private ScrollView svResult;
	//private TextView tvLog;
	//private EditText et_at;
	//private Button btsend,btRequestLog,btClearLog;
	
	
	
	private LineChartView chart;        //显示线条的自定义View
    private LineChartData data;          // 折线图封装的数据类
    private int numberOfLines = 4;         //线条的数量
    private int maxNumberOfLines = 4;     //最大的线条数据
    private int numberOfPoints = 12;     //点的数量

    float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints]; //二维数组，线的数量和点的数量

    private boolean hasAxes = true;       //是否有轴，x和y轴
    private boolean hasAxesNames = true;   //是否有轴的名字
    private boolean hasLines = true;       //是否有线（点和点连接的线）
    private boolean hasPoints = true;       //是否有点（每个值的点）
    private ValueShape shape = ValueShape.CIRCLE;    //点显示的形式，圆形，正方向，菱形
    private boolean isFilled = false;                //是否是填充
    private boolean hasLabels = false;               //每个点是否有名字
    private boolean isCubic = false;                 //是否是立方的，线条是直线还是弧线
    private boolean hasLabelForSelected = false;       //每个点是否可以选择（点击效果）
    private boolean pointsHaveDifferentColor;           //线条的颜色变换
    private boolean hasGradientToTransparent = false;      //是否有梯度的透明
 
	
	
	
	
	
	private TextView txv_hcho1_t;
	private TextView txv_hcho2_t;
	private TextView txv_hcho3_t;
	private TextView txv_hcho4_t;
	private TextView txv_hcho5_t;
	private TextView txv_hcho6_t;
	
	private TextView txv_hcho1_h;
	private TextView txv_hcho2_h;
	private TextView txv_hcho3_h;
	private TextView txv_hcho4_h;
	private TextView txv_hcho5_h;
	private TextView txv_hcho6_h;
	
	private TextView txv_hcho1_c;
	private TextView txv_hcho2_c;
	private TextView txv_hcho3_c;
	private TextView txv_hcho4_c;
	private TextView txv_hcho5_c;
	private TextView txv_hcho6_c;
	
	private TextView txv_hcho1_o;
	private TextView txv_hcho2_o;
	private TextView txv_hcho3_o;
	private TextView txv_hcho4_o;
	private TextView txv_hcho5_o;
	private TextView txv_hcho6_o;
	
	
	
	private static final int STATUS_FLASH = 0x20;
	private Timer mTimerFlash = new Timer();
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	public final Handler mHandlerStatus = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			Bundle bundle = msg.getData();
			String strArray[] = null;
			switch (msg.what) {
			case STATUS_FLASH:
				
				flash_data();
				break;
				
			
			default:
				break;
			}
		}		
	};
	
	public void flash_data(){
		
		txv_hcho1_t.setText(mMainActivity.str_hcho1_t);
		txv_hcho1_h.setText(mMainActivity.str_hcho1_h);
		txv_hcho1_c.setText(mMainActivity.str_hcho1_c);
		txv_hcho1_o.setText(mMainActivity.str_hcho1_o+"#"+mMainActivity.str_hcho1_ov);
		
		
		
		
		txv_hcho2_t.setText(mMainActivity.str_hcho2_t);
		txv_hcho2_h.setText(mMainActivity.str_hcho2_h);
		txv_hcho2_c.setText(mMainActivity.str_hcho2_c);
		txv_hcho2_o.setText(mMainActivity.str_hcho2_o+"#"+mMainActivity.str_hcho2_ov);
		
		
		
		txv_hcho3_t.setText(mMainActivity.str_hcho3_t);
		txv_hcho3_h.setText(mMainActivity.str_hcho3_h);
		txv_hcho3_c.setText(mMainActivity.str_hcho3_c);
		txv_hcho3_o.setText(mMainActivity.str_hcho3_o+"#"+mMainActivity.str_hcho3_ov);
		
		
		txv_hcho4_t.setText(mMainActivity.str_hcho4_t);
		txv_hcho4_h.setText(mMainActivity.str_hcho4_h);
		txv_hcho4_c.setText(mMainActivity.str_hcho4_c);
		txv_hcho4_o.setText(mMainActivity.str_hcho4_o+"#"+mMainActivity.str_hcho4_ov);
		
		txv_hcho5_t.setText(mMainActivity.str_hcho5_t);
		txv_hcho5_h.setText(mMainActivity.str_hcho5_h);
		txv_hcho5_c.setText(mMainActivity.str_hcho5_c);
		txv_hcho5_o.setText(mMainActivity.str_hcho5_o+"#"+mMainActivity.str_hcho5_ov);
		
		
		txv_hcho6_t.setText(mMainActivity.str_hcho6_t);
		txv_hcho6_h.setText(mMainActivity.str_hcho6_h);
		txv_hcho6_c.setText(mMainActivity.str_hcho6_c);
		txv_hcho6_o.setText(mMainActivity.str_hcho6_o+"#"+mMainActivity.str_hcho6_ov);
	}
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View statusLayout = inflater.inflate(R.layout.status_layout,
				container, false);
		
		Log.d(TAG, "onCreateView---->");

		
/*
		svResult = (ScrollView) statusLayout.findViewById(R.id.svResult);
		et_at = (EditText) statusLayout.findViewById(R.id.inputAT);
		btsend = (Button) statusLayout.findViewById(R.id.sendAT);
		btRequestLog = (Button) statusLayout.findViewById(R.id.requestlog);
		btClearLog = (Button) statusLayout.findViewById(R.id.clearlog);
		tvLog = (TextView) statusLayout.findViewById(R.id.tvLog);
*/		
		mMainActivity = (MainActivity) getActivity();
		mFragmentManager = getActivity().getSupportFragmentManager();
		
		
		
		txv_hcho1_t = (TextView)statusLayout.findViewById(R.id.Status_hcho1_t);
		txv_hcho2_t = (TextView)statusLayout.findViewById(R.id.Status_hcho2_t);
		txv_hcho3_t = (TextView)statusLayout.findViewById(R.id.Status_hcho3_t);
		txv_hcho4_t = (TextView)statusLayout.findViewById(R.id.Status_hcho4_t);
		txv_hcho5_t = (TextView)statusLayout.findViewById(R.id.Status_hcho5_t);
		txv_hcho6_t = (TextView)statusLayout.findViewById(R.id.Status_hcho6_t);
		
		
		txv_hcho1_h = (TextView)statusLayout.findViewById(R.id.Status_hcho1_h);
		txv_hcho2_h = (TextView)statusLayout.findViewById(R.id.Status_hcho2_h);
		txv_hcho3_h = (TextView)statusLayout.findViewById(R.id.Status_hcho3_h);
		txv_hcho4_h = (TextView)statusLayout.findViewById(R.id.Status_hcho4_h);
		txv_hcho5_h = (TextView)statusLayout.findViewById(R.id.Status_hcho5_h);
		txv_hcho6_h = (TextView)statusLayout.findViewById(R.id.Status_hcho6_h);
		
		
		
		txv_hcho1_c = (TextView)statusLayout.findViewById(R.id.Status_hcho1_c);
		txv_hcho2_c = (TextView)statusLayout.findViewById(R.id.Status_hcho2_c);
		txv_hcho3_c = (TextView)statusLayout.findViewById(R.id.Status_hcho3_c);
		txv_hcho4_c = (TextView)statusLayout.findViewById(R.id.Status_hcho4_c);
		txv_hcho5_c = (TextView)statusLayout.findViewById(R.id.Status_hcho5_c);
		txv_hcho6_c = (TextView)statusLayout.findViewById(R.id.Status_hcho6_c);
		
		
		txv_hcho1_o = (TextView)statusLayout.findViewById(R.id.Status_hcho1_o);
		txv_hcho2_o = (TextView)statusLayout.findViewById(R.id.Status_hcho2_o);
		txv_hcho3_o = (TextView)statusLayout.findViewById(R.id.Status_hcho3_o);
		txv_hcho4_o = (TextView)statusLayout.findViewById(R.id.Status_hcho4_o);
		txv_hcho5_o = (TextView)statusLayout.findViewById(R.id.Status_hcho5_o);
		txv_hcho6_o = (TextView)statusLayout.findViewById(R.id.Status_hcho6_o);
		
		
		chart = (LineChartView) statusLayout.findViewById(R.id.chart);
		
		
		flash_data();
		
		
		initData();
	    initEvent();
		
/*		
		btsend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String at_string = null;
				at_string = et_at.getText().toString()+"\r\n";
				
				mMainActivity.sendComMessage(at_string);
				addLog(at_string);
				
			
//			mMainActivity.postSensorconfig();
				
				
				
				

			}
		});
		
		
		btRequestLog.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String temp_string = null;
				
				
				
				FileHelper fHelper = new FileHelper(mContext);
                try {
                	temp_string = fHelper.read(mMainActivity.filename);
//                    Toast.makeText(mContext, "数据写入成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
 //                   Toast.makeText(mContext, "数据写入失败", Toast.LENGTH_SHORT).show();
                }
                
                
				
				addLog(temp_string);
				
			}
		});


		btClearLog.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				String temp_string = null;
				
				
				
				FileHelper fHelper = new FileHelper(mContext);

                try {
                    fHelper.save(mMainActivity.filename, temp_string);
//                    Toast.makeText(mContext, "数据写入成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
 //                   Toast.makeText(mContext, "数据写入失败", Toast.LENGTH_SHORT).show();
                }
				
			}
		});
		
		*/
		return statusLayout;
	}

    private void initData() {
        // Generate some random values.
        generateValues();   //设置四条线的值数据
        generateData();    //设置数据

        // Disable viewport recalculations, see toggleCubic() method for more info.
        chart.setViewportCalculationEnabled(false);

        chart.setZoomType(ZoomType.HORIZONTAL);//设置线条可以水平方向收缩，默认是全方位缩放
        resetViewport();   //设置折线图的显示大小
    }

    private void initEvent() {
 //       chart.setOnValueTouchListener(new ValueTouchListener());

    }

    /**
     * 图像显示大小
     */
    private void resetViewport() {
        // Reset viewport height range to (0,100)
        final Viewport v = new Viewport(chart.getMaximumViewport());
        v.bottom = 0;
        v.top = 80;
        v.left = 0;
        v.right = 300 - 1;
        chart.setMaximumViewport(v);
        chart.setCurrentViewport(v);
    }
    /**
     * 设置四条线条的数据
     */
    private void generateValues() {
        for (int i = 0; i < maxNumberOfLines; ++i) {
            for (int j = 0; j < numberOfPoints; ++j) {
                randomNumbersTab[i][j] = (float) Math.random() * 100f;
            }
        }
    }
    /**
     * 配置数据
     */
    private void generateData() {
          //存放线条对象的集合
        List<Line> lines = new ArrayList<Line>();
     //把数据设置到线条上面去
        for (int i = 0; i < numberOfLines; ++i) {

            List<PointValue> values = new ArrayList<PointValue>();
            for (int j = 0; j < numberOfPoints; ++j) {
                values.add(new PointValue(j, randomNumbersTab[i][j]));
            }

            Line line = new Line(values);
            line.setColor(ChartUtils.COLORS[i]);
            line.setShape(shape);
            line.setCubic(isCubic);
            line.setFilled(isFilled);
            line.setHasLabels(hasLabels);
            line.setHasLabelsOnlyForSelected(hasLabelForSelected);
            line.setHasLines(hasLines);
            line.setHasPoints(hasPoints);
//            line.setHasGradientToTransparent(hasGradientToTransparent);
            if (pointsHaveDifferentColor) {
                line.setPointColor(ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length]);
            }
            lines.add(line);
        }

        data = new LineChartData(lines);

        if (hasAxes) {
            Axis axisX = new Axis();
            Axis axisY = new Axis().setHasLines(true);
            if (hasAxesNames) {
                axisX.setTextColor(Color.BLACK);//设置x轴字体的颜色
                axisY.setTextColor(Color.BLACK);//设置y轴字体的颜色
                axisX.setName("Axis X");
                axisY.setName("Axis Y");
            }
            data.setAxisXBottom(axisX);
            data.setAxisYLeft(axisY);
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        data.setBaseValue(Float.NEGATIVE_INFINITY);
        chart.setLineChartData(data);

    }
    /**
     * 触摸监听类
     */
    
    /*
    private class ValueTouchListener implements LineChartOnValueSelectListener {

        @Override
        public void onValueSelected(int lineIndex, int pointIndex, PointValue value) {
            Toast.makeText(mContext, "Selected: " + value, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {


        }

    }
 */   
	
/*
	private void addLog(String str) {
		tvLog.append(str + "\n");
		//设置默认滚动到底部
		svResult.post(new Runnable() {
			public void run() {
				svResult.fullScroll(ScrollView.FOCUS_DOWN);
			}
		});
	}
*/
	
	public void postSensor() {
			
			
			
		
	}
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		Log.e(TAG, "onAttach-----");
		
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.e(TAG, "onCreate------");
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.e(TAG, "onActivityCreated-------");
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	
		Log.e(TAG, "onStart----->");
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.e(TAG, "onresume---->");
		MainActivity.curFragmentTag = getString(R.string.status_fg);
		mMainActivity.statusFragmentOn = true;
		
		
		
		mTimerFlash = new Timer();
		mTimerFlash.schedule(new TimerTask(){				
			public void run()
			{
				mHandlerStatus.sendEmptyMessage(STATUS_FLASH);
			}
		},0,1000);  //延时0ms后执行,1s执行一次
		
		
		
		
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		mMainActivity.statusFragmentOn = false;
		mTimerFlash.cancel();
		
		super.onPause();
		Log.e(TAG, "onpause");
	}
	
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.e(TAG, "onStop");
	}
	
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
		Log.e(TAG, "ondestoryView");
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.e(TAG, "ondestory");
	}
	
	@Override
	public void onDetach() {
		// TODO Auto-generated method stub
		super.onDetach();
		Log.d(TAG, "onDetach------");
		
	}
	
}
