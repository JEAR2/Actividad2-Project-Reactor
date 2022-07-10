package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.ReactiveRepository;
import io.pivotal.literx.repository.ReactiveUserRepository;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

/**
 * Learn how to control the demand.
 *
 * @author Sebastien Deleuze
 */
public class Part06Request {

	ReactiveRepository<User> repository = new ReactiveUserRepository();

//========================================================================================

	// TODO Create a StepVerifier that initially requests all values and expect 4 values to be received
	StepVerifier requestAllExpectFour(Flux<User> flux) {
		return  StepVerifier.create(flux).thenRequest(4).expectNextCount(4).expectComplete();
		//return null;
	}

//========================================================================================

	// TODO Create a StepVerifier that initially requests 1 value and expects User.SKYLER then requests another value and expects User.JESSE then stops verifying by cancelling the source
	StepVerifier requestOneExpectSkylerThenRequestOneExpectJesse(Flux<User> flux) {
		return  StepVerifier.create(flux)
				.thenRequest(1).expectNextMatches(user->user.equals(User.SKYLER))
				.thenRequest(1).expectNextMatches(user->user.equals(User.JESSE))
				.thenCancel();

	}

//========================================================================================

	// TODO Return a Flux with all users stored in the repository that prints automatically logs for all Reactive Streams signals
	Flux<User> fluxWithLog() {
		Flux<User> result = repository.findAll().log();
		return result;
	}

//========================================================================================

	// TODO Return a Flux with all users stored in the repository that prints "Starring:" on subscribe, "firstname lastname" for all values and "The end!" on complete
	Flux<User> fluxWithDoOnPrintln() {
		Flux<User> result = repository.findAll().doOnSubscribe(element-> System.out.println("Starring:")).doOnNext(user-> System.out.println(user.getFirstname()+" "+user.getLastname())).doOnComplete(()-> System.out.println("The end!"));

		return result;
	}

}
