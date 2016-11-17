package javaonela2015;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Turma {

	private List<Aluno> turma = new ArrayList<>();
	
	public void adicionar(Aluno a) {
		turma.add(a);
	}
	
	public boolean temAprovado() {
		return turma.stream().anyMatch(Aluno::isAprovado);
	}
	
	public boolean temReprovado() {
		return turma.stream().anyMatch(Aluno::isReprovado);
	}
	
	public List<Aluno> getAprovados() {
		return turma.stream().filter(Aluno::isAprovado).collect(toList());
	}
	
	public List<Aluno> getReprovados() {
		return turma.stream().filter(Aluno::isReprovado).collect(toList());
	}
	
	public List<String> getNomesAlunos() {
		return turma.stream().map(Aluno::getNome).collect(toList());
	}
	
	public double calcularMediaTurma() {
		return turma.stream().mapToDouble(Aluno::getMedia).average().getAsDouble();
	}
	
	public double getMenorMedia() {
		return turma.stream().mapToDouble(Aluno::getMedia).min().getAsDouble();
	}
	
	public double getMaiorMedia() {
		return turma.stream().mapToDouble(Aluno::getMedia).max().getAsDouble();
	}
	
	public Aluno getMelhorAluno() {
		return turma.stream().max((a1, a2) -> Double.compare(a1.getMedia(), a2.getMedia())).get();
	}
	
	public Aluno getPiorAluno() {
		return turma.stream().min((a1, a2) -> Double.compare(a1.getMedia(), a2.getMedia())).get();
	}
	
	public double calcularPercentualAprovacao() {
		return ((double)getAprovados().size() / (double)turma.size()) * 100;
	}
	
	public Map<Sexo, List<Aluno>> getAlunosGroupBySexo() {
		return turma.stream().collect(Collectors.groupingBy(Aluno::getSexo));
	}

	public Map<Situacao, List<Aluno>> getAlunosGroupBySituacao() {
		return turma.stream().collect(Collectors.groupingBy(Aluno::getSituacao));
	}
	
	public Map<Boolean, List<Aluno>> getAlunosParticionado() {
		return turma.stream().collect(Collectors.partitioningBy(Aluno::isAprovado));
	}
}
