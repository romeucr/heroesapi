package com.dio.HeroesAPI.service;

import com.dio.HeroesAPI.document.Hero;
import com.dio.HeroesAPI.repository.HeroRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HeroService {
  private final HeroRepository heroRepository;

  public HeroService(HeroRepository heroRepository) {
    this.heroRepository = heroRepository;
  }

  public Flux<Hero> findAll() {
    return Flux.fromIterable(heroRepository.findAll());
  }

  public Mono<Hero> findById(String id) {
    return Mono.justOrEmpty(heroRepository.findById(id));
  }

  public Mono<Hero> save(Hero hero) {
    return Mono.justOrEmpty(heroRepository.save(hero));
  }

  public Mono<Boolean> deleteById(String id) {
    heroRepository.deleteById(id);
    return Mono.just(true);
  }
}
