package solution.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Currency;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import solution.ExchangeRate;
import solution.ExchangeRatesService;
import solution.MyCurrencyConverter;

@ExtendWith(MockitoExtension.class)
public class MyCurrencyConverterTest {

	private static final double ACCEPTED_DELTA = 0.000001;

	private static final Currency NOK = Currency.getInstance("NOK");
	private static final Currency USD = Currency.getInstance("USD");

	@Mock
	private ExchangeRatesService service;

	@InjectMocks
	private MyCurrencyConverter currencyConverter;

	@Test
	void testBasicCurrencyConversion() throws IOException {

		//Arrange / Given
		when(service.getRate("NOK", "USD"))
				.thenReturn(new ExchangeRate(0.09));

		//Act / When
		double conversionResult = currencyConverter.convertAmount(500, NOK, USD);

		//Assert / Then
		assertEquals(45.0, conversionResult, ACCEPTED_DELTA);
	}

	@Test
	void testZeroIsReturnedWhenIOException() throws IOException {

		when(service.getRate(any(String.class), any(String.class)))
				.thenThrow(new IOException());

		assertEquals(0.0, currencyConverter.convertAmount(500, NOK, USD));
	}

	@Test
	void verifyThatGetRateWillBeCalledOnce() throws IOException {

		when(service.getRate(any(String.class), any(String.class)))
				.thenReturn(new ExchangeRate(0.09));

		currencyConverter.convertAmount(500, NOK, USD);

		verify(service, times(1)).getRate("NOK", "USD");
	}

}
