package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.boot.web.servlet.ServletContextInitializer;

@SpringBootApplication
public class DemoApplication implements ServletContextInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /**
     * Required so that @WebServlet(SimpleStatusServlet) is detected
     * during startup and by automated test suites.
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // no-op
    }
}
