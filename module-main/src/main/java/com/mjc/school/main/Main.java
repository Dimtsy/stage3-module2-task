package com.mjc.school.main;

import com.mjc.school.controller.menu.MenuHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ProgramConfig.class);
        MenuHelper helper = context.getBean(MenuHelper.class);
        helper.runner();
    }
}
