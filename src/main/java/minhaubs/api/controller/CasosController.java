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

import minhaubs.api.entity.Endereco;
import minhaubs.api.entity.InformacoesSaude;
import minhaubs.api.entity.Pessoa;
import minhaubs.api.entity.Posto;
import minhaubs.api.entity.RegistrosCasos;
import minhaubs.api.repository.EnderecoRepository;
import minhaubs.api.repository.InformacoesSaudeRepository;
import minhaubs.api.repository.PessoaRepository;
import minhaubs.api.repository.PostoRepository;
import minhaubs.api.repository.RegistrosCasosRepository;

@CrossOrigin
@RestController
@RequestMapping("casos")
public class CasosController {
    
    @Autowired
	private InformacoesSaudeRepository informacoesSaudeRepository;

    @Autowired
	private PostoRepository postoRepository;

    @Autowired
	private RegistrosCasosRepository registerCaseRepository;

    @Autowired
	private EnderecoRepository enderecoRepository;

    @Autowired
	private PessoaRepository pessoaRepository;
    
    
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
        Optional<Endereco> endereco = enderecoRepository.findByPerson(idPerson);

        RegistrosCasos registerCase = new RegistrosCasos();
        registerCase.setId(123L);
        registerCase.setAgente(agent.get()); 
        registerCase.setPessoa(person.get());
        registerCase.setInfo_saude(infoHealth.get());
        registerCase.setPosto(unit.get());
        registerCase.setEndereco(endereco.get());

        registerCaseRepository.save(registerCase);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PostMapping("/listarcasos")
    @ResponseBody
    public List<Endereco> getCases(@RequestBody Map<String, String> caseData){
        String mockHour = " 00:00:00";
        Long idUnit = Long.parseLong(caseData.get("posto"));
        Long idAgent = Long.parseLong(caseData.get("agente"));
        Long idCase = Long.parseLong(caseData.get("caso"));

        String dateStart = caseData.get("dataInicio");
        String dateEnd = caseData.get("dataFim");

        String dateHourStart = dateStart + mockHour;
        String dateHourEnd = dateEnd + mockHour;
        List<Endereco> cases = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        try {
            LocalDateTime  dataInicio = dateStart.isEmpty() ? null : LocalDateTime.parse(dateHourStart, formatter);
            LocalDateTime dataFim = dateEnd.isEmpty() ? null : LocalDateTime .parse(dateHourEnd, formatter);
    
            cases = enderecoRepository.findAddressByCases(idAgent,idUnit,idCase);
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }

        //TO DO: Substituir por um registerReposity.find personalizado pq os retornos de casos incluem endereço
        return cases;
    }
}
