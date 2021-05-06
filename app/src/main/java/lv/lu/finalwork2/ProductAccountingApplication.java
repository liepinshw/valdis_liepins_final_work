package lv.lu.finalwork2;

import lv.lu.finalwork2.config.AppConfiguration;
import lv.lu.finalwork2.ui.ConsulUI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ProductAccountingApplication {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ConsulUI consulUI = context.getBean(ConsulUI.class);
        consulUI.startUi();
    }

}
