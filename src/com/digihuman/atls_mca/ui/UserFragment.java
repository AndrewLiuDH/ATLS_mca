package com.digihuman.atls_mca.ui;

import com.digihuman.atls_mca.R;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UserFragment extends BaseFragment {

	
	private MainActivity mMainActivity ;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View settingLayout = inflater.inflate(R.layout.user_layout,
				container, false);
		
		
		mMainActivity = (MainActivity) getActivity();
		
		
		return settingLayout;
	}
	
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	
		MainActivity.curFragmentTag = getString(R.string.user_fg);

		mMainActivity.userFragmentOn = true;
		
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub


		mMainActivity.userFragmentOn = false;
		super.onPause();
	}

	
	

}
