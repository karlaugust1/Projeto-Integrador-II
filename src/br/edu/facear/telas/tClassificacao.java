package br.edu.facear.telas;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import br.edu.facear.classes.*;
import br.edu.facear.util.Som;

@SuppressWarnings("serial")
public class tClassificacao extends JFrame implements MouseListener {

	private JPanel painelGeral, painelSecundario, painelCabecalho;
	private JTable tabelaClassificacao;
	private JButton btnVoltar;
	private JLabel lblFundo, lblFundo2, lblClassificacao, lblIcone;
	private JTableHeader cabecalho;
	private JScrollPane barraRolagem;
	private DefaultTableModel modelo;
	private Classificacao classificacao;
	private Som som;
	public static boolean tocar;

	public tClassificacao() {
		super("JUST ANOTHER QUIZ GAME");
		som = new Som();

		criarJanela();
		criarCampos();
		criarBotoes();
		criarPainel();

		this.classificacao = new Classificacao();
		classificar();
		this.tabelaClassificacao.setModel(modelo);
	}

	private void classificar() {

		int i = 0;
		List<Classificacao> listaClassificada = classificacao.classificar();
		for (Classificacao c : listaClassificada) {
			Object[] o = new Object[6];
			o[0] = i + 1;
			o[1] = c.getJogador().getNome();
			o[2] = c.getJogador().getNivel();
			o[3] = c.getRespostaSemana();
			o[4] = c.getRespostaMes();
			o[5] = c.getTotal();

			modelo.addRow(o);

			i++;
		}

	}

	private void criarCampos() {

		this.lblFundo = new JLabel();
		this.lblFundo.setIcon(new ImageIcon("src/img/fundo8.png"));
		this.lblFundo.setBounds(0, 0, 794, 571);

		this.lblFundo2 = new JLabel();
		this.lblFundo2.setIcon(new ImageIcon("src/img/fundoC.png"));
		this.lblFundo2.setBounds(0, 0, 794, 571);

		this.lblIcone = new JLabel("");
		this.lblIcone.setIcon(new ImageIcon("src/img/podium2.png"));
		this.lblIcone.setBounds(93, 36, 64, 64);

		this.lblClassificacao = new JLabel("CLASSIFICAÇÃO");
		this.lblClassificacao.setForeground(Color.WHITE);
		this.lblClassificacao.setFont(new Font("Aileron", Font.PLAIN, 30));
		this.lblClassificacao.setBounds(167, 45, 419, 38);

		String[] coluna = { "POSIÇÃO", "NOME", "NÍVEL", "SEMANA", "MÊS", "TOTAL" };

		this.tabelaClassificacao = new JTable();
		this.tabelaClassificacao.setBackground(null);
		this.tabelaClassificacao.setShowGrid(false);
		this.tabelaClassificacao.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.tabelaClassificacao.setBackground(null);

		this.modelo = new DefaultTableModel(coluna, 0);

		this.cabecalho = tabelaClassificacao.getTableHeader();
		this.cabecalho.setFont(new Font("Aileron", Font.PLAIN, 14));

		this.barraRolagem = new JScrollPane(tabelaClassificacao);
		this.barraRolagem.setBackground(null);

		this.tabelaClassificacao.setModel(modelo);

	}

	private void criarBotoes() {

		this.btnVoltar = new JButton();
		this.btnVoltar.addMouseListener(this);
		this.btnVoltar.setIcon(new ImageIcon("src/img/back.png"));
		this.btnVoltar.setBounds(49, 49, 34, 34);
		this.btnVoltar.setBorderPainted(false);
		this.btnVoltar.setContentAreaFilled(false);

	}

	private void criarPainel() {

		this.painelCabecalho = new JPanel();
		this.painelCabecalho.setBounds(0, 0, 794, 114);
		this.painelCabecalho.setLayout(null);

		this.painelCabecalho.add(this.btnVoltar);
		this.painelCabecalho.add(this.lblIcone);
		this.painelCabecalho.add(this.lblClassificacao);
		this.painelCabecalho.add(this.lblFundo);

		this.painelSecundario = new JPanel();
		this.painelSecundario.setBounds(0, 114, 794, 457);
		this.painelSecundario.setLayout(new GridLayout(1, 1));

		this.painelSecundario.add(this.barraRolagem);

		this.painelGeral = new JPanel();
		this.painelGeral.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.painelGeral.setLayout(null);
		this.painelGeral.add(this.painelCabecalho);
		this.painelGeral.add(this.painelSecundario);

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
