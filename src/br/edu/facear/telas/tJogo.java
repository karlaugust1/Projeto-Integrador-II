package br.edu.facear.telas;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.*;
import br.edu.facear.classes.*;
import br.edu.facear.util.Som;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.*;

@SuppressWarnings("serial")
public class tJogo extends JFrame implements MouseListener {

	private JPanel painelGeral;
	private JTextArea txtPergunta;
	private JLabel lblFundo, lblNomeJogador1, lblNomeJogador2, lblHorcruxJogador1, lblHorcruxJogador2, // lblJogadorAtual,
			lblTempo, lblIconepergunta, lblCategoria, lblAutor, lblRodada, lblVezjogador, lblAnunciarmudanca,
			lblResposta, lblIconeautor, lblNivelJogador1, lblNivelJogador2, lblIconeJogador1, lblIconeJogador2,
			lblHorcruxMaximo1, lblHorcruxMaximo2;
	private JButton btnAlternativaA, btnAlternativaB, btnAlternativaC, btnAlternativaD, btnSair, btnDesistir, btnAjuda,
			btnSair_1, btnContinuar;
	private JProgressBar pontuacaoJogador1, pontuacaoJogador2;
	private String enunciadoResposta;
	private Thread tempo;
	private Jogo jogo;
	private Som som;
	private static Clip clip;
	private static AudioInputStream audio;
	private static boolean tocando;
	public static boolean tocar;

	public tJogo() {
		super("JUST ANOTHER QUIZ GAME");

		som = new Som();
		criarJanela();
		criarCampos();
		criarBotoes();
		criarPainel();
		if (tocar)
			tocarMusica();

		continuarJogo();

	}

	public void tocarMusica() {
		tMenu.pararMusica();
		if (!tocando) {
			String diretorio = "src/sons/", nome = "musicaJogo.wav";
			try {
				audio = AudioSystem
						.getAudioInputStream(new BufferedInputStream(new FileInputStream(new File(diretorio + nome))));
				clip = AudioSystem.getClip();
				clip.open((AudioInputStream) audio);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				tocando = true;
			} catch (Exception e) {

			}
		}
	}

	public static void pararMusica() {

		try {
			clip.stop();
			audio.close();
			tocando = false;
		} catch (Exception e) {

		}
	}

	private void desistir() {

		int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja realmente desistir?", "Não desiste :(",
				JOptionPane.YES_NO_OPTION);
		if (confirmacao == 0) {
			jogo.desistir();
			new tMenu().setVisible(true);
			dispose();
		}

	}

	public void atualizarInformacoes() {

		jogo.atualizarInformacoes();

	}

	public void carregarInformacoes() {

		try {
			jogo.setJogador1(Jogador.jogadorLogado);

			lblHorcruxJogador1.setText(Integer.toString((jogo.getJogador1().getHorcrux())));
			lblNomeJogador1.setText(jogo.getJogador1().getNome());
			pontuacaoJogador1.setValue(jogo.getJogador1().getPontuacao() * 10);
			lblNivelJogador1.setText(Integer.toString(jogo.getJogador1().getNivel()));

			lblHorcruxJogador2.setText(Integer.toString((jogo.getJogador2().getHorcrux())));
			lblNomeJogador2.setText(jogo.getJogador2().getNome());
			pontuacaoJogador2.setValue(jogo.getJogador2().getPontuacao() * 10);
			lblNivelJogador2.setText(Integer.toString(jogo.getJogador2().getNivel()));

		} catch (Exception e) {
			buscaJogador();
			carregarInformacoes();
		}

		alterarTurno();

	}

	public void buscaJogador() {

		try {
			jogo.setJogador2(new Jogador().sortear());

		} catch (Exception e) {
			buscaJogador();
		}

	}

	public void escolherJogadorInicio() {

		int r = new Random().nextInt(2);
		if (r == 0) {
			// lblJogadorAtual.setText(jogo.getJogador1().getNome());
			jogo.setTurno(true);
		} else {
			// lblJogadorAtual.setText(jogo.getJogador2().getNome());
			jogo.setTurno(false);
		}

	}

