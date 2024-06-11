package com.GMBLimpeza.processos.dtos;

import com.GMBLimpeza.processos.modelos.ClienteModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public record OrdemServicoDTO(ClienteModel cliente,
                              Integer numeroQuartos,
                              Integer numeroBanheiros,
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") Date dataAtendimento,
                              BigDecimal valor) {
}
