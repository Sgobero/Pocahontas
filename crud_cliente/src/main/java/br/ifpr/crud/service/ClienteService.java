package br.ifpr.crud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.ifpr.crud.exception.ApiException;
import br.ifpr.crud.exception.NegocioException;
import br.ifpr.crud.model.Cliente;
import br.ifpr.crud.repository.ClienteRepository;

@Component
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public ResponseEntity<Cliente> inserir(Cliente cliente) {
		try {
			//valida campos obrigatorios
			this.validaCamposObrigatorios(cliente);
			
			
			
			
			//Validar o email duplicado
			Optional<Cliente> optCliente = 			
					clienteRepository.findByEmail(cliente.getEmail());
			
			if(optCliente.isPresent())
				throw new NegocioException(
						"Erro ao inserir o cliente."+ 
				"E-mail já utilizado por outro cliente!");
			
			//Salvar
			cliente = clienteRepository.save(cliente);
			return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED); 
		} catch (Exception e) {
			if(e instanceof NegocioException)
				throw e;
			else
				throw new ApiException("Erro ao inserir o cliente.");
		}
	}
	
	private void validaCamposObrigatorios(Cliente cliente) 
					throws NegocioException{
		//validar nome
		if(cliente.getNome() == null ||
				cliente.getNome().trim().equals(""))
			throw new NegocioException("Erro ao salvar o cliete." + 
			" Campo nome é obrigatório.");
	}
	
}
