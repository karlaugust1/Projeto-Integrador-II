/*Faculdade Educacional Araucária
 * Karl August Harder
 * Projeto Integrador II
 * Prof.: Rodrigo B. Marcondes
 */
package br.edu.facear.classes;

import java.util.*;

import br.edu.facear.util.*;

public class Jogador {

	private int ID;
	private String Nome;
	private String Usuario;
	private String Senha;
	private int Nivel;
	private int Horcrux;
	private int Pontuacao;
	private Jogo partida1;
	private Jogo partida2;
	public static Jogador jogadorLogado;

	public int getID() {
		return this.ID;
	}

	public void setID(int id) {
		this.ID = id;
	}

	public String getNome() {
		return this.Nome;
	}

	public void setNome(String nome) {
		this.Nome = nome;
	}

	public String getUsuario() {
		return this.Usuario;
	}

	public void setUsuario(String usuario) {
		this.Usuario = usuario;
	}

	public String getSenha() {
		return this.Senha;
	}

	public void setSenha(String senhaUsuario) {
		this.Senha = senhaUsuario;
	}

	public int getNivel() {
		return this.Nivel;
	}

	public void setNivel(int nivel) {
		this.Nivel = nivel;
	}

	public int getHorcrux() {
		return this.Horcrux;
	}

	public void setHorcrux(int horcrux) {
		this.Horcrux = horcrux;
	}

	public int getPontuacao() {
		return this.Pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.Pontuacao = pontuacao;
	}

	public Jogo getPartida1() {
		return this.partida1;
	}

	public void setPartida1(Jogo partida1) {
		this.partida1 = partida1;
	}

	public Jogo getPartida2() {
		return this.partida2;
	}

	public void setPartida2(Jogo partida2) {
		this.partida2 = partida2;
	}

	public Jogador() {
	}

	public Jogador(int id, String nome, String usuario, String senha, int nivel, int horcrux, int pontuacao,
			Jogo partida1, Jogo partida2) {
		setID(id);
		setNome(nome);
		setUsuario(usuario);
		setSenha(senha);
		setNivel(nivel);
		setHorcrux(horcrux);
		setPontuacao(pontuacao);
		setPartida1(partida1);
		setPartida2(partida2);
	}
	
	public Jogador buscaJogador(int IDJogador) {
		
		List<Jogador> listaJogador = this.Ler();
		for(Jogador jog : listaJogador) {
			if(jog.getID() == IDJogador) {
				return jog;
			}
		}
		return null;
	}

	public boolean cadastrar() {

		boolean achouUsuario = false;
		int IDAtual = 0;

		List<Jogador> listaJogador = this.Ler();

		for (Jogador jog : listaJogador) {
			if (jog.getUsuario().equals(this.Usuario)) {
				achouUsuario = true;
			}
			IDAtual++;
		}
		if (achouUsuario) {
			return false;
		} else {
			this.ID = IDAtual + 1;
			this.Nivel = 0;
			this.Horcrux = 0;
			this.Pontuacao = 0;
			this.Gravar();
		}

		return true;
	}

	public int logar() {
		
		int retorno = 0;
		if(this.Usuario.isEmpty() && this.Senha.isEmpty())
			return 1;
		else if(this.Usuario.isEmpty())
			return 2;
		else if(this.Senha.isEmpty())
			return 3;
		else {
			List<Jogador> listaJogador = this.Ler();
			for(Jogador jog : listaJogador) {
				if(jog.getUsuario().equals(this.Usuario)) {
					if(jog.Senha.equals(this.Senha)) {
						retorno = 0;
						jogadorLogado = jog;
						break;
					}else {
						retorno = 5;
						break;
					}
				}else {
					retorno = 4;
				}
			}
		}
		
		return retorno;
	}

	public Jogador sortear() {

		Jogador retorno = null;
		try {
			List<Jogador> listaJogador = this.Ler();
			Random r = new Random();
			int posicaoSorteada = r.nextInt(listaJogador.size());
			if (listaJogador.get(posicaoSorteada).getID() == Jogador.jogadorLogado.getID())
				sortear();
			else {
				retorno = listaJogador.get(posicaoSorteada);
			}
		} catch (Exception e) {
			sortear();
		}

		return retorno;

	}

	public int validarNome() {

		if (this.Nome.isEmpty()) {
			return 1;
		} else if (this.Nome.indexOf(";") != -1) {
			return 2;
		} else {
			return 0;
		}
	}

	public int validarUsuario() {

		if (this.Usuario.isEmpty()) {
			return 1;
		} else if (this.Usuario.indexOf(";") != -1) {
			return 2;
		} else {
			return 0;
		}

	}

	public int validarSenha() {

		if (this.Senha.isEmpty())
			return 1;
		if (this.Senha.indexOf(";") != -1) {
			return 2;
		} else {
			return 0;
		}

	}

	public List<Jogador> Ler() {

		Arquivo arquivo = new Arquivo();

		arquivo.setNome("Jogadores.txt");

		List<String> lista = arquivo.ler();
		List<Jogador> listaJogadores = new ArrayList<Jogador>();

		String[] dados = { " " };

		for (String linha : lista) {

			dados = linha.split(";");

			Jogador jogador = new Jogador();

			jogador.setID(Integer.parseInt(dados[0]));
			jogador.setNome(dados[1]);
			jogador.setUsuario(dados[2]);
			jogador.setSenha(dados[3]);
			jogador.setNivel(Integer.parseInt(dados[4]));
			jogador.setHorcrux(Integer.parseInt(dados[5]));
			jogador.setPontuacao(Integer.parseInt(dados[6]));

			listaJogadores.add(jogador);
		}

		return listaJogadores;

	}

	public void Gravar() {

		String linha = this.ID + ";" + this.Nome + ";" + this.Usuario + ";" + this.Senha + ";" + this.Nivel + ";"
				+ this.Horcrux + ";" + this.Pontuacao;

		Arquivo arquivo = new Arquivo();
		arquivo.setNome("jogadores.txt");
		arquivo.setTexto(linha);
		arquivo.gravar();

	}

	public void atualizarDados() {

		boolean primeira = true;
		List<Jogador> listaJogadores = this.Ler();
		for (Jogador jog : listaJogadores) {
			if (jog.getID() == this.ID) {
				jog.setNivel(this.Nivel);
				jog.setHorcrux(this.Horcrux);
				jog.setPontuacao(this.Pontuacao);
			}

			String linha = jog.ID + ";" + jog.Nome + ";" + jog.Usuario + ";" + jog.Senha + ";" + jog.Nivel + ";"
					+ jog.Horcrux + ";" + jog.Pontuacao;

			Arquivo arquivo = new Arquivo();
			arquivo.setNome("jogadores.txt");
			arquivo.setTexto(linha);
			if (primeira) {
				arquivo.atualizar();
				primeira = false;
			} else {
				arquivo.gravar();
			}
		}

	}

}