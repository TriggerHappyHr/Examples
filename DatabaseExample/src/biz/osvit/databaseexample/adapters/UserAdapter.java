package biz.osvit.databaseexample.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import biz.osvit.databaseexample.R;
import biz.osvit.databaseexample.models.UserModel;
import biz.osvit.databaseexample.models.UserModelWrapper;

public class UserAdapter extends BaseAdapter {

	private Context mContext;

	private ArrayList<UserModel> mUserList;

	private ViewHolder mViewHolder;

	public UserAdapter(Context context, UserModelWrapper wrapper) {
		mContext = context;
		mUserList = wrapper.getUsers();
	}

	@Override
	public int getCount() {
		return mUserList.size();
	}

	@Override
	public UserModel getItem(int position) {
		return mUserList.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.list_view_user_row, parent, false);
			mViewHolder = new ViewHolder();
			mViewHolder.mFirstNameTextView = (TextView) convertView.findViewById(R.id.list_view_first_name);
			mViewHolder.mLastNameTextView = (TextView) convertView.findViewById(R.id.list_view_last_name);
			mViewHolder.mGenderTextView = (TextView) convertView.findViewById(R.id.list_view_gender);
			mViewHolder.mPhoneNumberTextView = (TextView) convertView.findViewById(R.id.list_view_phone_number);
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (ViewHolder) convertView.getTag();
		}

		UserModel user = getItem(position);
		mViewHolder.mFirstNameTextView.setText(user.getFirstName());
		mViewHolder.mLastNameTextView.setText(user.getLastName());
		mViewHolder.mGenderTextView.setText(user.getGender());
		mViewHolder.mPhoneNumberTextView.setText(user.getPhoneNumber());

		return convertView;

	}

	private static class ViewHolder {
		private TextView mFirstNameTextView;
		private TextView mLastNameTextView;
		private TextView mGenderTextView;
		private TextView mPhoneNumberTextView;
	}

}
