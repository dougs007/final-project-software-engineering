import React from 'react';

export default props => {

    const rows = props.unitys.map( unity => {
        return (
            <tr key={unity.id}>
                <td>{unity.id}</td>
                <td>{unity.name}</td>
                <td>
                    <button
                        type="button"
                        title="Editar"
                        className="btn btn-primary"
                        onClick={e => props.editAction(unity.id)}
                    >
                        <i className="pi pi-pencil"></i>
                    </button>
                    <button
                        type="button"
                        title="Excluir"
                        className="btn btn-danger" 
                        onClick={ e => props.deleteAction(unity)}
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
                    <th scope="col">Ações</th>
                </tr>
            </thead>
            <tbody>
                {rows}
            </tbody>
        </table>
    )
}
