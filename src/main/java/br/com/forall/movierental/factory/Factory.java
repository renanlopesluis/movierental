package br.com.forall.movierental.factory;

import org.springframework.stereotype.Component;

@Component
public interface Factory <T, R>{

	R build(T object);
}