	public void alterarTurno() {

		if (jogo.alterarTurno(jogo.getTurno())) {

			this.lblNivelJogador2.setFont(new Font("Aileron", Font.PLAIN, 16));
			this.lblNivelJogador2.setBounds(707, 70, 20, 20);

			this.lblIconeJogador2.setIcon(new ImageIcon("src/img/userUnfocusabled.png"));
			this.lblIconeJogador2.setBounds(667, 31, 64, 64);

			ImageIcon ic = new ImageIcon("src/img/userFocusabled.png");
			Image im = ic.getImage();
			im = im.getScaledInstance(96, 96, java.awt.Image.SCALE_SMOOTH);
			this.lblIconeJogador1.setIcon(new ImageIcon(im));
			this.lblIconeJogador1.setBounds(40, 15, 96, 96);

			this.lblNivelJogador1.setFont(new Font("Aileron", Font.PLAIN, 20));
			this.lblNivelJogador1.setBounds(103, 76, 26, 26);

			// lblJogadorAtual.setText(jogo.getJogador1().getNome());
			pontuacaoJogador2.setForeground(Color.gray);
			pontuacaoJogador1.setForeground(Color.GREEN);

		} else {

			this.lblNivelJogador1.setFont(new Font("Aileron", Font.PLAIN, 16));
			this.lblNivelJogador1.setBounds(88, 70, 20, 20);

			this.lblIconeJogador1.setIcon(new ImageIcon("src/img/userUnfocusabled.png"));
			this.lblIconeJogador1.setBounds(47, 31, 64, 64);

			ImageIcon ic = new ImageIcon("src/img/userFocusabled.png");
			Image im = ic.getImage();
			im = im.getScaledInstance(96, 96, java.awt.Image.SCALE_SMOOTH);
			this.lblIconeJogador2.setIcon(new ImageIcon(im));
			this.lblIconeJogador2.setBounds(655, 15, 96, 96);

			this.lblNivelJogador2.setFont(new Font("Aileron", Font.PLAIN, 20));
			this.lblNivelJogador2.setBounds(718, 76, 26, 26);
			pontuacaoJogador1.setForeground(Color.gray);
			pontuacaoJogador2.setForeground(Color.GREEN);
			// lblJogadorAtual.setText(jogo.getJogador2().getNome());

		}

	}

	public void buscaPergunta() {

		esvaziaCampos();
		jogo.setPergunta(new Pergunta().sortear(jogo));
		preencheCampos();
		if (!jogo.getFinalizado())
			cronometro();

	}

	public boolean validarResposta() {

		boolean retorno = false;

		Resposta resposta = new Resposta();
		resposta.setPergunta(jogo.getPergunta());
		resposta.setEnunciado(enunciadoResposta);

		if (resposta.autenticar()) {
			jogo.atualizarPontuacao();
			if (jogo.getTurno()) {
				jogo.setAcertosJogador1(jogo.getAcertosJogador1() + 1);
				new Classificacao(jogo.getJogador1()).Gravar();
				retorno = true;
			} else {
				jogo.setAcertosJogador2(jogo.getAcertosJogador2() + 1);
				new Classificacao(jogo.getJogador2()).Gravar();
				retorno = true;
			}
		} else {
			retorno = false;
		}
		atualizaDados();

		return retorno;
	}

	public void preencheCampos() {


		lblRodada.setText(Integer.toString(jogo.getRodada()) + "/15");

		this.txtPergunta.setText(jogo.getPergunta().getEnunciado());

		Resposta resposta = new Resposta();
		List<Resposta> listaResposta = resposta.ler();

		Random r = new Random();
		int i = 0;
		int contador = 0;
		for (Resposta resp : listaResposta) {
			if (resp.getPergunta().getID() == jogo.getPergunta().getID()) {
				do {
					i = r.nextInt(4);
					if (i == 0 && (btnAlternativaA.getText() == " ")) {
						btnAlternativaA.setText(resp.getEnunciado());
						contador++;
						break;
					} else if (i == 1 && (btnAlternativaB.getText() == " ")) {
						btnAlternativaB.setText(resp.getEnunciado());
						contador++;
						break;
					} else if (i == 2 && (btnAlternativaC.getText() == " ")) {
						btnAlternativaC.setText(resp.getEnunciado());
						contador++;
						break;
					} else if (i == 3 && (btnAlternativaD.getText() == " ")) {
						btnAlternativaD.setText(resp.getEnunciado());
						contador++;
						break;
					}
				} while (contador != 4);
			}
		}

		List<Jogador> listaJogador = new Jogador().Ler();
		for (Jogador jogador : listaJogador) {
			if (jogo.getPergunta().getAutor().getID() == jogador.getID())
				lblAutor.setText(jogador.getNome());
		}

		Categoria categoria = new Categoria();
		List<Categoria> listaCategoria = categoria.Ler();
		for (Categoria cat : listaCategoria) {
			if (this.jogo.getPergunta().getCategoria().getID() == cat.getID()) {
				this.lblCategoria.setText(cat.getNome());
				if (cat.getID() == 1)
					this.lblIconepergunta.setIcon(new ImageIcon("src/img/geografia.png"));
				else if (cat.getID() == 2)
					this.lblIconepergunta.setIcon(new ImageIcon("src/img/historia.png"));
				else if (cat.getID() == 3)
					this.lblIconepergunta.setIcon(new ImageIcon("src/img/ciencias.png"));
				else if (cat.getID() == 4)
					this.lblIconepergunta.setIcon(new ImageIcon("src/img/jogos.png"));
				else if (cat.getID() == 5)
					this.lblIconepergunta.setIcon(new ImageIcon("src/img/programacao.png"));
				else if (cat.getID() == 6)
					this.lblIconepergunta.setIcon(new ImageIcon("src/img/atualidades.png"));
			}
		}

	}

