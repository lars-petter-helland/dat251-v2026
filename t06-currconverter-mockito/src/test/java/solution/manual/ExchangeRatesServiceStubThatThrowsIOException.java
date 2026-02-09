package solution.manual;

import solution.ExchangeRate;
import solution.ExchangeRatesService;

import java.io.IOException;

public class ExchangeRatesServiceStubThatThrowsIOException implements
		ExchangeRatesService {

	@Override
	public ExchangeRate getRate(String source, String target) throws IOException {
		throw new IOException();
	}
}
