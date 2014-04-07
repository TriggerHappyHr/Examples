package biz.osvit.databaseexample.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import biz.osvit.databaseexample.R;
import biz.osvit.databaseexample.adapters.UserAdapter;
import biz.osvit.databaseexample.database.Database;
import biz.osvit.databaseexample.listeners.OnDataReadListener;
import biz.osvit.databaseexample.models.UserModelWrapper;

public class ShowUsersFragment extends BaseFragment implements OnDataReadListener {

	private ListView mUserListView;
	private UserAdapter mUserAdapter;

	private UserModelWrapper mUserModelWrapper;
	Database mDatabase;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View parent = inflater.inflate(R.layout.show_user_fragment, container, false);
		initUi(parent);
		initListeners();

		return parent;

	}

	@Override
	protected void initUi(View parent) {
		mUserModelWrapper = mDatabase.getAllUsers();
		mUserListView = (ListView) parent.findViewById(R.id.show_user_fragment_list_view);
		mUserAdapter = new UserAdapter(getActivity(), mUserModelWrapper);
		mUserListView.setAdapter(mUserAdapter);

	}

	@Override
	protected void initListeners() {

	}

	@Override
	public void onDataRead(Database database) {
		this.mDatabase = database;

	}

}
