	/*Faculdade Educacional Araucária
 * Karl August Harder
 * Projeto Integrador II
 * Prof.: Rodrigo B. Marcondes
 */

package br.edu.facear.classes;

import java.util.*;

import br.edu.facear.util.Arquivo;

public class Jogo {

	private int ID;
	private int Tempo;
	private boolean Turno;
	private int Rodada;
	private int AcertosJogador1;
	private int AcertosJogador2;
	private boolean Finalizado;
	private PerguntaExibida perguntaExibida;
	private Pergunta pergunta;
	private Jogador jogador1;
	private Jogador jogador2;

	public int getID() {
		return this.ID;
	}

	public void setID(int id) {
		this.ID = id;
	}

	public int getTempo() {
		return this.Tempo;
	}

	public void setTempo(int tempo) {
		this.Tempo = tempo;
	}

	public boolean getTurno() {
		return this.Turno;
	}

	public void setTurno(boolean turno) {
		this.Turno = turno;
	}

	public int getRodada() {
		return this.Rodada;
	}

	public void setRodada(int rodada) {
		this.Rodada = rodada;
	}

	public int getAcertosJogador1() {
		return this.AcertosJogador1;
	}

	public void setAcertosJogador1(int acertosJogador1) {
		this.AcertosJogador1 = acertosJogador1;
	}

	public int getAcertosJogador2() {
		return this.AcertosJogador2;
	}

	public void setAcertosJogador2(int acertosJogador2) {
		this.AcertosJogador2 = acertosJogador2;
	}

	public boolean getFinalizado() {
		return this.Finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.Finalizado = finalizado;
	}

	public PerguntaExibida getPerguntaExibida() {
		return this.perguntaExibida;
	}

	public void setPerguntaExibida(PerguntaExibida perguntaExibida) {
		this.perguntaExibida = perguntaExibida;
	}

