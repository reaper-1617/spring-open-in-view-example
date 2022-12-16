package com.gerasimchuk.openinviewexample.controller;

import com.gerasimchuk.openinviewexample.service.SampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class TestController {

    private final SampleService sampleService;

    @Value("${spring.jpa.open-in-view}")
    private boolean openInViewEnabled;

    @PostMapping("/test")
    public long doTest(@RequestParam Integer limit) {
        log.info("Test with open-in-view {}", openInViewEnabled ? "enabled" : "disabled");
        var millisBefore = System.currentTimeMillis();
        log.info("Fetching entries ...");
        var result = sampleService.findAll(limit);
        log.info("{} entries fetched", result.size());
        var millisAfter = System.currentTimeMillis();
        log.info("Time: {}", millisAfter - millisBefore);
        return millisAfter - millisBefore;
    }

}
