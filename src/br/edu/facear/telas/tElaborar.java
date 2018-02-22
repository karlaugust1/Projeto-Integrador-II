package br.edu.facear.telas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import br.edu.facear.classes.*;
import br.edu.facear.util.Som;

import java.util.List;

@SuppressWarnings("serial")
public class tElaborar extends JFrame implements MouseListener {

	private JPanel painelGeral;
	private JTextArea txtrPergunta;
	private JTextField txtAlternativaA, txtAlternativaB, txtAlternativaC, txtAlternativaD, txtAlternativa1,
			txtAlternativa2, txtAlternativa3, txtAlternativa4;
	private JLabel lblFundo, lblElaborarPergunta, lblIcone;
	private JButton btnCancelar, btnSalvar;
	private JRadioButton rdbtnAlternativaA, rdbtnAlternativaB, rdbtnAlternativaC, rdbtnAlternativaD;
	private ButtonGroup grupoBotoes;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbbxCategoria;
	private Categoria categoria;
	private Som som;
	public static boolean tocar;

	public tElaborar() {
		super("JUST ANOTHER QUIZ GAME");
		som = new Som();
		
		criarJanela();
		criarCampos();
		criarBotoes();
		criarPainel();

		carregarCategoria();
	}

	@SuppressWarnings("unchecked")
	public void carregarCategoria() {

		this.categoria = new Categoria();
		List<Categoria> listaCategoria = categoria.Ler();
		for (Categoria cat : listaCategoria) {
			cmbbxCategoria.addItem(cat.getNome());
		}
	}

	public void salvarPergunta() {

		Pergunta pergunta = new Pergunta();

		pergunta.setEnunciado(txtrPergunta.getText());

		Categoria categoria = new Categoria();
		categoria.setID(cmbbxCategoria.getSelectedIndex() + 1);
		pergunta.setCategoria(categoria);

		String alternativa = " ";

		if (rdbtnAlternativaA.isSelected()) {
			alternativa = txtAlternativaA.getText();
		} else if (rdbtnAlternativaB.isSelected()) {
			alternativa = (txtAlternativaB.getText());
		} else if (rdbtnAlternativaC.isSelected()) {
			alternativa = (txtAlternativaC.getText());
		} else if (rdbtnAlternativaD.isSelected()) {
			alternativa = (txtAlternativaD.getText());
		}

		for (int i = 0; i < 4; i++) {
			Resposta resp = new Resposta();
			if (i == 0) {
				resp.setEnunciado(txtAlternativaA.getText());
			} else if (i == 1) {
				resp.setEnunciado(txtAlternativaB.getText());
			} else if (i == 2) {
				resp.setEnunciado(txtAlternativaC.getText());
			} else if (i == 3) {
				resp.setEnunciado(txtAlternativaD.getText());
			}

			if (resp.getEnunciado().equals(alternativa)) {
				pergunta.setAlternativaCorreta(resp);
			}

			resp.criar();

		}

		pergunta.criar();

		JOptionPane.showMessageDialog(null, "Pergunta elaborada com sucesso!");
		dispose();

	}

	public boolean verificar() {

		boolean correto = true;

		String pergunta = txtrPergunta.getText();
		int verificacao = pergunta.indexOf("?");
		int verificacao1 = pergunta.indexOf(":");
		int verificacao2 = pergunta.indexOf(";");

		if (txtrPergunta.getText().equals(" ") || txtrPergunta.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Favor informar a pergunta.");
			correto = false;
		} else if (txtAlternativaA.getText().equals(" ") || txtAlternativaA.getText().equals("")
				|| txtAlternativaB.getText().equals(" ") || txtAlternativaB.getText().equals("")
				|| txtAlternativaC.getText().equals(" ") || txtAlternativaC.getText().equals("")
				|| txtAlternativaD.getText().equals(" ") || txtAlternativaD.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Favor informar a(s) resposta(s).");
			correto = false;
		} else if (verificacao == -1 && verificacao1 == -1) {
			JOptionPane.showMessageDialog(null, "Verifique se a pergunta possui '?' ou ':'");
			correto = false;
		} else if (verificacao2 != -1) {
			JOptionPane.showMessageDialog(null, "Verifique se a pergunta possui ';'");
			correto = false;
		} else if (txtAlternativaA.getText().indexOf(";") != -1) {
			JOptionPane.showMessageDialog(null, "Verifique se a alternativa A possui ';'");
			correto = false;
		} else if (txtAlternativaB.getText().indexOf(";") != -1) {
			JOptionPane.showMessageDialog(null, "Verifique se a alternativa B possui ';'");
			correto = false;
		} else if (txtAlternativaC.getText().indexOf(";") != -1) {
			JOptionPane.showMessageDialog(null, "Verifique se a alternativa C possui ';'");
			correto = false;
		} else if (txtAlternativaD.getText().indexOf(";") != -1) {
			JOptionPane.showMessageDialog(null, "Verifique se a alternativa D possui ';'");
			correto = false;
		}

		return correto;
	}

