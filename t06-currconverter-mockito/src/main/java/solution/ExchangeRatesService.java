package solution;

import java.io.IOException;

public interface ExchangeRatesService {

	ExchangeRate getRate(String source, String target) throws IOException;

}