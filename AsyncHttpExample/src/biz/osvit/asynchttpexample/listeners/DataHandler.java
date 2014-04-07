package biz.osvit.asynchttpexample.listeners;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class DataHandler {

	public static void getDataFromBackend(String data, final OnDataLoadedListener dataLoadedListener) {

		AsyncHttpClient asyncClient = new AsyncHttpClient();
		asyncClient.get(data, new AsyncHttpResponseHandler() {

			@Override
			public void onSuccess(int statusCode, Header[] header, byte[] data) {
				super.onSuccess(statusCode, header, data);
				String tempData = new String(data);
				dataLoadedListener.onDataLoadedLoaded(tempData);
			}

			@Override
			public void onFailure(int statusCode, Header[] arg1, byte[] arg2, Throwable arg3) {
				// TODO Auto-generated method stub
				super.onFailure(statusCode, arg1, arg2, arg3);
			}

		});

	}

}
