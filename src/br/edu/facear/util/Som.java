package br.edu.facear.util;

import java.io.*;

import javax.sound.sampled.*;

public class Som {

	private String diretorio, nome;
	private Clip clip;
	private AudioInputStream audio;

	public String getDiretorio() {
		return diretorio;
	}

	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Clip getClip() {
		return clip;
	}

	public void setClip(Clip clip) {
		this.clip = clip;
	}

	public AudioInputStream getAudio() {
		return audio;
	}

	public void setAudio(AudioInputStream audio) {
		this.audio = audio;
	}

	public Som() {
		this.diretorio = "src/sons/";
	}

	public Som(String dir, String nome) {
		this.diretorio = dir;
		this.nome = nome;
	}
	
	public void tocarSom() {
		try {
			audio = AudioSystem.getAudioInputStream(
					new BufferedInputStream(new FileInputStream(new File(this.diretorio + this.nome))));
			clip = AudioSystem.getClip();
			clip.open((AudioInputStream) audio);
			clip.start();

		} catch (Exception e) {

		}
	}

	public void mouseClick() {
		this.nome = "clickMouse.wav";
		tocarSom();
	}

	public void logon() {
		this.nome = "logon.wav";
		tocarSom();
	}
}
