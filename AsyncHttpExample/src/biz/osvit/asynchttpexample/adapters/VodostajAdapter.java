package biz.osvit.asynchttpexample.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import biz.osvit.asynchttpexample.R;
import biz.osvit.asynchttpexample.models.VodostajModel;
import biz.osvit.asynchttpexample.models.VodostajModelWrapper;

public class VodostajAdapter extends BaseAdapter {

	private Context mContext;
	private List<VodostajModel> mVodostajList;
	private ViewHolder mViewHolder;

	public VodostajAdapter(Context context, VodostajModelWrapper wrapper) {

		mContext = context;
		mVodostajList = wrapper.getVodostaji();

	}

	@Override
	public int getCount() {
		return mVodostajList.size();
	}

	@Override
	public VodostajModel getItem(int position) {
		return mVodostajList.get(position);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_vodostaj_list, parent, false);
			mViewHolder = new ViewHolder();
			mViewHolder.mDatumSatTextView = (TextView) convertView.findViewById(R.id.item_vodostaj_list_datum_sat_textview);
			mViewHolder.mVodostajTextView = (TextView) convertView.findViewById(R.id.item_vodostaj_list_vodostaj_textview);
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (ViewHolder) convertView.getTag();
		}

		VodostajModel vodostaj = getItem(position);
		mViewHolder.mDatumSatTextView.setText(vodostaj.getDatum());
		mViewHolder.mVodostajTextView.setText(vodostaj.getVodostaj());

		return convertView;
	}

	private static class ViewHolder {
		private TextView mDatumSatTextView;
		private TextView mVodostajTextView;
	}

}
