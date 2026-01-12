package com.loose.ioc;

import car.example.bean.MyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LooseCouplingExample {
    public static void main(String[] args) {
//        UserDataProvider userDataProvider = new UserDatabaseProvider();
//        UserManager userManagerWithDB = new UserManager(userDataProvider);
//        System.out.println(userManagerWithDB.getUserInfo());
//
//        UserDataProvider userDataProviderWs = new WebServiceProvider();
//        UserManager userManagerWs = new UserManager(userDataProviderWs);
//        System.out.println(userManagerWs.getUserInfo());
        ApplicationContext context = new ClassPathXmlApplicationContext(    "applicationLooseCouplingContext.xml");

        // Retrieve the bean by its ID and cast it to the correct class
        UserManager userManagerWithDB = (UserManager) context.getBean("userManagerWithDB");
        System.out.println(userManagerWithDB.getUserInfo());


        UserManager userDataProviderWs = (UserManager) context.getBean("userManagerWithWS");
        System.out.println(userDataProviderWs.getUserInfo());
    }
}
