package javaonela2015;

public class App {

	public static void main(String[] args) {
		Turma t = new Turma();
		t.adicionar(new Aluno("João", 5.0d, Sexo.MASCULINO));
		t.adicionar(new Aluno("Maria", 7.0d, Sexo.FEMININO));
		t.adicionar(new Aluno("Clara", 10.0d, Sexo.FEMININO));
		t.adicionar(new Aluno("Pedro", 8.5d, Sexo.MASCULINO));
		t.adicionar(new Aluno("Ana", 6.5d, Sexo.FEMININO));
		
		System.out.println("Tem aprovados? " + t.temAprovado());
		System.out.println("Tem reprovados? " + t.temReprovado());
		System.out.println("Aprovados: " + t.getAprovados());
		System.out.println("Reprovados " + t.getReprovados());
		System.out.println("Lista Alunos " + t.getNomesAlunos());
		System.out.println("Média da turma " + t.calcularMediaTurma());
		System.out.println("Menor média da turma " + t.getMenorMedia());
		System.out.println("Maior média da turma " + t.getMaiorMedia());
		System.out.println("Melhor aluno " + t.getMelhorAluno());
		System.out.println("Pior aluno " + t.getPiorAluno());
		System.out.println("% de aproveitamento da turma " + t.calcularPercentualAprovacao());
		System.out.println("Agrupamento por situacao " + t.getAlunosGroupBySituacao());
		System.out.println("Agrupamento por sexo " + t.getAlunosGroupBySexo());
		System.out.println("Particionado " + t.getAlunosParticionado());
		
		
	}
}
