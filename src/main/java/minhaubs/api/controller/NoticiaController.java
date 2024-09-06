package minhaubs.api.controller;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import minhaubs.api.entity.Noticia;
import minhaubs.api.entity.Pessoa;
import minhaubs.api.entity.Posto;
import minhaubs.api.repository.NoticiaRepository;
import minhaubs.api.repository.PessoaRepository;
import minhaubs.api.repository.PostoRepository;

@CrossOrigin
@RestController
@RequestMapping("noticia")
public class NoticiaController {
    
    @Autowired
	private PostoRepository postoRepository;

    @Autowired
	private PessoaRepository pessoaRepository;

    @Autowired
	private NoticiaRepository noticiaRepository;

    @SuppressWarnings("rawtypes")
    @PostMapping("/criarnoticia")
    @ResponseBody
    public ResponseEntity createNotice(@RequestBody Map<String, String> visitData) throws NoSuchAlgorithmException{

        Long idUnit = Long.parseLong(visitData.get("posto"));
        Long idAgent = Long.parseLong(visitData.get("agente"));
        String notice = visitData.get("noticia");

        Optional<Pessoa> person = pessoaRepository.findById(idAgent);
        Optional<Posto> unit = postoRepository.findById(idUnit);

        if(person.isEmpty() || unit.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
        }

        Noticia noticia = new Noticia();
        noticia.setId(123L);
        noticia.setAgente(person.get()); 
        noticia.setPosto(unit.get());
        noticia.setNoticia(notice);
        noticia.setDataHora(LocalDateTime.now());
        
        noticiaRepository.save(noticia);
    	return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PostMapping("/listarnoticias")
    @ResponseBody
    public List<Noticia> getNotices(@RequestBody Map<String, String> caseData){
        Long idUnit = Long.parseLong(caseData.get("posto"));
        Optional<Posto> unit = postoRepository.findById(idUnit);

        if(unit.isEmpty()){
            return new ArrayList<Noticia>(); 
        }

        List<Noticia> cases = noticiaRepository.findByPosto(unit.get());

        return cases;
    }

}
