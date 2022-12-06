package com.training.libraryapi.controller;

import com.training.libraryapi.domain.Produto;
import com.training.libraryapi.service.ProdutosService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/produtos")
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProdutoController {

    @Autowired
    private final ProdutosService produtosService;

    @GetMapping(path = "/allProdutos")
    public ResponseEntity<List<Produto>> listAll() {
        return ResponseEntity.ok(produtosService.getAllProdutos());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(produtosService.findById(id));
    }

    @GetMapping(path = "/qtdProdutos")
    public ResponseEntity<Long> qtdProduto() {
        return ResponseEntity.ok(produtosService.totalProdutos());
    }
    @PostMapping
    public ResponseEntity<Produto> savedProduto(@RequestBody Produto produto){
        return new ResponseEntity<>(produtosService.save(produto), HttpStatus.CREATED);
    }


}
