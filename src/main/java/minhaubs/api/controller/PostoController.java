package minhaubs.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import minhaubs.api.DTO.FamilyDTO;
import minhaubs.api.DTO.PessoaDTO;
import minhaubs.api.repository.PostoPessoaRepository;

@CrossOrigin
@RestController
@RequestMapping("posto")
public class PostoController {
    
    @Autowired
	private PostoPessoaRepository postoPessoaRepository;

    @GetMapping("/pessoas")
    @ResponseBody
    public List<PessoaDTO> getPessoasByPosto(@RequestParam(name = "posto") Long BHUId ) throws NoSuchAlgorithmException{
        List<String> resultSearch = postoPessoaRepository.findAllPersonsByBHUId(BHUId);
        ArrayList<PessoaDTO> result = new ArrayList<>();
        for(String i: resultSearch){
            String[] personFinded = i.split(",");
            result.add(new PessoaDTO(personFinded[0], personFinded[1]));
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
}
