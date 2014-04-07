package biz.osvit.databaseexample.fragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import biz.osvit.databaseexample.R;
import biz.osvit.databaseexample.database.Database;
import biz.osvit.databaseexample.listeners.OnDataReadListener;
import biz.osvit.databaseexample.models.UserModel;

public class AddUserFragment extends BaseFragment {

	private Database mDatabase;

	private EditText mFirstNameEditText;
	private EditText mLastNameEditText;
	private EditText mPhoneNumberEditText;

	private RadioGroup mGenderRadioGroup;

	private Button mAddButton;
	private Button mOverviewButton;

	private OnDataReadListener mOnDataReadListener;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		mDatabase = new Database(activity);

		try {
			mOnDataReadListener = (OnDataReadListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() + " must implement interface listener!");
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View parent = inflater.inflate(R.layout.add_user_fragment, container, false);
		initUi(parent);
		initListeners();
		return parent;
	}

	@Override
	protected void initUi(View parent) {

		mFirstNameEditText = (EditText) parent.findViewById(R.id.add_user_fragment_edit_text_first_name);
		mLastNameEditText = (EditText) parent.findViewById(R.id.add_user_fragment_edit_text_last_name);
		mPhoneNumberEditText = (EditText) parent.findViewById(R.id.add_user_fragment_edit_text_phone_number);

		mGenderRadioGroup = (RadioGroup) parent.findViewById(R.id.add_user_fragment_radio_group);

		mAddButton = (Button) parent.findViewById(R.id.add_user_fragment_button_add);
		mOverviewButton = (Button) parent.findViewById(R.id.add_user_fragment_button_overview);

	}

	@Override
	protected void initListeners() {

		mAddButton.setOnClickListener(mOnClickListener);
		mOverviewButton.setOnClickListener(mOnClickListener);

	}

	public OnClickListener mOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {

			case R.id.add_user_fragment_button_add:

				String tempFirstName = mFirstNameEditText.getText().toString();
				String tempLastName = mLastNameEditText.getText().toString();
				String tempGender = getGenderFromRadioGroup();
				String tempPhoneNumber = mPhoneNumberEditText.getText().toString();
				UserModel userModel = createUser(tempFirstName, tempLastName, tempGender, tempPhoneNumber);
				mDatabase.insertUser(userModel);
				clearInputFieldsMethod();

				break;

			case R.id.add_user_fragment_button_overview:

				mOnDataReadListener.onDataRead(mDatabase);

				break;

			}

		}
	};

	private String getGenderFromRadioGroup() {
		int checkedRadioButtonId = mGenderRadioGroup.getCheckedRadioButtonId();

		switch (checkedRadioButtonId) {
		case R.id.add_user_fragment_radio_button_male:
			return "musko";

		case R.id.add_user_fragment_radio_button_female:
			return "zensko";
		}

		return null;
	}

	private UserModel createUser(String firstName, String lastName, String gender, String phoneNumber) {

		UserModel userModel = new UserModel();
		userModel.setFirstName(firstName);
		userModel.setLastName(lastName);
		userModel.setGender(gender);
		userModel.setPhoneNumber(phoneNumber);
		return userModel;

	}

	private void clearInputFieldsMethod() {
		mFirstNameEditText.setText("");
		mLastNameEditText.setText("");
		mGenderRadioGroup.clearCheck();
		mPhoneNumberEditText.setText("");

	}

}
