package telas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 784, 439);
		contentPane.add(desktopPane);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu_cadastrar = new JMenu("Cadastrar");
		menuBar.add(menu_cadastrar);
		
		JMenuItem menuItem_aluno = new JMenuItem("Aluno");
		menuItem_aluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastrarAluno telaCadastrarAluno = new TelaCadastrarAluno();
				desktopPane.add(telaCadastrarAluno);
			}
		});
		menu_cadastrar.add(menuItem_aluno);
		
		JMenuItem menuItem_professor = new JMenuItem("Professor");
		menuItem_professor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastrarProfessor telaCadastarProfessor = new TelaCadastrarProfessor();
				desktopPane.add(telaCadastarProfessor);
			}
		});
		menu_cadastrar.add(menuItem_professor);
		
		JMenuItem menuItem_turma = new JMenuItem("Turma");
		menuItem_turma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastrarTurma telaCadastrarTurma = new TelaCadastrarTurma();
				desktopPane.add(telaCadastrarTurma);
			}
		});
		menu_cadastrar.add(menuItem_turma);
		
		JMenuItem menuItem_disciplina = new JMenuItem("Disciplina");
		menuItem_disciplina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastrarDisciplina telacadastrodisciplina = new TelaCadastrarDisciplina();
				desktopPane.add(telacadastrodisciplina);
			}
		});
		menu_cadastrar.add(menuItem_disciplina);
		
	}
}
