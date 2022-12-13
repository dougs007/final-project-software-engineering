import React from 'react'

import Card from '../../components/card'
import FormGroup from '../../components/form-group'
import SelectMenu from '../../components/selectMenu'

import { withRouter } from 'react-router-dom'
import * as messages from '../../components/toastr'

import StudentService from '../../app/service/studentService'
import UnityService from '../../app/service/unityService'
import CoachService from '../../app/service/coachService'

class CreateStudents extends React.Component {

    state = {
        id: null,
        name: '',
        genderId: 1,
        unityId: 1,
        userId: null,
        email: null,
        password: null,
        atualizando: false,
        units: [],
        coaches: [],
    }

    constructor() {
        super();
        this.service = new StudentService();
        this.serviceUnity = new UnityService();
        this.serviceCoach = new CoachService();
    }

    loadUnitys() {
        this.serviceUnity
            .getAll()
            .then(response => {
                this.setState( {units: response.data} )
            })
            .catch(erros => {
                messages.mensagemErro(erros.response.data)
            })
    }

    loadCoaches() {
        this.serviceCoach
            .getAll()
            .then(response => {
                this.setState( {coaches: response.data} )
            })
            .catch(erros => {
                messages.mensagemErro(erros.response.data)
            })
    }

    componentDidMount() {
        this.loadUnitys();
        this.loadCoaches();

        const params = this.props.match.params

        if (params.id) {
            this.service
                .obterPorId(params.id)
                .then(response => {
                    this.setState( {...response.data, atualizando: true} )
                    this.setState( {unityId: !response.data.unity ? 1 : response.data.unity.id} )
                    this.setState( {userId: !response.data.coach ? 1 : response.data.coach.id} )
                })
                .catch(erros => {
                    messages.mensagemErro(erros.response.data)
                })
        }
    }

    submit = () => {
        const { name, genderId, unityId, userId, email, password } = this.state;
        const student = { name, genderId, unityId, userId, email, password };

        try {
            this.service.validar(student)
        } catch (erro) {
            const mensagens = erro.mensagens;
            mensagens.forEach(msg => messages.mensagemErro(msg));
            return false;
        }

        this.service
            .salvar(student)
            .then(response => {
                this.props.history.push('/students')
                messages.mensagemSucesso('Aluno cadastrado com sucesso!')
            }).catch(error => {
                messages.mensagemErro(error.response.data)
            })
    }

    atualizar = () => {
        const { id, name, genderId, unityId, email, password } = this.state;
        const student = { id, name, genderId, unityId, email, password };

        this.service
            .atualizar(student)
            .then(response => {
                this.props.history.push('/students')
                messages.mensagemSucesso('Aluno atualizado com sucesso!')
            }).catch(error => {
                messages.mensagemErro(error.response.data)
            })
    }

    handleChange = (event) => {
        const value = event.target.value;
        const name = event.target.name;

        this.setState({ [name] : value })
    }

    getAllGenders() {
        return [
            {id: 1, name: 'Masculino'},
            {id: 2, name: 'Feminino'},
            {id: 3, name: 'Outros'},
        ];
    }

    render() {
        const units = this.state.units;
        const coaches = this.state.coaches;
        const genders = this.getAllGenders();

        return (
            <Card
                title={ this.state.atualizando
                    ? 'Atualização de Aluno'
                    : 'Cadastro de Aluno'
                }
            >
                <div className="row">
                    <div className="col-md-12">
                        <FormGroup
                            id="inputName"
                            label="Nome: *"
                        >
                            <input
                                id="inputName"
                                type="text"
                                className="form-control"
                                name="name"
                                value={this.state.name}
                                onChange={this.handleChange}
                            />
                        </FormGroup>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-4">
                        <FormGroup
                            id="inputGender"
                            label="Sexo: *"
                        >
                            <SelectMenu
                                id="inputGender"
                                value={this.state.genderId}
                                onChange={this.handleChange}
                                lista={genders}
                                name="genderId"
                                className="form-control"
                            />
                        </FormGroup>
                    </div>

                    <div className="col-md-4">
                        <FormGroup
                            id="inputUnity"
                            label="Unidade: *"
                        >
                            <SelectMenu
                                id="inputUnity" 
                                value={this.state.unityId}
                                onChange={this.handleChange}
                                lista={units}
                                name="unityId"
                                className="form-control"
                            />
                        </FormGroup>
                    </div>

                    <div className="col-md-4">
                        <FormGroup
                            id="inputUserId"
                            label="Professor: *"
                        >
                            <SelectMenu
                                id="inputUserId" 
                                value={this.state.userId}
                                onChange={this.handleChange}
                                lista={coaches}
                                name="userId"
                                className="form-control"
                            />
                        </FormGroup>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-6">
                        <FormGroup
                            id="inputEmail"
                            label="Email: *"
                        >
                            <input
                                id="inputEmail" 
                                type="email"
                                name="email"
                                value={this.state.email}
                                onChange={this.handleChange} 
                                className="form-control"
                            />
                        </FormGroup>
                    </div>

                    <div className="col-md-6">
                        <FormGroup
                            id="inputPassword"
                            label="Senha: *"
                        >
                            <input
                                id="inputPassword" 
                                type="password"
                                name="password"
                                value={this.state.password}
                                onChange={this.handleChange} 
                                className="form-control"
                            />
                        </FormGroup>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-6" >
                        { this.state.atualizando ? 
                            (
                                <button
                                    onClick={this.atualizar} 
                                    className="btn btn-success"
                                >
                                    <i className="pi pi-refresh"></i> Atualizar
                                </button>
                            ) : (
                                <button
                                    onClick={this.submit} 
                                    className="btn btn-success"
                                >
                                    <i className="pi pi-save"></i> Salvar
                                </button>
                            )
                        }
                        <button
                            onClick={e => this.props.history.push('/students')}
                            className="btn btn-danger"
                        >
                            <i className="pi pi-times"></i>Cancelar
                        </button>
                    </div>
                </div>
            </Card>
        )
    }
}

export default withRouter(CreateStudents);
