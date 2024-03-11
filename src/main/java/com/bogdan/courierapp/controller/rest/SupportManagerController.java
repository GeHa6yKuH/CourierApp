package com.bogdan.courierapp.controller.rest;

import com.bogdan.courierapp.dto.SupportManagerDto;
import com.bogdan.courierapp.entity.SupportManager;
import com.bogdan.courierapp.service.inter.SupportManagerService;
import com.bogdan.courierapp.validation.UUIDChecker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/manager")
@Tag(description = "manager controller for app managers", name = "supportManager-controller")
public class SupportManagerController {

    private final SupportManagerService supportManagerService;

    @GetMapping("/{id}")
    @Operation(summary = "basic support manager get rest method by id",
            description = "returns a support manager from database for given id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "manager found"),
                    @ApiResponse(responseCode = "500", description = "no such manager")
            })
    public SupportManager getSupportManagerById(@UUIDChecker @PathVariable("id") String id){
        return supportManagerService.getSupportManagerById(id);
    }

    @GetMapping("/nc")
    public List<SupportManager> getManagersWithFirstOrLastName(@RequestParam String firstOrLastName){
        return supportManagerService.getListOfManagersAfterPattern(firstOrLastName);
    }

    @GetMapping("/fa")
    public List<SupportManager> getManagersWithName(@RequestParam String Name){
        return supportManagerService.getListOfManagersAfterName(Name);
    }

    @PostMapping
    public SupportManagerDto createSupportManagerDto(@RequestBody SupportManagerDto supportManagerDto){
        return supportManagerService.createSupportManagerDto(supportManagerDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") String managerId) {
        supportManagerService.deleteById(managerId);
        return ResponseEntity.ok("Courier with ID " + managerId + " has been deleted");
    }

}
