import React from 'react';

import AuthService from '../../app/service/authService';

export default props => {

    const user = AuthService.getUserInfo();

    const rows = props.students.map( student => {
        return (
            <tr key={student.id}>
                <td>{student.id}</td>
                <td>{student.name}</td>
                <td>{student.codeMatricula}</td>
                <td>{!student.coach ? " - " : student.coach.name}</td>
                <td>{!student.unity ? " - " : student.unity.name}</td>
                <td>{student.gender}</td>
                <td>
                    <button
                        type="button"
                        title="Editar"
                        className="btn btn-primary"
                        onClick={e => props.editAction(student.id)}
                    >
                        <i className="pi pi-pencil"></i>
                    </button>
                    <button
                        type="button"
                        title="Excluir"
                        className="btn btn-danger" 
                        onClick={ e => props.deleteAction(student)}
                        disabled={user.roleId === 3}
                    >
                        <i className="pi pi-trash"></i>
                    </button>
                </td>
            </tr>
        )
    } )

    return (
        <table className="table table-hover">
            <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Nome</th>
                    <th scope="col">Matrícula</th>
                    <th scope="col">Professor</th>
                    <th scope="col">Unidade</th>
                    <th scope="col">Sexo</th>
                    <th scope="col">Ações</th>
                </tr>
            </thead>
            <tbody>
                {rows}
            </tbody>
        </table>
    )
}
