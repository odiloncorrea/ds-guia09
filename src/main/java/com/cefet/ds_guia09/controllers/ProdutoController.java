package com.cefet.ds_guia09.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cefet.ds_guia09.dto.ProdutoDTO;
import com.cefet.ds_guia09.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
    	ProdutoDTO produtoDTO = produtoService.findById(id);
        return ResponseEntity.ok(produtoDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAll() {
        List<ProdutoDTO> produtosDTOs = produtoService.findAll();
        return ResponseEntity.ok(produtosDTOs);
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> create(@RequestBody ProdutoDTO produtoDTO) {
    	ProdutoDTO novoProduto = produtoService.insert(produtoDTO);
        return ResponseEntity.status(201).body(novoProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @RequestBody ProdutoDTO tipoDTO) {
    	ProdutoDTO produtoAtualizado = produtoService.update(id, tipoDTO);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
    	produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/tipo/{tipoId}")
    public ResponseEntity<List<ProdutoDTO>> findByTipoId(@PathVariable Long tipoId) {
        List<ProdutoDTO> produtosDTOs = produtoService.findByTipoId(tipoId);
        return ResponseEntity.ok(produtosDTOs);
    }
}
