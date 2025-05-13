package com.cefet.ds_guia09.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cefet.ds_guia09.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	List<Produto> findByTipoId(Long tipoId);
}
	