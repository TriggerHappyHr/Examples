package biz.osvit.asynchttpexample.activities;

import java.util.Arrays;

import com.google.gson.Gson;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import biz.osvit.asynchttpexample.R;
import biz.osvit.asynchttpexample.fragments.ButtonFragment;
import biz.osvit.asynchttpexample.fragments.ShowVodostajListFragment;
import biz.osvit.asynchttpexample.listeners.OnDataLoadedListener;
import biz.osvit.asynchttpexample.listeners.OnGotDataFromHandler;
import biz.osvit.asynchttpexample.models.VodostajModel;
import biz.osvit.asynchttpexample.models.VodostajModelWrapper;

public class MainActivity extends BaseActivity implements OnDataLoadedListener, OnGotDataFromHandler {

	// String data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initUi();
		initListeners();

	}

	@Override
	protected void initUi() {

		ButtonFragment buttonFragment = new ButtonFragment();
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.activity_main_fragment_holder, buttonFragment);
		transaction.commit();

	}

	@Override
	protected void initListeners() {

	}

	@Override
	public void onDataLoadedLoaded(String data) {

		Gson parser = new Gson();
		VodostajModel[] listaModela = parser.fromJson(data, VodostajModel[].class);
		VodostajModelWrapper wrapper = new VodostajModelWrapper();
		wrapper.setVodostaji(Arrays.asList(listaModela));

		ShowVodostajListFragment vodostajListFragment = new ShowVodostajListFragment();
		OnGotDataFromHandler gotDataFromHandler = (OnGotDataFromHandler) vodostajListFragment;
		gotDataFromHandler.onGotDatafromHandler(wrapper);
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.activity_main_fragment_holder, vodostajListFragment);
		transaction.commit();

	}

	@Override
	public void onGotDatafromHandler(VodostajModelWrapper vodostaji) {

	}

}
