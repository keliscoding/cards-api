package io.github.zam0k.cardsapi.service.impl;

import io.github.zam0k.cardsapi.controller.dto.origin.OriginRequest;
import io.github.zam0k.cardsapi.controller.dto.origin.OriginResponse;
import io.github.zam0k.cardsapi.controller.dto.origin.OriginUpdateRequest;
import io.github.zam0k.cardsapi.exception.NotFoundException;
import io.github.zam0k.cardsapi.model.Origin;
import io.github.zam0k.cardsapi.repository.OriginRepository;
import io.github.zam0k.cardsapi.service.OriginService;
import io.github.zam0k.cardsapi.util.OriginMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OriginServiceImpl implements OriginService {

    private final OriginRepository repository;

    @Override
    @Transactional
    public OriginResponse create(OriginRequest originRequest) {
        Origin origin = repository.save(OriginMapper.INSTANCE.originRequestToOrigin(originRequest));
        return OriginMapper.INSTANCE.originToOriginResponse(origin);
    }

    @Override
    public List<OriginResponse> findAll() {
        return OriginMapper.INSTANCE.originListToOriginResponseList(repository.findAll());
    }

    @Override
    public OriginResponse findById(Long id) {
        Origin origin = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Origin was not found for parameters {id=" + id + "}"));
        return OriginMapper.INSTANCE.originToOriginResponse(origin);
    }

    @Override
    @Transactional
    public OriginResponse update(Long id, OriginUpdateRequest originUpdateRequest) {
        Optional<Origin> originOptional = repository.findById(id);

        if (!originOptional.isPresent()) {
            throw new NotFoundException("Origin was not found for parameters {id=" + id + "}");
        }

        Origin origin = originOptional.get();
        origin = OriginMapper.INSTANCE.originUpdateRequestToOrigin(origin, originUpdateRequest);
        repository.save(origin);
        return OriginMapper.INSTANCE.originToOriginResponse(origin);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Origin was not found for parameters {id=" + id + "}");
        }
        repository.deleteById(id);
    }
}
