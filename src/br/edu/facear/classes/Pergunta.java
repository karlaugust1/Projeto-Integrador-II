/*Faculdade Educacional Araucária
 * Karl August Harder
 * Projeto Integrador II
 * Prof.: Rodrigo B. Marcondes
 */

package br.edu.facear.classes;

import java.util.*;
import br.edu.facear.util.Arquivo;

public class Pergunta {

	private int ID;
	private int Avaliacoes;
	private String Enunciado;
	private Categoria categoria;
	private Jogador autor;
	private Resposta alternativaCorreta;

	public int getID() {
		return this.ID;
	}

	public void setID(int id) {
		this.ID = id;
	}

	public int getAvaliacoes() {
		return this.Avaliacoes;
	}

	public void setAvaliacoes(int avaliacoes) {
		this.Avaliacoes = avaliacoes;
	}

	public String getEnunciado() {
		return this.Enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.Enunciado = enunciado;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Jogador getAutor() {
		return this.autor;
	}

	public void setAutor(Jogador autor) {
		this.autor = autor;
	}

	public Resposta getAlternativaCorreta() {
		return this.alternativaCorreta;
	}

	public void setAlternativaCorreta(Resposta alternativa) {
		this.alternativaCorreta = alternativa;
	}

	public Pergunta() {
		this.autor = new Jogador();
	}

	public Pergunta(int id, int avaliacoes, String enunciado, Categoria categoria, Jogador autor,
			Resposta alternativa) {
		setID(id);
		setAvaliacoes(avaliacoes);
		setEnunciado(enunciado);
		setCategoria(categoria);
		setAutor(autor);
		setAlternativaCorreta(alternativa);
	}
	
	public Pergunta buscaPergunta(int IDPergunta) {
		
		List<Pergunta> listaPergunta = this.ler();
		for(Pergunta pergunta : listaPergunta) {
			if(pergunta.getID() == IDPergunta) {
				return pergunta;
			}
		}
		return null;
	}

	public List<Pergunta> buscaPerguntaJogador() {

		List<Pergunta> listaPergunta = this.ler();

		List<Pergunta> listaPerguntaJogador = new ArrayList<>();
		for (Pergunta pergunta : listaPergunta) {
			if (pergunta.getAutor().getID() == Jogador.jogadorLogado.getID()) {
				listaPerguntaJogador.add(pergunta);
			}
		}

		return listaPerguntaJogador;
	}

	public Pergunta sortear(Jogo jogo) {

		List<Pergunta> listaPerguntaDefault = this.ler();
		List<PerguntaExibida> listaPerguntaExibida = new PerguntaExibida().Ler();

		int catG = 0, catH = 0, catC = 0, catJ = 0, catP = 0, catA = 0, pergunta = 0;

		for (PerguntaExibida pergE : listaPerguntaExibida) {
			if (pergE.getPartida().getID() == jogo.getID()) {
				if (pergE.getPergunta().getCategoria().getID() == 1) {
					catG++;
				} else if (pergE.getPergunta().getCategoria().getID() == 2) {
					catH++;
				} else if (pergE.getPergunta().getCategoria().getID() == 3) {
					catC++;
				} else if (pergE.getPergunta().getCategoria().getID() == 4) {
					catJ++;
				} else if (pergE.getPergunta().getCategoria().getID() == 5) {
					catP++;
				} else if (pergE.getPergunta().getCategoria().getID() == 6) {
					catA++;
				}
			}
		}

		List<Pergunta> listaPergunta = new ArrayList<>();
		for (Pergunta perg : listaPerguntaDefault) {
			if (jogo.getRodada() < 12) {
				if (perg.getAvaliacoes() >= 5) {
					if (catG != 2 && perg.getCategoria().getID() == 1) {
						listaPergunta.add(perg);
					} else if (catH != 2 && perg.getCategoria().getID() == 2) {
						listaPergunta.add(perg);
					} else if (catC != 2 && perg.getCategoria().getID() == 3) {
						listaPergunta.add(perg);
					} else if (catJ != 2 && perg.getCategoria().getID() == 4) {
						listaPergunta.add(perg);
					} else if (catP != 2 && perg.getCategoria().getID() == 5) {
						listaPergunta.add(perg);
					} else if (catA != 2 && perg.getCategoria().getID() == 6) {
						listaPergunta.add(perg);
					}
				}
			}
			boolean adiciona = true;
			for (PerguntaExibida pergE : listaPerguntaExibida) {
				if (pergE.getPartida().getID() == jogo.getID()) {
					if (pergE.getPergunta().getID() == perg.getID()) {
						adiciona = false;
					}
				}
			}
			if (adiciona)
				if (perg.getAvaliacoes() >= 5)
					listaPergunta.add(perg);
		}

		do {
			pergunta = new Random().nextInt(listaPergunta.size());

			if (this.ID == listaPergunta.get(pergunta).getID()) {
				continue;
			}

			this.ID = listaPergunta.get(pergunta).getID();

			Categoria categoria = new Categoria();
			categoria.setID(listaPergunta.get(pergunta).getCategoria().getID());
			this.categoria = categoria;

			this.Avaliacoes = listaPergunta.get(pergunta).getAvaliacoes();

			Jogador jogador = new Jogador();
			jogador.setID(listaPergunta.get(pergunta).getAutor().getID());
			this.autor = jogador;

			this.Enunciado = listaPergunta.get(pergunta).getEnunciado();

			Resposta resposta = new Resposta();
			resposta.setID(listaPergunta.get(pergunta).getAlternativaCorreta().getID());
			this.alternativaCorreta = resposta;
			return this;

		} while (true);

	}

	public void criar() {
		
		int IDAtual = 0;
		List<Pergunta> listaPergunta = this.ler();
		for (Pergunta pergunta : listaPergunta) {
			IDAtual = pergunta.getID() + 1;
		}

		this.ID = IDAtual;

		this.Avaliacoes = 0;

		Jogador jogador = new Jogador();
		jogador.setID(Jogador.jogadorLogado.getID());
		this.autor = jogador;

		Resposta resposta = new Resposta();
		List<Resposta> listaResposta = resposta.ler();
		for (Resposta resp : listaResposta) {
			if (resp.getEnunciado().equals(this.alternativaCorreta.getEnunciado())) {
				this.alternativaCorreta.setID(resp.getID());
			}
		}

		this.gravar();

	}

	public void deletar() {

		// ID;CATEGORIA;AVALIAÇÕES;AUTOR;PERGUNTA;ID_RESPOSTA CORRETA
		List<Pergunta> listaPergunta = this.ler();
		boolean primeira = true;
		int i = 0;
		for (i = 0; i < listaPergunta.size(); i++) {
			if (listaPergunta.get(i).getID() != this.ID) {
				Pergunta pergunta = new Pergunta();

				pergunta.setID(listaPergunta.get(i).getID());

				Categoria categoria = new Categoria();
				categoria.setID(listaPergunta.get(i).getCategoria().getID());
				pergunta.setCategoria(categoria);

				pergunta.setAvaliacoes(listaPergunta.get(i).getAvaliacoes());

				Jogador jogador = new Jogador();
				jogador.setID(listaPergunta.get(i).getAutor().getID());
				pergunta.setAutor(jogador);

				pergunta.setEnunciado(listaPergunta.get(i).getEnunciado());

				Resposta resposta = new Resposta();
				resposta.setID(listaPergunta.get(i).getAlternativaCorreta().getID());
				pergunta.setAlternativaCorreta(resposta);

				if (primeira) {
					pergunta.atualizarLista();
					primeira = false;
				} else {
					pergunta.gravar();
				}

			}
		}

	}

	public void avaliar() {

		boolean primeira = true;
		List<Pergunta> listaPergunta = ler();
		for (Pergunta p : listaPergunta) {

			Pergunta pergunta = new Pergunta();

			pergunta.setID(p.getID());

			Categoria categoria = new Categoria();
			categoria.setID(p.getCategoria().getID());
			pergunta.setCategoria(categoria);

			if (this.ID == p.getID())
				pergunta.setAvaliacoes(this.Avaliacoes);
			else
				pergunta.setAvaliacoes(p.getAvaliacoes());

			Jogador jogador = new Jogador();
			jogador.setID(p.getAutor().getID());
			pergunta.setAutor(jogador);

			pergunta.setEnunciado(p.getEnunciado());

			Resposta resposta = new Resposta();
			resposta.setID(p.getAlternativaCorreta().getID());
			pergunta.setAlternativaCorreta(resposta);

			if (primeira) {
				pergunta.atualizarLista();
				primeira = false;
			} else {
				pergunta.gravar();
			}
		}

	}

	public List<Pergunta> ler() {

		Arquivo arquivo = new Arquivo();

		arquivo.setNome("Perguntas.txt");

		List<String> lista = arquivo.ler();
		List<Pergunta> listaPergunta = new ArrayList<Pergunta>();

		String[] dados = { " " };

		for (String linha : lista) {

			dados = linha.split(";");

			Pergunta pergunta = new Pergunta();

			pergunta.setID(Integer.parseInt(dados[0]));

			int IDCategoria = Integer.parseInt(dados[1]);
			Categoria categoria = new Categoria();
			categoria.setID(IDCategoria);
			pergunta.setCategoria(categoria);

			pergunta.setAvaliacoes(Integer.parseInt(dados[2]));

			int IDJogador = Integer.parseInt(dados[3]);
			Jogador jogador = new Jogador();
			jogador.setID(IDJogador);
			pergunta.setAutor(jogador);

			pergunta.setEnunciado(dados[4]);

			int IDResposta = Integer.parseInt(dados[5]);
			Resposta resposta = new Resposta();
			resposta.setID(IDResposta);
			pergunta.setAlternativaCorreta(resposta);

			listaPergunta.add(pergunta);

		}

		return listaPergunta;

	}

	public void gravar() {

		String linha = this.ID + ";" + this.categoria.getID() + ";" + this.Avaliacoes + ";" + this.autor.getID() + ";"
				+ this.Enunciado + ";" + this.alternativaCorreta.getID();

		Arquivo arquivo = new Arquivo();
		arquivo.setNome("Perguntas.txt");
		arquivo.setTexto(linha);
		arquivo.gravar();

	}

	public void atualizarLista() {

		String linha = this.ID + ";" + this.categoria.getID() + ";" + this.Avaliacoes + ";" + this.autor.getID() + ";"
				+ this.Enunciado + ";" + this.alternativaCorreta.getID();

		Arquivo arquivo = new Arquivo();
		arquivo.setNome("Perguntas.txt");
		arquivo.setTexto(linha);
		arquivo.atualizar();

	}

}
