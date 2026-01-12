package com.loose.ioc;

public class UserManager {
    private final UserDataProvider userDataProvider;

    public UserManager(UserDataProvider userDataProvider) {
        this.userDataProvider = userDataProvider;
    }

    public String getUserInfo() {
        return userDataProvider.getUserDetails();
    }
}
