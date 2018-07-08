package com.sb.inventory;

import com.sb.inventory.service.PropertyMgmt;
import com.sb.services.common.generator.ModelMapperGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication( scanBasePackages = { "com.sb" })
@RestController
@EnableScheduling
@EnableTransactionManagement
public class SBInventoryApp {

    public static void main(String[] args) {
        PropertyMgmt.loadProperties();
        ModelMapperGenerator.initialise();
        SpringApplication.run(SBInventoryApp.class, args);
    }

    @RequestMapping("/")
    public String index() {
        return "SupplyByte inventory Spring Boot Application";
    }
}
