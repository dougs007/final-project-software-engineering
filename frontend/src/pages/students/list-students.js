import React from 'react'
import { withRouter } from 'react-router-dom'

import Card from '../../components/card'
import StudentsTable from './studentsTable'
import StudentService from '../../app/service/studentService'

import * as messages from '../../components/toastr'

import {Dialog} from 'primereact/dialog';
import {Button} from 'primereact/button';


class ListStudents extends React.Component {

    state = {
        showConfirmDialog: false,
        studentDeletar: {},
        students : []
    }

    constructor() {
        super();
        this.service = new StudentService();
    }

    componentDidMount () {
        this.service
            .getAll()
            .then( response => {
                console.warn(response);
                this.setState({ students: response.data})
            }).catch(error => {
                console.error(error.response)
            });
    }

    editar = (id) => {
        this.props.history.push(`/student/${id}`)
    }

    abrirConfirmacao = (lancamento) => {
        this.setState({ showConfirmDialog : true, studentDeletar: lancamento  })
    }

    cancelarDelecao = () => {
        this.setState({ showConfirmDialog : false, studentDeletar: {}  })
    }

    deletar = () => {
        this.service
            .deletar(this.state.studentDeletar.id)
            .then(response => {
                const students = this.state.students;
                const index = students.indexOf(this.state.studentDeletar)
                students.splice(index, 1);
                this.setState( { students: students, showConfirmDialog: false } )
                messages.mensagemSucesso('Aluno deletado com sucesso!')
            }).catch(error => {
                console.warn(error);
                messages.mensagemErro('Ocorreu um erro ao tentar deletar o Aluno')
            })
    }

    preparaFormularioCadastro = () => {
        this.props.history.push('/student')
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
            <Card title="Alunos">
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
                            <StudentsTable
                                students={this.state.students} 
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
                        Confirma a exclusão deste Aluno?
                    </Dialog>
                </div>
            </Card>
        )
    }
}

export default withRouter(ListStudents);
