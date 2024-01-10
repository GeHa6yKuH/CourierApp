package com.bogdan.courierapp.controller.page;

import com.bogdan.courierapp.entity.Courier;
import com.bogdan.courierapp.service.inter.TestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final TestService testService;
    @GetMapping("/getcr/{id}")
    public Courier getCourierById(@PathVariable("id") String id){
        return testService.getCourierById(id);
    }
}
