package com.cefet.ds_guia09.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.ds_guia09.dto.ProdutoDTO;
import com.cefet.ds_guia09.entities.Produto;
import com.cefet.ds_guia09.repositories.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	// Buscar todos
	public List<ProdutoDTO> findAll() {
		List<Produto> listaProdutos = produtoRepository.findAll();
		return listaProdutos.stream().map(ProdutoDTO::new).toList();
	}
	
	// Buscar por ID
    public ProdutoDTO findById(Long id) {
    	Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + id));
        return new ProdutoDTO(produto);    
    }	
    
    // Inserir Produto
    public ProdutoDTO insert(ProdutoDTO produtoDTO) {
    	Produto produto = new Produto();
    	produto.setTitulo(produtoDTO.getTitulo());
    	produto.setDescricao(produtoDTO.getDescricao());
    	produto.setPreco(produtoDTO.getPreco());
    	produto.setEstoque(produtoDTO.getEstoque());
    	produto.setTipo(produtoDTO.getTipo());   
        Produto produtoSalvo = produtoRepository.save(produto);
        return new ProdutoDTO(produtoSalvo);
    }
    
    // Atualizar Produto 
    public ProdutoDTO update(Long id, ProdutoDTO produtoDTO) {
    	Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + id));
    	produto.setTitulo(produtoDTO.getTitulo());
    	produto.setDescricao(produtoDTO.getDescricao());
    	produto.setPreco(produtoDTO.getPreco());
    	produto.setEstoque(produtoDTO.getEstoque());
    	produto.setTipo(produtoDTO.getTipo());        
    	Produto tipoAtualizado = produtoRepository.save(produto);
        return new ProdutoDTO(tipoAtualizado);
    }

    // Remover por ID
    public void delete(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new EntityNotFoundException("Produto não encontrado com ID: " + id);
        }
        produtoRepository.deleteById(id);
    }
    
    
	// Buscar todos por tipo
	public List<ProdutoDTO> findByTipoId(Long tipoId) {
		List<Produto> listaProdutos = produtoRepository.findByTipoId(tipoId);
		return listaProdutos.stream().map(ProdutoDTO::new).toList();
	}

}
