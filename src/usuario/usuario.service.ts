import { HttpService } from '@nestjs/axios';
import { Injectable } from '@nestjs/common';
import { lastValueFrom } from 'rxjs';
import { vinculo } from './vinculo.model';

@Injectable()
export class UsuarioService {
    constructor( private httpService: HttpService) {}

    async getIdProfissional(cpf: any): Promise<any[]>{
        const url = `https://cnes.datasus.gov.br/services/profissionais?cpf=${cpf}`;
        const config = {
            headers: { Referer: url },
          };
        const response = await lastValueFrom(this.httpService.get(url,config));
        return response.data;
    }

    async getVinculosAtivos(idProfissional: any, cpf: any): Promise<vinculo[]> {
        const url = `https://cnes.datasus.gov.br/services/profissionais/${idProfissional}`;
        const urlReferer = `https://cnes.datasus.gov.br/pages/profissionais/consulta.jsp?search=${cpf}`;
        
        const config = {
            headers: { 
                "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/116.0",
                Referer: urlReferer,
                "Content-Type": "application/json; charset=utf-8"
            },
        };
        const response = await lastValueFrom(this.httpService.get(url,config));
        return response.data.vinculos;
    }
}
