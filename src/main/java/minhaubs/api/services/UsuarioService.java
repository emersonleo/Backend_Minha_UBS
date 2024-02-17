package minhaubs.api.services;

import javax.management.RuntimeErrorException;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

@Configuration
@Service
public class UsuarioService {

    @Bean 
        public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    public String getIdProfissional(String cpf){
        String result;
        RestTemplate restTemplate = new RestTemplate();
        final String url =  "https://cnes.datasus.gov.br/services/profissionais?cpf=" + cpf;
        try{
            MultiValueMap<String, String> headersURI = new LinkedMultiValueMap<String, String>();
            headersURI.add(HttpHeaders.REFERER, url);
            
            HttpEntity<?> entity = new HttpEntity<Object>(headersURI);
            HttpEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            result = responseEntity.getBody();
            return result;
        }catch(Error e ){
            throw new RuntimeErrorException(e,"Erro ao chamar a api de Id Profissional:" + e.getMessage());
        }
    }

    public String getVinculosAtivos(String ProfessionalId, String cpf){
        String result;
        RestTemplate restTemplate = new RestTemplate();
        final String url = "https://cnes.datasus.gov.br/services/profissionais/" + ProfessionalId;
        final String urlReferer = "https://cnes.datasus.gov.br/pages/profissionais/consulta.jsp?search=" + cpf;
        try{
            MultiValueMap<String, String> headersURI = new LinkedMultiValueMap<String, String>();
            headersURI.add(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/116.0");
            headersURI.add(HttpHeaders.REFERER, urlReferer);
            headersURI.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
            
            HttpEntity<?> entity = new HttpEntity<Object>(headersURI);
            HttpEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            result = responseEntity.getBody();
            return result;
        }catch(Error e ){
            throw new RuntimeErrorException(e,"Erro ao chamar a api de Id Profissional:" + e.getMessage());
        }
    }
}

