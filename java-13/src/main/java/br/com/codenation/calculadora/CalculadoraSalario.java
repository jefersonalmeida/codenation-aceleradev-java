package br.com.codenation.calculadora;


public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salary) {
		if (salary < 1039.00) return 0;
		double inss = calcularINSS(salary);
		double irrf = calcularIRRF(salary - inss);
		return Math.round(salary - (inss + irrf));
	}


	private double calcularINSS(double salary) {
		double percent = 0.08;
		if (salary > 1500 && salary <= 4000) {
			percent = 0.09;
		} else if (salary > 4000) {
			percent = 0.11;
		}
		return salary * percent;
	}

	private double calcularIRRF(double salary) {
		if (salary <= 3000) return 0;
		double percent = salary <= 6000 ? 0.075 : 0.15;
		return salary * percent;
	}
}