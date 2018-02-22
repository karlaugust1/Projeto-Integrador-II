package br.edu.facear.telas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import br.edu.facear.classes.*;
import br.edu.facear.util.Som;

import java.util.List;

@SuppressWarnings("serial")
public class tAvaliacao extends JFrame implements MouseListener {

	private JPanel painelGeral;
	private JTextArea txtPergunta;
	private JTextField txtAlternativaA, txtAlternativaB, txtAlternativaC, txtAlternativaD, txtAlternativa1,
			txtAlternativa2, txtAlternativa3, txtAlternativa4;
	private JLabel lblFundo, lblCategoria, lblIconeAutor, lblIconeCategoria, lblIconePergunta, lblAutor,
			lblAvaliacaoPerguntas;
	private JButton btnLike, btnVoltar, btnAnterior, btnProxima;
	private Pergunta pergunta;
	private PerguntaAvaliada perguntaAvaliada;
	private List<Pergunta> listaPergunta;
	private List<PerguntaAvaliada> listaPerguntaAvaliada;
	private int posicaoLista;
	private Som som;
	public static boolean tocar;

	public tAvaliacao() {
		super("JUST ANOTHER QUIZ GAME");
		som = new Som();
		
		criarJanela();
		criarCampos();
		criarBotoes();
		criarPainel();
		
		perguntaAvaliada = new PerguntaAvaliada();
		pergunta = new Pergunta();
		listaPergunta = this.pergunta.ler();
		posicaoLista = 0;
		buscaPergunta();
	}
	
	private void buscaPergunta() {

		try {
			this.pergunta = listaPergunta.get(posicaoLista);

			if (verificaIDJogador()) {
				posicaoLista++;
				buscaPergunta();
			} else if (verificaAvaliacaoJogador()) {
				posicaoLista++;
				buscaPergunta();
			} else
				preencheCampos();
		} catch (Exception e) {
			txtPergunta.setText("Você não possui mais perguntas para avaliar");
			lblAutor.setText(" ");
			txtAlternativaA.setText(" ");
			txtAlternativaB.setText(" ");
			txtAlternativaC.setText(" ");
			txtAlternativaD.setText(" ");
			lblCategoria.setText(" ");
			lblIconeCategoria.setIcon(null);
		}

	}
	
	private void preencheCampos() {

		txtPergunta.setText(this.pergunta.getEnunciado());

		Resposta resposta = new Resposta();
		List<Resposta> listaResposta = resposta.ler();

		int i = 0;
		for (Resposta resp : listaResposta) {
			if (resp.getPergunta().getID() == this.pergunta.getID()) {
				if (i == 0) {
					txtAlternativaA.setText(resp.getEnunciado());
				} else if (i == 1) {
					txtAlternativaB.setText(resp.getEnunciado());
				} else if (i == 2) {
					txtAlternativaC.setText(resp.getEnunciado());
				} else if (i == 3) {
					txtAlternativaD.setText(resp.getEnunciado());
				}
				i++;
			}
		}

		Jogador autor = new Jogador();
		List<Jogador> listaJogador = autor.Ler();
		for (Jogador jogador : listaJogador) {
			if (this.pergunta.getAutor().getID() == jogador.getID())
				lblAutor.setText(jogador.getNome());
		}
		
		List<Categoria> listacat = new Categoria().Ler();
		for (Categoria c : listacat) {
			if(this.pergunta.getCategoria().getID() == c.getID()) {
				lblCategoria.setText(c.getNome());
				if(c.getID() == 1) {
					
					this.lblIconeCategoria.setIcon(new ImageIcon("src/img/geografia.png"));
				}else if(c.getID() == 2){
					this.lblIconeCategoria.setIcon(new ImageIcon("src/img/historia.png"));
				}if(c.getID() == 3) {
					this.lblIconeCategoria.setIcon(new ImageIcon("src/img/ciencias.png"));
				}else if(c.getID() == 4) {
					this.lblIconeCategoria.setIcon(new ImageIcon("src/img/jogos.png"));
				}else if(c.getID() == 5) {
					this.lblIconeCategoria.setIcon(new ImageIcon("src/img/programacao.png"));
				}else if(c.getID() == 6) {
					this.lblIconeCategoria.setIcon(new ImageIcon("src/img/atualidades.png"));
				}
				
				break;
			}
		}
		
		
		
	}

