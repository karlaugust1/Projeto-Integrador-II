package br.edu.facear.telas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

import br.edu.facear.classes.*;
import br.edu.facear.util.Som;

@SuppressWarnings("serial")
public class tFinal extends JFrame implements MouseListener {

	private JPanel painelGeral;
	private JLabel lblFundo, lblResultado, lblJogador, lblFogos, lblFogos1, lblFogos2, lblFogos3;
	private JButton btnSair, btnJogarNovamente;
	private Jogo jogo;
	private Som som;

	public tFinal() {
		super("JUST ANOTHER QUIZ GAME");
		som = new Som();
		
		criarJanela();
		criarBotoes();
		criarCampos();
		criarPainel();

		carregarInformacoes();
		atualizaDados();
	}

	public void carregarInformacoes() {

		List<Jogo> listaJogo = new Jogo().ler();
		for (Jogo jog : listaJogo) {
			if (jog.getJogador1().getID() == Jogador.jogadorLogado.getID()) {
				jogo = new Jogo(jog.getID(), jog.getTempo(), jog.getTurno(), jog.getRodada(), jog.getAcertosJogador1(),
						jog.getAcertosJogador2(), jog.getPergunta(), jog.getJogador1(), jog.getJogador2());
			}
		}

	}

	public void atualizaDados() {

		Jogador jogador = jogo.exibirGanhador();
		if (jogador != null) {
			lblResultado.setText("PARABÉNS!");
			lblJogador.setText(jogador.getNome() + " você foi o ganhador desta partida!");
			this.lblFogos.setIcon(new ImageIcon("src/img/azul.gif"));
			this.lblFogos1.setIcon(new ImageIcon("src/img/amarelo.gif"));
			this.lblFogos2.setIcon(new ImageIcon("src/img/vermelho.gif"));
			this.lblFogos3.setIcon(new ImageIcon("src/img/verde.gif"));
			
		} else {
			lblResultado.setText("OOPS! EMPATOU!");
			lblJogador.setText("Quem sabe na próxima alguém consiga ser melhor.");
		}
		jogo.deletar();
	}

	private void criarCampos() {

		this.lblFundo = new JLabel();
		this.lblFundo.setIcon(new ImageIcon("src/img/fundo8.png"));
		this.lblFundo.setBounds(0, 0, 794, 571);

		this.lblResultado = new JLabel();
		this.lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblResultado.setFont(new Font("Aileron", Font.PLAIN, 40));
		this.lblResultado.setForeground(Color.WHITE);
		this.lblResultado.setBounds(225, 159, 350, 126);

		this.lblJogador = new JLabel();
		this.lblJogador.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblJogador.setFont(new Font("Aileron", Font.PLAIN, 30));
		this.lblJogador.setForeground(Color.WHITE);
		this.lblJogador.setBounds(10, 286, 774, 64);

		this.lblFogos = new JLabel();
		this.lblFogos.setBounds(0, 159, 400, 379);

		this.lblFogos1 = new JLabel();
		this.lblFogos1.setBounds(350, 30, 400, 379);
		
		this.lblFogos2 = new JLabel();
		this.lblFogos2.setBounds(40, 0, 400, 379);
		
		this.lblFogos3 = new JLabel();
		this.lblFogos3.setBounds(400, 159, 400, 379);
		
		
			
	}

	private void criarBotoes() {

		this.btnSair = new JButton("SAIR");
		this.btnSair.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.btnSair.setIcon(new ImageIcon("src/img/back3.png"));
		this.btnSair.addMouseListener(this);
		this.btnSair.setBounds(166, 428, 178, 45);

		this.btnJogarNovamente = new JButton("JOGAR NOVAMENTE");
		this.btnJogarNovamente.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.btnJogarNovamente.setIcon(new ImageIcon("src/img/novojogo.png"));
		this.btnJogarNovamente.addMouseListener(this);
		this.btnJogarNovamente.setBounds(354, 428, 280, 45);

	}

	private void criarPainel() {

		this.painelGeral = new JPanel();
		this.painelGeral.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.painelGeral.setLayout(null);
		this.painelGeral.add(this.lblResultado);
		this.painelGeral.add(this.lblJogador);
		this.painelGeral.add(this.btnSair);
		this.painelGeral.add(this.btnJogarNovamente);
		this.painelGeral.add(this.lblFogos);
		this.painelGeral.add(this.lblFogos1);
		this.painelGeral.add(this.lblFogos2);
		this.painelGeral.add(this.lblFogos3);
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
		if(e.getSource() == btnSair) {
			tJogo.pararMusica();
			som.mouseClick();
			new tMenu().setVisible(true);
			dispose();
		}else if(e.getSource() == btnJogarNovamente) {
			som.mouseClick();
			new tJogo().setVisible(true);
			dispose();
		}
		
	}

	public void mousePressed(MouseEvent e) {	}

	public void mouseReleased(MouseEvent e) {	}
	
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == btnSair) {
			this.btnSair.setIcon(new ImageIcon("src/img/back.png"));
		} else if(e.getSource() == btnJogarNovamente) {
			this.btnJogarNovamente.setIcon(new ImageIcon("src/img/novojogo21.png"));
		}
		
	}


	public void mouseExited(MouseEvent e) {
		if(e.getSource() == btnSair) {
			this.btnSair.setIcon(new ImageIcon("src/img/back3.png"));
		}else if(e.getSource() == btnJogarNovamente) {
			this.btnJogarNovamente.setIcon(new ImageIcon("src/img/novojogo.png"));
		}
		
	}

}
