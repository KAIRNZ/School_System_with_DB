package classes;

public class Aluno {
	
	private String nome;
	private String data_nascimento;
	private String turma_id;
	private String cidade;
	private String estado;
	
	public Aluno(String nome, String data_nascimento, String turma_id, String cidade, String estado) {
		this.nome = nome;
		this.data_nascimento = data_nascimento;
		this.turma_id = turma_id;
		this.cidade = cidade;
		this.estado = estado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public String getTurma_id() {
		return turma_id;
	}

	public void setTurma_id(String turma_id) {
		this.turma_id = turma_id;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
