package edu.unbosque.adiana.config;

import edu.unbosque.adiana.client.ClientStorageAdapter;
import edu.unbosque.adiana.contract.ContractAcceptanceStorageAdapter;
import edu.unbosque.adiana.contract.ContractService;
import edu.unbosque.adiana.contract.ContractServiceImpl;
import edu.unbosque.adiana.contract.ContractStorageAdapter;
import edu.unbosque.adiana.security.AuthenticatorAdapter;
import edu.unbosque.adiana.security.AuthenticatorService;
import edu.unbosque.adiana.security.AuthenticatorServiceImpl;
import edu.unbosque.adiana.security.wrapper.DotenvWrapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class SpringConfiguration {

	@Bean
	public DotenvWrapper dotenvWrapper() {
		return new DotenvWrapper();
	}

	// Services

	@Bean
	public AuthenticatorService authenticatorService(
		final Environment environment,
		final ClientStorageAdapter storage,
		final AuthenticatorAdapter auth
	) {
		return new AuthenticatorServiceImpl(environment, storage, auth);
	}

	@Bean
	public ContractService contractService(
		final ContractAcceptanceStorageAdapter contractAccStorage,
		final ContractStorageAdapter contractStorage
	) {
		return new ContractServiceImpl(contractAccStorage, contractStorage);
	}
}
