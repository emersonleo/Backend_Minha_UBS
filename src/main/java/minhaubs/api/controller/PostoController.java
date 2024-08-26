package minhaubs.api.controller;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import minhaubs.api.DTO.FamilyDTO;
import minhaubs.api.DTO.PessoaDTO;
import minhaubs.api.entity.Familia;
import minhaubs.api.entity.InformacoesSaude;
import minhaubs.api.entity.Pessoa;
import minhaubs.api.entity.Posto;
import minhaubs.api.entity.RegistrosCasos;
import minhaubs.api.entity.Visita;
import minhaubs.api.repository.FamiliaRepository;
import minhaubs.api.repository.InformacoesSaudeRepository;
import minhaubs.api.repository.PessoaRepository;
import minhaubs.api.repository.PostoPessoaRepository;
import minhaubs.api.repository.PostoRepository;
import minhaubs.api.repository.RegistrosCasosRepository;
import minhaubs.api.repository.VisitaRepository;

@CrossOrigin
@RestController
@RequestMapping("posto")
public class PostoController {
    
    @Autowired
	private PostoPessoaRepository postoPessoaRepository;

    @Autowired
	private PessoaRepository pessoaRepository;
    
    @Autowired
	private FamiliaRepository familiaRepository;

    @Autowired
	private VisitaRepository visitaRepository;

    @Autowired
	private InformacoesSaudeRepository informacoesSaudeRepository;

    @Autowired
	private PostoRepository postoRepository;

    @Autowired
	private RegistrosCasosRepository registerCaseRepository;

    @GetMapping("/pessoas")
    @ResponseBody
    public List<PessoaDTO> getPessoasByPosto(@RequestParam(name = "posto") Long BHUId ) throws NoSuchAlgorithmException{
        List<String> resultSearch = postoPessoaRepository.findAllPersonsByBHUId(BHUId);
        ArrayList<PessoaDTO> result = new ArrayList<>();
        for(String i: resultSearch){
            String[] personFinded = i.split(",");
            result.add(new PessoaDTO(Integer.parseInt(personFinded[0]), personFinded[1], personFinded[2]));
        }
        return result;
    }

    @GetMapping("/familia")
    @ResponseBody
    public List<FamilyDTO> getFamiliaByPosto(@RequestParam(name = "posto") Long BHUId ) throws NoSuchAlgorithmException{
        List<String> resultSearch = postoPessoaRepository.findAllFamiliesByBHUId(BHUId);
        ArrayList<FamilyDTO> result = new ArrayList<>();
        for(String i: resultSearch){
            String[] familyFinded = i.split(",");
            result.add(new FamilyDTO(familyFinded[0], familyFinded[1]));
        }
        return result;
    }

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


    //@SuppressWarnings("rawtypes")
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


    @SuppressWarnings("rawtypes")
    @PostMapping("/registrarcaso")
    @ResponseBody
    public ResponseEntity registerCase(@RequestBody Map<String, String> caseData) throws NoSuchAlgorithmException{

        Long idUnit = Long.parseLong(caseData.get("posto"));
        Long idAgent = Long.parseLong(caseData.get("agente"));
        Long idPerson = Long.parseLong(caseData.get("pessoa"));
        Long idCase = Long.parseLong(caseData.get("caso"));

        if(idUnit == null || idAgent == null || idPerson == null || idCase == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Pessoa> agent = pessoaRepository.findById(idAgent);
        Optional<Pessoa> person = pessoaRepository.findById(idPerson);
        Optional<InformacoesSaude> infoHealth = informacoesSaudeRepository.findById(idCase);
        Optional<Posto> unit = postoRepository.findById(idUnit);

        RegistrosCasos registerCase = new RegistrosCasos();
        registerCase.setId(123L);
        registerCase.setAgente(agent.get()); 
        registerCase.setPessoa(person.get());
        registerCase.setInfo_saude(infoHealth.get());
        registerCase.setPosto(unit.get());

        registerCaseRepository.save(registerCase);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
}
