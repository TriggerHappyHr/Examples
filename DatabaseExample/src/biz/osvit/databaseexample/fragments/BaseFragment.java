package biz.osvit.databaseexample.fragments;

import android.app.Fragment;
import android.view.View;

public abstract class BaseFragment extends Fragment {

	protected abstract void initUi(View parent);

	protected abstract void initListeners();

}
