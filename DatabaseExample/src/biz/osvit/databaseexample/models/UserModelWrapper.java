package biz.osvit.databaseexample.models;

import java.util.ArrayList;

public class UserModelWrapper {

	private ArrayList<UserModel> users = new ArrayList<UserModel>();

	// tu je problem???? prije je bio private List<UserModel> users;

	public ArrayList<UserModel> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<UserModel> users) {
		this.users = users;
	}

	public void addUser(UserModel user) {
		users.add(user);
	}

}
