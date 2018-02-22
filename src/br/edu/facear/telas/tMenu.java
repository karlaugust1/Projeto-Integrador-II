package br.edu.facear.telas;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.*;
import br.edu.facear.classes.*;
import br.edu.facear.util.*;

@SuppressWarnings("serial")
public class tMenu extends JFrame implements MouseListener {

	private JPanel painelGeral;
	private JButton btnClassificacao, btnJogar, btnAvaliacao, btnPerguntas, btnSair, btnRegras, btnSom;
	private JLabel lblFundo, lblJogador, lblNivel, lblHorcrux;
	private Som som;
	private static Clip clip;
	private static AudioInputStream audio;
	private static boolean tocando;
	public static boolean tocar = true;

	public tMenu() {
		super("JUST ANOTHER QUIZ GAME");

		som = new Som();
		criarJanela();
		criarBotoes();
		criarCampos();
		criarPainel();

		if (tocar)
			tocarFundo();

		carregarNomeJogador();

	}

	public void tocarFundo() {

		if (!tocando) {
			String diretorio = "src/sons/", nome = "musicaFundo.wav";
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

	public void carregarNomeJogador() {

		lblJogador.setText(Jogador.jogadorLogado.getNome());
		lblNivel.setText(lblNivel.getText() + Jogador.jogadorLogado.getNivel());
		lblHorcrux.setText(lblHorcrux.getText() + Jogador.jogadorLogado.getHorcrux());

	}

	private void criarCampos() {

		this.lblFundo = new JLabel();
		this.lblFundo.setIcon(new ImageIcon("src/img/fundo8.png"));
		this.lblFundo.setBounds(0, 0, 794, 571);

		this.lblJogador = new JLabel();
		this.lblJogador.setForeground(Color.WHITE);
		this.lblJogador.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblJogador.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.lblJogador.setBounds(451, 31, 210, 20);

		this.lblNivel = new JLabel("Nível: ");
		this.lblNivel.setForeground(Color.WHITE);
		this.lblNivel.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.lblNivel.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblNivel.setBounds(557, 49, 105, 20);

		this.lblHorcrux = new JLabel("Horcrux: ");
		this.lblHorcrux.setForeground(Color.WHITE);
		this.lblHorcrux.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblHorcrux.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.lblHorcrux.setBounds(557, 68, 105, 20);

	}

	private void criarBotoes() {

		this.btnJogar = new JButton();
		this.btnJogar.setIcon(new ImageIcon("src/img/jogar.png"));
		this.btnJogar.addMouseListener(this);
		this.btnJogar.setToolTipText("Inicar partida");
		this.btnJogar.setBounds(165, 142, 225, 301);

		this.btnClassificacao = new JButton("CLASSIFICAÇÃO");
		this.btnClassificacao.setToolTipText("Visualizar classificação de jogadores");
		this.btnClassificacao.setHorizontalAlignment(SwingConstants.LEFT);
		this.btnClassificacao.addMouseListener(this);
		this.btnClassificacao.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.btnClassificacao.setIcon(new ImageIcon("src/img/podium.png"));
		this.btnClassificacao.setBounds(400, 142, 225, 93);

		this.btnAvaliacao = new JButton("AVALIAÇÃO");
		this.btnAvaliacao.setToolTipText("Avalizar perguntas existentes");
		this.btnAvaliacao.setHorizontalAlignment(SwingConstants.LEFT);
		this.btnAvaliacao.setIcon(new ImageIcon("src/img/like.png"));
		this.btnAvaliacao.addMouseListener(this);
		this.btnAvaliacao.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.btnAvaliacao.setBounds(400, 246, 225, 93);

		this.btnPerguntas = new JButton("PERGUNTAS");

		this.btnPerguntas.setToolTipText("Gerenciar suas perguntas");
		this.btnPerguntas.setHorizontalAlignment(SwingConstants.LEFT);
		this.btnPerguntas.setIcon(new ImageIcon("src/img/questions.png"));
		this.btnPerguntas.addMouseListener(this);
		this.btnPerguntas.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.btnPerguntas.setBounds(400, 350, 225, 93);

		this.btnSair = new JButton("");
		this.btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmacao;
				confirmacao = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Sair",
						JOptionPane.YES_NO_OPTION);
				if (confirmacao == 0) {
					System.exit(1);
				}
			}
		});
		this.btnSair.setToolTipText("Sair do sistema");
		this.btnSair.setIcon(new ImageIcon("src/img/exit.png"));
		this.btnSair.setBounds(675, 31, 46, 57);
		this.btnSair.addMouseListener(this);

		this.btnRegras = new JButton("");
		this.btnRegras.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.btnRegras.addMouseListener(this);
		this.btnRegras.setContentAreaFilled(false);
		ImageIcon ii = new ImageIcon("src/img/info.png");
		Image ic = ii.getImage();
		ic = ic.getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);
		this.btnRegras.setIcon(new ImageIcon(ic));
		this.btnRegras.setBounds(675, 482, 50, 57);

		this.btnSom = new JButton();
		this.btnSom.addMouseListener(this);
		this.btnSom.setContentAreaFilled(false);
		this.btnSom.setBorder(null);
		this.btnSom.setBounds(615, 482, 50, 57);
		if (tocar) {
			btnSom.setIcon(new ImageIcon("src/img/som3.png"));
		} else {
			btnSom.setIcon(new ImageIcon("src/img/som4.png"));
		}

	}

	private void criarPainel() {

		this.painelGeral = new JPanel();
		this.painelGeral.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.painelGeral.setLayout(null);
		this.painelGeral.add(this.lblJogador);
		this.painelGeral.add(this.lblNivel);
		this.painelGeral.add(this.lblHorcrux);
		this.painelGeral.add(this.btnJogar);
		this.painelGeral.add(this.btnAvaliacao);
		this.painelGeral.add(this.btnClassificacao);
		this.painelGeral.add(this.btnPerguntas);
		this.painelGeral.add(this.btnSair);
		this.painelGeral.add(this.btnRegras);
		this.painelGeral.add(this.btnSom);
		this.painelGeral.add(this.lblFundo);
		setContentPane(this.painelGeral);

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

		if (e.getSource() == btnAvaliacao) {
			tAvaliacao.tocar = tMenu.tocar;
			if (tocar)
				som.mouseClick();
			new tAvaliacao().setVisible(true);
			dispose();
		} else if (e.getSource() == btnPerguntas) {
			tPerguntas.tocar = tMenu.tocar;
			if (tocar)
				som.mouseClick();
			new tPerguntas().setVisible(true);
			dispose();
		} else if (e.getSource() == btnJogar) {
			tJogo.tocar = tMenu.tocar;
			pararMusica();
			if (tocar)
				som.mouseClick();
			new tJogo().setVisible(true);
			dispose();
		} else if (e.getSource() == btnClassificacao) {
			tClassificacao.tocar = tMenu.tocar;
			if (tocar)
				som.mouseClick();
			new tClassificacao().setVisible(true);
			dispose();
		} else if (e.getSource() == btnRegras) {
			tRegras.tocar = tMenu.tocar;
			if (tocar)
				som.mouseClick();
			new tRegras().setVisible(true);
			dispose();
		} else if (e.getSource() == btnSom) {
			pararMusica();
			if (tocar) {
				tocar = false;
				btnSom.setIcon(new ImageIcon("src/img/som2.png"));
			} else {
				tocar = true;
				btnSom.setIcon(new ImageIcon("src/img/som.png"));
				tocarFundo();
			}
		}
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == btnJogar) {
			this.btnJogar.setIcon(new ImageIcon("src/img/jogar2.png"));
		} else if (e.getSource() == btnAvaliacao) {
			this.btnAvaliacao.setIcon(new ImageIcon("src/img/like2.png"));
		} else if (e.getSource() == btnClassificacao) {
			this.btnClassificacao.setIcon(new ImageIcon("src/img/podium2.png"));
		} else if (e.getSource() == btnPerguntas) {
			this.btnPerguntas.setIcon(new ImageIcon("src/img/questions2.png"));
		} else if (e.getSource() == btnSair) {
			this.btnSair.setIcon(new ImageIcon("src/img/exit2.png"));
		} else if (e.getSource() == btnRegras) {
			ImageIcon ii = new ImageIcon("src/img/info2.png");
			Image ic = ii.getImage();
			ic = ic.getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);
			this.btnRegras.setIcon(new ImageIcon(ic));
			btnRegras.setContentAreaFilled(true);
			btnRegras.setBounds(569, 482, 152, 57);
			btnRegras.setText("REGRAS");
			btnRegras.setFont(new Font("Aileron", Font.PLAIN, 16));
		} else if (e.getSource() == btnSom) {

			if (tocar) {
				btnSom.setIcon(new ImageIcon("src/img/som.png"));
			} else {
				btnSom.setIcon(new ImageIcon("src/img/som2.png"));
			}
		}

	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource() == btnJogar) {
			this.btnJogar.setIcon(new ImageIcon("src/img/jogar.png"));
		} else if (e.getSource() == btnAvaliacao) {
			this.btnAvaliacao.setIcon(new ImageIcon("src/img/like.png"));
		} else if (e.getSource() == btnClassificacao) {
			this.btnClassificacao.setIcon(new ImageIcon("src/img/podium.png"));
		} else if (e.getSource() == btnPerguntas) {
			this.btnPerguntas.setIcon(new ImageIcon("src/img/questions.png"));
		} else if (e.getSource() == btnSair) {
			this.btnSair.setIcon(new ImageIcon("src/img/exit.png"));
		} else if (e.getSource() == btnRegras) {
			ImageIcon ii = new ImageIcon("src/img/info.png");
			Image ic = ii.getImage();
			ic = ic.getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);
			this.btnRegras.setIcon(new ImageIcon(ic));
			btnRegras.setContentAreaFilled(false);
			btnRegras.setBounds(675, 482, 50, 57);
			btnRegras.setText("");
		} else if (e.getSource() == btnSom) {
			if (tocar) {
				btnSom.setIcon(new ImageIcon("src/img/som3.png"));
			} else {
				btnSom.setIcon(new ImageIcon("src/img/som4.png"));
			}
		}

	}
}
