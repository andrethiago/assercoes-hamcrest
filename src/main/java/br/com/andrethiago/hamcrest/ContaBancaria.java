package br.com.andrethiago.hamcrest;

import java.math.BigDecimal;

public class ContaBancaria implements Comparable<ContaBancaria> {

	private String agencia;

	private String numero;

	private BigDecimal saldo;

	public ContaBancaria(String agencia, String numero, BigDecimal saldo) {
		super();
		this.agencia = agencia;
		this.numero = numero;
		this.saldo = saldo;
	}

	public String getAgencia() {
		return agencia;
	}

	public String getNumero() {
		return numero;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public int compareTo(ContaBancaria outra) {
		return this.getSaldo().compareTo(outra.getSaldo());
	}

}
