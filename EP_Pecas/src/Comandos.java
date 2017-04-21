
public enum Comandos 
{
	LISTARPECA("listpart"), 
	RECUPERARPECA("getpart"), 
	MOSTRARPECA("showpart"), 
	LIMPARLISTA("clearlist"), 
	ADDSUBPECA("addsubpart"),
	ADDPECA("addpart"),
	ENCERRAR("quit");
	
	private final String valor;
	
	Comandos(String valorComando)
	{
		valor = valorComando;
	}
	
	public String getValor()
	{
		return valor;
	}
	
	public boolean Equals(String comando)
	{
		return valor.equals(comando.toLowerCase());
	}
}
