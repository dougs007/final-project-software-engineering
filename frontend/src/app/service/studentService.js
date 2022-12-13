import ApiService from '../apiservice';

import ErroValidacao from '../exception/ErroValidacao';

export default class StudentService extends ApiService {

    constructor() {
        super('/api/students')
    }

    obterPorId(id) {
        return this.get(`/${id}`);
    }

    validar(student) {
        const erros = [];

        if (!student.name) {
            erros.push("Informe o Nome.")
        }

        if (!student.genderId) {
            erros.push("Informe o Sexo.")
        }

        if (!student.unityId) {
            erros.push("Informe a Unidade.")
        }

        if (!student.userId) {
            erros.push("Informe o Professor.")
        }

        if (!student.email) {
            erros.push("Informe o E-mail.")
        } else if (!(student.email.indexOf("@") > -1)) {
            erros.push("Informe um E-mail válido.")
        }

        if (!student.password && !student.id) {
            erros.push("Informe a Senha.")
        }

        if (erros && erros.length > 0) {
            throw new ErroValidacao(erros);
        }
    }

    salvar(student) {
        return this.post('/', student);
    }

    atualizar (student) {
        return this.put(`/${student.id}`, student);
    }

    getAll() {
        return this.get("");
    }

    getAllByCoachId() {
        return this.get("");
    }

    deletar(id) {
        return this.delete(`/${id}`)
    }
}
