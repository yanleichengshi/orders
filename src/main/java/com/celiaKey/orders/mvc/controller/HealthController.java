package com.celiaKey.orders.mvc.controller;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class HealthController {
    @Resource
    private RestTemplate restTemplate;


    @PostMapping("/health")
    public String testOne() {
        ResponseEntity<JSONObject> resp = restTemplate.getForEntity("http://localhost:8081/orders/actuator/health", JSONObject.class);
        if (resp.getStatusCodeValue() != HttpStatus.HTTP_OK) {
            return StringUtils.EMPTY;
        }
        JSONObject components = resp.getBody().getJSONObject("components");
        JSONObject db = components.getJSONObject("db");
        JSONObject redis = components.getJSONObject("redis");

        return null;
    }
}
