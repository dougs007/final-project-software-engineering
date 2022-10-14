import React from 'react'
import { withRouter } from 'react-router-dom'

import Card from '../../components/card'
import PlansTable from './plansTable'
import PlanService from '../../app/service/planService'

import * as messages from '../../components/toastr'

import {Dialog} from 'primereact/dialog';
import {Button} from 'primereact/button';


class ListaPlans extends React.Component {

    state = {
        showConfirmDialog: false,
        planoDeletar: {},
        plans : []
    }

    constructor() {
        super();
        this.service = new PlanService();
    }

    componentDidMount () {
        this.service
            .getAll()
            .then( response => {
                this.setState({ plans: response.data})
            }).catch(error => {
                console.error(error.response)
            });
    }

    editar = (id) => {
        this.props.history.push(`/plan/${id}`)
    }

    abrirConfirmacao = (lancamento) => {
        this.setState({ showConfirmDialog : true, planoDeletar: lancamento  })
    }

    cancelarDelecao = () => {
        this.setState({ showConfirmDialog : false, planoDeletar: {}  })
    }

    deletar = () => {
        this.service
            .deletar(this.state.planoDeletar.id)
            .then(response => {
                const plans = this.state.plans;
                const index = plans.indexOf(this.state.planoDeletar)
                plans.splice(index, 1);
                this.setState( { plans: plans, showConfirmDialog: false } )
                messages.mensagemSucesso('Plano deletado com sucesso!')
            }).catch(error => {
                console.warn(error);
                messages.mensagemErro('Ocorreu um erro ao tentar deletar a Plano')
            })
    }

    preparaFormularioCadastro = () => {
        this.props.history.push('/plan')
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
            <Card title="Planos">
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
                            <PlansTable
                                plans={this.state.plans} 
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
                        Confirma a exclusão deste Plano?
                    </Dialog>
                </div>
            </Card>
        )
    }
}

export default withRouter(ListaPlans);
