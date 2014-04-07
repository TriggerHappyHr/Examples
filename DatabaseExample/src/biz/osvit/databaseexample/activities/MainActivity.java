package biz.osvit.databaseexample.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import biz.osvit.databaseexample.R;
import biz.osvit.databaseexample.database.Database;
import biz.osvit.databaseexample.fragments.AddUserFragment;
import biz.osvit.databaseexample.fragments.ShowUsersFragment;
import biz.osvit.databaseexample.listeners.OnDataReadListener;

public class MainActivity extends BaseActivity implements OnDataReadListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initUi();
		initListeners();

	}

	@Override
	protected void initUi() {

		AddUserFragment addUserFragment = new AddUserFragment();
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.activity_main_fragment_holder, addUserFragment);
		transaction.commit();

	}

	@Override
	protected void initListeners() {

	}

	@Override
	public void onDataRead(Database database) {

		ShowUsersFragment showUserFragment = new ShowUsersFragment();
		OnDataReadListener onDataReadListener = (OnDataReadListener) showUserFragment;
		onDataReadListener.onDataRead(database);
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.replace(R.id.activity_main_fragment_holder, showUserFragment);
		transaction.commit();

	}

}
