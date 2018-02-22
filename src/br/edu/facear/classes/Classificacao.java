/*Faculdade Educacional Araucária
 * Karl August Harder
 * Projeto Integrador II
 * Prof.: Rodrigo B. Marcondes
 */

package br.edu.facear.classes;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import br.edu.facear.util.Arquivo;

public class Classificacao implements Comparable<Classificacao> {

	private int RespostaSemana;
	private int RespostaMes;
	private int Total;
	private Date Data;
	private Jogador jogador;

	public int getRespostaSemana() {
		return this.RespostaSemana;
	}

	public void setRespostaSemana(int respostaSemana) {
		this.RespostaSemana = respostaSemana;
	}

	public int getRespostaMes() {
		return this.RespostaMes;
	}

	public void setRespostaMes(int respostaMes) {
		this.RespostaMes = respostaMes;
	}

	public int getTotal() {
		return this.Total;
	}

	public void setTotal(int total) {
		this.Total = total;
	}

	public Date getData() {
		return this.Data;
	}

	public void setData(Date data) {
		this.Data = data;
	}

	public Jogador getJogador() {
		return this.jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public Classificacao() {
		this.jogador = new Jogador();
	}

	public Classificacao(Jogador jogador) {
		this.jogador = jogador;
	}

	public Classificacao(int respostaSemana, int respostaMes, int total, Date data, Jogador jogador) {
		setRespostaSemana(respostaSemana);
		setRespostaMes(respostaMes);
		setTotal(total);
		setData(data);
		setJogador(jogador);
	}

	public List<Classificacao> classificar() {

		List<Classificacao> listaClassificada = new ArrayList<>();
		List<Jogador> listaJogador = new Jogador().Ler();
		List<Classificacao> listaPontuacao = this.Ler();
		
		Calendar dataInicioSemana = Calendar.getInstance();
		dataInicioSemana.add(Calendar.DAY_OF_YEAR, -7);
		
		Calendar dataInicioMes = Calendar.getInstance();
		dataInicioMes.add(Calendar.DAY_OF_YEAR, -30);
		
		
		for(Jogador jog : listaJogador) {
			
			Classificacao rank = new Classificacao();
			rank.setJogador(jog);
			for(Classificacao ponto : listaPontuacao) {
				if(ponto.getJogador().getID() == jog.getID()) {
					if(ponto.getData().after(dataInicioSemana.getTime())) {
						rank.RespostaSemana += 1;
						rank.Total += 1;
					}else if(ponto.getData().after(dataInicioMes.getTime())) {
						rank.RespostaMes += 1;
						rank.Total += 1;
					}
				}
			}
			
			listaClassificada.add(rank);
		}
		
		Collections.sort(listaClassificada);
	
		return listaClassificada;

	}

	public List<Classificacao> Ler(){

		Arquivo arquivo = new Arquivo();

		arquivo.setNome("Pontuacao.txt");

		List<Classificacao> listaClassificacao = new ArrayList<Classificacao>();
		List<String> lista = arquivo.ler();

		String[] dados = { " " };

		for (String linha : lista) {

			dados = linha.split(";");

			Classificacao classificacao = new Classificacao();

			Jogador jogador = new Jogador();
			jogador.setID(Integer.parseInt(dados[0]));
			classificacao.setJogador(jogador);
			
			String dataString = dados[1];
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			Date data = null;
			try {
				data = df.parse(dataString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			classificacao.setData(data);

			listaClassificacao.add(classificacao);
		}

		return listaClassificacao;

	}

	public void Gravar() {

		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String linha = this.jogador.getID() + ";" + sdf.format(data);

		Arquivo arquivo = new Arquivo();
		arquivo.setNome("Pontuacao.txt");
		arquivo.setTexto(linha);
		arquivo.gravar();

	}

	@Override
	public int compareTo(Classificacao c) {
		if (this.Total > c.getTotal()) {
			return -1;
		} else if (this.Total < c.getTotal()) {
			return 1;
		} else {
			return 0;
		}
	}

}