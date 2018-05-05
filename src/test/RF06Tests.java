package test;

import clase.Asistente;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RF06Tests {

	public final static String USUARIO = "delucas";

	Asistente jenkins;

	@Before
	public void setup() {
		jenkins = new Asistente("jenkins");
	}

	@Test
	public void calculos() {
		Assert.assertEquals(
				"@delucas 3",
				jenkins.escuchar("@jenkins cuánto es 1 + 2")
			);
		
		Assert.assertEquals(
				"@delucas -5",
				jenkins.escuchar("@jenkins cuánto es 3 - 8")
			);
		
		Assert.assertEquals(
				"@delucas 7",
				jenkins.escuchar("@jenkins cuánto es 3 + 8 / 2")
			);
		
		Assert.assertEquals(
				"@delucas 1024",
				jenkins.escuchar("@jenkins cuánto es 2^10")
			);
		
		Assert.assertEquals(
				"@delucas -5",
				jenkins.escuchar("@jenkins cuánto es 3 - 8")
			);
		
		Assert.assertEquals(
				"@delucas 1",
				jenkins.escuchar("@jenkins cuánto es 5 - 2 * 2")
			);
		
		Assert.assertEquals(
				"@delucas 10",
				jenkins.escuchar("@jenkins cuánto es el 10% de 100")
			);
		
		Assert.assertEquals(
				"@delucas 42",
				jenkins.escuchar("@jenkins cuánto es el 17 + 5 ^ 2")
			);

		
	}
	
	@Test
	public void calculosCompuestos() {
		Assert.assertEquals(
				"@delucas -6",
				jenkins.escuchar("@jenkins cuánto es (4-8)*2 + 4 / ( 1 + 1)")
			);

		Assert.assertEquals(
				"@delucas -38",
				jenkins.escuchar("@jenkins cuánto es 8*4 + 35 / 5 - (2 + 5 * 22) + 5 * ( 3 + 8 / 2 )")
			);
		
		Assert.assertEquals(
				"@delucas 44",
				jenkins.escuchar("@jenkins cuánto es 3 * 17 / ( 8 - 5 ^ 2 ) + (10 + 3*4) + 10% * 250")
			);
		
		Assert.assertEquals(
				"@delucas 713",
				jenkins.escuchar("@jenkins cuánto es 8 / 4 + 3 - 8 * 2 - 25 / 5 + 3 ^ ( 8 / 2 + 2 * (3 - 2) )")
			);
	
	}
	
	@Test
	public void calculosConDecimales() {
		Assert.assertEquals(
				"@delucas 6",
				jenkins.escuchar("@jenkins cuánto es 2.5 + 3.5")
			);
		
		Assert.assertEquals(
				"@delucas 71.5",
				jenkins.escuchar("@jenkins cuánto es 80 + 3 / 2 - 5 * 2")
			);
		
		Assert.assertEquals(
				"@delucas -128.25",
				jenkins.escuchar("@jenkins cuánto es  (10 * 10 - 1) - 2 ^ ( 2 ^ 2 + 4 ) + 3.5 + 101 / 4")
			);
		
		Assert.assertEquals(
				"@delucas " + (23.3 + 4.0 * 23.0 + 5.9 - Math.pow(2.0, 3.0) + 5.0 / 25.0 + 30.0 * 15.0 / 100.0 - 8.0),
				jenkins.escuchar("@jenkins cuánto es 23.3 + 4*23 + 5.9 - 2 ^ 3 + 5 / 25 + 30*15% - 8")
			);
		
		Assert.assertEquals(
				"@delucas " + (23.0 * 5.0 + 5.0 - Math.pow(2.0, 4.0) + 3.0 / 2.0),
				jenkins.escuchar("@jenkins cuánto es 23 * 5 + 5 - 2 ^ 4 + 3 / 2")
			);
		
		Assert.assertEquals(
				"@delucas " + (23.3 + 4.0 * 23.0 + 5.9 - Math.pow(2.0, 3.0) + 5.0 / 25.0 + 30.0 * 15.0 / 100.0 - 8.0),
				jenkins.escuchar("@jenkins cuánto es 23.3 + 4*23 + 5.9 - 2 ^ 3 + 5 / 25 + 30*15% - 8")
			);
	}
	
	public void calculosConNumerosNegativos() {
		Assert.assertEquals(
				"@delucas -29",
				jenkins.escuchar("@jenkins cuánto es - 8 * 3 - 5")
			);
		
		Assert.assertEquals(
				"@delucas 1",
				jenkins.escuchar("@jenkins cuánto es 3 * ( - 2 + 6 / 3 ) + 1")
			);
		
		Assert.assertEquals(
				"@delucas -2",
				jenkins.escuchar("@jenkins cuánto es  3 ^ 3 - 10 + (3 * (- 5 + 2)) - 10")
			);
	}

}
