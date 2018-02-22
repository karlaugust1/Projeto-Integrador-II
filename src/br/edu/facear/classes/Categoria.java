/*Faculdade Educacional Araucária
 * Karl August Harder
 * Projeto Integrador II
 * Prof.: Rodrigo B. Marcondes
 */


package br.edu.facear.classes;

import java.util.ArrayList;
import java.util.List;

import br.edu.facear.util.*;

public class Categoria {
	
	private int ID;
	private String Nome;
	
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
	
	public Categoria() {
	}
	
	public Categoria(int id, String nome, int quantidadePergunta, Pergunta pergunta) {
		setID(id);
		setNome(nome);
	}
		
	public int sortear() {
		return this.ID;
	}
	
	public List<Categoria> Ler(){
		
		Arquivo arquivo = new Arquivo();
		
		arquivo.setNome("Categorias.txt");
		
		List<Categoria> listaCategoria = new ArrayList<>();
		List<String> lista = arquivo.ler();
		
		String[] dados = {" "};
		
		for(String linha : lista) {
		
			dados = linha.split(";");
			
			Categoria categoria = new Categoria();
			
			categoria.setID(Integer.parseInt(dados[0]));
			categoria.setNome(dados[1]);
			
			listaCategoria.add(categoria);
			
		}
		return listaCategoria;
	}
	
	public void Gravar() {
		
		String linha = this.ID+";"+this.Nome;
		
		Arquivo arquivo = new Arquivo();
		arquivo.setNome("Categorias.txt");
		arquivo.setTexto(linha);
		arquivo.gravar();
		
	}
	
}