	@SuppressWarnings("rawtypes")
	public void criarCampos() {

		this.lblFundo = new JLabel();
		this.lblFundo.setIcon(new ImageIcon("src/img/fundo8.png"));
		this.lblFundo.setBounds(0, 0, 794, 571);

		this.lblIcone = new JLabel();
		this.lblIcone.setIcon(new ImageIcon("src/img/add64.png"));
		this.lblIcone.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblIcone.setBounds(93, 36, 64, 64);

		this.lblElaborarPergunta = new JLabel("ELABORAR PERGUNTA");
		this.lblElaborarPergunta.setForeground(Color.WHITE);
		this.lblElaborarPergunta.setFont(new Font("Aileron", Font.PLAIN, 30));
		this.lblElaborarPergunta.setBounds(167, 45, 326, 37);

		this.txtrPergunta = new JTextArea();
		this.txtrPergunta.setFont(new Font("Aileron", Font.PLAIN, 20));
		this.txtrPergunta.setWrapStyleWord(true);
		this.txtrPergunta.setLineWrap(true);
		this.txtrPergunta.setBounds(93, 167, 619, 116);

		this.txtAlternativa1 = new JTextField();
		this.txtAlternativa1.setEditable(false);
		this.txtAlternativa1.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtAlternativa1.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativa1.setText("A");
		this.txtAlternativa1.setBounds(93, 294, 26, 26);

		this.txtAlternativaA = new JTextField();
		this.txtAlternativaA.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativaA.setBounds(126, 294, 559, 26);

		this.txtAlternativa2 = new JTextField();
		this.txtAlternativa2.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtAlternativa2.setText("B");
		this.txtAlternativa2.setEditable(false);
		this.txtAlternativa2.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativa2.setBounds(93, 324, 26, 26);

		this.txtAlternativaB = new JTextField();
		this.txtAlternativaB.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativaB.setBounds(126, 324, 559, 26);

		this.txtAlternativa3 = new JTextField();
		this.txtAlternativa3.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtAlternativa3.setText("C");
		this.txtAlternativa3.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativa3.setEditable(false);
		this.txtAlternativa3.setBounds(93, 354, 26, 26);

		this.txtAlternativaC = new JTextField();
		this.txtAlternativaC.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativaC.setBounds(126, 354, 559, 26);

		this.txtAlternativa4 = new JTextField();
		this.txtAlternativa4.setEditable(false);
		this.txtAlternativa4.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtAlternativa4.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativa4.setText("D");
		this.txtAlternativa4.setBounds(93, 384, 26, 26);

		this.txtAlternativaD = new JTextField();
		this.txtAlternativaD.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.txtAlternativaD.setBounds(126, 384, 559, 26);

		this.rdbtnAlternativaA = new JRadioButton();
		this.rdbtnAlternativaA.setContentAreaFilled(false);
		this.rdbtnAlternativaA.setSelected(true);
		this.rdbtnAlternativaA.setBounds(691, 294, 21, 26);

		this.rdbtnAlternativaB = new JRadioButton();
		this.rdbtnAlternativaB.setContentAreaFilled(false);
		this.rdbtnAlternativaB.setBounds(691, 324, 21, 26);

		this.rdbtnAlternativaC = new JRadioButton();
		this.rdbtnAlternativaC.setContentAreaFilled(false);
		this.rdbtnAlternativaC.setBounds(691, 354, 21, 26);

		this.rdbtnAlternativaD = new JRadioButton();
		this.rdbtnAlternativaD.setContentAreaFilled(false);
		this.rdbtnAlternativaD.setBounds(691, 384, 21, 26);

		this.grupoBotoes = new ButtonGroup();
		this.grupoBotoes.add(rdbtnAlternativaA);
		this.grupoBotoes.add(rdbtnAlternativaB);
		this.grupoBotoes.add(rdbtnAlternativaC);
		this.grupoBotoes.add(rdbtnAlternativaD);

		this.cmbbxCategoria = new JComboBox();
		this.cmbbxCategoria.setBounds(93, 125, 619, 31);
		this.cmbbxCategoria.setFont(new Font("Aileron", Font.PLAIN, 16));

	}

