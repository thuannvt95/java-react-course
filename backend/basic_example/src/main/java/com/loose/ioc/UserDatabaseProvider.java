package com.loose.ioc;

public class UserDatabaseProvider implements UserDataProvider {
    @Override
    public String getUserDetails() {
        return "User Details from database";
    }
//    public String getUserDetail() {
//        return "User Details from database";
//    }

}