	private void proximaPergunta() {
		try {
			posicaoLista++;
			if (posicaoLista == listaPergunta.size()) {
				posicaoLista = 0;
			}

			this.pergunta = listaPergunta.get(posicaoLista);

			if (verificaIDJogador())
				proximaPergunta();
			else if (verificaAvaliacaoJogador())
				proximaPergunta();
			else
				preencheCampos();
		} catch (Exception e) {
			txtPergunta.setText("Você não possui mais perguntas para avaliar");
			lblAutor.setText(" ");
			txtAlternativaA.setText(" ");
			txtAlternativaB.setText(" ");
			txtAlternativaC.setText(" ");
			txtAlternativaD.setText(" ");
			lblCategoria.setText(" ");
		}

	}

	private void perguntaAnterior() {
		try {
			posicaoLista--;
			if (posicaoLista < 0) {
				posicaoLista = listaPergunta.size() - 1;
			}
			this.pergunta = listaPergunta.get(posicaoLista);

			if (verificaIDJogador())
				perguntaAnterior();
			else if (verificaAvaliacaoJogador())
				perguntaAnterior();
			else
				preencheCampos();
		} catch (Exception e) {
			txtPergunta.setText("Você não possui mais perguntas para avaliar");
			lblAutor.setText(" ");
			txtAlternativaA.setText(" ");
			txtAlternativaB.setText(" ");
			txtAlternativaC.setText(" ");
			txtAlternativaD.setText(" ");
			lblCategoria.setText(" ");
		}
	}

	private boolean verificaIDJogador() {

		boolean igual = false;
		if (this.pergunta.getAutor().getID() == Jogador.jogadorLogado.getID()) {
			igual = true;
		}

		return igual;
	}

	private boolean verificaAvaliacaoJogador() {

		boolean avaliou = false;
		PerguntaAvaliada perguntaAvaliada = new PerguntaAvaliada();
		listaPerguntaAvaliada = perguntaAvaliada.ler();

		for (PerguntaAvaliada pa : listaPerguntaAvaliada) {
			if ((pa.getJogador().getID() == Jogador.jogadorLogado.getID())
					&& (pa.getPergunta().getID() == this.pergunta.getID())) {
				avaliou = true;
			}
		}

		return avaliou;
	}

	private void avaliarPergunta() {

		this.pergunta.setAvaliacoes(this.pergunta.getAvaliacoes() + 1);
		this.pergunta.avaliar();

		Jogador jogador = new Jogador();
		jogador.setID(Jogador.jogadorLogado.getID());
		this.perguntaAvaliada.setJogador(jogador);

		this.perguntaAvaliada.setPergunta(this.pergunta);
		this.perguntaAvaliada.gravar();
	}

	private void criarPainel() {

		this.painelGeral = new JPanel();
		this.painelGeral.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.painelGeral.setLayout(null);
		this.painelGeral.add(this.txtPergunta);
		this.painelGeral.add(this.txtAlternativa1);
		this.painelGeral.add(this.txtAlternativa2);
		this.painelGeral.add(this.txtAlternativa3);
		this.painelGeral.add(this.txtAlternativa4);
		this.painelGeral.add(this.txtAlternativaA);
		this.painelGeral.add(this.txtAlternativaB);
		this.painelGeral.add(this.txtAlternativaC);
		this.painelGeral.add(this.txtAlternativaD);
		this.painelGeral.add(this.lblCategoria);
		this.painelGeral.add(this.lblIconeAutor);
		this.painelGeral.add(this.lblIconeCategoria);
		this.painelGeral.add(this.lblIconePergunta);
		this.painelGeral.add(this.lblAutor);
		this.painelGeral.add(this.lblAvaliacaoPerguntas);
		this.painelGeral.add(this.btnLike);
		this.painelGeral.add(this.btnVoltar);
		this.painelGeral.add(this.btnAnterior);
		this.painelGeral.add(this.btnProxima);
		this.painelGeral.add(this.lblFundo);
		this.setContentPane(this.painelGeral);
				
	}

