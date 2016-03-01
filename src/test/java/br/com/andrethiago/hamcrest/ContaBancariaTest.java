package br.com.andrethiago.hamcrest;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ContaBancariaTest {

	@Test
	public void verificaOrdemCrescenteContas() {
		ContaBancaria conta01 = new ContaBancaria("0001", "1234-1", new BigDecimal(1500.00));
		ContaBancaria conta02 = new ContaBancaria("0001", "1234-2", new BigDecimal(10000.00));
		ContaBancaria conta03 = new ContaBancaria("0001", "1234-3", new BigDecimal(5000.00));

		List<ContaBancaria> contas = Arrays.asList(new ContaBancaria[] { conta01, conta02, conta03 });
		Collections.sort(contas);

		assertEquals(conta01, contas.get(0));
		assertEquals(conta03, contas.get(1));
		assertEquals(conta02, contas.get(2));
	}

	@Test
	public void verificaOrdemCrescenteContasComHamcrest() {
		ContaBancaria conta01 = new ContaBancaria("0001", "1234-1", new BigDecimal(1500.00));
		ContaBancaria conta02 = new ContaBancaria("0001", "1234-2", new BigDecimal(10000.00));
		ContaBancaria conta03 = new ContaBancaria("0001", "1234-3", new BigDecimal(5000.00));

		List<ContaBancaria> contas = Arrays.asList(new ContaBancaria[] { conta01, conta02, conta03 });
		Collections.sort(contas);

		assertThat(contas, contains(conta01, conta03, conta02));
	}

	@Test
	public void verificaSaldoContasSemHamcrest() {

		ContaBancaria conta01 = new ContaBancaria("0001", "1234-1", new BigDecimal(1500.00));
		ContaBancaria conta02 = new ContaBancaria("0001", "1234-2", new BigDecimal(10000.00));
		ContaBancaria conta03 = new ContaBancaria("0001", "1234-3", new BigDecimal(5000.00));

		List<ContaBancaria> contas = Arrays.asList(new ContaBancaria[] { conta01, conta02, conta03 });

		BigDecimal saldoMinimo = new BigDecimal(1000.00);

		// verifica que todas as contas pertencem a mesma agência e que o saldo
		// de todas as contas é maior que 1000.00
		for (ContaBancaria conta : contas) {
			assertEquals("0001", conta.getAgencia());
			assertTrue(conta.getSaldo().compareTo(saldoMinimo) == 1);
		}

	}

	@Test
	public void verificaSaldoContasComHamcrest() {

		ContaBancaria conta01 = new ContaBancaria("0001", "1234-1", new BigDecimal(1500.00));
		ContaBancaria conta02 = new ContaBancaria("0001", "1234-2", new BigDecimal(10000.00));
		ContaBancaria conta03 = new ContaBancaria("0001", "1234-3", new BigDecimal(5000.00));

		List<ContaBancaria> contas = Arrays.asList(new ContaBancaria[] { conta01, conta02, conta03 });

		BigDecimal saldoMinimo = new BigDecimal(1000.00);

		assertThat(contas, everyItem(Matchers.<ContaBancaria> hasProperty("agencia", equalTo("0001"))));
		assertThat(contas, everyItem(Matchers.<ContaBancaria> hasProperty("saldo", greaterThan(saldoMinimo))));

	}

}
