package br.edu.facear.classes;

import java.util.ArrayList;
import java.util.List;

import br.edu.facear.util.*;;

public class PerguntaAvaliada {

	private Jogador jogador;
	private Pergunta pergunta;

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public PerguntaAvaliada() {
	}

	public PerguntaAvaliada(Jogador jogador, Pergunta pergunta) {
		this.jogador = jogador;
		this.pergunta = pergunta;
	}

	public boolean perguntaAvaliada() {

		return false;
	}

	public List<PerguntaAvaliada> ler() {
		
		Arquivo arquivo = new Arquivo();

		arquivo.setNome("PerguntaAvaliada.txt");

		List<String> lista = arquivo.ler();
		List<PerguntaAvaliada> listaPerguntaAvaliada = new ArrayList<>();

		String[] dados = { " " };

		for (String linha : lista) {
			
			PerguntaAvaliada perguntaAvaliada = new PerguntaAvaliada();

			dados = linha.split(";");
			Jogador jogador = new Jogador();
			jogador.setID(Integer.parseInt(dados[0]));
			perguntaAvaliada.setJogador(jogador);
			
			Pergunta pergunta = new Pergunta();
			pergunta.setID(Integer.parseInt(dados[1]));
			perguntaAvaliada.setPergunta(pergunta);
			
			listaPerguntaAvaliada.add(perguntaAvaliada);
			
		}
		
		return listaPerguntaAvaliada;
		
	}
	
	public void gravar() {

		String texto = this.jogador.getID() + ";" + this.pergunta.getID();

		Arquivo arquivo = new Arquivo();
		arquivo.setNome("PerguntaAvaliada.txt");
		arquivo.setTexto(texto);
		arquivo.gravar();

	}
	

}
