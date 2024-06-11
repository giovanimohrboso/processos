package com.GMBLimpeza.processos.controles;

import com.GMBLimpeza.processos.dtos.OrdemServicoDTO;
import com.GMBLimpeza.processos.modelos.ClienteModel;
import com.GMBLimpeza.processos.modelos.OrdemServicoModel;
import com.GMBLimpeza.processos.repositorios.ClienteRepository;
import com.GMBLimpeza.processos.repositorios.OrdemServicoRepository;
import com.GMBLimpeza.processos.servicos.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class OrdemServicoController {
    @Autowired
    OrdemServicoRepository ordemServicoRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    private EmailService emailService;

    @PostMapping("/ordemServico")
    public ResponseEntity<OrdemServicoModel> saveOrdemServico(@RequestBody OrdemServicoDTO ordemServicoDTO){
        var clienteModel = new ClienteModel();
        BeanUtils.copyProperties(ordemServicoDTO.cliente(), clienteModel);
        var clienteSalvo = clienteRepository.save(clienteModel);
        var ordemServicoModel = new OrdemServicoModel();
        BeanUtils.copyProperties(ordemServicoDTO, ordemServicoModel);
        ordemServicoModel.setCliente(clienteSalvo);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ordemServicoDTO.dataAtendimento());
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        ordemServicoModel.setDataAtendimento(calendar.getTime());
        String textoEmail;
        textoEmail = "Olá " + clienteSalvo.getNome() + " Sua Limpeza foi agendada para " + formatter.format(ordemServicoModel.getDataAtendimento());
        emailService.sendEmail(clienteSalvo.getEmail(),"GMPLimpeza - Limpeza Agendada",textoEmail);
        return ResponseEntity.status(HttpStatus.CREATED).body(ordemServicoRepository.save(ordemServicoModel));
    }

    @GetMapping("/ordens")
    public ResponseEntity<List<OrdemServicoModel>> getAllOrdens() {
        List<OrdemServicoModel> ordemServicoList = ordemServicoRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(ordemServicoList);
    }
}
