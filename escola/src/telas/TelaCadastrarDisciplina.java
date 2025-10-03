package telas;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import classes.CadastroAuxiliar;
import classes.Disciplina;
import db.BancoTeste;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCadastrarDisciplina extends JInternalFrame {
	private JTextField textField_nome;
	private static final long serialVersionUID = 1L;
	private Disciplina disciplina;
	private CadastroAuxiliar cd_turma;
	private CadastroAuxiliar cd_professor;

	public TelaCadastrarDisciplina() {
		setIconifiable(true);
		setClosable(true);
		setBounds(0, 0, 429, 309);
		getContentPane().setLayout(null);

		cd_turma = BancoTeste.buscarTurma();
		cd_professor= BancoTeste.buscarProfessor();

		JLabel lblNewLabel = new JLabel("Cadastro Disciplina");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 10, 413, 43);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_nome = new JLabel("Nome");
		lblNewLabel_nome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_nome.setBounds(10, 63, 55, 31);
		getContentPane().add(lblNewLabel_nome);

		textField_nome = new JTextField();
		textField_nome.setBounds(69, 72, 244, 19);
		getContentPane().add(textField_nome);
		textField_nome.setColumns(10);

		JLabel lblNewLabel1 = new JLabel("Turma");
		lblNewLabel1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel1.setBounds(10, 117, 84, 31);
		getContentPane().add(lblNewLabel1);

		JLabel lblProfessor = new JLabel("Professor");
		lblProfessor.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblProfessor.setBounds(10, 175, 84, 31);
		getContentPane().add(lblProfessor);

		JComboBox comboBox_turma = new JComboBox();
		comboBox_turma.setModel(new DefaultComboBoxModel(cd_turma.getNomes()));
		comboBox_turma.setBounds(104, 125, 209, 21);
		getContentPane().add(comboBox_turma);

		JComboBox comboBox_professor = new JComboBox();
		comboBox_professor.setModel(new DefaultComboBoxModel(cd_professor.getNomes()));
		comboBox_professor.setBounds(104, 183, 209, 21);
		getContentPane().add(comboBox_professor);

		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disciplina = new Disciplina(textField_nome.getText(), cd_professor.retornarId(comboBox_professor.getSelectedIndex()), cd_turma.retornarId(comboBox_turma.getSelectedIndex()));
				BancoTeste.inserirDisciplina(disciplina.getNome(),disciplina.getProfessor_id() , disciplina.getTurma_id());
				
				dispose();
			}
		});
		btnNewButton.setBounds(104, 226, 209, 31);
		getContentPane().add(btnNewButton);
		setVisible(true);
	}
}
