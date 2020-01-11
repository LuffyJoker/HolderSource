package com.peng.springbootstudytest;

import com.holderzone.holder.saas.store.dto.dto.organization.BrandDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringBootStudyTestApplication {

    public static void main(String[] args) {
        BrandDTO brandDTO = new BrandDTO();
        log.info("品牌：{}", brandDTO);
        SpringApplication.run(SpringBootStudyTestApplication.class, args);
    }

}
