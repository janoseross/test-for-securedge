package securedge.backend.model;

import lombok.Getter;

public class ExchangeRatesMessage {
	
	public ExchangeRatesMessage(double usd, double eur, double gbp) {
		this.usd = usd;
		this.eur = eur;
		this.gbp = gbp;
	}
	
	private double usd;
	
	private double eur;

	private double gbp;
	
	public double getUsd() {
		return usd;
	}


	public double getEur() {
		return eur;
	}


	public double getGbp() {
		return gbp;
	}


	
}
