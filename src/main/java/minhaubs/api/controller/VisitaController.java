package minhaubs.api.controller;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import minhaubs.api.entity.Familia;
import minhaubs.api.entity.Pessoa;
import minhaubs.api.entity.Posto;
import minhaubs.api.entity.Visita;
import minhaubs.api.repository.FamiliaRepository;
import minhaubs.api.repository.PessoaRepository;
import minhaubs.api.repository.PostoRepository;
import minhaubs.api.repository.VisitaRepository;


@CrossOrigin
@RestController
@RequestMapping("visita")
public class VisitaController {

    @Autowired
	private PessoaRepository pessoaRepository;

    @Autowired
	private FamiliaRepository familiaRepository;

    @Autowired
	private VisitaRepository visitaRepository;

    @Autowired
	private PostoRepository postoRepository;

    @SuppressWarnings("rawtypes")
    @PostMapping("/visita")
    @ResponseBody
    public ResponseEntity createVisit(@RequestBody Map<String, String> visitData) throws NoSuchAlgorithmException{

        Long idPerson = Long.parseLong(visitData.get("agente"));
        Long idFamily = Long.parseLong(visitData.get("familia"));
        Long idUnit = Long.parseLong(visitData.get("posto"));

        Optional<Familia> family = familiaRepository.findById(idFamily);
        Optional<Pessoa> person = pessoaRepository.findById(idPerson);
        Optional<Posto> unit = postoRepository.findById(idUnit);

        if(family.isEmpty() || person.isEmpty() || unit.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
        }

        Visita visita = new Visita();
        visita.setId(123L);
        visita.setFamilia(family.get()); 
        visita.setPessoa(person.get());
        visita.setPosto(unit.get());
        visita.setDataHora(LocalDateTime.now());
        
        visitaRepository.save(visita);
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PostMapping("/listarvisitas")
    @ResponseBody
    public List<Visita> listVisit(@RequestBody Map<String, String> visitData) throws NoSuchAlgorithmException{

        Long idUnit = Long.parseLong(visitData.get("posto"));
        Long idAgent = Long.parseLong(visitData.get("agente"));
        //Long idFamilia = Long.parseLong(visitData.get("familia"));
        String dateStart = visitData.get("dataInicio");
        String dateEnd = visitData.get("dataFim");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dataInicio = dateStart.isEmpty() ? null : LocalDateTime.parse(dateStart, formatter);
        LocalDateTime dataFim = dateEnd.isEmpty() ? null : LocalDateTime.parse(dateEnd, formatter);

        List<Visita> result =  visitaRepository.findByFiltro(idUnit, idAgent, dataInicio, dataFim);

    	return result;
    }

}
