package br.edu.facear.telas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import br.edu.facear.classes.Jogador;
import br.edu.facear.util.Som;

@SuppressWarnings("serial")
public class tCadastro extends JFrame implements MouseListener{

	private JPanel painelGeral;
	private JLabel lblFundo, lblIcone, lblCadastro, lblIconeNome, lblNome, lblIconeUsuario, lblUsuario, lblIconeSenha,
			lblSenha, lblIconeConfirmacao, lblConfirmacao;
	private JTextField txtNome, txtUsuario;
	private JPasswordField pssSenha, pssConfirmacao;
	private JButton btnCadastrar, btnVoltar;
	private Som som;

	public tCadastro() {
		som = new Som();
		
		criarJanela();
		criarCampos();
		criarBotoes();
		criarPainel();
	}
	
	@SuppressWarnings("deprecation")
	private void validar() {
		
		Jogador jogador = new Jogador();
		
		jogador.setNome(txtNome.getText());
		
		jogador.setUsuario(txtUsuario.getText());
		jogador.setSenha(pssSenha.getText());
		String senha = pssSenha.getText();
		String senhaConfirmacao = pssConfirmacao.getText();
		
		if(senha.equals(senhaConfirmacao)) {

			if(this.txtNome.getText().isEmpty() && this.txtUsuario.getText().isEmpty() && this.pssSenha.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Favor informar um nome, usuário e senha");
			}else if(jogador.validarSenha() == 1) {
				JOptionPane.showMessageDialog(null, "Favor informar uma senha");			
			}else if(jogador.validarSenha() == 2) {
				JOptionPane.showMessageDialog(null, "Favor informar uma senha sem ';'");			
			}else if(jogador.validarNome() == 1) {
				JOptionPane.showMessageDialog(null, "Favor informar um nome");
			}else if(jogador.validarNome() == 2) {
				JOptionPane.showMessageDialog(null, "Favor informar um nome sem ';'");				
			}else if(jogador.validarUsuario() == 1) {
				JOptionPane.showMessageDialog(null, "Favor informar um usuário");
			}else if(jogador.validarUsuario() == 2) {
				JOptionPane.showMessageDialog(null, "Favor informar um usuário sem ';'");
			}else if (jogador.cadastrar()) {
					JOptionPane.showMessageDialog(null, "Jogador cadastrado com sucesso");
					new tLogin().setVisible(true);
					dispose();
			}else{
				JOptionPane.showMessageDialog(null,
						"Nome de usuário já cadastrado, favor informar um nome válido");
			}
		}else{
			JOptionPane.showMessageDialog(null,
					"Confirmação de senha diferente da informada, favor informar novamente.");				
			
		}
		
	}

