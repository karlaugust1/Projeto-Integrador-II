package br.edu.facear.telas;

import java.awt.*;
import br.edu.facear.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import br.edu.facear.classes.*;


@SuppressWarnings("serial")
public class tLogin extends JFrame implements MouseListener {

	private JPanel painelGeral;
	private JTextField txtUsuario;
	private JLabel lblUsuario, lblSenha, lblIcone, lblfundo, lblLogin, lblIconeUser, lblIconePass;
	private JPasswordField pssSenha;
	private JButton btnCadastrar, btnLogin;
	private Jogador jogador;
	private Som som;

	private ImageIcon fundo = new ImageIcon("src/img/fundo8.png");

	public tLogin() {
		super("JUST ANOTHER QUIZ GAME");
		som = new Som();

		criarJanela();
		criarCampos();
		criarBotoes();
		criarPainel();

		jogador = new Jogador();
		getRootPane().setDefaultButton(btnLogin);

	}
	
	@SuppressWarnings("deprecation")
	public void logar() {

		jogador.setUsuario(txtUsuario.getText());
		jogador.setSenha(pssSenha.getText());

		if(jogador.logar() == 0) {
			new tMenu().setVisible(true);
			dispose();
			som.logon();
		}else if(jogador.logar() == 1)
			JOptionPane.showMessageDialog(null, "Favor informar um usuário e senha");
		else if (jogador.logar() == 2)
			JOptionPane.showMessageDialog(null, "Favor informar um usuário");
		else if(jogador.logar() == 3)
			JOptionPane.showMessageDialog(null, "Favor informar uma senha");
		else if(jogador.logar() == 4)
			JOptionPane.showMessageDialog(null, "Usuário inexistente, favor informar um usuário válido");
		else if(jogador.logar() == 5)
			JOptionPane.showMessageDialog(null, "Senha incorreta, favor verifique sua senha");
		
	}

	private void criarCampos() {

		this.lblfundo = new JLabel();
		this.lblfundo.setIcon(fundo);
		
		this.lblfundo.setBounds(0, 0, 794, 571);

		this.lblIcone = new JLabel();
		ImageIcon ii = new ImageIcon("src/img/user.png");
		Image ic = ii.getImage();
		ic = ic.getScaledInstance(192, 192, java.awt.Image.SCALE_SMOOTH);
		this.lblIcone.setIcon(new ImageIcon(ic));
		this.lblIcone.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblIcone.setBounds(180, 28, 424, 242);

		this.lblLogin = new JLabel("LOGIN");
		this.lblLogin.setForeground(Color.WHITE);
		this.lblLogin.setFont(new Font("Aileron", Font.PLAIN, 30));
		this.lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblLogin.setBounds(180, 240, 424, 51);

		this.lblIconeUser = new JLabel();
		this.lblIconeUser.setIcon(new ImageIcon("src/img/user (2).png"));
		this.lblIconeUser.setBounds(131, 350, 40, 46);

		this.lblUsuario = new JLabel("USUÁRIO");
		this.lblUsuario.setForeground(Color.WHITE);
		this.lblUsuario.setFont(new Font("Aileron SemiBold", Font.PLAIN, 18));
		this.lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblUsuario.setBounds(180, 328, 424, 26);

		this.txtUsuario = new JTextField(30);
		this.txtUsuario.setFont(new Font("Aileron", Font.PLAIN, 20));
		this.txtUsuario.setBounds(180, 360, 424, 26);

		this.lblIconePass = new JLabel();
		this.lblIconePass.setIcon(new ImageIcon("src/img/lock1.png"));
		this.lblIconePass.setBounds(131, 408, 39, 40);

		this.lblSenha = new JLabel("SENHA");
		this.lblSenha.setForeground(Color.WHITE);
		this.lblSenha.setFont(new Font("Aileron SemiBold", Font.PLAIN, 18));
		this.lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblSenha.setBounds(180, 386, 424, 26);

		this.pssSenha = new JPasswordField(30);
		this.pssSenha.setFont(new Font("Aileron", Font.PLAIN, 20));
		this.pssSenha.setBounds(180, 416, 424, 26);

	}

	private void criarBotoes() {

		this.btnCadastrar = new JButton("Criar novo cadastro");
		this.btnCadastrar = new JButton("Criar novo cadastro");
		this.btnCadastrar.setFont(new Font("Aileron SemiBold", Font.PLAIN, 14));
		this.btnCadastrar.setForeground(Color.WHITE);
		this.btnCadastrar.setBorder(null);
		this.btnCadastrar.addMouseListener(this);
		this.btnCadastrar.setContentAreaFilled(false);
		this.btnCadastrar.setBounds(410, 447, 194, 23);

		this.btnLogin = new JButton("LOGAR");
		this.btnLogin.addMouseListener(this);
		this.btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				logar();
			}
		});
		this.btnLogin.setFont(new Font("Aileron SemiBold", Font.PLAIN, 20));
		this.btnLogin.setBounds(325, 479, 141, 51);

	}

	private void criarPainel() {

		this.painelGeral = new JPanel();
		this.painelGeral.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.painelGeral.setLayout(null);
		this.painelGeral.add(lblIcone);
		this.painelGeral.add(lblLogin);
		this.painelGeral.add(lblIconeUser);
		this.painelGeral.add(lblUsuario);
		this.painelGeral.add(txtUsuario);
		this.painelGeral.add(lblIconePass);
		this.painelGeral.add(lblSenha);
		this.painelGeral.add(pssSenha);
		this.painelGeral.add(btnCadastrar);
		this.painelGeral.add(btnLogin);
		this.painelGeral.add(lblfundo);
		this.painelGeral.setOpaque(false);

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
		if (e.getSource() == btnLogin) {
			som.mouseClick();
			logar();
		}else if(e.getSource() == btnCadastrar) {
			som.mouseClick();
			new tCadastro().setVisible(true);
			dispose();
		}
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
		if (this.btnCadastrar == e.getSource()) {
			this.btnCadastrar.setContentAreaFilled(true);
			this.btnCadastrar.setForeground(Color.BLACK);
		}
	}

	public void mouseExited(MouseEvent e) {
		if (this.btnCadastrar == e.getSource()) {
			this.btnCadastrar.setContentAreaFilled(false);
			this.btnCadastrar.setForeground(Color.WHITE);
		}
	}
}
