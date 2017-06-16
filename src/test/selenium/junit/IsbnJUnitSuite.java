package test.selenium.junit;

import org.junit.runners.Suite;
import org.junit.runner.RunWith;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	LoginTest.class,
	IsbnBookTest.class,
	AddIsbnTest.class
})

public class IsbnJUnitSuite {
	
}
