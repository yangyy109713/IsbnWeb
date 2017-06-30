package test.selenium.junit;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	LoginTest.class,
		AddIsbnTest.class,
	IsbnBookTest.class

})

public class IsbnJUnitSuite {
	
}
