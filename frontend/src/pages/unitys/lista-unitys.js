import React from 'react'
import { withRouter } from 'react-router-dom'

import Card from '../../components/card'
import UnitysTable from './unitysTable'
import UnityService from '../../app/service/unityService'

import * as messages from '../../components/toastr'

import {Dialog} from 'primereact/dialog';
import {Button} from 'primereact/button';


class ListaUnitys extends React.Component {

    state = {
        showConfirmDialog: false,
        unidadeDeletar: {},
        unitys : []
    }

    constructor() {
        super();
        this.service = new UnityService();
    }

    componentDidMount () {
        this.service
            .getAll()
            .then( response => {
                this.setState({ unitys: response.data})
            }).catch(error => {
                console.error(error.response)
            });
    }

    editar = (id) => {
        this.props.history.push(`/unity/${id}`)
    }

    abrirConfirmacao = (lancamento) => {
        this.setState({ showConfirmDialog : true, unidadeDeletar: lancamento  })
    }

    cancelarDelecao = () => {
        this.setState({ showConfirmDialog : false, unidadeDeletar: {}  })
    }

    deletar = () => {
        this.service
            .deletar(this.state.unidadeDeletar.id)
            .then(response => {
                const unitys = this.state.unitys;
                const index = unitys.indexOf(this.state.unidadeDeletar)
                unitys.splice(index, 1);
                this.setState( { unitys: unitys, showConfirmDialog: false } )
                messages.mensagemSucesso('Unidade deletada com sucesso!')
            }).catch(error => {
                console.warn(error);
                messages.mensagemErro('Ocorreu um erro ao tentar deletar a Unidade')
            })
    }

    preparaFormularioCadastro = () => {
        this.props.history.push('/unity')
    }

    render () {
        const confirmDialogFooter = (
            <div>
                <Button
                    label="Confirmar"
                    icon="pi pi-check"
                    onClick={this.deletar}
                />
                <Button
                    label="Cancelar"
                    icon="pi pi-times"
                    onClick={this.cancelarDelecao} 
                    className="p-button-secondary"
                />
            </div>
        );

        return (
            <Card title="Unidades">
                <div className="row">
                    <div className="col-md-6">
                        <div className="bs-component">
                            <button
                                onClick={this.preparaFormularioCadastro}
                                type="button"
                                className="btn btn-info"
                            >
                                <i className="pi pi-plus"></i>
                                Cadastrar
                            </button>
                        </div>
                    </div>
                </div>   
                <br/ >
                <div className="row">
                    <div className="col-md-12">
                        <div className="bs-component">
                            <UnitysTable
                                unitys={this.state.unitys} 
                                deleteAction={this.abrirConfirmacao}
                                editAction={this.editar}
                            />
                        </div>
                    </div>  
                </div> 
                <div>
                    <Dialog header="Confirmação" 
                        visible={this.state.showConfirmDialog} 
                        style={{width: '50vw'}}
                        footer={confirmDialogFooter} 
                        modal={true} 
                        onHide={() => this.setState({showConfirmDialog: false})}
                    >
                        Confirma a exclusão desta Unidade?
                    </Dialog>
                </div>
            </Card>
        )
    }
}

export default withRouter(ListaUnitys);
