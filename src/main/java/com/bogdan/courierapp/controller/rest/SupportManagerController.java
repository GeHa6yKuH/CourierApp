package com.bogdan.courierapp.controller.rest;

import com.bogdan.courierapp.dto.ErrorDto;
import com.bogdan.courierapp.dto.SupportManagerDto;
import com.bogdan.courierapp.entity.SupportManager;
import com.bogdan.courierapp.service.inter.SupportManagerService;
import com.bogdan.courierapp.validation.UUIDChecker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Tag(description = "controller for managing orders", name = "SupportManagerController")
public class SupportManagerController {

    private final SupportManagerService supportManagerService;

    // *****************************************getSupportManagerById()*****************************************

    @Operation(summary = "Retrieve support manager by ID",
            description = "Returns the support manager with the provided ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Support manager found",
                            content = {@Content(schema = @Schema(implementation = SupportManager.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "No support manager found with the provided ID",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500", description = "Internal server error occurred",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")})
            })
    @GetMapping("/{id}")
    public SupportManager getSupportManagerById(@UUIDChecker @PathVariable("id") String id) {
        return supportManagerService.getSupportManagerById(id);
    }

    // ****************************************getManagersWithFirstOrLastName()****************************************

    @Operation(summary = "Retrieve support managers by first or last name",
            description = "Returns a list of support managers whose first or last name matches the provided pattern",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful request",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SupportManager.class)),
                                    mediaType = "application/json")})
            })
    @GetMapping("/nc")
    public List<SupportManager> getManagersWithFirstOrLastName(@RequestParam String firstOrLastName) {
        return supportManagerService.getListOfManagersAfterPattern(firstOrLastName);
    }

    // *****************************************getManagersWithName()*****************************************

    @Operation(summary = "Retrieve support managers by name",
            description = "Returns a list of support managers whose name matches the provided name",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Support managers found",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = SupportManager.class)),
                                    mediaType = "application/json")})
            })
    @GetMapping("/fa")
    public List<SupportManager> getManagersWithName(@RequestParam String Name) {
        return supportManagerService.getListOfManagersAfterName(Name);
    }

    // *****************************************createSupportManagerDto()*****************************************

    @Operation(summary = "Create a new support manager",
            description = "Creates a new support manager based on the provided SupportManagerDto object",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Insert JSON format data according to SupportManagerDto class",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SupportManagerDto.class)
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "New support manager successfully created",
                            content = {@Content(schema = @Schema(implementation = SupportManagerDto.class),
                                    mediaType = "application/json")})
            }
    )
    @PostMapping
    public SupportManagerDto createSupportManagerDto(@RequestBody SupportManagerDto supportManagerDto) {
        return supportManagerService.createSupportManagerDto(supportManagerDto);
    }

    // *****************************************deleteById()*****************************************


    @Operation(summary = "Delete support manager by ID",
            description = "Deletes the support manager with the provided ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Support manager with provided ID successfully deleted",
                            content = {@Content(schema = @Schema(defaultValue = "Support manager successfully deleted"),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "404", description = "No support manager found with the provided ID",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500", description = "Internal server error occurred",
                            content = {@Content(schema = @Schema(implementation = ErrorDto.class),
                                    mediaType = "application/json")})
            }
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") String managerId) {
        supportManagerService.deleteById(managerId);
        return ResponseEntity.ok("Courier with ID " + managerId + " has been deleted");
    }

}
