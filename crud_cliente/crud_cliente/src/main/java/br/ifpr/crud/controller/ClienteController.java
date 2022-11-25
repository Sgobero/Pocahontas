package br.ifpr.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ifpr.crud.exception.ApiException;
import br.ifpr.crud.model.Cliente;
import br.ifpr.crud.repository.ClienteRepository;
import br.ifpr.crud.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscar(
			@PathVariable Integer id) {
		Optional<Cliente> optCliente = 
				clienteRepository.findById(id);
		
		if(optCliente.isPresent())
			return new ResponseEntity<Cliente>(
					optCliente.get(), HttpStatus.OK);
		
		return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> inserir(
			@RequestBody Cliente cliente) {
	
		return clienteService.inserir(cliente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(
			@PathVariable Integer id,
			@RequestBody Cliente cliente) {
			
		return clienteService.atualizar(id, cliente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		if(! clienteRepository.existsById(id))
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
		try {
			clienteRepository.deleteById(id);
			
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			throw new ApiException("Erro ao remover o cliente.");
		}
	}

}
