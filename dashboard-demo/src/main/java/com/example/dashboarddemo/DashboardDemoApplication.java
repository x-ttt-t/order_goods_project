package com.example.dashboarddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class DashboardDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DashboardDemoApplication.class, args);
    }

}
