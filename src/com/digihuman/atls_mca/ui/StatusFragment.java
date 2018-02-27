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
	
	
	
	private LineChartView chart;        //��ʾ�������Զ���View
    private LineChartData data;          // ����ͼ��װ��������
    private int numberOfLines = 4;         //����������
    private int maxNumberOfLines = 4;     //������������
    private int numberOfPoints = 12;     //�������

    float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints]; //��ά���飬�ߵ������͵������

    private boolean hasAxes = true;       //�Ƿ����ᣬx��y��
    private boolean hasAxesNames = true;   //�Ƿ����������
    private boolean hasLines = true;       //�Ƿ����ߣ���͵����ӵ��ߣ�
    private boolean hasPoints = true;       //�Ƿ��е㣨ÿ��ֵ�ĵ㣩
    private ValueShape shape = ValueShape.CIRCLE;    //����ʾ����ʽ��Բ�Σ�����������
    private boolean isFilled = false;                //�Ƿ������
    private boolean hasLabels = false;               //ÿ�����Ƿ�������
    private boolean isCubic = false;                 //�Ƿ��������ģ�������ֱ�߻��ǻ���
    private boolean hasLabelForSelected = false;       //ÿ�����Ƿ����ѡ�񣨵��Ч����
    private boolean pointsHaveDifferentColor;           //��������ɫ�任
    private boolean hasGradientToTransparent = false;      //�Ƿ����ݶȵ�͸��
 
	
	
	
	
	
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
//                    Toast.makeText(mContext, "����д��ɹ�", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
 //                   Toast.makeText(mContext, "����д��ʧ��", Toast.LENGTH_SHORT).show();
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
//                    Toast.makeText(mContext, "����д��ɹ�", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
 //                   Toast.makeText(mContext, "����д��ʧ��", Toast.LENGTH_SHORT).show();
                }
				
			}
		});
		
		*/
		return statusLayout;
	}

    private void initData() {
        // Generate some random values.
        generateValues();   //���������ߵ�ֵ����
        generateData();    //��������

        // Disable viewport recalculations, see toggleCubic() method for more info.
        chart.setViewportCalculationEnabled(false);

        chart.setZoomType(ZoomType.HORIZONTAL);//������������ˮƽ����������Ĭ����ȫ��λ����
        resetViewport();   //��������ͼ����ʾ��С
    }

    private void initEvent() {
 //       chart.setOnValueTouchListener(new ValueTouchListener());

    }

    /**
     * ͼ����ʾ��С
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
     * ������������������
     */
    private void generateValues() {
        for (int i = 0; i < maxNumberOfLines; ++i) {
            for (int j = 0; j < numberOfPoints; ++j) {
                randomNumbersTab[i][j] = (float) Math.random() * 100f;
            }
        }
    }
    /**
     * ��������
     */
    private void generateData() {
          //�����������ļ���
        List<Line> lines = new ArrayList<Line>();
     //���������õ���������ȥ
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
                axisX.setTextColor(Color.BLACK);//����x���������ɫ
                axisY.setTextColor(Color.BLACK);//����y���������ɫ
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
     * ����������
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
		//����Ĭ�Ϲ������ײ�
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
		},0,1000);  //��ʱ0ms��ִ��,1sִ��һ��
		
		
		
		
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
