package com.gerasimchuk.openinviewexample.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<SampleEntity, Long> {

}