	private void criarBotoes() {

		this.btnLike = new JButton();
		this.btnLike.addMouseListener(this);
		this.btnLike.setIcon(new ImageIcon("src/img/like3.png"));
		this.btnLike.setBorderPainted(false);
		this.btnLike.setContentAreaFilled(false);
		this.btnLike.setBounds(588, 345, 64, 64);
		
		this.btnVoltar = new JButton();
		this.btnVoltar.addMouseListener(this);
		this.btnVoltar.setIcon(new ImageIcon("src/img/back.png"));
		this.btnVoltar.setBounds(49, 49, 34, 34);
		this.btnVoltar.setContentAreaFilled(false);
		this.btnVoltar.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.btnVoltar.setBorderPainted(false);

		this.btnAnterior = new JButton();
		this.btnAnterior.addMouseListener(this);
		this.btnAnterior.setIcon(new ImageIcon("src/img/anterior.png"));
		this.btnAnterior.setBounds(10, 271, 34, 78);
		this.btnAnterior.setContentAreaFilled(false);
		this.btnAnterior.setBorderPainted(false);

		this.btnProxima = new JButton();
		this.btnProxima.addMouseListener(this);
		this.btnProxima.setIcon(new ImageIcon("src/img/proxima.png"));
		this.btnProxima.setBorderPainted(false);
		this.btnProxima.setContentAreaFilled(false);
		this.btnProxima.setBounds(750, 271, 34, 78);

	}
	
