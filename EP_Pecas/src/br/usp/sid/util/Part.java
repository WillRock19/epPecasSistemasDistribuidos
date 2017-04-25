package br.usp.sid.util;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Part implements Serializable
{

	private String codigo;

    private String nome;

    private String descricao;

    private List<Subcomponente> subComponentes;
    
    public Part(String nome, String codigo, String descricao) 
    {
    	this.nome = nome;
    	this.codigo = codigo;
    	this.descricao = descricao;
    	this.subComponentes = new LinkedList<>();
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

	public List<Subcomponente> getSubComponentes() {
		return subComponentes;
	}

	public void setSubComponentes(List<Subcomponente> subComponentes) {
		this.subComponentes = subComponentes;
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
		this.subComponentes.clear();
	}
	
	private boolean SubComponentesEstaVazio()
	{
		return subComponentes.isEmpty();
	}
}
