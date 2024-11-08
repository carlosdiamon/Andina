package edu.unbosque.adiana.database.operator.mapper;

import edu.unbosque.adiana.database.operator.entity.OperatorEntity;
import edu.unbosque.adiana.operator.Operator;
import edu.unbosque.adiana.operator.data.City;
import edu.unbosque.adiana.operator.data.Country;
import org.jetbrains.annotations.NotNull;

public final class OperatorMapper {

	public static Operator toOperator(final @NotNull OperatorEntity entity) {
		return new Operator(
			entity.getName(),
			entity.getMic(),
			new City( // TODO: Mejorable
				entity.getCity().getName(),
				new Country(
					entity.getCountry().getName(),
					entity.getCountry().getEconomicStatus()
				)
			),
			entity.getWebsite(),
			entity.getCurrencyCode().getSymbol(),
			entity.getCommissionRate()
		);
	}

}
