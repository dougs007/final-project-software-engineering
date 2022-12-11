import React from 'react'

import Card from '../../components/card'
import FormGroup from '../../components/form-group'
import SelectMenu from '../../components/selectMenu'

import { withRouter } from 'react-router-dom'
import * as messages from '../../components/toastr'

import CoachService from '../../app/service/coachService'
import UnityService from '../../app/service/unityService'

class CreateCoaches extends React.Component {

    state = {
        id: null,
        name: '',
        description: '',
        value: 0.0,
        qtdDaysValidity: 0,
        atualizando: false,
        units: []
    }

    constructor() {
        super();
        this.service = new CoachService();
        this.serviceUnity = new UnityService();
    }

    componentDidMount() {
        this.serviceUnity
                .getAll()
                .then(response => {
                    this.setState( {units: response.data} )
                })
                .catch(erros => {
                    messages.mensagemErro(erros.response.data)
                })

        const params = this.props.match.params

        if (params.id) {
            this.service
                .obterPorId(params.id)
                .then(response => {
                    this.setState( {...response.data, atualizando: true} )
                })
                .catch(erros => {
                    messages.mensagemErro(erros.response.data)
                })
        }
    }

    submit = () => {
        const { name, description, value, qtdDaysValidity} = this.state;
        const coach = { name, description, value, qtdDaysValidity };

        try {
            this.service.validar(coach)
        } catch (erro) {
            const mensagens = erro.mensagens;
            mensagens.forEach(msg => messages.mensagemErro(msg));
            return false;
        }     

        this.service
            .salvar(coach)
            .then(response => {
                this.props.history.push('/coaches')
                messages.mensagemSucesso('Professor cadastrado com sucesso!')
            }).catch(error => {
                messages.mensagemErro(error.response.data)
            })
    }

    atualizar = () => {
        const { id, name, description, value, qtdDaysValidity} = this.state;
        const coach = { id, name, description, value, qtdDaysValidity };

        this.service
            .atualizar(coach)
            .then(response => {
                this.props.history.push('/coaches')
                messages.mensagemSucesso('Professor atualizado com sucesso!')
            }).catch(error => {
                messages.mensagemErro(error.response.data)
            })
    }

    handleChange = (event) => {
        const value = event.target.value;
        const name = event.target.name;

        this.setState({ [name] : value })
    }

    render() {
        const unidades = this.state.units;

        return (
            <Card
                title={ this.state.atualizando
                    ? 'Atualização de Professor'
                    : 'Cadastro de Professor'
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
                    <div className="col-md-12">
                        <FormGroup
                            id="inputDescription"
                            label="Descrição: *"
                        >
                            <input
                                id="inputDescription"
                                type="text"
                                className="form-control"
                                name="description"
                                value={this.state.description}
                                onChange={this.handleChange}
                            />
                        </FormGroup>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-6">
                        <FormGroup
                            id="inputValue"
                            label="Valor: *"
                        >
                            <input
                                id="inputValue" 
                                type="text"
                                name="value"
                                value={this.state.value}
                                onChange={this.handleChange} 
                                className="form-control"
                            />
                        </FormGroup>
                    </div>

                    <div className="col-md-6">
                        <FormGroup
                            id="inputUnity"
                            label="Unidade: *"
                        >
                            <SelectMenu
                                id="inputUnity" 
                                value={this.state.unity_id}
                                onChange={this.handleChange}
                                lista={unidades}
                                name="unity"
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
                            onClick={e => this.props.history.push('/coaches')}
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

export default withRouter(CreateCoaches);
