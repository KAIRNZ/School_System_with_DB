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

public class BancoTeste {

	public static void inserirFuncionario(String cpf,String rg,String nome, String telefone, String data_nascimento,String rua, String bairro,
			int numero_casa,String cidade,String estado,double comissao, int adm) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Connection conn = null;
		PreparedStatement st = null;

		try {

			conn = DB.getConnection();

			st = conn.prepareStatement("insert into funcionario (cpf, rg, nome, telefone, data_nascimento, rua, bairro, numero_casa,"
					+ " cidade, estado, comissao, adm) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)");

			st.setString(1, cpf);
			st.setString(2, rg);
			st.setString(3, nome);
			st.setString(4, telefone);
			st.setDate(5,  new java.sql.Date(sdf.parse(data_nascimento).getTime()));
			st.setString(6, rua);
			st.setString(7,bairro);
			st.setInt(8, numero_casa);
			st.setString(9, cidade);
			st.setString(10, estado);
			st.setDouble(11,comissao);
			st.setInt(12, adm);
			

			st.executeUpdate();

		} catch (SQLException | ParseException e) {

			e.printStackTrace();

		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

	public static void editarFuncionario(String cpf, String rg, String nome, String telefone, String data_nascimento,
			String rua, String bairro, int numero_casa, String cidade, String estado, double comissao) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;

		try {

			conn = DB.getConnection();

			st = conn.prepareStatement(
					"UPDATE funcionario SET funcionario.cpf = ?, funcionario.rg = ?, funcionario.nome = ?,funcionario.telefone = ?, funcionario.data_nascimento = ?, funcionario.rua = ?, funcionario.bairro = ?, funcionario.numero_casa = ? , funcionario.cidade = ?, funcionario.estado = ?, funcionario.comissao = ? "
							+ "WHERE funcionario.cpf = ? ");

			st.setString(1, cpf);
			st.setString(2, rg);
			st.setString(3, nome);
			st.setString(4, telefone);
			st.setDate(5, new java.sql.Date(sdf.parse(data_nascimento).getTime()));
			st.setString(6, rua);
			st.setString(7, bairro);
			st.setInt(8, numero_casa);
			st.setString(9, cidade);
			st.setString(10, estado);
			st.setDouble(11, comissao);
			st.setString(12, cpf);

			st.executeUpdate();

		} catch (SQLException | ParseException e) {

			e.printStackTrace();

		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

	public static void deletarFuncionario(String cpf) {

		Connection conn = null;
		PreparedStatement st = null;

		try {

			conn = DB.getConnection();

			st = conn.prepareStatement("Delete from funcionario where funcionario.cpf = ?");

			st.setString(1, cpf);

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
	
	public static void deletarCliente(String cpf) {

		Connection conn = null;
		PreparedStatement st = null;

		try {

			conn = DB.getConnection();

			st = conn.prepareStatement("delete from cliente where cliente.cpf = ?");

			st.setString(1, cpf);

			st.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

	public static void alterarCliente(String cpf, String nome, String telefone, String data_nascimento, String email,
			String rua, String bairro, int numero_casa, String cidade, String estado) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Connection conn = null;
		PreparedStatement st = null;

		try {

			conn = DB.getConnection();

			st = conn.prepareStatement(
					"update cliente set cliente.cpf = ?, cliente.nome = ?, cliente.telefone = ?, cliente.data_nascimento = ?, cliente.email = ?, cliente.rua = ?, cliente.bairro = ?, cliente.numero_casa = ?, cliente.cidade = ?, cliente.estado = ? where cliente.cpf = ?");

			st.setString(1, cpf);
			st.setString(2, nome);
			st.setString(3, telefone);
			st.setDate(4, new java.sql.Date(sdf.parse(data_nascimento).getTime()));
			st.setString(5, email);
			st.setString(6, rua);
			st.setString(7, bairro);
			st.setInt(8, numero_casa);
			st.setString(9, cidade);
			st.setString(10, estado);
			st.setString(11, cpf);

			st.executeUpdate();

		} catch (SQLException | ParseException e) {

			e.printStackTrace();

		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

	public static DefaultTableModel buscaEspecificaCliente(DefaultTableModel model, String nome_busca) {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		model.setRowCount(0); // resetar o model

		try {

			conn = DB.getConnection();

			st = conn.prepareStatement("select * from cliente where cliente.nome like ?");

			st.setString(1, "%" + nome_busca + "%");

			rs = st.executeQuery();

			while (rs.next()) {

				String cpf = rs.getString("cpf");
				String nome = rs.getString("nome");
				String telefone = rs.getString("telefone");
				String data_nascimento = sdf.format(rs.getDate("data_nascimento"));
				String email = rs.getString("email");
				String rua = rs.getString("rua");
				String bairro = rs.getString("bairro");
				int numero_casa = rs.getInt("numero_casa");
				String cidade = rs.getString("cidade");
				String estado = rs.getString("estado");
				model.addRow(new Object[] { cpf, nome, telefone, data_nascimento, email, rua, bairro, numero_casa,
						cidade, estado });

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

	public static DefaultTableModel buscarCliente(DefaultTableModel model) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		model.setRowCount(0);

		try {

			conn = DB.getConnection();
			st = conn.createStatement();

			rs = st.executeQuery("select * from cliente");

			while (rs.next()) {

				String cpf = rs.getString("cpf");
				String nome = rs.getString("nome");
				String telefone = rs.getString("telefone");
				String data_nascimento = sdf.format(rs.getDate("data_nascimento"));
				String email = rs.getString("email");
				String rua = rs.getString("rua");
				String bairro = rs.getString("bairro");
				int numero_casa = rs.getInt("numero_casa");
				String cidade = rs.getString("cidade");
				String estado = rs.getString("estado");
				model.addRow(new Object[] { cpf, nome, telefone, data_nascimento, email, rua, bairro, numero_casa,
						cidade, estado });

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
	
	public static void inserirCliente(String cpf, String nome, String telefone, String data_nascimento, String email,
			String rua, String bairro, int numero_casa, String cidade, String estado) {

		String sql = "INSERT INTO cliente "
				+ "(cpf, nome, telefone, data_nascimento, email, rua, bairro, numero_casa, cidade, estado) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try (Connection conn = DB.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, cpf);
			st.setString(2, nome);
			st.setString(3, telefone);
			st.setDate(4, new java.sql.Date(sdf.parse(data_nascimento).getTime()));
			st.setString(5, email);
			st.setString(6, rua);
			st.setString(7, bairro);

			st.setInt(8, numero_casa);

			st.setString(9, cidade);
			st.setString(10, estado);

			st.executeUpdate();

		} catch (SQLException | ParseException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao cadastrar cliente: " + e.getMessage(), e);
		}
	}

	public static DefaultTableModel buscarProdutoTroca(DefaultTableModel model) {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		model.setRowCount(0);

		try {

			conn = DB.getConnection();
			st = conn.createStatement();

			rs = st.executeQuery(
					"select produto.id, produto.nome, produto.quantidade, produto.preco, produto.marca from produto where produto.quantidade >= 1");

			while (rs.next()) {

				int id = rs.getInt("id");
				String nome = rs.getString("nome");
				int quantidade = rs.getInt("quantidade");
				double preco = rs.getDouble("preco");
				String marca = rs.getString("marca");
				model.addRow(new Object[] { id, nome, quantidade, preco, marca });

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
	
	public static DefaultTableModel buscarEspecificaProduto(DefaultTableModel model, String nome_produto) {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		model.setRowCount(0);

		try {

			conn = DB.getConnection();
			
			st = conn.prepareStatement("select produto.id, produto.tipo_produto, produto.nome, produto.quantidade, produto.preco, produto.marca from produto "
			+ "where produto.nome LIKE ?");
			
			st.setString(1, "%" + nome_produto + "%");
			
			rs = st.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("id");
				String tipo_produto = rs.getString("tipo_produto");
				String nome = rs.getString("nome");
				int quantidade = rs.getInt("quantidade");
				double preco = rs.getDouble("preco");
				String marca = rs.getString("marca");
				model.addRow(new Object[] { id, tipo_produto, nome, quantidade, preco, marca });

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

	public static DefaultTableModel buscarVenda(DefaultTableModel model) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		model.setRowCount(0);

		try {

			conn = DB.getConnection();
			st = conn.createStatement();

			rs = st.executeQuery(
					"SELECT venda.id, venda.data, venda.forma_pagamento, venda.cpf_funcionario, venda.cpf_cliente, cli.nome, venda.id_produto, pro.nome, pro.preco "
							+ "FROM venda " + "Inner JOIN produto pro ON venda.id_produto = pro.id "
							+ "INNER JOIN cliente cli ON venda.cpf_cliente = cli.cpf");

			while (rs.next()) {

				int id = rs.getInt("id");
				String data = sdf.format(rs.getDate("data"));
				String forma_pagamento = rs.getString("forma_pagamento");
				String cpf_funcionario = rs.getString("cpf_funcionario");
				String cpf_cliente = rs.getString("cpf_cliente");
				String nome_cliente = rs.getString("nome");
				int id_produto = rs.getInt("id_produto");
				String nome_produto = rs.getString("pro.nome");
				double preco = rs.getDouble("pro.preco");

				model.addRow(new Object[] { id, data, forma_pagamento, cpf_funcionario, cpf_cliente, nome_cliente,
						id_produto, nome_produto, preco });

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

	public static DefaultTableModel buscarEspecificaVenda(DefaultTableModel model, String cpf) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		model.setRowCount(0);

		try {

			conn = DB.getConnection();

			st = conn.prepareStatement(
					"SELECT venda.id, venda.data, venda.forma_pagamento, venda.cpf_funcionario, venda.cpf_cliente, cli.nome, venda.id_produto, pro.nome, pro.preco "
							+ "FROM venda " + "Inner JOIN produto pro ON venda.id_produto = pro.id "
							+ "INNER JOIN cliente cli ON venda.cpf_cliente = cli.cpf "
							+ "WHERE venda.cpf_cliente = ?");
			
			st.setString(1, cpf);

			rs = st.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("id");
				String data = sdf.format(rs.getDate("data"));
				String forma_pagamento = rs.getString("forma_pagamento");
				String cpf_funcionario = rs.getString("cpf_funcionario");
				String cpf_cliente = rs.getString("cpf_cliente");
				String nome_cliente = rs.getString("nome");
				int id_produto = rs.getInt("id_produto");
				String nome_produto = rs.getString("pro.nome");
				double preco = rs.getDouble("pro.preco");

				model.addRow(new Object[] { id, data, forma_pagamento, cpf_funcionario, cpf_cliente, nome_cliente,
						id_produto, nome_produto, preco });

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
	
	public static void trocarProduto(int id_venda, int id_produto_venda, int id_produto) {

		Connection conn = null;
		PreparedStatement st = null;

		try {

			conn = DB.getConnection();

			st = conn.prepareStatement("UPDATE venda SET venda.id_produto = ? WHERE venda.id = ?");

			st.setInt(1, id_produto);
			st.setInt(2, id_venda);

			st.executeUpdate();			
			
			st = conn.prepareStatement("UPDATE produto SET produto.quantidade = produto.quantidade + 1 WHERE produto.id = ?");

			st.setInt(1, id_produto_venda);

			st.executeUpdate();
			
			st = conn.prepareStatement("UPDATE produto SET produto.quantidade = produto.quantidade - 1 WHERE produto.id = ?");

			st.setInt(1, id_produto);

			st.executeUpdate();
			

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}
	
	public static void inserirFornecedor(String cnpj, String nome_fantasia, String site, String email,
			String telefone, String rua, String bairro, int numero_empresa, String cidade, String estado,
			String tipo_produto) {

		String sql = "INSERT INTO fornecedor (cnpj, nome_fantasia, site, email, telefone, rua, bairro, numero_empresa, cidade, `estado`, `tipo_produto`) VALUES (? ,? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";

		try (Connection conn = DB.getConnection(); PreparedStatement st = conn.prepareStatement(sql)) {

			st.setString(1, cnpj);
			st.setString(2, nome_fantasia);
			st.setString(3, site);
			st.setString(4, email);
			st.setString(5, telefone);
			st.setString(6, rua);
			st.setString(7, bairro);
			st.setInt(8, numero_empresa);
			st.setString(9, cidade);
			st.setString(10, estado);
			st.setString(11, tipo_produto);

			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao cadastrar cliente: " + e.getMessage(), e);
		}
	}

	public static void deletarFornecedor(String cnpj) {

		Connection conn = null;
		PreparedStatement st = null;

		try {

			conn = DB.getConnection();
			st = conn.prepareStatement("delete from fornecedor where fornecedor.cnpj = ? ");

			st.setString(1, cnpj);

			st.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

	public static void alterarFornecedor(String cnpj, String nome_fantasia, String site, String email, String telefone,
			String rua, String bairro, int numero_empresa, String cidade, String estado, String tipo_produto) {

		Connection conn = null;
		PreparedStatement st = null;

		try {

			conn = DB.getConnection();
			st = conn.prepareStatement(
					"update fornecedor set fornecedor.cnpj = ?, fornecedor.nome_fantasia = ?, fornecedor.site = ?, fornecedor.email = ?, fornecedor.telefone = ?, fornecedor.rua = ?, fornecedor.bairro = ?, fornecedor.numero_empresa = ?, fornecedor.cidade = ?, fornecedor.estado = ?, fornecedor.tipo_produto = ? where fornecedor.cnpj = ? ");

			st.setString(1, cnpj);
			st.setString(2, nome_fantasia);
			st.setString(3, site);
			st.setString(4, email);
			st.setString(5, telefone);
			st.setString(6, rua);
			st.setString(7, bairro);
			st.setInt(8, numero_empresa);
			st.setString(9, cidade);
			st.setString(10, estado);
			st.setString(11, tipo_produto);
			st.setString(12, cnpj);

			st.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

	public static DefaultTableModel buscaEspecificaFornecedor(DefaultTableModel model, String buscar) {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		model.setRowCount(0);

		try {

			conn = DB.getConnection();
			st = conn.prepareStatement("select * from fornecedor where fornecedor.nome_fantasia like ?");
			st.setString(1, "%" + buscar + "%");

			rs = st.executeQuery();

			while (rs.next()) {

				String cnpj = rs.getString("cnpj");
				String nome_fantasia = rs.getString("nome_fantasia");
				String site = rs.getString("site");
				String email = rs.getString("email");
				String telefone = rs.getString("telefone");
				String rua = rs.getString("rua");
				String bairro = rs.getString("bairro");
				String numero_empresa = rs.getString("numero_empresa");
				String cidade = rs.getString("cidade");
				String estado = rs.getString("estado");
				String tipo_produto = rs.getString("tipo_produto");

				model.addRow(new Object[] { cnpj, nome_fantasia, site, email, telefone, rua, bairro, numero_empresa,
						cidade, estado, tipo_produto });

			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			DB.closeStatement(st);
			DB.closeConnection();
		}

		return model;

	}

	public static DefaultTableModel buscarFornecedor(DefaultTableModel model) {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		model.setRowCount(0);

		try {

			conn = DB.getConnection();
			st = conn.createStatement();

			rs = st.executeQuery(
					"select fornecedor.cnpj, fornecedor.nome_fantasia, fornecedor.site, fornecedor.email, fornecedor.telefone, fornecedor.rua, fornecedor.bairro, fornecedor.numero_empresa, fornecedor.cidade, fornecedor.estado, fornecedor.tipo_produto from fornecedor");

			while (rs.next()) {

				String cnpj = rs.getString("cnpj");
				String nome_fantasia = rs.getString("nome_fantasia");
				String site = rs.getString("site");
				String email = rs.getString("email");
				String telefone = rs.getString("telefone");
				String rua = rs.getString("rua");
				String bairro = rs.getString("bairro");
				String numero_empresa = rs.getString("numero_empresa");
				String cidade = rs.getString("cidade");
				String estado = rs.getString("estado");
				String tipo_produto = rs.getString("tipo_produto");

				model.addRow(new Object[] { cnpj, nome_fantasia, site, email, telefone, rua, bairro, numero_empresa,
						cidade, estado, tipo_produto });

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
	
	public static void inserirProdutoRoupa(String nome, int quantidade, Double preco, String marca, String tamanho,
			String cor, String genero, String descricao) {

		Connection conn = null;
		PreparedStatement st = null;

		try {

			conn = DB.getConnection();
			st = conn.prepareStatement(
					"insert into produto (tipo_produto, nome, quantidade, preco, marca, tamanho, cor, genero, descricao) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");

			st.setString(1, "ROUPA");
			st.setString(2, nome);
			st.setInt(3, quantidade);
			st.setDouble(4, preco);
			st.setString(5, marca);
			st.setString(6, tamanho);
			st.setString(7, cor);
			st.setString(8, genero);
			st.setString(9, descricao);

			st.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

	public static DefaultTableModel buscaEspecificaProduto(DefaultTableModel model, String nome_busca) {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		model.setNumRows(0);

		try {

			conn = DB.getConnection();

			st = conn.prepareStatement(
					"SELECT produto.id, produto.tipo_produto, produto.nome, produto.quantidade, produto.preco, produto.marca from produto WHERE produto.nome like ?");
			st = conn.prepareStatement(
					"SELECT produto.id, produto.tipo_produto, produto.nome, produto.quantidade, produto.preco, produto.marca from produto WHERE produto.tipo_produto like ?");
			st = conn.prepareStatement(
					"SELECT produto.id, produto.tipo_produto, produto.nome, produto.quantidade, produto.preco, produto.marca from produto WHERE produto.marca like ?");

			st.setString(1, "%" + nome_busca + "%");

			rs = st.executeQuery();

			while (rs.next()) {

				String id = rs.getString("id");
				String tipo_produto = rs.getString("tipo_produto");
				String nome = rs.getString("nome");
				String quantidade = rs.getString("quantidade");
				String preco = rs.getString("preco");
				String marca = rs.getString("marca");

				model.addRow(new Object[] { id, tipo_produto, nome, quantidade, preco, marca });

			}

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			DB.closeStatement(st);

			DB.closeConnection();
		}

		return model;

	}

	public static DefaultTableModel buscarProduto(DefaultTableModel model) {

		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = DB.getConnection();
			st = conn.createStatement();

			rs = st.executeQuery(
					"select produto.id, produto.tipo_produto, produto.nome, produto.quantidade, produto.preco, produto.marca from produto");

			while (rs.next()) {

				String id = rs.getString("id");
				String tipo_produto = rs.getString("tipo_produto");
				String nome = rs.getString("nome");
				String quantidade = rs.getString("quantidade");
				String preco = rs.getString("preco");
				String marca = rs.getString("marca");

				model.addRow(new Object[] { id, tipo_produto, nome, quantidade, preco, marca });
			}

		} catch (SQLException e) {
			// TODO: handle exception

			e.printStackTrace();
		} finally {

			DB.closeStatement(st);
			DB.closeResultSet(rs);
			DB.closeConnection();
		}
		return model;
	}

	public static ArrayList<String> buscarRoupaId(String id) {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList<String> dados_roupa = new ArrayList<String>();

		try {
			conn = DB.getConnection();

			st = conn.prepareStatement(
					"select produto.tipo_produto, produto.nome, produto.quantidade, produto.preco, produto.marca, produto.tamanho, produto.cor, produto.genero, produto.descricao from produto where produto.id = ?");

			st.setString(1, id);

			rs = st.executeQuery();

			while (rs.next()) {

				dados_roupa.add(rs.getString("nome"));
				dados_roupa.add(rs.getString("quantidade"));
				dados_roupa.add(rs.getString("preco"));
				dados_roupa.add(rs.getString("marca"));
				dados_roupa.add(rs.getString("tamanho"));
				dados_roupa.add(rs.getString("cor"));
				dados_roupa.add(rs.getString("genero"));
				dados_roupa.add(rs.getString("descricao"));

			}

		} catch (SQLException e) {
			// TODO: handle exception

			e.printStackTrace();
		} finally {

			DB.closeStatement(st);
			DB.closeResultSet(rs);
			DB.closeConnection();
		}
		return dados_roupa;
	}

	public static void inserirProdutoPerfume(String nome, int quantidade, Double preco, String marca, Double volume,
			String tipo_perfume) {

		Connection conn = null;
		PreparedStatement st = null;

		try {

			conn = DB.getConnection();
			st = conn.prepareStatement(
					"insert into produto (tipo_produto, nome, quantidade, preco, marca, volume, tipo_perfume) values (?, ?, ?, ?, ?, ?, ?)");

			st.setString(1, "PERFUME");
			st.setString(2, nome);
			st.setInt(3, quantidade);
			st.setDouble(4, preco);
			st.setString(5, marca);
			st.setDouble(6, volume);
			st.setString(7, tipo_perfume);

			st.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

	public static ArrayList<String> buscarPerfumeId(String id) {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList<String> dados_perfume = new ArrayList<String>();

		try {
			conn = DB.getConnection();

			st = conn.prepareStatement(
					"select  produto.tipo_produto, produto.nome, produto.quantidade, produto.preco, produto.marca, produto.volume, produto.tipo_perfume from produto where produto.id = ?");

			st.setString(1, id);

			rs = st.executeQuery();

			while (rs.next()) {

				dados_perfume.add(rs.getString("nome"));
				dados_perfume.add(rs.getString("quantidade"));
				dados_perfume.add(rs.getString("preco"));
				dados_perfume.add(rs.getString("marca"));
				dados_perfume.add(rs.getString("volume"));
				dados_perfume.add(rs.getString("tipo_perfume"));

			}

		} catch (SQLException e) {
			// TODO: handle exception

			e.printStackTrace();
		} finally {

			DB.closeStatement(st);
			DB.closeResultSet(rs);
			DB.closeConnection();
		}
		return dados_perfume;
	}

	public static void inserirProdutoLivro(String nome, int quantidade, Double preco, String marca, String traducao,
			String autor, String editora) {

		Connection conn = null;
		PreparedStatement st = null;

		try {

			conn = DB.getConnection();
			st = conn.prepareStatement(
					"insert into produto (tipo_produto, nome, quantidade, preco, marca, traducao, autor, editora) values (?, ?, ?, ?, ?, ?, ?, ?)");

			st.setString(1, "LIVRO");
			st.setString(2, nome);
			st.setInt(3, quantidade);
			st.setDouble(4, preco);
			st.setString(5, marca);
			st.setString(6, traducao);
			st.setString(7, autor);
			st.setString(8, editora);

			st.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}

	public static ArrayList<String> buscarLivroId( String id) {

		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		ArrayList<String> dados_livro = new ArrayList<String>();

		try {
			conn = DB.getConnection();
			st = conn.prepareStatement("select  produto.tipo_produto, produto.nome, produto.quantidade, produto.preco, produto.marca, produto.traducao, produto.autor, produto.editora from produto where produto.id = ?");
            
			st.setString(1, id);
			
			rs = st.executeQuery();
			
			

			while (rs.next()) {

				
				dados_livro.add(rs.getString("nome"));
				dados_livro.add(rs.getString("quantidade"));
				dados_livro.add(rs.getString("preco"));
				dados_livro.add(rs.getString("marca"));
				dados_livro.add(rs.getString("traducao"));
				dados_livro.add(rs.getString("autor"));
				dados_livro.add(rs.getString("editora"));

			}

		} catch (SQLException e) {
			// TODO: handle exception

			e.printStackTrace();
		} finally {

			DB.closeStatement(st);
			DB.closeResultSet(rs);
			DB.closeConnection();
		}
		return dados_livro;
	}	

}
