package com.dio.HeroesAPI;

import com.dio.HeroesAPI.repository.HeroRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.dio.HeroesAPI.constants.HeroConstant.HEROES_ENDPOINT_LOCAL;

@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest
class HeroesApiApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	HeroRepository heroRepository;

	@Test
	public void shouldGetHeroWhenIdExist() {
		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "2")
						.exchange()
						.expectStatus().isOk()
						.expectBody();
	}

	@Test
	public void shouldReturnNotFoundWhenIdDoesNotExist() {
		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "9999")
						.exchange()
						.expectStatus().isNotFound();
	}

	@Test
	public void shouldDeleteHeroWhenIdExist() {
		webTestClient.delete().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "1")
						.exchange()
						.expectStatus().isOk()
						.expectBody(Void.class);
	}

}
