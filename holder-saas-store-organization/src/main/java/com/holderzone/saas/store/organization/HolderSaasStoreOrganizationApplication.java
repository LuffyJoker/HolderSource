package com.holderzone.saas.store.organization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class HolderSaasStoreOrganizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(HolderSaasStoreOrganizationApplication.class, args);
    }

}
