package test.selenium.util;

public class TestLog {
	public static void main(String[] args){
		Log log = new Log(TestLog.class);
		log.trace("trace level");
		log.debug("debug level");
		log.info("info level");
		log.warn("warn level");
		log.error("error level");
		log.fatal("fatal level");
	}
}
