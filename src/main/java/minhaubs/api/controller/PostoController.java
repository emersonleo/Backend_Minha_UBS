package minhaubs.api.controller;

import java.security.NoSuchAlgorithmException;
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
import minhaubs.api.entity.Pessoa;
import minhaubs.api.entity.Visita;
import minhaubs.api.repository.FamiliaRepository;
import minhaubs.api.repository.PessoaRepository;
import minhaubs.api.repository.PostoPessoaRepository;
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

    @GetMapping("/pessoas")
    @ResponseBody
    public List<PessoaDTO> getPessoasByPosto(@RequestParam(name = "posto") Long BHUId ) throws NoSuchAlgorithmException{
        List<String> resultSearch = postoPessoaRepository.findAllPersonsByBHUId(BHUId);
        ArrayList<PessoaDTO> result = new ArrayList<>();
        for(String i: resultSearch){
            String[] personFinded = i.split(",");
            result.add(new PessoaDTO(personFinded[0], personFinded[1], personFinded[2]));
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
    public ResponseEntity getFamiliaByPosto(@RequestBody Map<String, String> visitData) throws NoSuchAlgorithmException{

        Long id_person = Long.parseLong(visitData.get("agente"));
        Long id_family = Long.parseLong(visitData.get("familia"));

        Optional<Familia> family = familiaRepository.findById(id_family);
        Optional<Pessoa> person = pessoaRepository.findById(id_person);

        if(family.isEmpty() || person.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
        }

        Visita visita = new Visita();
        visita.setId(123L);
        visita.setFamilia(family.get());
        visita.setPessoa(person.get());
        
        visitaRepository.save(visita);
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
