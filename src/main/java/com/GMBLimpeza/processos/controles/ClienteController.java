package com.GMBLimpeza.processos.controles;

import com.GMBLimpeza.processos.modelos.ClienteModel;
import com.GMBLimpeza.processos.repositorios.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @PostMapping("/cliente")
    public ResponseEntity<ClienteModel> saveCliente(@RequestBody ClienteModel clienteModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(clienteModel));
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteModel>> getAllClientes() {
        List<ClienteModel> clienteList = clienteRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(clienteList);
    }

}
