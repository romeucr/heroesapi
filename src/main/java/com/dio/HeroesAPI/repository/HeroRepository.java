package com.dio.HeroesAPI.repository;

import com.dio.HeroesAPI.document.Hero;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface HeroRepository extends CrudRepository<Hero, String> {
}
