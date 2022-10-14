import React from 'react'

import Card from '../../components/card'
import FormGroup from '../../components/form-group'
import SelectMenu from '../../components/selectMenu'

import { withRouter } from 'react-router-dom'
import * as messages from '../../components/toastr'

import UnityService from '../../app/service/unityService'

class CadastroUnity extends React.Component {

    state = {
        id: null,
        name: '',
        city: '',
        state: '',
        postalCode: '',
        atualizando: false,
    }

    constructor() {
        super();
        this.service = new UnityService();
    }

    componentDidMount() {
        const params = this.props.match.params
       
        if(params.id){
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
        const { name, state, city, postalCode} = this.state;
        const unity = { name, state, city, postalCode };

        try {
            this.service.validar(unity)
        } catch (erro) {
            const mensagens = erro.mensagens;
            mensagens.forEach(msg => messages.mensagemErro(msg));
            return false;
        }     

        this.service
            .salvar(unity)
            .then(response => {
                this.props.history.push('/unitys')
                messages.mensagemSucesso('Unidade cadastrada com sucesso!')
            }).catch(error => {
                messages.mensagemErro(error.response.data)
            })
    }

    atualizar = () => {
        const { id, name, state, city, postalCode} = this.state;
        const unity = { id, name, state, city, postalCode };

        this.service
            .atualizar(unity)
            .then(response => {
                this.props.history.push('/unitys')
                messages.mensagemSucesso('Unidade atualizada com sucesso!')
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
        const states = this.service.getStates();

        return (
            <Card
                title={ this.state.atualizando
                    ? 'Atualização de Unidade'
                    : 'Cadastro de Unidade'
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
                    <div className="col-md-6">
                        <FormGroup
                            id="inputState"
                            label="Estado: *"
                        >
                            <SelectMenu
                                id="inputState" 
                                value={this.state.state}
                                onChange={this.handleChange}
                                lista={states}
                                name="state"
                                className="form-control"
                            />
                        </FormGroup>
                    </div>
                    <div className="col-md-6">
                        <FormGroup
                            id="inputCity"
                            label="Cidade: *"
                        >
                            <input
                                id="inputCity" 
                                type="text"
                                name="city"
                                value={this.state.city}
                                onChange={this.handleChange} 
                                className="form-control"
                            />
                        </FormGroup>
                    </div>
                </div>
                <div className="row">
                    <div className="col-md-12">
                         <FormGroup
                            id="inputPostalCode"
                            label="CEP: *"
                        >
                            <input
                                id="inputPostalCode" 
                                type="text"
                                name="postalCode"
                                value={this.state.postalCode}
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
                            onClick={e => this.props.history.push('/unitys')} 
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

export default withRouter(CadastroUnity);
