import ApiService from '../apiservice';

import ErroValidacao from '../exception/ErroValidacao';

export default class UnityService extends ApiService {

    constructor() {
        super('/api/units')
    }

    getStates() {
        return  [
            { id: 'Selecione...',             name: '' },
            { id: 'Acre - AC',                name: 'Acre - AC' },
            { id: 'Alagoas - AL',             name: 'Alagoas - AL' },
            { id: 'Amazonas - AM',            name: 'Amazonas - AM' },
            { id: 'Amapá - AP',               name: 'Amapá - AP' },
            { id: 'Bahia - BA',               name: 'Bahia - BA' },
            { id: 'Ceará - CE',               name: 'Ceará - CE' },
            { id: 'Distrito Federal - DF',    name: 'Distrito Federal - DF' },
            { id: 'Espírito Santo - ES',      name: 'Espírito Santo - ES' },
            { id: 'Goiás - GO',               name: 'Goiás - GO' },
            { id: 'Maranhão - MA',            name: 'Maranhão - MA' },
            { id: 'Minas Gerais - MG',        name: 'Minas Gerais - MG' },
            { id: 'Mato Grosso do Sul - MS',  name: 'Mato Grosso do Sul - MS' },
            { id: 'Mato Grosso - MT',         name: 'Mato Grosso - MT' },
            { id: 'Pará - PA',                name: 'Pará - PA' },
            { id: 'Paraiba - PB',             name: 'Paraiba - PB' },
            { id: 'Pernambuco - PE',          name: 'Pernambuco - PE' },
            { id: 'Piauí - PI',               name: 'Piauí - PI' },
            { id: 'Paraná - PR',              name: 'Paraná - PR' },
            { id: 'Rio de Janeiro - RJ',      name: 'Rio de Janeiro - RJ' },
            { id: 'Rio Grande do Norte - RN', name: 'Rio Grande do Norte - RN' },
            { id: 'Rondônia - RO',            name: 'Rondônia - RO' },
            { id: 'Roraima - RR',             name: 'Roraima - RR' },
            { id: 'Rio Grande do Sul - RS',   name: 'Rio Grande do Sul - RS' },
            { id: 'Santa Catarina - SC',      name: 'Santa Catarina - SC' },
            { id: 'Sergipe - SE',             name: 'Sergipe - SE' },
            { id: 'São Paulo - SP',           name: 'São Paulo - SP' },
            { id: 'Tocantins - TO',           name: 'Tocantins - TO' },
        ]
    }

    obterPorId(id) {
        return this.get(`/${id}`);
    }

    validar(unity) {
        const erros = [];

        if (!unity.name) {
            erros.push("Informe o Nome.")
        }

        if (!unity.state) {
            erros.push("Informe o Estado.")
        }

        if (!unity.city) {
            erros.push("Informe a Cidade.")
        }

        if (!unity.postalCode) {
            erros.push("Informe o Cep.")
        }

        if (erros && erros.length > 0) {
            throw new ErroValidacao(erros);
        }
    }

    salvar(unity) {
        return this.post('/', unity);
    }

    atualizar (unity) {
        return this.put(`/${unity.id}`, unity);
    }

    getAll() {
        return this.get("");
    }

    deletar(id) {
        return this.delete(`/${id}`)
    }
}
