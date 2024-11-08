package edu.unbosque.adiana.config;

import edu.unbosque.adiana.client.ClientStorageAdapter;
import edu.unbosque.adiana.contract.ContractService;
import edu.unbosque.adiana.contract.ContractStorageAdapter;
import edu.unbosque.adiana.investor.InvestorService;
import edu.unbosque.adiana.investor.InvestorServiceImpl;
import edu.unbosque.adiana.investor.InvestorStorageAdapter;
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
	public InvestorService investorService(
		final Environment environment,
		final ClientStorageAdapter clientStorage,
		final InvestorStorageAdapter investorStorage
	) {
		return new InvestorServiceImpl(clientStorage, investorStorage, environment);
	}

	@Bean
	public ContractService contractService(
		final InvestorStorageAdapter investorStorage,
		final ContractStorageAdapter contractStorage
	) {
		return new ContractServiceImpl(investorStorage, contractStorage);
	}

}
