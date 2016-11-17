package javaonela2015;

public class Aluno {
	
	private static double MEDIA = 7.0d;

	private String nome;
	
	private double media;
	
	private Situacao situacao;
	
	private Sexo sexo;
	
	public Aluno(String nome, double media, Sexo sexo) {
		this.nome = nome;
		this.media = media;
		this.sexo = sexo;
		this.situacao = isAprovado() ? Situacao.APROVADO : Situacao.REPROVADO;
			
	}

	public String getNome() {
		return nome;
	}

	public double getMedia() {
		return media;
	}

	public boolean isAprovado() {
		return media >= MEDIA;
	}
	
	public boolean isReprovado() {
		return !isAprovado();
	}
	
	public Situacao getSituacao() {
		return situacao;
	}

	public Sexo getSexo() {
		return sexo;
	}

	@Override
	public String toString() {
		return "Aluno [nome=" + nome + ", media=" + media + "]";
	}
	
}