	private void criarCampos() {

		this.lblFundo = new JLabel();
		this.lblFundo.setIcon(new ImageIcon("src/img/fundo8.png"));
		this.lblFundo.setBounds(0, 0, 794, 571);

		this.lblIcone = new JLabel();
		this.lblIcone.setIcon(new ImageIcon("src/img/iconeCadastro.png"));
		this.lblIcone.setBounds(331, 66, 128, 128);

		this.lblCadastro = new JLabel("CADASTRO");
		this.lblCadastro.setForeground(Color.WHITE);
		this.lblCadastro.setFont(new Font("Aileron", Font.PLAIN, 30));
		this.lblCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblCadastro.setBounds(304, 192, 184, 31);

		this.lblIconeNome = new JLabel();
		this.lblIconeNome.setIcon(new ImageIcon("src/img/user32.png"));
		this.lblIconeNome.setBounds(140, 262, 32, 32);

		this.lblNome = new JLabel("NOME");
		this.lblNome.setForeground(Color.WHITE);
		this.lblNome.setFont(new Font("Aileron SemiBold", Font.PLAIN, 18));
		this.lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNome.setBounds(182, 240, 424, 26);

		this.txtNome = new JTextField(30);
		this.txtNome.setFont(new Font("Aileron", Font.PLAIN, 20));
		this.txtNome.setBounds(182, 265, 424, 26);

		this.lblIconeUsuario = new JLabel();
		this.lblIconeUsuario.setIcon(new ImageIcon("src/img/user (2).png"));
		this.lblIconeUsuario.setBounds(139, 314, 33, 33);

		this.lblUsuario = new JLabel("USUÁRIO");
		this.lblUsuario.setForeground(Color.WHITE);
		this.lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblUsuario.setFont(new Font("Aileron SemiBold", Font.PLAIN, 18));
		this.lblUsuario.setBounds(182, 294, 424, 20);

		this.txtUsuario = new JTextField();
		this.txtUsuario.setFont(new Font("Aileron", Font.PLAIN, 20));
		this.txtUsuario.setBounds(182, 317, 424, 26);

		this.lblIconeSenha = new JLabel();
		this.lblIconeSenha.setIcon(new ImageIcon("src/img/lock1.png"));
		this.lblIconeSenha.setBounds(140, 367, 33, 33);

		this.lblSenha = new JLabel("SENHA");
		this.lblSenha.setForeground(Color.WHITE);
		this.lblSenha.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblSenha.setFont(new Font("Aileron SemiBold", Font.PLAIN, 18));
		this.lblSenha.setBounds(182, 348, 424, 22);

		this.pssSenha = new JPasswordField(30);
		this.pssSenha.setFont(new Font("Aileron", Font.PLAIN, 20));
		this.pssSenha.setBounds(182, 372, 424, 26);

		this.lblIconeConfirmacao = new JLabel();
		this.lblIconeConfirmacao.setIcon(new ImageIcon("src/img/relock.png"));
		this.lblIconeConfirmacao.setBounds(140, 426, 33, 33);

		this.lblConfirmacao = new JLabel("CONFIRMAÇÃO DE SENHA");
		this.lblConfirmacao.setForeground(Color.WHITE);
		this.lblConfirmacao.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblConfirmacao.setFont(new Font("Aileron SemiBold", Font.PLAIN, 18));
		this.lblConfirmacao.setBounds(182, 405, 424, 26);

		this.pssConfirmacao = new JPasswordField(30);
		this.pssConfirmacao.setFont(new Font("Aileron", Font.PLAIN, 20));
		this.pssConfirmacao.setBounds(182, 428, 424, 26);

	}

	private void criarPainel() {
		this.painelGeral = new JPanel();
		this.painelGeral.setLayout(null);
		this.painelGeral.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.painelGeral.add(this.lblIcone);
		this.painelGeral.add(this.lblIcone);
		this.painelGeral.add(this.lblCadastro);
		this.painelGeral.add(this.lblIconeNome);
		this.painelGeral.add(this.lblNome);
		this.painelGeral.add(this.txtNome);
		this.painelGeral.add(this.lblIconeUsuario);
		this.painelGeral.add(this.lblUsuario);
		this.painelGeral.add(this.txtUsuario);
		this.painelGeral.add(this.lblIconeSenha);
		this.painelGeral.add(this.lblSenha);
		this.painelGeral.add(this.pssSenha);
		this.painelGeral.add(this.lblIconeConfirmacao);
		this.painelGeral.add(this.lblConfirmacao);
		this.painelGeral.add(this.pssConfirmacao);
		this.painelGeral.add(this.btnCadastrar);
		this.painelGeral.add(this.btnVoltar);
		this.painelGeral.add(this.lblFundo);
		this.painelGeral.setOpaque(false);

		this.setContentPane(this.painelGeral);
	}

	private void criarBotoes() {

		this.btnCadastrar = new JButton("CADASTRAR");
		this.btnCadastrar.setFont(new Font("Aileron SemiBold", Font.PLAIN, 20));
		this.btnCadastrar.setBounds(304, 467, 184, 49);
		this.btnCadastrar.addMouseListener(this);
		
		this.btnVoltar = new JButton();
		this.btnVoltar.setIcon(new ImageIcon("src/img/back.png"));
		this.btnVoltar.setBorderPainted(false);
		this.btnVoltar.addMouseListener(this);
		this.btnVoltar.setContentAreaFilled(false);
		this.btnVoltar.setBounds(249, 475, 35, 35);

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
		if(e.getSource() == btnVoltar) {
			som.mouseClick();
			new tLogin().setVisible(true);
			dispose();
		}else if(e.getSource() == btnCadastrar) {
			som.mouseClick();
			validar();
		}
	}

	public void mousePressed(MouseEvent e) {	}

	public void mouseReleased(MouseEvent e) {	}

	public void mouseEntered(MouseEvent e) {
		if(e.getSource() == btnVoltar) {
			this.btnVoltar.setIcon(new ImageIcon("src/img/back2.png"));
		}
		
	}
	
	public void mouseExited(MouseEvent e) {
		if(e.getSource() == btnVoltar) {
			this.btnVoltar.setIcon(new ImageIcon("src/img/back.png"));
		}
	}
}
