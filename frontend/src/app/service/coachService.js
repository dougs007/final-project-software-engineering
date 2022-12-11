import ApiService from '../apiservice';

import ErroValidacao from '../exception/ErroValidacao';

export default class CoachService extends ApiService {

    constructor() {
        super('/api/coaches')
    }

    obterPorId(id) {
        return this.get(`/${id}`);
    }

    validar(unity) {
        const erros = [];

        if (!unity.name) {
            erros.push("Informe o Nome.")
        }

        if (!unity.description) {
            erros.push("Informe a Descrição.")
        }

        if (!unity.value) {
            erros.push("Informe o Valor.")
        }

        if (!unity.qtdDaysValidity) {
            erros.push("Informe a quantidade de dias válidos.")
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
