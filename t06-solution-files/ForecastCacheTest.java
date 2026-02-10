package myapp.cache;

import myapp.domain.Forecast;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT) //To avoid errors when stubbing
												  //with different parameters
public class ForecastCacheTest {

	// --- Data values used in the tests -------------------------------------
	
		// ... Maybe you can set up some test data to be reused in the tests?


	// --- Instance variables ------------------------------------------------

	@Mock private ForecastFetcher mockedForecastFetcher;
	@Mock private TimestampService mockedTimestampService;

	@InjectMocks private ForecastCache forecastCache;

	// --- Test setup --------------------------------------------------------

	@BeforeEach
	public void setup() {
		
		// ... General setup ...
		
		// ... Maybe you eventually can refactor out default behavior for the 
		//     mocks, which can be overridden in test methods if needed?
	}

	// --- The tests ---------------------------------------------------------

    @Test
    public void secondRequestShouldReturnForcastFromYCacheDirectly() {
        Forecast fBergen = new Forecast("Bergen");
        //Arrange
        when(mockedForecastFetcher.fetchForecastFor("Bergen"))
                .thenReturn(fBergen);
        //Act
        Forecast fActual1 = forecastCache.getForecastFor("Bergen");
        Forecast fActual2 = forecastCache.getForecastFor("Bergen");
        //Assert
        assertSame(fBergen, fActual1);
        assertSame(fBergen, fActual2);

        verify(mockedForecastFetcher).fetchForecastFor("Bergen");
    }

	@Test
	public void firstRequestShouldFetchAndReturnForcastFromYr() {
        Forecast fBergen = new Forecast("Bergen");
        //Arrange
        when(mockedForecastFetcher.fetchForecastFor("Bergen"))
                .thenReturn(fBergen);
        //Act
        Forecast fActual = forecastCache.getForecastFor("Bergen");
        //Assert
        assertSame(fBergen, fActual);
	}
	
	// ... more tests ...

}