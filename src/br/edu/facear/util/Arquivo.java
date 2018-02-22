/*Faculdade Educacional Araucária
 * Karl August Harder
 * Projeto Integrador II
 * Prof.: Rodrigo B. Marcondes
 */

package br.edu.facear.util;

import java.io.*;
import java.util.*;

public class Arquivo {

	private String Diretorio;
	private String Nome;
	private String Texto;

	public String getDiretorio() {
		return this.Diretorio;
	}

	public void setDiretorio(String diretorio) {
		this.Diretorio = diretorio;
	}

	public String getNome() {
		return this.Nome;
	}

	public void setNome(String nome) {
		this.Nome = nome;
	}

	public String getTexto() {
		return this.Texto;
	}

	public void setTexto(String texto) {
		this.Texto = texto;
	}

	public Arquivo() {
		this.Diretorio = "src/arq";
	}

	public Arquivo(String dir, String nome, String texto) {
		this.Diretorio = dir;
		this.Nome = nome;
		this.Texto = texto;
	}

	public boolean gravar() {

		File dir = new File(this.Diretorio);
		File arq = new File(dir, this.Nome);

		try {
			arq.createNewFile();

			boolean acrescentar = true;

			FileWriter fw = new FileWriter(arq, acrescentar);

			PrintWriter pw = new PrintWriter(fw);

			pw.println(this.Texto);

			pw.flush();
			pw.close();

		} catch (Exception e) {
			System.out.println("Erro ao Gravar o arquivo: " + e.getMessage());
		}

		//System.out.println("O arquivo foi gravado com sucesso!");
		return true;
	}

	public List<String> ler() {

		List<String> lista = new ArrayList<>();

		File dir = new File(this.Diretorio);
		File arq = new File(dir, this.Nome);

		try {
			String linha;

			FileReader fr = new FileReader(arq);

			BufferedReader br = new BufferedReader(fr);

			while ((linha = br.readLine()) != null) {
				lista.add(linha);
			}

			br.close();

		} catch (Exception e) {
			System.out.println("Erro ao ler o arquivo: " + e.getMessage());
		}

		return lista;

	}
	
	public boolean atualizar() {

		File dir = new File(this.Diretorio);
		File arq = new File(dir, this.Nome);

		try {
			arq.createNewFile();

			boolean acrescentar = false;

			FileWriter fw = new FileWriter(arq, acrescentar);

			PrintWriter pw = new PrintWriter(fw);

			pw.println(this.Texto);

			pw.flush();
			pw.close();

		} catch (Exception e) {
			System.out.println("Erro ao Gravar o arquivo: " + e.getMessage());
		}

		//System.out.println("O arquivo foi gravado com sucesso!");
		return true;
	}

}
