package br.ifpr.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ifpr.crud.model.Cliente;

public interface ClienteRepository extends 
	JpaRepository<Cliente, Integer> {
	
	Optional<Cliente> findByEmail(String email);

}
