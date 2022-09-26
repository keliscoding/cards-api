package io.github.zam0k.cardsapi.controller;

import io.github.zam0k.cardsapi.controller.dto.origin.OriginRequest;
import io.github.zam0k.cardsapi.controller.dto.origin.OriginResponse;
import io.github.zam0k.cardsapi.controller.dto.origin.OriginUpdateRequest;
import io.github.zam0k.cardsapi.service.OriginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/origins")
@RequiredArgsConstructor
@Api(tags = {"origins"})
public class OriginController {

    private final OriginService service;

    @ApiOperation(value = "creates a new origin")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 400, message = "BAD REQUEST")
    })
    @PostMapping()
    public ResponseEntity<OriginResponse> create(@Valid @RequestBody OriginRequest originRequest) {
        OriginResponse originResponse = service.create(originRequest);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(originResponse.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "find all available origins")
    @GetMapping()
    public ResponseEntity<List<OriginResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @ApiOperation(value = "finds a specific origin")
    @ApiResponses({
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    @GetMapping("/{id}")
    public ResponseEntity<OriginResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @ApiOperation(value = "updates a specific origin attributes")
    @ApiResponses({
            @ApiResponse(code = 204, message = "NO CONTENT"),
            @ApiResponse(code = 400, message = "BAD REQUEST"),
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<OriginResponse> update(@PathVariable("id") Long id,
                                                 @RequestBody OriginUpdateRequest originUpdateRequest) {
        service.update(id, originUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "deletes a origin")
    @ApiResponses({
            @ApiResponse(code = 204, message = "NO CONTENT"),
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<OriginResponse> remove(@PathVariable("id") Long id) {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }
}
