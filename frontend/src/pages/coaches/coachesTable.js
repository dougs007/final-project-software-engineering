import React from 'react';
import currencyFormatter from 'currency-formatter';

export default props => {

    const rows = props.coaches.map( coach => {
        return (
            <tr key={coach.id}>
                <td>{coach.id}</td>
                <td>{coach.name}</td>
                <td>{ currencyFormatter.format(coach.value, { locale: 'pt-BR'}) }</td>
                <td>{coach.qtdDaysValidity}</td>
                <td>
                    <button
                        type="button"
                        title="Editar"
                        className="btn btn-primary"
                        onClick={e => props.editAction(coach.id)}
                    >
                        <i className="pi pi-pencil"></i>
                    </button>
                    <button
                        type="button"
                        title="Excluir"
                        className="btn btn-danger" 
                        onClick={ e => props.deleteAction(coach)}
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
                    <th scope="col">Valor</th>
                    <th scope="col">Dias</th>
                    <th scope="col">Ações</th>
                </tr>
            </thead>
            <tbody>
                {rows}
            </tbody>
        </table>
    )
}