	public void atualizaDadosAjuda() {

		lblHorcruxJogador1.setText(Integer.toString(jogo.getJogador1().getHorcrux()));
		lblHorcruxJogador2.setText(Integer.toString(jogo.getJogador2().getHorcrux()));

		Thread pontuacao = new Thread() {
			public void run() {
				if (jogo.getTurno()) {
					for (int i = pontuacaoJogador1.getValue(); i >= 0; i--) {
						pontuacaoJogador1.setValue(i);
						try {
							Thread.sleep(17);
						} catch (InterruptedException e) {

						}
						if (jogo.getJogador1().getPontuacao() == 0 && pontuacaoJogador1.getValue() == 0)
							break;
						if (pontuacaoJogador1.getValue() == 0) {
							if (jogo.getJogador1().getPontuacao() == 0)
								break;
							pontuacaoJogador1.setValue(100);
							i = 100;
						}
						if (pontuacaoJogador1.getValue() == jogo.getJogador1().getPontuacao() * 10) {
							break;
						}
					}

				} else {
					for (int i = pontuacaoJogador2.getValue(); i >= 0; i--) {
						pontuacaoJogador2.setValue(i);
						try {
							Thread.sleep(17);
						} catch (InterruptedException e) {

						}

						if (jogo.getJogador2().getPontuacao() == 0 && pontuacaoJogador2.getValue() == 0)
							break;
						if (pontuacaoJogador2.getValue() == 0) {
							pontuacaoJogador2.setValue(100);
							i = 100;
						}
						if (pontuacaoJogador2.getValue() == jogo.getJogador2().getPontuacao() * 10) {
							break;
						}
					}
				}
			}
		};
		pontuacao.start();
		lblNivelJogador1.setText(Integer.toString(jogo.getJogador1().getNivel()));
		lblNivelJogador2.setText(Integer.toString(jogo.getJogador2().getNivel()));
	}

	public void atualizaDados() {

		lblHorcruxJogador1.setText(Integer.toString(jogo.getJogador1().getHorcrux()));
		lblHorcruxJogador2.setText(Integer.toString(jogo.getJogador2().getHorcrux()));

		Thread pontuacao = new Thread() {
			public void run() {
				if (jogo.getTurno()) {
					for (int i = pontuacaoJogador1.getValue(); i <= 100; i++) {
						pontuacaoJogador1.setValue(i);
						try {
							Thread.sleep(17);
						} catch (InterruptedException e) {

						}
						if (pontuacaoJogador1.getValue() == 100) {
							pontuacaoJogador1.setValue(0);
							i = 0;
						}
						if (pontuacaoJogador1.getValue() == jogo.getJogador1().getPontuacao() * 10) {
							break;
						}
					}

				} else {
					for (int i = pontuacaoJogador2.getValue(); i <= 100; i++) {
						pontuacaoJogador2.setValue(i);
						try {
							Thread.sleep(17);
						} catch (InterruptedException e) {

						}
						if (pontuacaoJogador2.getValue() == 100) {
							pontuacaoJogador2.setValue(0);
							i = 0;
						}
						if (pontuacaoJogador2.getValue() == jogo.getJogador2().getPontuacao() * 10) {
							break;
						}
					}
				}
			}
		};
		pontuacao.start();
		lblNivelJogador1.setText(Integer.toString(jogo.getJogador1().getNivel()));
		lblNivelJogador2.setText(Integer.toString(jogo.getJogador2().getNivel()));

	}

	public void esvaziaCampos() {

		btnAlternativaA.setText(" ");
		btnAlternativaB.setText(" ");
		btnAlternativaC.setText(" ");
		btnAlternativaD.setText(" ");
		txtPergunta.setText(" ");

		btnAlternativaA.setEnabled(true);
		btnAlternativaB.setEnabled(true);
		btnAlternativaC.setEnabled(true);
		btnAlternativaD.setEnabled(true);
		btnAjuda.setVisible(true);

	}

