import React from 'react';
import currencyFormatter from 'currency-formatter';

export default props => {

    const rows = props.plans.map( plan => {
        return (
            <tr key={plan.id}>
                <td>{plan.id}</td>
                <td>{plan.name}</td>
                <td>{ currencyFormatter.format(plan.value, { locale: 'pt-BR'}) }</td>
                <td>{plan.qtdDaysValidity}</td>
                <td>
                    <button
                        type="button"
                        title="Editar"
                        className="btn btn-primary"
                        onClick={e => props.editAction(plan.id)}
                    >
                        <i className="pi pi-pencil"></i>
                    </button>
                    <button
                        type="button"
                        title="Excluir"
                        className="btn btn-danger" 
                        onClick={ e => props.deleteAction(plan)}
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
