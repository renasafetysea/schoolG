package ru.hogwarts.school.controller;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

@RestController
public class InfoController {
        @Autowired
        Environment environment;
        @GetMapping("port")
        public ResponseEntity<String> getPort() {

                return ResponseEntity.ok(environment.getProperty("local.server.port"));
        }
        @GetMapping("sum")
        public ResponseEntity<Long> fastTimeValue(){
                Long timeValue = System.currentTimeMillis();
                int sum = IntStream.range(1, 1000000).sum();
                Long fastTime = System.currentTimeMillis() - timeValue;
                return ResponseEntity.ok(fastTime);
        }
}
