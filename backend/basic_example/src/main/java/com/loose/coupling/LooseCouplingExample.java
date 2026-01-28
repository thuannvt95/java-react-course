package com.loose.coupling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LooseCouplingExample {

    public static void main(String[] args) {
        UserDataProvider userDataProvider = new UserDatabaseProvider();
        UserManager userManagerWithDB = new UserManager(userDataProvider);
        System.out.println(userManagerWithDB.getUserInfo());

        UserDataProvider userDataProviderWs = new WebServiceProvider();
        UserManager userManagerWs = new UserManager(userDataProviderWs);
        System.out.println(userManagerWs.getUserInfo());

        Map<Integer,String> repeatWord = new HashMap<>();
    }
    public static void getSubStringsList(String s, int k) {
        List<String> results = new ArrayList<>();
        Map<String, Integer> freq = new HashMap<>();

        for (int i = 0; i <= s.length() - k; i++) {
            // String subStr = s.substring(i, i + k);
            System.out.println(s.substring(i, i + k));
            freq.put(s.substring(i, i + k), freq.getOrDefault(s.substring(i, i + k), 0) + 1);
        }

        // return results;
    }

}
