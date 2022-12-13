import ApiService from '../apiservice';

import ErroValidacao from '../exception/ErroValidacao';

export default class CoachService extends ApiService {

    constructor() {
        super('/api/coaches')
    }

    obterPorId(id) {
        return this.get(`/${id}`);
    }

    validar(coach) {
        const erros = [];

        if (!coach.name) {
            erros.push("Informe o Nome.")
        }

        if (!coach.genderId) {
            erros.push("Informe o Sexo.")
        }

        if (!coach.unityId) {
            erros.push("Informe a Unidade.")
        }

        if (!coach.email) {
            erros.push("Informe o E-mail.")
        } else if (coach.email.indexOf("@" > -1)) {
            erros.push("Informe um E-mail válido.")
        }

        if (!coach.password && !coach.id) {
            erros.push("Informe a Senha.")
        }

        if (erros && erros.length > 0) {
            throw new ErroValidacao(erros);
        }
    }

    salvar(coach) {
        return this.post('/', coach);
    }

    atualizar (coach) {
        return this.put(`/${coach.id}`, coach);
    }

    getAll() {
        return this.get("");
    }

    deletar(id) {
        return this.delete(`/${id}`)
    }
}
