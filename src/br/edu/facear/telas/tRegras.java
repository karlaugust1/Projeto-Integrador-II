package br.edu.facear.telas;

import javax.swing.*;
import javax.swing.border.*;

import br.edu.facear.util.Som;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class tRegras extends JFrame implements MouseListener {

	private JPanel painelGeral;
	private JTextArea txtRegras;
	private JLabel lblFundo, lblRegras, lblIcone, lblRegraAjuda, lblIconeRegraAjuda, lblRegraDesistir,
			lblIconeRegraDesistir, lblIconeRegraSair, lblRegraSair;
	private JButton btnVoltar;
	private Som som;
	public static boolean tocar;

	public tRegras() {
		super("JUST ANOTHER QUIZ GAME");
		som = new Som();

		criarJanela();
		criarCampos();
		criarBotoes();
		criarPainel();

	}

	private void criarCampos() {

		this.lblFundo = new JLabel();
		this.lblFundo.setIcon(new ImageIcon("src/img/fundo8.png"));
		this.lblFundo.setBounds(0, 0, 794, 571);

		this.lblRegras = new JLabel("REGRAS");
		this.lblRegras.setForeground(Color.WHITE);
		this.lblRegras.setFont(new Font("Aileron", Font.PLAIN, 30));
		this.lblRegras.setBounds(167, 45, 178, 37);

		this.lblIcone = new JLabel("");
		this.lblIcone.setIcon(new ImageIcon("src/img/info2.png"));
		this.lblIcone.setBounds(93, 36, 64, 64);

		this.lblIconeRegraAjuda = new JLabel();
		this.lblIconeRegraAjuda.setIcon(new ImageIcon("src/img/help2.png"));
		this.lblIconeRegraAjuda.setBounds(68, 150, 32, 32);

		this.lblRegraAjuda = new JLabel("ESTE BOTÃO FAZ COM QUE O JOGADOR POSSA PEDIR AJUDA PARA UMA QUESTÃO");
		this.lblRegraAjuda.setForeground(Color.WHITE);
		this.lblRegraAjuda.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.lblRegraAjuda.setBounds(110, 153, 684, 25);

		this.lblIconeRegraDesistir = new JLabel("");
		this.lblIconeRegraDesistir.setIcon(new ImageIcon("src/img/desistir2.png"));
		this.lblIconeRegraDesistir.setBounds(68, 196, 32, 32);

		this.lblRegraDesistir = new JLabel("ESTE BOTÃO FAZ COM QUE O JOGADOR DESISTA DA PARTIDA");
		this.lblRegraDesistir.setForeground(Color.WHITE);
		this.lblRegraDesistir.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.lblRegraDesistir.setBounds(110, 197, 684, 25);

		this.lblIconeRegraSair = new JLabel("");
		this.lblIconeRegraSair.setIcon(new ImageIcon("src/img/back2.png"));
		this.lblIconeRegraSair.setBounds(68, 239, 32, 32);

		this.lblRegraSair = new JLabel("ESTE BOTÃO FAZ COM QUE VOCÊ SAIA DO JOGO, SALVANDO-O");
		this.lblRegraSair.setForeground(Color.WHITE);
		this.lblRegraSair.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.lblRegraSair.setBounds(110, 240, 684, 25);

		this.txtRegras = new JTextArea();
		this.txtRegras.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtRegras.setForeground(Color.WHITE);
		this.txtRegras.setEditable(false);
		this.txtRegras.setOpaque(false);
		this.txtRegras.setBackground(null);
		this.txtRegras.setWrapStyleWord(true);
		this.txtRegras.setLineWrap(true);
		this.txtRegras.setBounds(72, 293, 684, 310);
		this.txtRegras.setText(
				"-> A CADA QUESTÃO CORRETA O JOGADOR GANHA 3 PONTOS\n\n-> A CADA 10 PONTOS GANHOS VOCÊ GANHA UM HORCRUX\n\n-> A CADA 7 HORCRUX VOCÊ GANHA UM NÍVEL\n\n-> O CUSTO DA AJUDA É DE 5 PONTOS\n\n-> CADA QUESTÃO TERÁ 20 SEGUNDOS PARA SER RESPONDIDA\n\n-> CADA PARTIDA TERÁ NO MÁXIMO 15 RODADAS");

	}

	private void criarBotoes() {

		this.btnVoltar = new JButton();
		this.btnVoltar.setIcon(new ImageIcon("src/img/back.png"));
		this.btnVoltar.setBorderPainted(false);
		this.btnVoltar.addMouseListener(this);
		this.btnVoltar.setContentAreaFilled(false);
		this.btnVoltar.setBounds(49, 49, 34, 34);

	}

	private void criarPainel() {

		painelGeral = new JPanel();
		painelGeral.setBorder(new EmptyBorder(5, 5, 5, 5));
		painelGeral.setLayout(null);
		this.painelGeral.add(this.lblRegras);
		this.painelGeral.add(this.lblIcone);
		this.painelGeral.add(this.lblIconeRegraAjuda);
		this.painelGeral.add(this.lblRegraAjuda);
		this.painelGeral.add(this.lblIconeRegraDesistir);
		this.painelGeral.add(this.lblRegraDesistir);
		this.painelGeral.add(this.lblIconeRegraSair);
		this.painelGeral.add(this.lblRegraSair);
		this.painelGeral.add(this.txtRegras);
		this.painelGeral.add(this.btnVoltar);
		this.painelGeral.add(this.lblFundo);

		this.setContentPane(this.painelGeral);

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
		if (e.getSource() == btnVoltar) {
			if (tocar)
				som.mouseClick();
			new tMenu().setVisible(true);
			dispose();
		}

	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == btnVoltar) {
			this.btnVoltar.setIcon(new ImageIcon("src/img/back2.png"));
		}
	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource() == btnVoltar) {
			this.btnVoltar.setIcon(new ImageIcon("src/img/back.png"));
		}
	}
}
