package biz.osvit.asynchttpexample.models;

import com.google.gson.annotations.SerializedName;

public class VodostajModel {

	@SerializedName(value = "datum_sat")
	private String datum;

	@SerializedName(value = "vodostaj")
	private String vodostaj;

	public String getDatum() {
		return datum;
	}

	public String getVodostaj() {
		return vodostaj;
	}

}
