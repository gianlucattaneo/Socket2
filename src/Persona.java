import java.util.ArrayList;
import java.util.List;

public class Persona {
	private String nome;
	private List<Persona> amici;
	
	Persona(String nome){
		this.nome = nome;
		this.amici = new ArrayList<Persona>();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Persona> getAmici() {
		return amici;
	}
	public void addAmico(Persona nome) {
		this.amici.add(nome);
	}
	public boolean findAmico(String nome) {
		boolean found = false;
		for(Persona p : amici) {
			if(p.nome.equals(nome)) {
				found = true;
				break;
			}
		}
		return found;
	}
	public boolean deleteAmico(String nome) {
		boolean removed = false;
		for(Persona p : amici) {
			if(p.nome.equals(nome)) {
				amici.remove(p);
				removed = true;
				break;
			}
		}
		return removed;
	}
	public String printAmici() {
		String result = "";
		for(Persona p : amici) {
			result += p.getNome() + ", ";
			}
		if (result.length()>2) {
			result = result.substring(0,result.length()-2);
		}
		return result;
	}
	public String toString() {
		return String.format("Nome: %s", this.nome);
	}
}
