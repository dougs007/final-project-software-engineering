import React from 'react'
import { withRouter } from 'react-router-dom'

import Card from '../../components/card'
import CoachesTable from './coachesTable'
import CoachService from '../../app/service/coachService'

import * as messages from '../../components/toastr'

import {Dialog} from 'primereact/dialog';
import {Button} from 'primereact/button';


class ListCoaches extends React.Component {

    state = {
        showConfirmDialog: false,
        coachDeletar: {},
        coaches : []
    }

    constructor() {
        super();
        this.service = new CoachService();
    }

    componentDidMount () {
        this.service
            .getAll()
            .then( response => {
                console.warn(response);
                this.setState({ coaches: response.data})
            }).catch(error => {
                console.error(error.response)
            });
    }

    editar = (id) => {
        this.props.history.push(`/coach/${id}`)
    }

    abrirConfirmacao = (lancamento) => {
        this.setState({ showConfirmDialog : true, coachDeletar: lancamento  })
    }

    cancelarDelecao = () => {
        this.setState({ showConfirmDialog : false, coachDeletar: {}  })
    }

    deletar = () => {
        this.service
            .deletar(this.state.coachDeletar.id)
            .then(response => {
                const coaches = this.state.coaches;
                const index = coaches.indexOf(this.state.coachDeletar)
                coaches.splice(index, 1);
                this.setState( { coaches: coaches, showConfirmDialog: false } )
                messages.mensagemSucesso('Professor deletado com sucesso!')
            }).catch(error => {
                console.warn(error);
                messages.mensagemErro('Ocorreu um erro ao tentar deletar o Professor')
            })
    }

    preparaFormularioCadastro = () => {
        this.props.history.push('/coach')
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
            <Card title="Professor">
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
                            <CoachesTable
                                coaches={this.state.coaches} 
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
                        Confirma a exclusão deste Coach?
                    </Dialog>
                </div>
            </Card>
        )
    }
}

export default withRouter(ListCoaches);
