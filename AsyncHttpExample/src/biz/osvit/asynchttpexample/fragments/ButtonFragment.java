package biz.osvit.asynchttpexample.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import biz.osvit.asynchttpexample.R;
import biz.osvit.asynchttpexample.listeners.DataHandler;
import biz.osvit.asynchttpexample.listeners.OnDataLoadedListener;

public class ButtonFragment extends BaseFragment {

	private Button mShowButton;

	private OnDataLoadedListener mDataLoadedListener;

	String data;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View parent = inflater.inflate(R.layout.fragment_show_button, container, false);
		initUi(parent);
		initListeners();
		return parent;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mDataLoadedListener = (OnDataLoadedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement interface listeners");
		}

	}

	@Override
	protected void initUi(View parent) {

		mShowButton = (Button) parent.findViewById(R.id.show_list_fragment_button);

	}

	@Override
	protected void initListeners() {
		mShowButton.setOnClickListener(mOnClickListener);

	}

	private OnClickListener mOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.show_list_fragment_button:

				DataHandler.getDataFromBackend("http://www.creativestudio.hr/cg/vodostaj.php", mDataLoadedListener);

				break;
			}

		}
	};

}