	public void criarBotoes() {

		this.btnCancelar = new JButton("CANCELAR");
		this.btnCancelar.addMouseListener(this);
		this.btnCancelar.setHorizontalAlignment(SwingConstants.LEADING);
		this.btnCancelar.setIcon(new ImageIcon("src/img/cancel.png"));
		this.btnCancelar.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.btnCancelar.setBounds(380, 421, 160, 41);

		this.btnSalvar = new JButton("CONFIRMAR");
		this.btnSalvar.addMouseListener(this);
		this.btnSalvar.setHorizontalAlignment(SwingConstants.LEADING);
		this.btnSalvar.setIcon(new ImageIcon("src/img/success.png"));
		this.btnSalvar.setFont(new Font("Aileron", Font.PLAIN, 16));
		this.btnSalvar.setBounds(552, 421, 160, 41);

	}

	private void criarPainel() {

		this.painelGeral = new JPanel();
		this.painelGeral.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.painelGeral.setLayout(null);
		this.painelGeral.add(this.txtrPergunta);
		this.painelGeral.add(this.txtAlternativaA);
		this.painelGeral.add(this.txtAlternativaB);
		this.painelGeral.add(this.txtAlternativaC);
		this.painelGeral.add(this.txtAlternativaD);
		this.painelGeral.add(this.txtAlternativa1);
		this.painelGeral.add(this.txtAlternativa2);
		this.painelGeral.add(this.txtAlternativa3);
		this.painelGeral.add(this.txtAlternativa4);
		this.painelGeral.add(this.lblElaborarPergunta);
		this.painelGeral.add(this.lblIcone);
		this.painelGeral.add(this.btnCancelar);
		this.painelGeral.add(this.btnSalvar);
		this.painelGeral.add(this.rdbtnAlternativaA);
		this.painelGeral.add(this.rdbtnAlternativaB);
		this.painelGeral.add(this.rdbtnAlternativaC);
		this.painelGeral.add(this.rdbtnAlternativaD);
		this.painelGeral.add(this.cmbbxCategoria);
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
		if (e.getSource() == btnCancelar) {
			if(tocar)som.mouseClick();
			new tPerguntas().setVisible(true);
			dispose();
		}else if(e.getSource() == this.btnSalvar) {
			if(tocar)som.mouseClick();
			if (verificar()) {
				salvarPergunta();
				new tPerguntas().setVisible(true);
				dispose();
			}
		}
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == btnCancelar) {
			this.btnCancelar.setIcon(new ImageIcon("src/img/cancel2.png"));
		} else if (e.getSource() == btnSalvar) {
			this.btnSalvar.setIcon(new ImageIcon("src/img/success2.png"));
		}

	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource() == btnCancelar) {
			this.btnCancelar.setIcon(new ImageIcon("src/img/cancel.png"));
		} else if (e.getSource() == btnSalvar) {
			this.btnSalvar.setIcon(new ImageIcon("src/img/success.png"));
		}

	}

}
