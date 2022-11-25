package br.ifpr.crud.execeptionhandler;

import java.time.OffsetDateTime;

public class MensagemException {
	
	private Integer status;
	private OffsetDateTime dataHora;
	private String titulo;
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public OffsetDateTime getDataHora() {
		return dataHora;
	}
	
	public void setDataHora(OffsetDateTime dataHora) {
		this.dataHora = dataHora;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
}
