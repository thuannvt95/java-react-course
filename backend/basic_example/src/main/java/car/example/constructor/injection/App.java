package car.example.constructor.injection;

import car.example.bean.MyBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(    "applicationContructionContext.xml");

        // Retrieve the bean by its ID and cast it to the correct class
        Car car = (Car) context.getBean("myCar");
        car.displayDetails();
    }
}
