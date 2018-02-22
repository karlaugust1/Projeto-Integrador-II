/*Faculdade Educacional Araucária
 * Karl August Harder
 * Projeto Integrador II
 * Prof.: Rodrigo B. Marcondes
 */

package br.edu.facear.classes;

import java.util.*;
import br.edu.facear.util.Arquivo;

public class Resposta {

	private int ID;
	private String Enunciado;
	private Pergunta pergunta;

	public int getID() {
		return this.ID;
	}

	public void setID(int id) {
		this.ID = id;
	}

	public String getEnunciado() {
		return this.Enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.Enunciado = enunciado;
	}

	public Pergunta getPergunta() {
		return this.pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public Resposta() {
	}

	public Resposta(int id, String enunciado, Pergunta pergunta) {
		this.ID = id;
		this.Enunciado = enunciado;
		this.pergunta = pergunta;
	}

	public void criar() {

		int IDAtual = 0;
		List<Resposta> listaResposta = this.ler();
		for (Resposta resposta : listaResposta) {
			IDAtual = resposta.getID() + 1;
		}

		this.ID = IDAtual;

		int IDAtualPergunta = 0;
		Pergunta pergunta = new Pergunta();
		List<Pergunta> listaPergunta = pergunta.ler();
		for (Pergunta perg : listaPergunta) {
			IDAtualPergunta = perg.getID() + 1;
		}
		pergunta.setID(IDAtualPergunta);
		this.pergunta = pergunta;

		this.gravar();

	}

	public void deletar() {
		// ID;ID_PERGUNTA;ALTERNATIVA;
		List<Resposta> listaResposta = this.ler();
		boolean primeira = true;
		int i = 0;
		for (i = 0; i < listaResposta.size(); i++) {
			if (listaResposta.get(i).getPergunta().getID() != this.pergunta.getID()) {
				Resposta resposta = new Resposta();

				resposta.setID(listaResposta.get(i).getID());

				Pergunta pergunta = new Pergunta();
				pergunta.setID(listaResposta.get(i).getPergunta().getID());
				resposta.setPergunta(pergunta);

				resposta.setEnunciado(listaResposta.get(i).getEnunciado());

				if (primeira) {
					resposta.atualizarLista();
					primeira = false;
				} else {
					resposta.gravar();
				}
			}
		}

	}

	public boolean autenticar() {
		
		Boolean correto = false;
		List<Resposta> listaResposta = this.ler();
		for(Resposta resp : listaResposta) {
			if(resp.getEnunciado().equals(this.Enunciado) && resp.getID() == this.pergunta.getAlternativaCorreta().getID()) {
				correto = true;
				break;
			}else {
				correto = false;
			}
		}
		return correto;
	}

	public List<Resposta> ler() {

		Arquivo arquivo = new Arquivo();

		arquivo.setNome("Respostas.txt");

		List<String> lista = arquivo.ler();
		List<Resposta> listaResposta = new ArrayList<>();

		String[] dados = { " " };

		for (String linha : lista) {

			dados = linha.split(";");

			Resposta resposta = new Resposta();

			resposta.setID(Integer.parseInt(dados[0]));

			int IDPergunta = Integer.parseInt(dados[1]);
			Pergunta pergunta = new Pergunta();
			pergunta.setID(IDPergunta);
			resposta.setPergunta(pergunta);

			resposta.setEnunciado(dados[2]);

			listaResposta.add(resposta);

		}
		return listaResposta;
	}

	public void gravar() {

		String linha = this.ID + ";" + this.pergunta.getID() + ";" + this.Enunciado;

		Arquivo arquivo = new Arquivo();
		arquivo.setNome("Respostas.txt");
		arquivo.setTexto(linha);
		arquivo.gravar();

	}

	public void atualizarLista() {

		String linha = this.ID + ";" + this.pergunta.getID() + ";" + this.Enunciado;

		Arquivo arquivo = new Arquivo();
		arquivo.setNome("Respostas.txt");
		arquivo.setTexto(linha);
		arquivo.atualizar();

	}
}
