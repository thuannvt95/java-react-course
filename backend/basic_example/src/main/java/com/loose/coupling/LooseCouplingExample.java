package com.loose.coupling;

public class LooseCouplingExample {
    public static void main(String[] args) {
        UserDataProvider userDataProvider = new UserDatabaseProvider();
        UserManager userManagerWithDB = new UserManager(userDataProvider);
        System.out.println(userManagerWithDB.getUserInfo());

        UserDataProvider userDataProviderWs = new WebServiceProvider();
        UserManager userManagerWs = new UserManager(userDataProviderWs);
        System.out.println(userManagerWs.getUserInfo());
    }
}
