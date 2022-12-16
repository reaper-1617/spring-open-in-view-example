package com.gerasimchuk.openinviewexample.service;

import com.gerasimchuk.openinviewexample.domain.SampleEntity;
import com.gerasimchuk.openinviewexample.domain.SampleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SampleService {

    private final SampleRepository sampleRepository;

    @Transactional(readOnly = true)
    public List<SampleEntity> findAll(int n) {
        return sampleRepository.findAll(PageRequest.of(0, n)).getContent();
    }

    @EventListener(value = ContextRefreshedEvent.class)
    public void fillDatabase(){
        int n = 1000000;
        log.info("Generating {} entities ... ", n);
        List<SampleEntity> entities = new ArrayList<>(n);
        for(int i = 0; i < n; i++){
            var sample = new SampleEntity();
            sample.setSample(UUID.randomUUID().toString());
            entities.add(sample);
        }
        log.info("Saving generated entities ...");
        sampleRepository.saveAll(entities);
        log.info("Entities saved successfully");
        entities.clear();
    }
}
