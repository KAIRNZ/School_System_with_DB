package classes;

public class Disciplina {
	private String nome;
	private String professor_id;
	private String turma_id;
	
	public Disciplina(String nome, String turma_id, String professor_id) {
		this.nome = nome;
		this.professor_id = professor_id;
		this.turma_id = turma_id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProfessor_id() {
		return professor_id;
	}

	public void setProfessor_id(String professor_id) {
		this.professor_id = professor_id;
	}

	public String getTurma_id() {
		return turma_id;
	}

	public void setTurma_id(String turma_id) {
		this.turma_id = turma_id;
	}

}
