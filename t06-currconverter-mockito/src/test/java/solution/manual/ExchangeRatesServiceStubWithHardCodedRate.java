package solution.manual;

import solution.ExchangeRate;
import solution.ExchangeRatesService;

import java.io.IOException;

public class ExchangeRatesServiceStubWithHardCodedRate implements ExchangeRatesService {

	@Override
	public ExchangeRate getRate(String source, String target) throws IOException {
		ExchangeRate exRate = new ExchangeRate();
		
		if (source.equals("NOK") && target.equals("USD")) {
			exRate.rate = 1.0/8.3;
		}
		
		return exRate;
	}
}
