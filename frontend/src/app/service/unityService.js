import ApiService from '../apiservice';

import ErroValidacao from '../exception/ErroValidacao';

export default class UnityService extends ApiService {

    constructor() {
        super('/api/units')
    }

    getStates() {
        return  [
            { label: 'Selecione...',             value: '' },
            { label: 'Acre - AC',                value: 'Acre - AC' },
            { label: 'Alagoas - AL',             value: 'Alagoas - AL' },
            { label: 'Amazonas - AM',            value: 'Amazonas - AM' },
            { label: 'Amapá - AP',               value: 'Amapá - AP' },
            { label: 'Bahia - BA',               value: 'Bahia - BA' },
            { label: 'Ceará - CE',               value: 'Ceará - CE' },
            { label: 'Distrito Federal - DF',    value: 'Distrito Federal - DF' },
            { label: 'Espírito Santo - ES',      value: 'Espírito Santo - ES' },
            { label: 'Goiás - GO',               value: 'Goiás - GO' },
            { label: 'Maranhão - MA',            value: 'Maranhão - MA' },
            { label: 'Minas Gerais - MG',        value: 'Minas Gerais - MG' },
            { label: 'Mato Grosso do Sul - MS',  value: 'Mato Grosso do Sul - MS' },
            { label: 'Mato Grosso - MT',         value: 'Mato Grosso - MT' },
            { label: 'Pará - PA',                value: 'Pará - PA' },
            { label: 'Paraiba - PB',             value: 'Paraiba - PB' },
            { label: 'Pernambuco - PE',          value: 'Pernambuco - PE' },
            { label: 'Piauí - PI',               value: 'Piauí - PI' },
            { label: 'Paraná - PR',              value: 'Paraná - PR' },
            { label: 'Rio de Janeiro - RJ',      value: 'Rio de Janeiro - RJ' },
            { label: 'Rio Grande do Norte - RN', value: 'Rio Grande do Norte - RN' },
            { label: 'Rondônia - RO',            value: 'Rondônia - RO' },
            { label: 'Roraima - RR',             value: 'Roraima - RR' },
            { label: 'Rio Grande do Sul - RS',   value: 'Rio Grande do Sul - RS' },
            { label: 'Santa Catarina - SC',      value: 'Santa Catarina - SC' },
            { label: 'Sergipe - SE',             value: 'Sergipe - SE' },
            { label: 'São Paulo - SP',           value: 'São Paulo - SP' },
            { label: 'Tocantins - TO',           value: 'Tocantins - TO' },
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
