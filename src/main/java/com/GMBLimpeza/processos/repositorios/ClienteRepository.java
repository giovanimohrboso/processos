package com.GMBLimpeza.processos.repositorios;

import com.GMBLimpeza.processos.modelos.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<ClienteModel, UUID> {
}
