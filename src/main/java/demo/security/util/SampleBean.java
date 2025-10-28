package demo.security.util;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class SampleBean {
	@Resource
	private LanguageToLocaleConverter languageToLocaleConverter; // pb line

	public String myFunction() {
		// logic
		var result = "some result";
		var a = 10;
		var b = 20;
		return languageToLocaleConverter.toString();
	}

}
