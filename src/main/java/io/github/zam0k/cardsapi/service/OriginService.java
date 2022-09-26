package io.github.zam0k.cardsapi.service;

import io.github.zam0k.cardsapi.controller.dto.origin.OriginRequest;
import io.github.zam0k.cardsapi.controller.dto.origin.OriginResponse;
import io.github.zam0k.cardsapi.controller.dto.origin.OriginUpdateRequest;

import java.util.List;

public interface OriginService {
    OriginResponse create(OriginRequest originRequest);

    List<OriginResponse> findAll();

    OriginResponse findById(Long id);

    OriginResponse update(Long id, OriginUpdateRequest originUpdateRequest);

    void remove(Long id);
}
