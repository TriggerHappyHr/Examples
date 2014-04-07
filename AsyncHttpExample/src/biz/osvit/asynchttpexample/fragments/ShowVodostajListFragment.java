package biz.osvit.asynchttpexample.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import biz.osvit.asynchttpexample.R;
import biz.osvit.asynchttpexample.activities.MainActivity;
import biz.osvit.asynchttpexample.adapters.VodostajAdapter;
import biz.osvit.asynchttpexample.listeners.OnGotDataFromHandler;
import biz.osvit.asynchttpexample.models.VodostajModelWrapper;

public class ShowVodostajListFragment extends BaseFragment implements OnGotDataFromHandler {

	private ListView mVodostajListView;
	private VodostajAdapter mVodostajAdapter;

	private VodostajModelWrapper mVodostajWrapper;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View parent = inflater.inflate(R.layout.fragment_show_vodostaj_list, container, false);
		initUi(parent);
		initListeners();
		return parent;

	}

	@Override
	protected void initUi(View parent) {
		mVodostajListView = (ListView) parent.findViewById(R.id.fragment_show_vodostaj_list);

		mVodostajAdapter = new VodostajAdapter(getActivity(), mVodostajWrapper);
		mVodostajListView.setAdapter(mVodostajAdapter);

	}

	@Override
	protected void initListeners() {

	}

	@Override
	public void onGotDatafromHandler(VodostajModelWrapper vodostaji) {
		this.mVodostajWrapper = vodostaji;
	}

}
