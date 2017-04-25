package br.usp.sid.util;

import java.io.Serializable;

public class Subcomponente implements Serializable
{	
	public Part subPeca;
	
	public int quantidadeSubpecas;
	
	public Subcomponente(Part subParte, int quantidadeSubpecas)
	{
		this.subPeca = subParte;
		this.quantidadeSubpecas = quantidadeSubpecas;
	}
}
