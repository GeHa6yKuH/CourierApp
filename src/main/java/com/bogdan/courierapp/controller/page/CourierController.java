package com.bogdan.courierapp.controller.page;

import com.bogdan.courierapp.entity.Courier;
import com.bogdan.courierapp.service.inter.TestService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/test")
public class CourierController {

    private final TestService testService;
    @GetMapping("/getcr/{id}")
    public Courier getCourierById(@PathVariable("id") String id){
        return testService.getCourierById(id);
    }

    @PostMapping("/createCourier")
    public Courier createCourier(@RequestBody Courier courier) {
        return testService.createCourier(courier);
    }

    @PutMapping("/updateCourier/")
    public ResponseEntity<String> updateCourierName(
            @RequestParam String id,
            @RequestParam String name) {
        testService.updateCourierName(id, name);
        return ResponseEntity.ok("Courier with ID " + id + " has been update name " + name);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") String courierId) {
        testService.deleteById(courierId);
        return ResponseEntity.ok("Courier with ID " + courierId + " has been deleted");
    }

}
