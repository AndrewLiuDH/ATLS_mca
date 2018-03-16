package com.digihuman.atls_mca.ui;

import com.digihuman.atls_mca.R;

import android.content.Context;  
import android.graphics.Bitmap;  
import android.graphics.BitmapFactory;  
import android.graphics.Canvas;  
import android.util.AttributeSet;  
import android.view.MotionEvent;  
import android.view.View;  
  
/** 
 * Created by Jackie on 2015/12/15. 
 */  
public class SwitchButton extends View {  
    private Bitmap mSwitchBackgroud_open, mSwitchBackgroud_close, mSlideBackground;  
  
    private int mCurrentX;  //��ǰX��ƫ����  
    private boolean mCurrentState = false;  //��ǰ״̬���жϻ����Ƿ񻬶��ɹ�  
    private boolean mIsSliding = false;    //�����Ƿ����ڻ���  
  
    private OnSwitchStateChangeListener mOnSwitchStateChangeListener; //״̬�ı�Ļص�  
  
    public SwitchButton(Context context, AttributeSet attrs) {  
        super(context, attrs);  
  
        initBitmap();  
    }  
  
    //��ʼ������ͼƬ  
    private void initBitmap() {  
        mSwitchBackgroud_open = BitmapFactory.decodeResource(getResources(), R.drawable.switch_background_open);
        mSwitchBackgroud_close = BitmapFactory.decodeResource(getResources(), R.drawable.switch_background_close);
        mSlideBackground = BitmapFactory.decodeResource(getResources(), R.drawable.slide_button_background);  
    }  
  
    //���õ�ǰ�ؼ��Ŀ�͸�  
    @Override  
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);  
  
        // ���ÿ��صĿ�͸�  
        if (mCurrentState){
        	setMeasuredDimension(mSwitchBackgroud_open.getWidth(), mSwitchBackgroud_open.getHeight());  
        }else{
        	setMeasuredDimension(mSwitchBackgroud_close.getWidth(), mSwitchBackgroud_close.getHeight());
        }
    }  
  
    @Override  
    protected void onDraw(Canvas canvas) {  
        //���Ʊ���  
    	if (mCurrentState){
    		canvas.drawBitmap(mSwitchBackgroud_open, 0, 0, null); 
    	}else{
    		canvas.drawBitmap(mSwitchBackgroud_close, 0, 0, null);
    	} 
  
        if (false) {  //���ڻ�����  
            int left = mCurrentX - mSlideBackground.getWidth();  
            if (left < 0) {  //������߽�  
                left = 0;  
            } else if (left > mSwitchBackgroud_open.getWidth() - mSlideBackground.getWidth()) { //�����ұ߽�  
                left = mSwitchBackgroud_open.getWidth() - mSlideBackground.getWidth();  
            }  
  
            canvas.drawBitmap(mSlideBackground, left, 0, null);  
        } else {  
            if (mCurrentState) {  
                //���ƿ��ؿ���״̬  
                int left = mSwitchBackgroud_open.getWidth() - mSlideBackground.getWidth();  
                canvas.drawBitmap(mSlideBackground, left, 0, null);  
            } else {  
                //���ƿ��عص�״̬  
                canvas.drawBitmap(mSlideBackground, 0, 0, null);  
            }  
        }  
  
        super.onDraw(canvas);  
    }  
  
    
    /*
     * 
     * 
     protected void onDraw(Canvas canvas) {  
        //���Ʊ���  
    	
    	if (mCurrentState){
    		canvas.drawBitmap(mSwitchBackgroud_open, 0, 0, null); 
    		
    		if (mIsSliding) {  //���ڻ�����  
                int left = mCurrentX - mSlideBackground.getWidth();  
                if (left < 0) {  //������߽�  
                    left = 0;  
                } else if (left > mSwitchBackgroud_open.getWidth() - mSlideBackground.getWidth()) { //�����ұ߽�  
                    left = mSwitchBackgroud_open.getWidth() - mSlideBackground.getWidth();  
                }  
      
                canvas.drawBitmap(mSlideBackground, left, 0, null);  
            } else {  
                if (mCurrentState) {  
                    //���ƿ��ؿ���״̬  
                    int left = mSwitchBackgroud_open.getWidth() - mSlideBackground.getWidth();  
                    canvas.drawBitmap(mSlideBackground, left, 0, null);  
                } else {  
                    //���ƿ��عص�״̬  
                    canvas.drawBitmap(mSlideBackground, 0, 0, null);  
                }  
            }  
    		
    		
        }else{
        	
        	canvas.drawBitmap(mSwitchBackgroud_close, 0, 0, null); 
        	
        	if (mIsSliding) {  //���ڻ�����  
                int left = mCurrentX - mSlideBackground.getWidth();  
                if (left < 0) {  //������߽�  
                    left = 0;  
                } else if (left > mSwitchBackgroud_close.getWidth() - mSlideBackground.getWidth()) { //�����ұ߽�  
                    left = mSwitchBackgroud_close.getWidth() - mSlideBackground.getWidth();  
                }  
      
                canvas.drawBitmap(mSlideBackground, left, 0, null);  
            } else {  
                if (mCurrentState) {  
                    //���ƿ��ؿ���״̬  
                    int left = mSwitchBackgroud_close.getWidth() - mSlideBackground.getWidth();  
                    canvas.drawBitmap(mSlideBackground, left, 0, null);  
                } else {  
                    //���ƿ��عص�״̬  
                    canvas.drawBitmap(mSlideBackground, 0, 0, null);  
                }  
            }  
        } 
  
        
  
        super.onDraw(canvas);  
    }  
     * 
     * 
     * */
    @Override  
    public boolean onTouchEvent(MotionEvent event) {  
    	int center;
    	boolean state;
        switch (event.getAction()) {  
            case MotionEvent.ACTION_DOWN:  
                mCurrentX = (int) event.getX();  
                mIsSliding = true;  
                break;  
            
            case MotionEvent.ACTION_UP: 
            	mIsSliding = false;
 //           case MotionEvent.ACTION_MOVE:  	
                mCurrentX = (int) event.getX();  
                center = mSwitchBackgroud_open.getWidth() / 2;  
                
                state = (mCurrentX > center)?true:false;   //���黬���ľ��볬��������һ�룬��ʾ�����ɹ���״̬��Ҫ�ı� 
                
  
                if (mCurrentState != state && mOnSwitchStateChangeListener != null) {  //����״̬��һ������ʾ�����Ѿ������ɹ���״̬�ı�  
                    mOnSwitchStateChangeListener.onSwitchStateChange(state);  
                }  
  
                mCurrentState = state;  
                break;  
        }  
  
        invalidate();  
        return true;  
    }  
  
    /** 
     * ��ȡ���ص�״̬ 
     * @param state 
     */  
    public boolean getSwitchState() {  
        return mCurrentState;  
    }  
    
    
    /** 
     * ���ÿ��ص�״̬ 
     * @param state 
     */  
    public void setSwitchState(boolean state) {  
        mCurrentState = state;  
        
        invalidate();
    }  
 
    public void setOnSwitchStateChangeListener(OnSwitchStateChangeListener onSwitchStateChangeListener) {  
        this.mOnSwitchStateChangeListener = onSwitchStateChangeListener;  
    }  

}  