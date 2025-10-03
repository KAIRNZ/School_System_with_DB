package classes;

public class CadastroAuxiliar {

	private String[] nomeProfessores;
	private String[] idsProfessor;

	private String[] nomes;
	private String[] ids;
	
	public CadastroAuxiliar(String[] ids, String[] nomes) {
		this.ids = ids; this.nomes = nomes;
	}
	public CadastroAuxiliar(String [] ids, String[] nomes, String[] idsProfessor, String[] nomeProfessores) {
		this.idsProfessor = idsProfessor;this.nomeProfessores=nomeProfessores;this.ids=ids;this.nomes=nomes;
	}

	public String retornarId(int n) {
		return ids[n];
	}
	
	public String[] getNomeProfessores() {
		return nomeProfessores;
	}
	public void setNomeProfessores(String[] nomeProfessores) {
		this.nomeProfessores = nomeProfessores;
	}
	public String[] getIdsProfessor() {
		return idsProfessor;
	}
	public void setIdsProfessor(String[] idsProfessor) {
		this.idsProfessor = idsProfessor;
	}
	public String[] getNomes() {
		return nomes;
	}
	public void setNomes(String[] nomes) {
		this.nomes = nomes;
	}
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	

}
