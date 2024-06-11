package com.GMBLimpeza.processos.repositorios;

import com.GMBLimpeza.processos.modelos.OrdemServicoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrdemServicoRepository extends JpaRepository<OrdemServicoModel, UUID> {
}
