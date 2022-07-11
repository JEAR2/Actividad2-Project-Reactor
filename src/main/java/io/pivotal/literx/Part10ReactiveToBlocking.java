package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Learn how to turn Reactive API to blocking one.
 *
 * @author Sebastien Deleuze
 */
public class Part10ReactiveToBlocking {

//========================================================================================

	// TODO Return the user contained in that Mono
	User monoToValue(Mono<User> mono) {
		//User user = mono.map(elemnet->new User(elemnet.getUsername(),elemnet.getFirstname(),elemnet.getLastname())).block();
		User user = mono.block();
		return user;
	}

//========================================================================================

	// TODO Return the users contained in that Flux
	Iterable<User> fluxToValues(Flux<User> flux) {
		//Iterable<User> users = flux.map(user->new User(user.getUsername(),user.getFirstname(),user.getLastname())).toIterable();
		Iterable<User> users = flux.toIterable();
		return users;
	}

}
