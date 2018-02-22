package br.edu.facear.telas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import br.edu.facear.classes.*;
import br.edu.facear.util.Som;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

@SuppressWarnings("serial")
public class tPerguntas extends JFrame implements MouseListener {

	private JPanel painelGeral;
	private JTextArea txtPergunta;
	private JTextField txtAlternativaA, txtAlternativaB, txtAlternativaC, txtAlternativaD, txtAlternativa1,
			txtAlternativa2, txtAlternativa3, txtAlternativa4;
	private JLabel lblFundo, lblIcone, lblPerguntas, lblCategoria, lblIconeCategoria;
	private JButton btnExcluir, btnEditar, btnElaborarPergunta, btnAnterior, btnProxima, btnVoltar;
	private Pergunta pergunta;
	private List<Pergunta> listaPerguntaJogador;
	private int posicaoLista;
	private Som som;
	public static boolean tocar;

	public tPerguntas() {
		super("JUST ANOTHER QUIZ GAME");
		som = new Som();
		
		criarJanela();
		criarCampos();
		criarBotoes();
		criarPainel();

		Pergunta pergunta = new Pergunta();
		listaPerguntaJogador = pergunta.buscaPerguntaJogador();

		if (verificaLista()) {
			preencheCampos();
		}

	}

	public boolean verificaLista() {

		Pergunta pergunta = new Pergunta();
		this.listaPerguntaJogador = pergunta.buscaPerguntaJogador();

		if (listaPerguntaJogador.isEmpty()) {
			this.txtPergunta.setText("Você ainda não elaborou nenhuma pergunta");
			this.txtAlternativaA.setText("");
			this.txtAlternativaB.setText("");
			this.txtAlternativaC.setText("");
			this.txtAlternativaD.setText("");
			this.lblCategoria.setText("");
			this.lblIconeCategoria.setIcon(null);
			this.posicaoLista = -1;
			return false;
		} else {
			this.pergunta = listaPerguntaJogador.get(posicaoLista);

			return true;
		}

	}

