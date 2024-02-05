package com.bogdan.courierapp.controller.page;

import com.bogdan.courierapp.dto.CourierDto;
import com.bogdan.courierapp.dto.CourierUpdate;
import com.bogdan.courierapp.entity.Courier;
import com.bogdan.courierapp.service.inter.TestService;
import com.bogdan.courierapp.validation.UUIDChecker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/courier")
@Tag(description = "courier managing controller", name = "courier-controller")
public class CourierController {

    private final TestService testService;
    @GetMapping("/{id}")
    @Operation(summary = "basic courier get rest method by id")
    public Courier getCourierById(@UUIDChecker @PathVariable("id") String id){
        return testService.getCourierById(id);
    }

    @PostMapping
    public Courier createCourier(@RequestBody Courier courier) {
        return testService.createCourier(courier);
    }

    @PutMapping
    public ResponseEntity<String> updateCourierName(
            @RequestParam String id,
            @RequestParam String name) {
        testService.updateCourierName(id, name);
        return ResponseEntity.ok("Courier with ID " + id + " has been update name " + name);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") String courierId) {
        testService.deleteById(courierId);
        return ResponseEntity.ok("Courier with ID " + courierId + " has been deleted");
    }

    @PutMapping("/complex")
    public ResponseEntity<Courier> updateCourierMoreInfo(@RequestBody CourierUpdate courierUpdate) {
        Courier courier = testService.updateComplexCourier(courierUpdate);
        return ResponseEntity.ok(courier);
    }

    @GetMapping("/do/{id}")
    public CourierDto getCourierDtoById(@PathVariable("id") String id){
        return testService.getCourierDtoById(id);
    }

}