	private void criarCampos() {

		this.lblFundo = new JLabel();
		this.lblFundo.setIcon(new ImageIcon("src/img/fundo8.png"));
		this.lblFundo.setBounds(0, 0, 794, 571);

		this.lblIconePergunta = new JLabel();
		this.lblIconePergunta.setIcon(new ImageIcon("src/img/like2.png"));
		this.lblIconePergunta.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblIconePergunta.setBounds(93, 36, 64, 64);

		this.lblAvaliacaoPerguntas = new JLabel("AVALIAÇÃO DE PERGUNTAS");
		this.lblAvaliacaoPerguntas.setForeground(Color.WHITE);
		this.lblAvaliacaoPerguntas.setFont(new Font("Aileron", Font.PLAIN, 30));
		this.lblAvaliacaoPerguntas.setBounds(167, 45, 419, 38);

		this.txtPergunta = new JTextArea();
		this.txtPergunta.setFont(new Font("Aileron", Font.PLAIN, 20));
		this.txtPergunta.setForeground(Color.WHITE);
		this.txtPergunta.setEditable(false);
		this.txtPergunta.setOpaque(false);
		this.txtPergunta.setWrapStyleWord(true);
		this.txtPergunta.setBackground(null);
		this.txtPergunta.setLineWrap(true);
		this.txtPergunta.setBounds(93, 167, 619, 116);

		this.txtAlternativa1 = new JTextField();
		this.txtAlternativa1.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativa1.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtAlternativa1.setEditable(false);
		this.txtAlternativa1.setText("A");
		this.txtAlternativa1.setBounds(93, 294, 26, 26);

		this.txtAlternativaA = new JTextField();
		this.txtAlternativaA.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativaA.setEditable(false);
		this.txtAlternativaA.setBounds(126, 294, 408, 26);

		this.txtAlternativa2 = new JTextField();
		this.txtAlternativa2.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtAlternativa2.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativa2.setEditable(false);
		this.txtAlternativa2.setText("B");
		this.txtAlternativa2.setBounds(93, 324, 26, 26);

		this.txtAlternativaB = new JTextField();
		this.txtAlternativaB.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativaB.setEditable(false);
		this.txtAlternativaB.setBounds(126, 324, 408, 26);

		this.txtAlternativa3 = new JTextField();
		this.txtAlternativa3.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtAlternativa3.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativa3.setEditable(false);
		this.txtAlternativa3.setText("C");
		this.txtAlternativa3.setBounds(93, 354, 26, 26);

		this.txtAlternativaC = new JTextField();
		this.txtAlternativaC.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativaC.setEditable(false);
		this.txtAlternativaC.setBounds(126, 354, 408, 26);

		this.txtAlternativa4 = new JTextField();
		this.txtAlternativa4.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativa4.setEditable(false);
		this.txtAlternativa4.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtAlternativa4.setText("D");
		this.txtAlternativa4.setBounds(93, 384, 26, 26);

		this.txtAlternativaD = new JTextField();
		this.txtAlternativaD.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativaD.setEditable(false);
		this.txtAlternativaD.setBounds(126, 384, 408, 26);

		this.lblIconeAutor = new JLabel();
		this.lblIconeAutor.setIcon(new ImageIcon("src/img/user32.png"));
		this.lblIconeAutor.setBounds(552, 298, 34, 34);

		this.lblAutor = new JLabel("SYSTEM");
		this.lblAutor.setForeground(Color.WHITE);
		this.lblAutor.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.lblAutor.setBounds(588, 303, 152, 20);

		this.lblCategoria = new JLabel("HISTÓRIA");
		this.lblCategoria.setForeground(Color.WHITE);
		this.lblCategoria.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.lblCategoria.setBounds(165, 419, 254, 33);
		
		this.lblIconeCategoria = new JLabel();
		this.lblIconeCategoria.setIcon(new ImageIcon("src/img/historia.png"));
		this.lblIconeCategoria.setBounds(126, 405, 60, 60);

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
			if(tocar)som.mouseClick();
			new tMenu().setVisible(true);
			dispose();
		}else if(e.getSource() == btnAnterior) {
			if(tocar)som.mouseClick();
			perguntaAnterior();
		}else if(e.getSource() == btnProxima) {
			if(tocar)som.mouseClick();
			proximaPergunta();
		}else if(e.getSource() == btnLike) {
			if(tocar)som.mouseClick();
			if (!verificaAvaliacaoJogador()) {
				avaliarPergunta();
				proximaPergunta();
			}
			
		}
	}
	
	public void mousePressed(MouseEvent e) {	}

	public void mouseReleased(MouseEvent e) {	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == btnLike) {
			this.btnLike.setIcon(new ImageIcon("src/img/like2.png"));
		} else if (e.getSource() == btnVoltar) {
			this.btnVoltar.setIcon(new ImageIcon("src/img/back2.png"));
		}else if (e.getSource() == btnProxima) {
			this.btnProxima.setIcon(new ImageIcon("src/img/proxima2.png"));
		}else if (e.getSource() == btnAnterior) {
			this.btnAnterior.setIcon(new ImageIcon("src/img/anterior2.png"));
		}
		
	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource() == btnLike) {
			this.btnLike.setIcon(new ImageIcon("src/img/like3.png"));
		}else if (e.getSource() == btnVoltar) {
			this.btnVoltar.setIcon(new ImageIcon("src/img/back.png"));
		}else if (e.getSource() == btnProxima) {
			this.btnProxima.setIcon(new ImageIcon("src/img/proxima.png"));
		}else if (e.getSource() == btnAnterior) {
			this.btnAnterior.setIcon(new ImageIcon("src/img/anterior.png"));
		}
	}
}
