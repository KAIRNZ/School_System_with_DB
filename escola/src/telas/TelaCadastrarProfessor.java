package telas;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import classes.Professor;
import db.BancoTeste;

import javax.swing.DefaultComboBoxModel;

public class TelaCadastrarProfessor extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField_nome;
	private JTextField textField_cidade;
	private Professor professor;

	public TelaCadastrarProfessor() {
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 450, 230);
		getContentPane().setLayout(null);
		setVisible(true);
		
		JLabel label_titulo = new JLabel("CADASTRAR PROFESSOR");
		label_titulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_titulo.setHorizontalAlignment(SwingConstants.CENTER);
		label_titulo.setBounds(10, 11, 414, 20);
		getContentPane().add(label_titulo);
		
		JLabel label_nome = new JLabel("Nome");
		label_nome.setHorizontalAlignment(SwingConstants.LEFT);
		label_nome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_nome.setBounds(10, 42, 48, 20);
		getContentPane().add(label_nome);
		
		textField_nome = new JTextField();
		textField_nome.setBounds(58, 42, 366, 20);
		getContentPane().add(textField_nome);
		textField_nome.setColumns(10);
		
		JLabel label_data = new JLabel("Data de Nascimento");
		label_data.setHorizontalAlignment(SwingConstants.LEFT);
		label_data.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_data.setBounds(10, 73, 120, 20);
		getContentPane().add(label_data);
		
		JLabel label_cidade = new JLabel("Cidade");
		label_cidade.setHorizontalAlignment(SwingConstants.LEFT);
		label_cidade.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_cidade.setBounds(10, 104, 46, 20);
		getContentPane().add(label_cidade);
		
		textField_cidade = new JTextField();
		textField_cidade.setColumns(10);
		textField_cidade.setBounds(58, 104, 256, 20);
		getContentPane().add(textField_cidade);
		
		JLabel label_estado = new JLabel("Estado");
		label_estado.setHorizontalAlignment(SwingConstants.LEFT);
		label_estado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_estado.setBounds(324, 104, 47, 20);
		getContentPane().add(label_estado);
		
		JComboBox comboBox_estado = new JComboBox();
		comboBox_estado.setModel(new DefaultComboBoxModel(new String[] {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
		comboBox_estado.setBounds(371, 104, 53, 20);
		getContentPane().add(comboBox_estado);
		
		MaskFormatter mask_data = null;
		try {
			mask_data = new MaskFormatter("##/##/####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		JFormattedTextField textField_data = new JFormattedTextField(mask_data);
		textField_data.setBounds(133, 73, 88, 20);
		getContentPane().add(textField_data);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 146, 414, 33);
		getContentPane().add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton button_cadastrar = new JButton("Cadastrar Professor");
		button_cadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				professor = new Professor(textField_nome.getText(), textField_data.getText(), textField_cidade.getText(), comboBox_estado.getSelectedItem().toString());
				BancoTeste.inserirProfessor(professor.getNome(), professor.getData_nascimento(), professor.getCidade(), professor.getEstado());
			dispose();
			}
			
		});
		panel.add(button_cadastrar);

	}
}