	public void ajuda() {

		if (jogo.ajudar()) {
			int r = new Random().nextInt(2);
			if (r == 0) {
				List<Resposta> listaResposta = new Resposta().ler();
				String enunciadoResposta = " ";
				int i = 0, cont = 0;
				for (Resposta resp : listaResposta) {
					if (resp.getPergunta().getID() == jogo.getPergunta().getID()) {

						if (resp.getID() == jogo.getPergunta().getAlternativaCorreta().getID())
							enunciadoResposta = resp.getEnunciado();

						i = new Random().nextInt(4);
						if (i == 0 && !(btnAlternativaA.getText().equals(enunciadoResposta))
								&& btnAlternativaA.isEnabled()) {
							btnAlternativaA.setEnabled(false);
							cont++;
						} else if (i == 1 && !(btnAlternativaB.getText().equals(enunciadoResposta))
								&& btnAlternativaB.isEnabled()) {
							btnAlternativaB.setEnabled(false);
							cont++;
						} else if (i == 2 && !(btnAlternativaC.getText().equals(enunciadoResposta))
								&& btnAlternativaC.isEnabled()) {
							btnAlternativaC.setEnabled(false);
							cont++;
						} else if (i == 3 && !(btnAlternativaD.getText().equals(enunciadoResposta))
								&& btnAlternativaD.isEnabled()) {
							btnAlternativaD.setEnabled(false);
							cont++;
						}
					}
					if (cont == 2)
						break;
				}
			} else {
				jogo.setTempo(jogo.getTempo() + 10);
				this.lblTempo.setForeground(Color.YELLOW);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Voce não pode pedir ajuda neste momento", "Ajuda",
					JOptionPane.INFORMATION_MESSAGE);
		}

		atualizaDadosAjuda();

	}

	private void cronometro() {

		tempo = new Thread() {

			public void run() {

				int segundos = 20;

				try {
					for (jogo.setTempo(segundos); jogo.getTempo() >= 0; jogo.setTempo(jogo.getTempo() - 1)) {
						if (jogo.getTempo() > 9) {
							lblTempo.setForeground(Color.WHITE);
							lblTempo.setText("00:" + Integer.toString(jogo.getTempo()));
						} else {
							lblTempo.setText("00:0" + Integer.toString(jogo.getTempo()));
							lblTempo.setForeground(Color.ORANGE);
						}

						if (jogo.getTempo() <= 5)
							lblTempo.setForeground(Color.RED);

						if (jogo.getTempo() == 0) {
							alteraPainel();
							// Para arrumar uma falha do sistema
							alterarTurnoCronometro();
						}
						Thread.sleep(1000);
					}
				} catch (Exception e) {

				}

			}

		};
		tempo.start();
	}

