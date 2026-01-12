package com.loose.coupling;

public class UserDatabaseProvider implements UserDataProvider {
    @Override
    public String getUserDetails() {
        return "User Details from database";
    }
//    public String getUserDetail() {
//        return "User Details from database";
//    }

}
