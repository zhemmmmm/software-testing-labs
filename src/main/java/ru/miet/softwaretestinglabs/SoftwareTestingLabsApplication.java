package ru.miet.softwaretestinglabs;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import ru.miet.softwaretestinglabs.impl.CalculatorViewImpl;

import javax.swing.*;

@SpringBootApplication
public class SoftwareTestingLabsApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(SoftwareTestingLabsApplication.class)
                .headless(false).run(args);

        SwingUtilities.invokeLater(() -> {
            CalculatorViewImpl view = ctx.getBean(CalculatorViewImpl.class);
            view.setVisible(true);
        });
    }
}