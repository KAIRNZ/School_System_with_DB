	package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import classes.CadastroAuxiliar;

public class BancoTeste {

	private static CadastroAuxiliar cd; 
	
	public static void inserirAluno(String nome, String data_nascimento, String cidade, String estado, String turma) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Connection conn = null;
		PreparedStatement st = null;

		try {

			conn = DB.getConnection();

			st = conn.prepareStatement("insert into aluno (nome, data_nascimento, cidade, estado, turma_id) values (?, ?, ?, ?, ?)");

//			st.setDate(5,  new java.sql.Date(sdf.parse(data_nascimento).getTime()));
			
			st.setString(1, nome);
			st.setDate(2,  new java.sql.Date(sdf.parse(data_nascimento).getTime()));
			st.setString(3, cidade);
			st.setString(4, estado);
			st.setInt(5, Integer.parseInt(turma));

			st.executeUpdate();

		} catch (SQLException | ParseException e) {

			e.printStackTrace();

		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}
	public static void inserirProfessor(String nome, String data_nascimento, String cidade, String estado) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Connection conn = null;
		PreparedStatement st = null;

		try {

			conn = DB.getConnection();

			st = conn.prepareStatement("insert into Professor (nome, data_nascimento, cidade, estado) values (?, ?, ?, ?)");

//			st.setDate(5,  new java.sql.Date(sdf.parse(data_nascimento).getTime()));
			
			st.setString(1, nome);
			st.setDate(2,  new java.sql.Date(sdf.parse(data_nascimento).getTime()));
			st.setString(3, cidade);
			st.setString(4, estado);

			st.executeUpdate();

		} catch (SQLException | ParseException e) {

			e.printStackTrace();

		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}
	public static void inserirTurma(String nome, String turno, String data_inicio, String data_fim) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Connection conn = null;
		PreparedStatement st = null;

		try {

			conn = DB.getConnection();

			st = conn.prepareStatement("insert into turma (nome, turno, data_inicio, data_termino) values (?, ?, ?, ?)");

//			st.setDate(5,  new java.sql.Date(sdf.parse(data_nascimento).getTime()));
			
			st.setString(1, nome);
			st.setString(2, turno);
			st.setDate(3,  new java.sql.Date(sdf.parse(data_inicio).getTime()));
			st.setDate(4,  new java.sql.Date(sdf.parse(data_fim).getTime()));

			st.executeUpdate();

		} catch (SQLException | ParseException e) {

			e.printStackTrace();

		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}
	public static void inserirDisciplina(String nome, String turma_id, String professor_id) {

		Connection conn = null;
		PreparedStatement st = null;

		try {

			conn = DB.getConnection();

			st = conn.prepareStatement("insert into disciplina (nome, turma_id, professor_id) values (?, ?, ?)");

//			st.setDate(5,  new java.sql.Date(sdf.parse(data_nascimento).getTime()));
			
			st.setString(1, nome);
//			st.setDate(2,  new java.sql.Date(sdf.parse(data_nascimento).getTime()));
			st.setInt(2, Integer.parseInt(turma_id));
			st.setInt(3, Integer.parseInt(professor_id));

			st.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}
	public static DefaultTableModel buscaEspecificaFuncionario(DefaultTableModel model, String nome_busca) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		model.setRowCount(0);

		model.setRowCount(0); // resetar o model

		try {

			conn = DB.getConnection();

			st = conn.prepareStatement("select * from funcionario where funcionario.nome like ?");

			st.setString(1, "%" + nome_busca + "%");

			rs = st.executeQuery();

			while (rs.next()) {

				String cpf = rs.getString("cpf");
				String rg = rs.getString("rg");
				String nome = rs.getString("nome");
				String telefone = rs.getString("telefone");
				String data_nascimento = sdf.format(rs.getDate("data_nascimento"));
				String rua = rs.getString("rua");
				String bairro = rs.getString("bairro");
				int numero_casa = rs.getInt("numero_casa");
				String cidade = rs.getString("cidade");
				String estado = rs.getString("estado");
				Double comissao = rs.getDouble("comissao");
				int adm = rs.getInt("adm");
				model.addRow(new Object[] { cpf, rg, nome, telefone, data_nascimento, rua, bairro, numero_casa, cidade,
						estado, comissao, adm });

			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

		return model;

	}
	
	public static CadastroAuxiliar buscarTurma() {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		ArrayList<String> idsArray = new ArrayList<String>();
		ArrayList<String> nomesArray = new ArrayList<String>();

		try {

			conn = DB.getConnection();
			
			st = conn.createStatement();

			rs = st.executeQuery("select turma.id, turma.nome from turma ");

			while (rs.next()) {
				String id = rs.getInt("id")+ "";
				idsArray.add(id);
				String nome = rs.getString("nome");
				nomesArray.add(nome);
				
			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			DB.closeStatement(st);
			DB.closeResultSet(rs);
			DB.closeConnection();
		}
		String[] idsVetor = new String[idsArray.size()];
		String[] nomesVetor = new String[nomesArray.size()];
		
		for(int i = 0; i < nomesVetor.length; i++) {
			idsVetor[i] = idsArray.get(i);
			nomesVetor[i] = nomesArray.get(i);
		}
		
		cd = new CadastroAuxiliar(idsVetor, nomesVetor);
		
		return cd;

	}
	public static CadastroAuxiliar buscarProfessor() {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		ArrayList<String> idsArray = new ArrayList<String>();
		ArrayList<String> nomesArray = new ArrayList<String>();

		try {

			conn = DB.getConnection();
			
			st = conn.createStatement();

			rs = st.executeQuery("select professor.id, professor.nome from professor ");

			while (rs.next()) {
				String id = rs.getInt("id")+ "";
				idsArray.add(id);
				String nome = rs.getString("nome");
				nomesArray.add(nome);
				
			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			DB.closeStatement(st);
			DB.closeResultSet(rs);
			DB.closeConnection();
		}
		String[] idsVetor = new String[idsArray.size()];
		String[] nomesVetor = new String[nomesArray.size()];
		
		for(int i = 0; i < nomesVetor.length; i++) {
			idsVetor[i] = idsArray.get(i);
			nomesVetor[i] = nomesArray.get(i);
		}
		
		cd = new CadastroAuxiliar(idsVetor, nomesVetor);
		
		return cd;

	}

	public static DefaultTableModel buscarFuncionario(DefaultTableModel model) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		model.setRowCount(0);

		try {

			conn = DB.getConnection();
			st = conn.createStatement();

			rs = st.executeQuery("select * from funcionario");

			while (rs.next()) {

				String cpf = rs.getString("cpf");
				String rg = rs.getString("rg");
				String nome = rs.getString("nome");
				String telefone = rs.getString("telefone");
				String data_nascimento = sdf.format(rs.getDate("data_nascimento"));
				String rua = rs.getString("rua");
				String bairro = rs.getString("bairro");
				int numero_casa = rs.getInt("numero_casa");
				String cidade = rs.getString("cidade");
				String estado = rs.getString("estado");
				Double comissao = rs.getDouble("comissao");
				int adm = rs.getInt("adm");
				model.addRow(new Object[] { cpf, rg, nome, telefone, data_nascimento, rua, bairro, numero_casa, cidade,
						estado, comissao, adm });

			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			DB.closeStatement(st);
			DB.closeResultSet(rs);
			DB.closeConnection();
		}

		return model;

	}
}
