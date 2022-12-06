package com.training.libraryapi.service;

import com.training.libraryapi.domain.Produto;
import com.training.libraryapi.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProdutosService {

    private static List<Produto> produtos;
    private final ProdutoRepository produtoRepository;

    /*static {
        produtos = new ArrayList<>(List.of(new Produto(1L,
                        "Carioca",
                        "jdçlkasjdla",
                        "Preto e Forte",
                        2.50,
                        "tradicional"),
                new Produto(2L,
                        "Expresso",
                        "jdçlkasjdla",
                        "Intenso",
                        3.50,
                        "tradicional")));
    }*/

    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    public Produto findById(long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto não encontrado"));

    }

    public long totalProdutos() {
        long qtdProdutos = produtoRepository.findAll().size();
        log.info("Quantidade de Produtos: {}", qtdProdutos);
        return qtdProdutos;
    }

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

}
