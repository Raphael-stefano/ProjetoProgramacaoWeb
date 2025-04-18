package com.example.trablho_prog_web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1")
@Tag(name = "MainRest", description = "Exemplo de um RestController")
public class ApiRestController {

	@GetMapping(path = "/exemplo")
	@Operation(summary = "Retorna todos os exemplos armazenados")
	@ApiResponses({ @ApiResponse(responseCode = "200")

	})
	public ResponseEntity<List<Map<String, String>>> getExemplo() {
		List<Map<String, String>> listaExemplo = new ArrayList<>();
		Map<String, String> item1 = new HashMap<>();
		Map<String, String> item2 = new HashMap<>();
		item1.put("id", "1");
		item1.put("nome", "Raphael");
		item2.put("id", "2");
		item2.put("nome", "Stefano");

		listaExemplo.add(item1);
		listaExemplo.add(item2);

		return ResponseEntity.ok().header("Content-Type", "application/json").body(listaExemplo);
	}
	
	@Operation(summary="Retorna um exemplo por Id")
	@GetMapping(path = "/exemplos/{id}")
	public ResponseEntity<String> getExemploId(@PathVariable("id") int idx) {
		return ResponseEntity.ok().header("Content-Type", "application/json").body("Exemplo -> " + idx);
	}
	
	@Operation(summary="Atualiza um exemplo por id")
	@PutMapping(path = "/exemplos/{id}")
	public String getExemploIdParam(@PathVariable("id") int id, @RequestParam("nome") String nome) {
		return "Id ->" + id + " nome ->" + nome;
	}

}