package academy.devdojo.springboot2.controller;

import academy.devdojo.springboot2.domain.Anime;
import academy.devdojo.springboot2.requests.AnimePostRequestBody;
import academy.devdojo.springboot2.requests.AnimePutRequestBody;
import academy.devdojo.springboot2.service.AnimeService;
import academy.devdojo.springboot2.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {
    //Construtor
    private final DateUtil dateUtil;
    private final AnimeService animeService;

    //localhost:8080/anime/list
    @GetMapping
    public ResponseEntity<List<Anime>> list() {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(animeService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id) {
        return ResponseEntity.ok(animeService.findByidOrThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody AnimePostRequestBody animePostRequestBody){
        return new ResponseEntity<>(animeService.save(animePostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody AnimePutRequestBody animePutRequestBody) {
        animeService.replace(animePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
