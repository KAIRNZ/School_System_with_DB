package telas;

import java.awt.Button;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import classes.Turma;
import db.BancoTeste;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class TelaCadastrarTurma extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField_nome;
	private Turma turma;

	public TelaCadastrarTurma() {
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 450, 300);
		getContentPane().setLayout(null);
		setVisible(true);

		JLabel label_titulo = new JLabel("CADASTRAR TURMA");
		label_titulo.setHorizontalAlignment(SwingConstants.CENTER);
		label_titulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_titulo.setBounds(10, 11, 414, 20);
		getContentPane().add(label_titulo);

		JLabel label_nome = new JLabel("Nome");
		label_nome.setHorizontalAlignment(SwingConstants.LEFT);
		label_nome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_nome.setBounds(10, 42, 48, 20);
		getContentPane().add(label_nome);

		textField_nome = new JTextField();
		textField_nome.setColumns(10);
		textField_nome.setBounds(58, 42, 366, 20);
		getContentPane().add(textField_nome);

		JLabel label_nome_1 = new JLabel("Turno");
		label_nome_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_nome_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_nome_1.setBounds(10, 73, 43, 20);
		getContentPane().add(label_nome_1);

		JRadioButton rdbtnManha = new JRadioButton("Manhã");
		rdbtnManha.setSelected(true);
		rdbtnManha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnManha.setBounds(58, 73, 61, 20);
		getContentPane().add(rdbtnManha);

		JRadioButton rdbtnTarde = new JRadioButton("Tarde");
		rdbtnTarde.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnTarde.setBounds(129, 73, 61, 20);
		getContentPane().add(rdbtnTarde);

		JRadioButton rdbtnNoite = new JRadioButton("Noite");
		rdbtnNoite.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnNoite.setBounds(195, 73, 61, 20);
		getContentPane().add(rdbtnNoite);

		ButtonGroup grupoturno = new ButtonGroup();
		grupoturno.add(rdbtnManha);
		grupoturno.add(rdbtnTarde);
		grupoturno.add(rdbtnNoite);

		JLabel label_nome_1_1 = new JLabel("Data de Início");
		label_nome_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_nome_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_nome_1_1.setBounds(10, 104, 85, 20);
		getContentPane().add(label_nome_1_1);

		MaskFormatter mask_data = null;

		try {
			mask_data = new MaskFormatter("##/##/####");
		} catch (ParseException e) {

			e.printStackTrace();
		}


		JFormattedTextField textField_data_i = new JFormattedTextField(mask_data);
		textField_data_i.setBounds(98, 104, 88, 20);
		getContentPane().add(textField_data_i);

		JLabel label_nome_1_1_1 = new JLabel("Data de Término");
		label_nome_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_nome_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_nome_1_1_1.setBounds(195, 104, 102, 20);
		getContentPane().add(label_nome_1_1_1);

		JFormattedTextField textField_data_f = new JFormattedTextField((mask_data));
		textField_data_f.setBounds(297, 104, 88, 20);
		getContentPane().add(textField_data_f);

		JPanel panel = new JPanel();
		panel.setBounds(10, 147, 414, 33);
		getContentPane().add(panel);

		JButton button_cadastrar = new JButton("Cadastrar Turma");
		button_cadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String turno;
				
				if(rdbtnManha.isSelected()) {turno = "Manhã";
				}else if(rdbtnTarde.isSelected()) {turno = "Tarde";
				}else {turno = "Noite";}
				
				turma = new Turma(textField_nome.getText(), turno, textField_data_i.getText(), textField_data_f.getText());
				
				BancoTeste.inserirTurma(turma.getNome(), turma.getTurno(), turma.getData_inicio(), turma.getData_termino());
				
				dispose();
			}
		});
		panel.add(button_cadastrar);

	}
}