	public Pergunta getPergunta() {
		return this.pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public Jogador getJogador1() {
		return this.jogador1;
	}

	public void setJogador1(Jogador jogador1) {
		this.jogador1 = jogador1;
	}

	public Jogador getJogador2() {
		return this.jogador2;
	}

	public void setJogador2(Jogador jogador2) {
		this.jogador2 = jogador2;
	}

	public Jogo() {
	}

	public Jogo(int id, int tempo, boolean turno, int rodada, int acertosJogador1, int acertosJogador2,
			Pergunta pergunta, Jogador jogador1, Jogador jogador2) {
		setID(id);
		setTempo(tempo);
		setTurno(turno);
		setRodada(rodada);
		setAcertosJogador1(acertosJogador1);
		setAcertosJogador2(acertosJogador2);
		setPergunta(pergunta);
		setJogador1(jogador1);
		setJogador2(jogador2);
	}

	public void atualizarInformacoes() {

		List<PerguntaExibida> listaPerguntaExibida = new PerguntaExibida().Ler();
		for (PerguntaExibida pergExib : listaPerguntaExibida) {
			if (pergExib.getPartida().getID() >= this.ID) {
				this.ID = pergExib.getPartida().getID();
			}
		}
		this.ID += 1;
	}

	public boolean ajudar() {

		boolean retorno = true;
		if (Turno) {

			if (jogador1.getNivel() > 0) {
				if (jogador1.getPontuacao() < 5) {
					int pontuacao = 10 + (jogador1.getPontuacao() - 5);
					jogador1.setPontuacao(pontuacao);

					jogador1.setHorcrux(jogador1.getHorcrux() - 1);
					if (jogador1.getHorcrux() == -1) {
						jogador1.setHorcrux(6);
						jogador1.setNivel(jogador1.getNivel() - 1);
					}
				} else {
					jogador1.setPontuacao(jogador1.getPontuacao() - 5);
				}

			} else {

				if (jogador1.getPontuacao() < 5 && jogador1.getHorcrux() > 0) {
					int pontuacao = 10 + (jogador1.getPontuacao() - 5);
					jogador1.setPontuacao(pontuacao);

					jogador1.setHorcrux(jogador1.getHorcrux() - 1);
					/*if (jogador1.getHorcrux() == -1) {
						jogador1.setHorcrux(6);

					} else {
						jogador1.setPontuacao(jogador1.getPontuacao() - 5);
					}*/
				} else if (jogador1.getPontuacao() > 4) {
					jogador1.setPontuacao(jogador1.getPontuacao() - 5);
				} else {
					retorno = false;
				}
			}
		} else {

			if (jogador2.getNivel() > 0) {
				if (jogador2.getPontuacao() < 5) {
					int pontuacao = 10 + (jogador2.getPontuacao() - 5);
					jogador2.setPontuacao(pontuacao);

					jogador2.setHorcrux(jogador2.getHorcrux() - 1);
					if (jogador2.getHorcrux() == -1) {
						jogador2.setHorcrux(6);
						jogador2.setNivel(jogador2.getNivel() - 1);
					}
				} else {
					jogador2.setPontuacao(jogador2.getPontuacao() - 5);
				}

			} else {

				if (jogador2.getPontuacao() < 5 && jogador2.getHorcrux() > 0) {
					int pontuacao = 10 + (jogador2.getPontuacao() - 5);
					jogador2.setPontuacao(pontuacao);
					
					jogador2.setHorcrux(jogador2.getHorcrux() - 1);
					/*if (jogador2.getHorcrux() == -1) {
						jogador2.setHorcrux(6);

					} else {
						int pontuacao1 = 10 + (jogador2.getPontuacao() - 5);
						jogador2.setPontuacao(pontuacao1);
						//jogador2.setPontuacao(jogador2.getPontuacao() - 5);
					}*/
				} else if (jogador2.getPontuacao() > 4) {
					jogador2.setPontuacao(jogador2.getPontuacao() - 5);
				} else {
					retorno = false;
				}
			}
		}
		return retorno;

	}

	public boolean alterarTurno(boolean turno) {

		if (turno) {
			this.setTurno(false);
		} else {
			this.setTurno(true);
		}

		return this.Turno;

	}

	public void desistir() {

		new PerguntaExibida().deletar(this);

	}

	public void deletar() {

		boolean primeira = true;
		List<Jogo> listaJogos = this.ler();
		for (Jogo jogo : listaJogos) {
			if (jogo.getJogador1().getID() != this.jogador1.getID()) {

				if (primeira) {
					jogo.atualizarArquivo();
					primeira = false;
				} else {
					jogo.gravar();
				}
			} else {
				jogo.setID(0);
				if (primeira) {
					jogo.atualizarArquivo();
					primeira = false;
				} else {
					jogo.gravar();
				}
			}

		}

	}

	public void sair() {

		boolean primeiro = true, gravar = true;
		;
		List<Jogo> listaJogo = this.ler();
		if (!listaJogo.isEmpty()) {
			for (Jogo jogo : listaJogo) {
				if (jogo.getID() != this.ID) {
					Jogo j = new Jogo();

					j.setID(jogo.getID());
					j.setTempo(jogo.getTempo());
					j.setTurno(jogo.getTurno());
					j.setRodada(jogo.getRodada());
					j.setAcertosJogador1(jogo.getAcertosJogador1());
					j.setAcertosJogador2(jogo.getAcertosJogador2());
					j.setFinalizado(jogo.getFinalizado());
					j.setPerguntaExibida(jogo.getPerguntaExibida());
					j.setPergunta(jogo.getPergunta());
					j.setJogador1(jogo.getJogador1());
					j.setJogador2(jogo.getJogador2());

					if (primeiro) {
						j.atualizarArquivo();
						primeiro = false;
					} else {
						j.gravar();
					}

				} else {
					gravar = false;
					if (primeiro) {
						this.atualizarArquivo();
						primeiro = false;
					} else {
						gravar();
					}
				}
			}
			if (gravar) {
				if (primeiro) {
					this.atualizarArquivo();
					primeiro = false;
				} else {
					gravar();
				}
			}
		} else {
			this.gravar();
		}

	}

	public Jogador exibirGanhador() {
		
		if(getAcertosJogador1() > getAcertosJogador2())
			return this.jogador1;
		else if(getAcertosJogador2() > getAcertosJogador1())
			return this.jogador2;
		else
			return null;
	}

	public boolean validarFinal() {
		return false;
	}

	public void atualizarPontuacao() {

		if (this.Turno) {
			if (this.jogador1.getPontuacao() >= 7) {
				this.jogador1.setPontuacao(this.jogador1.getPontuacao() + 3);
				int diferenca = this.jogador1.getPontuacao() - 10;
				this.jogador1.setPontuacao(diferenca);
				this.jogador1.setHorcrux(this.jogador1.getHorcrux() + 1);
				if (this.jogador1.getHorcrux() == 7) {
					this.jogador1.setNivel(this.jogador1.getNivel() + 1);
					this.jogador1.setHorcrux(0);
				}
			} else {
				this.jogador1.setPontuacao(this.jogador1.getPontuacao() + 3);
			}
			jogador1.atualizarDados();
		} else {
			if (this.jogador2.getPontuacao() >= 7) {
				this.jogador2.setPontuacao(this.jogador2.getPontuacao() + 3);
				int diferenca = this.jogador2.getPontuacao() - 10;
				this.jogador2.setPontuacao(diferenca);
				this.jogador2.setHorcrux(this.jogador2.getHorcrux() + 1);
				if (this.jogador2.getHorcrux() == 7) {
					this.jogador2.setNivel(this.jogador2.getNivel() + 1);
					this.jogador2.setHorcrux(0);
				}
			} else {
				this.jogador2.setPontuacao(this.jogador2.getPontuacao() + 3);
			}
			jogador2.atualizarDados();
		}

	}

	public List<Jogo> ler() {

		Arquivo arquivo = new Arquivo();

		arquivo.setNome("Jogos.txt");

		List<String> lista = arquivo.ler();
		List<Jogo> listaJogos = new ArrayList<>();

		String[] dados = { " " };

		for (String linha : lista) {

			dados = linha.split(";");

			Jogo jogo = new Jogo();

			if (Integer.parseInt(dados[0]) != 0) {

				jogo.setID(Integer.parseInt(dados[0]));
				jogo.setTempo(Integer.parseInt(dados[1]));
				if (dados[2].equals("true"))
					jogo.setTurno(true);
				else
					jogo.setTurno(false);

				jogo.setRodada(Integer.parseInt(dados[3]));
				jogo.setAcertosJogador1(Integer.parseInt(dados[4]));
				jogo.setAcertosJogador2(Integer.parseInt(dados[5]));

				jogo.setPergunta(new Pergunta().buscaPergunta(Integer.parseInt(dados[6])));
				jogo.setJogador1(new Jogador().buscaJogador(Integer.parseInt(dados[7])));
				jogo.setJogador2(new Jogador().buscaJogador(Integer.parseInt(dados[8])));

				listaJogos.add(jogo);
			}

		}

		return listaJogos;

	}

	public void gravar() {

		String linha = this.ID + ";" + this.Tempo + ";" + this.Turno + ";" + this.Rodada + ";" + this.AcertosJogador1
				+ ";" + this.AcertosJogador2 + ";" + this.pergunta.getID() + ";" + this.jogador1.getID() + ";"
				+ this.jogador2.getID();

		Arquivo arquivo = new Arquivo();
		arquivo.setNome("Jogos.txt");
		arquivo.setTexto(linha);
		arquivo.gravar();

	}

	public void atualizarArquivo() {

		String linha = this.ID + ";" + this.Tempo + ";" + this.Turno + ";" + this.Rodada + ";" + this.AcertosJogador1
				+ ";" + this.AcertosJogador2 + ";" + this.pergunta.getID() + ";" + this.jogador1.getID() + ";"
				+ this.jogador2.getID();

		Arquivo arquivo = new Arquivo();
		arquivo.setNome("Jogos.txt");
		arquivo.setTexto(linha);
		arquivo.atualizar();

	}
}
