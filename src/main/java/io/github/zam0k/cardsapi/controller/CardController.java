package io.github.zam0k.cardsapi.controller;

import io.github.zam0k.cardsapi.config.Swagger2Config;
import io.github.zam0k.cardsapi.controller.dto.card.CardRequest;
import io.github.zam0k.cardsapi.controller.dto.card.CardResponse;
import io.github.zam0k.cardsapi.controller.dto.card.CardUpdateRequest;
import io.github.zam0k.cardsapi.service.CardService;
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
@RequestMapping("v1/cards")
@RequiredArgsConstructor
@Api(tags = {Swagger2Config.CARDS_TAG})
public class CardController {

    private final CardService service;

    @ApiOperation(value = "creates a new card")
    @ApiResponses({
            @ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 400, message = "BAD REQUEST")
    })
    @PostMapping()
    public ResponseEntity<CardResponse> create(@Valid @RequestBody CardRequest cardRequest) {

        CardResponse cardResponse = service.create(cardRequest);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(cardResponse.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @ApiOperation(value = "find all available cards")
    @GetMapping()
    public ResponseEntity<List<CardResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @ApiOperation(value = "finds a specific card")
    @ApiResponses({
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CardResponse> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @ApiOperation(value = "updates a specific card attributes")
    @ApiResponses({
            @ApiResponse(code = 204, message = "NO CONTENT"),
            @ApiResponse(code = 400, message = "BAD REQUEST"),
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CardResponse> update(@PathVariable("id") Long id,
                                               @Valid @RequestBody CardUpdateRequest cardUpdateRequest) {
        service.update(id, cardUpdateRequest);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "deletes a card")
    @ApiResponses({
            @ApiResponse(code = 204, message = "NO CONTENT"),
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<CardResponse> remove(@PathVariable("id") Long id) {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }
}
