package service.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Results {
	
	private Quote[] quote;
	
	
	public Quote[] getQuote() {
		return quote;
	}
	
	public void setQuote(Quote[] quote) {
		this.quote = quote;
	}
	
	
}