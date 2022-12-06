package com.training.libraryapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Size(min = 2, max = 100)
	private String nome;

	@NotNull
	@Size(min = 2, max = 100)
	private String usuario;

	@NotNull
	@Size(min = 5, max = 100)
	private String senha;

    @NotNull
	private String email;
	
	//private String tipo;
	
	@OneToMany(mappedBy = "usuario",cascade= CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Produto> produtos;

}