	private void continuarJogo() {

		boolean achou = false;
		List<Jogo> listaJogo = new Jogo().ler();
		Jogo jogoCarregado = new Jogo();
		for (Jogo jogo : listaJogo) {	
			if (jogo.getJogador1().getID() == Jogador.jogadorLogado.getID()) {
				achou = true;
				jogoCarregado = jogo;
			}

		}
		if (achou) {
			int escolha = JOptionPane.showOptionDialog(null, "Deseja continuar partida anterior?", "Continuar?",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (escolha == 0) {
				
				jogo = new Jogo(jogoCarregado.getID(), jogoCarregado.getTempo(), jogoCarregado.getTurno(),
						jogoCarregado.getRodada() , jogoCarregado.getAcertosJogador1(),
						jogoCarregado.getAcertosJogador2(), jogoCarregado.getPergunta(), Jogador.jogadorLogado,
						jogoCarregado.getJogador2());
				carregarInformacoes();
			} else {
				jogoCarregado.deletar();

				jogo = new Jogo();
				buscaJogador();
				atualizarInformacoes();
				carregarInformacoes();
				escolherJogadorInicio();
				carregarInformacoes();
			}
		} else {
			jogo = new Jogo();
			atualizarInformacoes();
			buscaJogador();
			carregarInformacoes();
			escolherJogadorInicio();
			carregarInformacoes();

		}

		buscaPergunta();

	}

	private void alterarTurnoCronometro() {
		if (jogo.getTurno()) {

			lblNivelJogador2.setFont(new Font("Aileron", Font.PLAIN, 16));
			lblNivelJogador2.setBounds(707, 70, 20, 20);

			lblIconeJogador2.setIcon(new ImageIcon("src/img/userUnfocusabled.png"));
			lblIconeJogador2.setBounds(667, 31, 64, 64);

			ImageIcon ic = new ImageIcon("src/img/userFocusabled.png");
			Image im = ic.getImage();
			im = im.getScaledInstance(96, 96, java.awt.Image.SCALE_SMOOTH);
			lblIconeJogador1.setIcon(new ImageIcon(im));
			lblIconeJogador1.setBounds(40, 15, 96, 96);

			lblNivelJogador1.setFont(new Font("Aileron", Font.PLAIN, 20));
			lblNivelJogador1.setBounds(103, 76, 26, 26);

			// lblJogadorAtual.setText(jogo.getJogador1().getNome());
			pontuacaoJogador2.setForeground(Color.gray);
			pontuacaoJogador1.setForeground(Color.GREEN);

		} else {

			lblNivelJogador1.setFont(new Font("Aileron", Font.PLAIN, 16));
			lblNivelJogador1.setBounds(88, 70, 20, 20);

			lblIconeJogador1.setIcon(new ImageIcon("src/img/userUnfocusabled.png"));
			lblIconeJogador1.setBounds(47, 31, 64, 64);

			ImageIcon ic = new ImageIcon("src/img/userFocusabled.png");
			Image im = ic.getImage();
			im = im.getScaledInstance(96, 96, java.awt.Image.SCALE_SMOOTH);
			lblIconeJogador2.setIcon(new ImageIcon(im));
			lblIconeJogador2.setBounds(655, 15, 96, 96);

			lblNivelJogador2.setFont(new Font("Aileron", Font.PLAIN, 20));
			lblNivelJogador2.setBounds(718, 76, 26, 26);
			pontuacaoJogador1.setForeground(Color.gray);
			pontuacaoJogador2.setForeground(Color.GREEN);
			// lblJogadorAtual.setText(jogo.getJogador2().getNome());

		}
	}

	public void alteraPainel() {
		
		jogo.setRodada(jogo.getRodada() + 1);
		if (jogo.getRodada() == 16) {
			jogo.setFinalizado(true);
			tempo.interrupt();
			jogo.sair();
			new tFinal().setVisible(true);
			dispose();
		}

		PerguntaExibida perguntaExibida = new PerguntaExibida();
		perguntaExibida.setPartida(jogo);
		perguntaExibida.setPergunta(jogo.getPergunta());
		perguntaExibida.gravar();
		try {

			tempo.interrupt();
		} catch (Exception e) {

		}

		txtPergunta.setVisible(false);
		btnAlternativaA.setVisible(false);
		btnAlternativaB.setVisible(false);
		btnAlternativaC.setVisible(false);
		btnAlternativaD.setVisible(false);
		btnAjuda.setVisible(false);
		btnDesistir.setVisible(false);
		btnSair.setVisible(false);
		lblTempo.setVisible(false);
		lblCategoria.setVisible(false);
		lblRodada.setVisible(false);
		lblIconepergunta.setVisible(false);
		lblIconeautor.setVisible(false);
		lblAutor.setVisible(false);

		lblVezjogador.setVisible(true);
		btnSair_1.setVisible(true);
		btnContinuar.setVisible(true);
		lblResposta.setVisible(true);

		if (jogo.getTempo() == 0) {

			alterarTurno();
			if (jogo.getTurno())
				lblVezjogador.setText(jogo.getJogador1().getNome());
			else
				lblVezjogador.setText(jogo.getJogador2().getNome());
			lblAnunciarmudanca.setVisible(true);
			lblResposta.setText("Tempo esgotado!");
		} else if (validarResposta()) {
			lblResposta.setText("Você acertou!");
			lblVezjogador.setVisible(false);
		} else {
			alterarTurno();
			if (jogo.getTurno())
				lblVezjogador.setText(jogo.getJogador1().getNome());
			else
				lblVezjogador.setText(jogo.getJogador2().getNome());
			lblAnunciarmudanca.setVisible(true);
			lblResposta.setText("Você errou!");
		}

	}

	public void voltarPainel() {

		lblVezjogador.setVisible(false);
		btnSair_1.setVisible(false);
		btnContinuar.setVisible(false);
		lblResposta.setVisible(false);
		lblAnunciarmudanca.setVisible(false);

		txtPergunta.setVisible(true);
		btnAlternativaA.setVisible(true);
		btnAlternativaB.setVisible(true);
		btnAlternativaC.setVisible(true);
		btnAlternativaD.setVisible(true);
		btnAjuda.setVisible(true);
		btnDesistir.setVisible(true);
		btnSair.setVisible(true);
		lblTempo.setVisible(true);
		lblCategoria.setVisible(true);
		lblRodada.setVisible(true);
		lblIconepergunta.setVisible(true);
		lblIconeautor.setVisible(true);
		lblAutor.setVisible(true);
	}

	private void criarCampos() {

		this.lblFundo = new JLabel();
		this.lblFundo.setIcon(new ImageIcon("src/img/fundo8.png"));
		this.lblFundo.setBounds(0, 0, 794, 571);

		this.lblNomeJogador1 = new JLabel();
		this.lblNomeJogador1.setForeground(Color.WHITE);
		this.lblNomeJogador1.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNomeJogador1.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.lblNomeJogador1.setBounds(0, 110, 158, 20);

		this.lblNomeJogador2 = new JLabel();
		this.lblNomeJogador2.setForeground(Color.WHITE);
		this.lblNomeJogador2.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNomeJogador2.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.lblNomeJogador2.setBounds(620, 110, 157, 20);

		this.lblHorcruxJogador1 = new JLabel();
		this.lblHorcruxJogador1.setForeground(Color.WHITE);
		this.lblHorcruxJogador1.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblHorcruxJogador1.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.lblHorcruxJogador1.setBounds(47, 131, 30, 29);

		this.lblHorcruxJogador2 = new JLabel();
		this.lblHorcruxJogador2.setForeground(Color.WHITE);
		this.lblHorcruxJogador2.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblHorcruxJogador2.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.lblHorcruxJogador2.setBounds(668, 131, 30, 29);

		this.lblTempo = new JLabel("00:");
		this.lblTempo.setForeground(Color.WHITE);
		this.lblTempo.setFont(new Font("Aileron", Font.PLAIN, 22));
		this.lblTempo.setBounds(542, 124, 73, 36);

		this.lblIconepergunta = new JLabel();
		this.lblIconepergunta.setBounds(191, 336, 33, 33);

		this.lblCategoria = new JLabel();
		this.lblCategoria.setForeground(Color.WHITE);
		this.lblCategoria.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.lblCategoria.setBounds(228, 336, 125, 33);

		this.lblAutor = new JLabel();
		this.lblAutor.setForeground(Color.WHITE);
		this.lblAutor.setFont(new Font("Aileron", Font.PLAIN, 13));
		this.lblAutor.setBounds(435, 336, 170, 33);

		this.lblRodada = new JLabel();
		this.lblRodada.setForeground(Color.WHITE);
		this.lblRodada.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblRodada.setFont(new Font("Aileron", Font.PLAIN, 22));
		this.lblRodada.setBounds(396, 119, 64, 36);

		this.lblVezjogador = new JLabel();
		this.lblVezjogador.setVisible(false);
		this.lblVezjogador.setForeground(Color.WHITE);
		this.lblVezjogador.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblVezjogador.setFont(new Font("Aileron", Font.PLAIN, 26));
		this.lblVezjogador.setBounds(208, 289, 365, 35);

		this.lblAnunciarmudanca = new JLabel("Agora é a vez do jogador:");
		this.lblAnunciarmudanca.setForeground(Color.WHITE);
		this.lblAnunciarmudanca.setVisible(false);
		this.lblAnunciarmudanca.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblAnunciarmudanca.setFont(new Font("Aileron", Font.PLAIN, 26));
		this.lblAnunciarmudanca.setBounds(208, 243, 365, 35);

		this.lblResposta = new JLabel();
		this.lblResposta.setForeground(Color.WHITE);
		this.lblResposta.setVisible(false);
		this.lblResposta.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblResposta.setFont(new Font("Aileorn", Font.PLAIN, 30));
		this.lblResposta.setBounds(208, 162, 365, 41);

		this.lblIconeautor = new JLabel();
		this.lblIconeautor.setIcon(new ImageIcon("src/img/user32.png"));
		this.lblIconeautor.setBounds(396, 336, 33, 33);

		this.lblNivelJogador1 = new JLabel("99");
		this.lblNivelJogador1.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNivelJogador1.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.lblNivelJogador1.setBounds(88, 70, 20, 20);

		this.lblNivelJogador2 = new JLabel("99");
		this.lblNivelJogador2.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNivelJogador2.setFont(new Font("Aileron", Font.PLAIN, 20));
		this.lblNivelJogador2.setBounds(718, 76, 26, 26);

		this.txtPergunta = new JTextArea();
		this.txtPergunta.setForeground(Color.WHITE);
		this.txtPergunta.setEditable(false);
		this.txtPergunta.setLineWrap(true);
		this.txtPergunta.setOpaque(false);
		this.txtPergunta.setWrapStyleWord(true);
		this.txtPergunta.setBackground(null);
		this.txtPergunta.setFont(new Font("Aileron", Font.PLAIN, 18));
		this.txtPergunta.setBounds(168, 166, 447, 159);

		this.lblIconeJogador1 = new JLabel();
		this.lblIconeJogador1.setIcon(new ImageIcon("src/img/userUnfocusabled.png"));
		this.lblIconeJogador1.setBounds(47, 31, 64, 64);

		this.lblIconeJogador2 = new JLabel();
		ImageIcon ic = new ImageIcon("src/img/userFocusabled.png");
		Image im = ic.getImage();
		im = im.getScaledInstance(96, 96, java.awt.Image.SCALE_SMOOTH);
		this.lblIconeJogador2.setIcon(new ImageIcon(im));
		this.lblIconeJogador2.setBounds(655, 15, 96, 96);

		this.lblHorcruxMaximo1 = new JLabel("/ 7");
		this.lblHorcruxMaximo1.setForeground(Color.WHITE);
		this.lblHorcruxMaximo1.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.lblHorcruxMaximo1.setBounds(81, 135, 30, 20);

		this.lblHorcruxMaximo2 = new JLabel("/ 7");
		this.lblHorcruxMaximo2.setForeground(Color.WHITE);
		this.lblHorcruxMaximo2.setFont(new Font("HelvLight", Font.PLAIN, 16));
		this.lblHorcruxMaximo2.setBounds(702, 135, 30, 20);

		this.pontuacaoJogador1 = new JProgressBar();
		this.pontuacaoJogador1.setMaximum(100);
		this.pontuacaoJogador1.setValue(5);
		this.pontuacaoJogador1.setBorderPainted(false);
		this.pontuacaoJogador1.setOrientation(SwingConstants.VERTICAL);
		this.pontuacaoJogador1.setBounds(68, 166, 25, 368);

		this.pontuacaoJogador2 = new JProgressBar();
		this.pontuacaoJogador2.setValue(8);
		this.pontuacaoJogador2.setOrientation(SwingConstants.VERTICAL);
		this.pontuacaoJogador2.setBorderPainted(false);
		this.pontuacaoJogador2.setMaximum(100);
		this.pontuacaoJogador2.setBounds(688, 166, 25, 368);

	}

	private void criarBotoes() {

		this.btnAlternativaA = new JButton();
		this.btnAlternativaA.addMouseListener(this);
		this.btnAlternativaA.setHorizontalAlignment(SwingConstants.LEFT);
		this.btnAlternativaA.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.btnAlternativaA.setBounds(168, 385, 447, 29);

		this.btnAlternativaB = new JButton();
		this.btnAlternativaB.addMouseListener(this);
		this.btnAlternativaB.setHorizontalAlignment(SwingConstants.LEFT);
		this.btnAlternativaB.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.btnAlternativaB.setBounds(168, 425, 447, 29);

		this.btnAlternativaC = new JButton();
		this.btnAlternativaC.addMouseListener(this);
		this.btnAlternativaC.setHorizontalAlignment(SwingConstants.LEFT);
		this.btnAlternativaC.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.btnAlternativaC.setBounds(168, 465, 447, 29);

		this.btnAlternativaD = new JButton();
		this.btnAlternativaD.addMouseListener(this);
		this.btnAlternativaD.setHorizontalAlignment(SwingConstants.LEFT);
		this.btnAlternativaD.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.btnAlternativaD.setBounds(168, 505, 447, 29);

		this.btnSair = new JButton();
		this.btnSair.addMouseListener(this);
		this.btnSair.setToolTipText("Sair do jogo");
		this.btnSair.setIcon(new ImageIcon("src/img/back2.png"));
		this.btnSair.setBounds(168, 119, 36, 36);
		this.btnSair.setContentAreaFilled(false);
		this.btnSair.setBorder(null);

		this.btnDesistir = new JButton();
		this.btnDesistir.addMouseListener(this);
		this.btnDesistir.setBorder(null);
		this.btnDesistir.setToolTipText("Desistir da partida e voltar ao menu principal");
		this.btnDesistir.setIcon(new ImageIcon("src/img/desistir.png"));
		this.btnDesistir.setBounds(214, 119, 36, 36);
		this.btnDesistir.setContentAreaFilled(false);

		this.btnAjuda = new JButton("");
		this.btnAjuda.setToolTipText("Solitar ajuda");
		this.btnAjuda.addMouseListener(this);
		this.btnAjuda.setIcon(new ImageIcon("src/img/help.png"));
		this.btnAjuda.setBounds(260, 119, 36, 36);
		this.btnAjuda.setBorder(null);
		this.btnAjuda.setContentAreaFilled(false);

		this.btnSair_1 = new JButton("SAIR");
		this.btnSair_1.setVisible(false);
		this.btnSair_1.addMouseListener(this);
		this.btnSair_1.setIcon(new ImageIcon("src/img/back3.png"));
		this.btnSair_1.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.btnSair_1.setBounds(205, 397, 127, 41);

		this.btnContinuar = new JButton("Continuar");
		this.btnContinuar.setVisible(false);
		this.btnContinuar.addMouseListener(this);
		this.btnContinuar.setIcon(new ImageIcon("src/img/resume.png"));
		this.btnContinuar.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.btnContinuar.setBounds(345, 397, 228, 41);

	}

	private void criarPainel() {

		this.painelGeral = new JPanel();
		this.painelGeral.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.painelGeral.setLayout(null);
		this.painelGeral.add(this.txtPergunta);
		this.painelGeral.add(this.lblNomeJogador1);
		this.painelGeral.add(this.lblNomeJogador2);
		this.painelGeral.add(this.lblHorcruxJogador1);
		this.painelGeral.add(this.lblHorcruxJogador2);
		// this.painelGeral.add(this.lblJogadorAtual);
		this.painelGeral.add(this.lblTempo);
		this.painelGeral.add(this.lblIconepergunta);
		this.painelGeral.add(this.lblCategoria);
		this.painelGeral.add(this.lblAutor);
		this.painelGeral.add(this.lblRodada);
		this.painelGeral.add(this.lblNivelJogador1);
		this.painelGeral.add(this.lblNivelJogador2);
		this.painelGeral.add(this.lblIconeautor);
		this.painelGeral.add(this.pontuacaoJogador1);
		this.painelGeral.add(this.pontuacaoJogador2);
		this.painelGeral.add(this.btnAlternativaA);
		this.painelGeral.add(this.btnAlternativaB);
		this.painelGeral.add(this.btnAlternativaC);
		this.painelGeral.add(this.btnAlternativaD);
		this.painelGeral.add(this.btnSair);
		this.painelGeral.add(this.btnDesistir);
		this.painelGeral.add(this.btnAjuda);
		this.painelGeral.add(this.lblIconeJogador1);
		this.painelGeral.add(this.lblIconeJogador2);
		this.painelGeral.add(this.lblHorcruxMaximo1);
		this.painelGeral.add(this.lblHorcruxMaximo2);

		this.painelGeral.add(this.lblVezjogador);
		this.painelGeral.add(this.lblAnunciarmudanca);
		this.painelGeral.add(this.lblResposta);
		this.painelGeral.add(this.btnContinuar);
		this.painelGeral.add(this.btnSair_1);
		this.painelGeral.add(this.lblFundo);
		setContentPane(painelGeral);

	}

	private void criarJanela() {
		this.setBounds(0, 0, 800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/img/logo.png"));
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btnSair) {
			if (tocar)
				som.mouseClick();
			pararMusica();
			tempo.interrupt();
			jogo.sair();
			new tMenu().setVisible(true);
			dispose();
		} else if (e.getSource() == btnAlternativaA) {
			enunciadoResposta = btnAlternativaA.getText();
			alteraPainel();
		} else if (e.getSource() == btnAlternativaB) {
			enunciadoResposta = btnAlternativaB.getText();
			alteraPainel();
		} else if (e.getSource() == btnAlternativaC) {
			enunciadoResposta = btnAlternativaC.getText();
			alteraPainel();
		} else if (e.getSource() == btnAlternativaD) {
			enunciadoResposta = btnAlternativaD.getText();
			alteraPainel();
		} else if (e.getSource() == btnSair_1) {
			if (tocar)
				som.mouseClick();
			
			pararMusica();
			tempo.interrupt();
			jogo.sair();
			new tMenu().setVisible(true);
			dispose();
		} else if (e.getSource() == btnContinuar) {
			if (tocar)
				som.mouseClick();
			buscaPergunta();
			voltarPainel();
		} else if (e.getSource() == btnDesistir) {
			if (tocar)
				som.mouseClick();
			pararMusica();
			desistir();
		} else if (e.getSource() == btnAjuda) {
			if (tocar)
				som.mouseClick();
			ajuda();
			this.btnAjuda.setVisible(false);
		}
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == btnSair) {
			this.btnSair.setIcon(new ImageIcon("src/img/back.png"));
		} else if (e.getSource() == btnAjuda) {
			this.btnAjuda.setIcon(new ImageIcon("src/img/help2.png"));
		} else if (e.getSource() == btnDesistir) {
			this.btnDesistir.setIcon(new ImageIcon("src/img/desistir2.png"));
		} else if (e.getSource() == btnSair_1) {
			this.btnSair_1.setIcon(new ImageIcon("src/img/back.png"));
		} else if (e.getSource() == btnContinuar) {
			this.btnContinuar.setIcon(new ImageIcon("src/img/resume2.png"));
		}

	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource() == btnSair) {
			this.btnSair.setIcon(new ImageIcon("src/img/back2.png"));
		} else if (e.getSource() == btnAjuda) {
			this.btnAjuda.setIcon(new ImageIcon("src/img/help.png"));
		} else if (e.getSource() == btnDesistir) {
			this.btnDesistir.setIcon(new ImageIcon("src/img/desistir.png"));
		} else if (e.getSource() == btnContinuar) {
			this.btnContinuar.setIcon(new ImageIcon("src/img/resume.png"));
		} else if (e.getSource() == btnSair_1) {
			this.btnSair_1.setIcon(new ImageIcon("src/img/back3.png"));
		}

	}

}
