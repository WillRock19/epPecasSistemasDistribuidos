import java.util.List;

public class Part
{

	private String codigo;

    private String nome;

    private String descricao;

    private List<Subcomponente> subcomponentes;

    public Part() {}
    
    public Part(String nome, String codigo, String descricao) 
    {
    	this.nome = nome;
    	this.codigo = codigo;
    	this.descricao = descricao;
    }

	public Part(String codigo, String nome, String descricao, List<Subcomponente> subcomponentes) {
	    this.codigo = codigo;
	    this.nome = nome;
	    this.descricao = descricao;
	    this.subcomponentes = subcomponentes;
    }

	public String getCodigo() {

		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Subcomponente> getSubcomponentes() {
		return subcomponentes;
	}

	public void setSubcomponentes(List<Subcomponente> subcomponentes) {
		this.subcomponentes = subcomponentes;
	}

	public boolean PecaEhPrimitiva()
	{
		 return SubComponentesEstaVazio();
	}
	
	public boolean PecaEhAgregada()
	{
		return !SubComponentesEstaVazio();
	}
	
	public void LimparSubcomponentes()
	{
		this.subcomponentes.clear();
	}
	
	private boolean SubComponentesEstaVazio()
	{
		return subcomponentes.isEmpty();
	}
}
