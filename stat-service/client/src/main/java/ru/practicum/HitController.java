package ru.practicum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.dtos.HitDto;
import ru.practicum.dtos.HitForStatDto;
import ru.practicum.model.Hit;
import ru.practicum.service.HitServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class HitController {
    private final HitServiceImpl service;

    @PostMapping("hit")
    public String addHit(@RequestBody HitDto hitDto) {
        log.info("Обработка запроса addHit начата: {}",hitDto);
        String response = service.addHit(hitDto);
        log.info("Результат запроса: {}",response);
        return response;
    }

    @GetMapping("stats")
    public List<HitForStatDto> getStatistics(@RequestParam
                                                @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                LocalDateTime start,
                                             @RequestParam
                                                @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                LocalDateTime end,
                                             @RequestParam(defaultValue = "false") Boolean unique,
                                             @RequestParam(required = false) List<String> uris) {
        log.info("Запрос getStatistics начат с параметрами: \n" +
                "start - {};\n" +
                "end - {};\n" +
                "unique - {};\n" +
                "uris - {};", start,end,unique,uris);
        List<HitForStatDto> statistics = service.getStatistics(start,end,unique,uris);
        log.info("Результат запроса getStatistics: {}", statistics);
        return statistics;
    }
}
