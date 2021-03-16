package com.dio.HeroesAPI.controller;

import com.dio.HeroesAPI.document.Hero;
import com.dio.HeroesAPI.repository.HeroRepository;
import com.dio.HeroesAPI.service.HeroService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.dio.HeroesAPI.constants.HeroConstant.HEROES_ENDPOINT_LOCAL;

@RestController
@Slf4j
@AllArgsConstructor
public class HeroController {

  HeroService heroService;
  HeroRepository heroRepository;

  private static final org.slf4j.Logger logs =
          org.slf4j.LoggerFactory.getLogger(HeroController.class);

  @GetMapping(HEROES_ENDPOINT_LOCAL)
  public Flux<Hero> getAllHeroes() {
    logs.info("Requesting the list of all heroes");
    return heroService.findAll();
  }

  @GetMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
  public Mono<ResponseEntity<Hero>> findHeroById(@PathVariable String id) {
    logs.info("Requesting hero with ID {}.", id);
    return heroService.findById(id)
            .map((item) -> new ResponseEntity<>(item, HttpStatus.OK))
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping(HEROES_ENDPOINT_LOCAL)
  @ResponseStatus(code = HttpStatus.CREATED)
  public Mono<Hero> createHero(@RequestBody Hero hero) {
    logs.info("A new hero was created!");
    return heroService.save(hero);
  }

  @DeleteMapping(HEROES_ENDPOINT_LOCAL + "/{id}")
  @ResponseStatus(code = HttpStatus.OK)
  public Mono<HttpStatus> deleteHeroById(@PathVariable String id) {
    heroService.deleteById(id);
    logs.info("Hero with id {} was deleted.", id);
    return Mono.just(HttpStatus.OK);
  }

}