	public void preencheCampos() {

		Resposta resposta = new Resposta();
		List<Resposta> listaResposta = resposta.ler();

		this.txtPergunta.setText(this.pergunta.getEnunciado());

		int i = 0;
		for (Resposta resp : listaResposta) {
			if (resp.getPergunta().getID() == pergunta.getID()) {
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
		Categoria categoria = new Categoria();
		List<Categoria> listaCategoria = categoria.Ler();
		for (Categoria cat : listaCategoria) {
			if (this.pergunta.getCategoria().getID() == cat.getID()) {
				this.lblCategoria.setText(cat.getNome());
				if(cat.getID() == 1)
					this.lblIconeCategoria.setIcon(new ImageIcon("src/img/geografia.png"));
				else if(cat.getID() == 2)
					this.lblIconeCategoria.setIcon(new ImageIcon("src/img/historia.png"));
				else if(cat.getID() == 3)
					this.lblIconeCategoria.setIcon(new ImageIcon("src/img/ciencias.png"));
				else if(cat.getID() == 4)
					this.lblIconeCategoria.setIcon(new ImageIcon("src/img/jogos.png"));
				else if(cat.getID() == 5)
					this.lblIconeCategoria.setIcon(new ImageIcon("src/img/programacao.png"));
				else if(cat.getID() == 6)
					this.lblIconeCategoria.setIcon(new ImageIcon("src/img/atualidades.png"));
			}
		}

	}

	public void excluirPergunta() {

		int confirmacao = 0;

		UIManager.put("OptionPane.yesButtonText", "Confirmar");
		UIManager.put("OptionPane.noButtonText", "Cancelar");

		confirmacao = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir a pergunta?", "Confirmar exclusão",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (confirmacao == 0) {

			Pergunta pergunta = new Pergunta();
			pergunta = this.pergunta;

			Resposta resposta = new Resposta();
			resposta.setPergunta(pergunta);

			resposta.deletar();
			pergunta.deletar();

			JOptionPane.showMessageDialog(null, "Pergunta excluida com sucesso!");

			listaPerguntaJogador = pergunta.buscaPerguntaJogador();
			posicaoLista -= 2;
		}
	}

	public void proximaPergunta() {
		try {
			posicaoLista++;
			if (posicaoLista == listaPerguntaJogador.size()) {
				posicaoLista = 0;
			}

			this.pergunta = listaPerguntaJogador.get(posicaoLista);

			preencheCampos();

		} catch (Exception e) {

		}

	}

	public void perguntaAnterior() {

		try {
			posicaoLista--;
			if (posicaoLista < 0) {
				posicaoLista = listaPerguntaJogador.size() - 1;
			}

			this.pergunta = listaPerguntaJogador.get(posicaoLista);

			preencheCampos();
		} catch (Exception e) {

		}
	}

	private void criarBotoes() {
		this.btnVoltar = new JButton();
		this.btnVoltar.addMouseListener(this);
		this.btnVoltar.setIcon(new ImageIcon("src/img/back.png"));
		this.btnVoltar.setBounds(49, 49, 34, 34);
		this.btnVoltar.setBorderPainted(false);
		this.btnVoltar.setContentAreaFilled(false);

		this.btnAnterior = new JButton();
		this.btnAnterior.addMouseListener(this);
		this.btnAnterior.setIcon(new ImageIcon("src/img/anterior.png"));
		this.btnAnterior.setBounds(10, 271, 34, 78);
		this.btnAnterior.setBorderPainted(false);
		this.btnAnterior.setContentAreaFilled(false);

		this.btnProxima = new JButton();
		this.btnProxima.addMouseListener(this);
		this.btnProxima.setIcon(new ImageIcon("src/img/proxima.png"));
		this.btnProxima.setBounds(750, 271, 34, 78);
		this.btnProxima.setBorderPainted(false);
		this.btnProxima.setContentAreaFilled(false);

		this.btnExcluir = new JButton("Excluir");
		this.btnExcluir.addMouseListener(this);
		this.btnExcluir.setHorizontalAlignment(SwingConstants.LEADING);
		this.btnExcluir.setIcon(new ImageIcon("src/img/trash.png"));
		this.btnExcluir.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.btnExcluir.setBounds(412, 421, 145, 41);

		this.btnEditar = new JButton("Editar");
		this.btnEditar.addMouseListener(this);
		this.btnEditar.setHorizontalAlignment(SwingConstants.LEADING);
		this.btnEditar.setIcon(new ImageIcon("src/img/pencil.png"));
		this.btnEditar.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.btnEditar.setBounds(567, 421, 145, 41);

		this.btnElaborarPergunta = new JButton("Elaborar pergunta");
		this.btnElaborarPergunta.addMouseListener(this);
		this.btnElaborarPergunta.setHorizontalAlignment(SwingConstants.LEADING);
		this.btnElaborarPergunta.setIcon(new ImageIcon("src/img/add.png"));
		this.btnElaborarPergunta.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.btnElaborarPergunta.setBounds(487, 45, 225, 41);

	}

	private void criarCampos() {

		this.lblFundo = new JLabel();
		this.lblFundo.setIcon(new ImageIcon("src/img/fundo8.png"));
		this.lblFundo.setBounds(0, 0, 794, 571);

		this.lblIcone = new JLabel("");
		this.lblIcone.setIcon(new ImageIcon("src/img/questions2.png"));
		this.lblIcone.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblIcone.setBounds(93, 36, 64, 64);

		this.lblPerguntas = new JLabel("PERGUNTAS");
		this.lblPerguntas.setForeground(Color.WHITE);
		this.lblPerguntas.setFont(new Font("Aileron", Font.PLAIN, 30));
		this.lblPerguntas.setBounds(167, 45, 178, 37);

		this.txtPergunta = new JTextArea();
		this.txtPergunta.setForeground(Color.WHITE);
		this.txtPergunta.setEditable(false);
		this.txtPergunta.setOpaque(false);
		this.txtPergunta.setWrapStyleWord(true);
		this.txtPergunta.setLineWrap(true);
		this.txtPergunta.setBackground(null);
		this.txtPergunta.setFont(new Font("Aileron", Font.PLAIN, 20));
		this.txtPergunta.setBounds(93, 167, 619, 116);

		this.txtAlternativa1 = new JTextField();
		this.txtAlternativa1.setEditable(false);
		this.txtAlternativa1.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtAlternativa1.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativa1.setText("A");
		this.txtAlternativa1.setBounds(93, 294, 26, 26);

		this.txtAlternativaA = new JTextField();
		this.txtAlternativaA.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativaA.setEditable(false);
		this.txtAlternativaA.setBounds(126, 294, 586, 26);

		this.txtAlternativa2 = new JTextField();
		this.txtAlternativa2.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtAlternativa2.setText("B");
		this.txtAlternativa2.setEditable(false);
		this.txtAlternativa2.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativa2.setBounds(93, 324, 26, 26);

		this.txtAlternativaB = new JTextField();
		this.txtAlternativaB.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativaB.setEditable(false);
		this.txtAlternativaB.setBounds(126, 324, 586, 26);

		this.txtAlternativa3 = new JTextField();
		this.txtAlternativa3.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtAlternativa3.setText("C");
		this.txtAlternativa3.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativa3.setEditable(false);
		this.txtAlternativa3.setBounds(93, 354, 26, 26);

		this.txtAlternativaC = new JTextField();
		this.txtAlternativaC.setEditable(false);
		this.txtAlternativaC.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativaC.setBounds(126, 354, 586, 26);

		this.txtAlternativa4 = new JTextField();
		this.txtAlternativa4.setEditable(false);
		this.txtAlternativa4.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtAlternativa4.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativa4.setText("D");
		this.txtAlternativa4.setBounds(93, 384, 26, 26);

		this.txtAlternativaD = new JTextField();
		this.txtAlternativaD.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativaD.setEditable(false);
		this.txtAlternativaD.setBounds(126, 384, 586, 26);

		this.lblCategoria = new JLabel("HISTÓRIA");
		this.lblCategoria.setForeground(Color.WHITE);
		this.lblCategoria.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.lblCategoria.setBounds(165, 419, 254, 33);

		this.lblIconeCategoria = new JLabel();
		this.lblIconeCategoria.setIcon(new ImageIcon("src/img/historia.png"));
		this.lblIconeCategoria.setBounds(126, 405, 60, 60);

	}

	private void criarPainel() {

		this.painelGeral = new JPanel();
		this.painelGeral.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.painelGeral.setLayout(null);
		this.painelGeral.add(this.txtPergunta);
		this.painelGeral.add(this.txtAlternativaA);
		this.painelGeral.add(this.txtAlternativaB);
		this.painelGeral.add(this.txtAlternativaC);
		this.painelGeral.add(this.txtAlternativaD);
		this.painelGeral.add(this.txtAlternativa1);
		this.painelGeral.add(this.txtAlternativa2);
		this.painelGeral.add(this.txtAlternativa3);
		this.painelGeral.add(this.txtAlternativa4);
		this.painelGeral.add(this.lblIcone);
		this.painelGeral.add(this.lblPerguntas);
		this.painelGeral.add(this.lblCategoria);
		this.painelGeral.add(this.lblIconeCategoria);
		this.painelGeral.add(this.btnExcluir);
		this.painelGeral.add(this.btnEditar);
		this.painelGeral.add(this.btnElaborarPergunta);
		this.painelGeral.add(this.btnAnterior);
		this.painelGeral.add(this.btnProxima);
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
			if(tocar)som.mouseClick();
			new tMenu().setVisible(true);
			dispose();
		} else if (e.getSource() == btnEditar) {
			tEditar.tocar = tPerguntas.tocar;
			if(tocar)som.mouseClick();
			new tEditar(this.pergunta).setVisible(true);
			dispose();
		} else if (e.getSource() == btnElaborarPergunta) {
			tElaborar.tocar = tPerguntas.tocar;
			if(tocar)som.mouseClick();
			new tElaborar().setVisible(true);
			dispose();
		} else if (e.getSource() == btnProxima) {
			if(tocar)som.mouseClick();
			proximaPergunta();
		} else if (e.getSource() == btnAnterior) {
			if(tocar)som.mouseClick();
			perguntaAnterior();
		} else if (e.getSource() == btnExcluir) {
			if(tocar)som.mouseClick();
			if (verificaLista()) {
				excluirPergunta();
				proximaPergunta();
				verificaLista();
			}
		}
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == btnExcluir) {
			this.btnExcluir.setIcon(new ImageIcon("src/img/trash2.png"));
		} else if (e.getSource() == btnVoltar) {
			this.btnVoltar.setIcon(new ImageIcon("src/img/back2.png"));
		} else if (e.getSource() == btnEditar) {
			this.btnEditar.setIcon(new ImageIcon("src/img/pencil2.png"));
		} else if (e.getSource() == btnElaborarPergunta) {
			this.btnElaborarPergunta.setIcon(new ImageIcon("src/img/add2.png"));
		} else if (e.getSource() == btnProxima) {
			this.btnProxima.setIcon(new ImageIcon("src/img/proxima2.png"));
		} else if (e.getSource() == btnAnterior) {
			this.btnAnterior.setIcon(new ImageIcon("src/img/anterior2.png"));
		}

	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource() == btnExcluir) {
			this.btnExcluir.setIcon(new ImageIcon("src/img/trash.png"));
		} else if (e.getSource() == btnVoltar) {
			this.btnVoltar.setIcon(new ImageIcon("src/img/back.png"));
		} else if (e.getSource() == btnEditar) {
			this.btnEditar.setIcon(new ImageIcon("src/img/pencil.png"));
		} else if (e.getSource() == btnElaborarPergunta) {
			this.btnElaborarPergunta.setIcon(new ImageIcon("src/img/add.png"));
		} else if (e.getSource() == btnProxima) {
			this.btnProxima.setIcon(new ImageIcon("src/img/proxima.png"));
		} else if (e.getSource() == btnAnterior) {
			this.btnAnterior.setIcon(new ImageIcon("src/img/anterior.png"));
		}

	}
}
