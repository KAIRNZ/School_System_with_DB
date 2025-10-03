package classes;

public class Turma {
	private String nome;
	private String turno;
	private String data_inicio;
	private String data_termino;
	
	public Turma(String nome, String turno, String data_inicio, String data_termino) {
		this.nome = nome;
		this.turno = turno;
		this.data_inicio = data_inicio;
		this.data_termino = data_termino;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(String data_inicio) {
		this.data_inicio = data_inicio;
	}

	public String getData_termino() {
		return data_termino;
	}

	public void setData_termino(String data_termino) {
		this.data_termino = data_termino;
	}

}
