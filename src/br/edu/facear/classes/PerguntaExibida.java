/*Faculdade Educacional Araucária
 * Karl August Harder
 * Projeto Integrador II
 * Prof.: Rodrigo B. Marcondes
 */

package br.edu.facear.classes;

import java.util.List;
import java.util.ArrayList;
import br.edu.facear.util.Arquivo;

public class PerguntaExibida {

	private int QtdPergunta;
	private Pergunta pergunta;
	private Jogo partida;

	public int getQtdPergunta() {
		return this.QtdPergunta;
	}

	public void setQtdPergunta(int qtdPergunta) {
		this.QtdPergunta = qtdPergunta;
	}

	public Pergunta getPergunta() {
		return this.pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public Jogo getPartida() {
		return partida;
	}

	public void setPartida(Jogo partida) {
		this.partida = partida;
	}

	public PerguntaExibida() {
	}

	public PerguntaExibida(int qtdPergunta, Pergunta pergunta, Categoria categoria) {
		setQtdPergunta(qtdPergunta);
		setPergunta(pergunta);
	}

	public List<PerguntaExibida> Ler() {

		Arquivo arquivo = new Arquivo();

		arquivo.setNome("PerguntaExibida.txt");

		List<String> lista = arquivo.ler();
		List<PerguntaExibida> listaPerguntaExibida = new ArrayList<>();

		String[] dados = { " " };

		for (String linha : lista) {

			dados = linha.split(";");

			PerguntaExibida perguntaExibida = new PerguntaExibida();

			int IDPartida = Integer.parseInt(dados[0]);
			Jogo partida = new Jogo();
			partida.setID(IDPartida);
			perguntaExibida.setPartida(partida);

			Pergunta pergunta = new Pergunta();
			pergunta.setID(Integer.parseInt(dados[2]));

			Categoria cat = new Categoria();
			cat.setID(Integer.parseInt(dados[1]));
			pergunta.setCategoria(cat);

			perguntaExibida.setPergunta(pergunta);

			listaPerguntaExibida.add(perguntaExibida);

		}

		return listaPerguntaExibida;

	}

	public void gravar() {

		String linha = this.partida.getID() + ";" + this.pergunta.getCategoria().getID() + ";" + this.pergunta.getID();

		Arquivo arquivo = new Arquivo();
		arquivo.setNome("PerguntaExibida.txt");
		arquivo.setTexto(linha);
		arquivo.gravar();

	}

	public void atualizar() {

		String linha = this.partida.getID() + ";" + this.pergunta.getCategoria().getID() + ";" + this.pergunta.getID();

		Arquivo arquivo = new Arquivo();
		arquivo.setNome("PerguntaExibida.txt");
		arquivo.setTexto(linha);
		arquivo.atualizar();

	}

	public void deletar(Jogo jogo) {

		boolean primeira = true;
		List<PerguntaExibida> listaPerguntaExibida = this.Ler();
		for (PerguntaExibida pergE : listaPerguntaExibida) {
			
			if(pergE.getPartida().getID() != jogo.getID()) {
				
				PerguntaExibida perguntaExibida = new PerguntaExibida();
				perguntaExibida.setPartida(pergE.getPartida());
				perguntaExibida.setPergunta(pergE.getPergunta());
				
				if(primeira) {
					perguntaExibida.atualizar();
					primeira = false;
				}else
					perguntaExibida.gravar();
				
			}
		}

	}

}
