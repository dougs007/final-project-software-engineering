import React from 'react';

// import UsuarioService from '../app/service/usuarioService';
import { AuthContext } from '../main/provedorAutenticacao';

class Home extends React.Component{

    state = { }

    componentDidMount() { }

    render(){
        return (
            <div className="jumbotron">
                <h1 className="display-3">Bem vindo!</h1>
                <p className="lead">
                    Esse é seu sistema de gestão de atividades físicas.
                </p>
                <hr className="my-4" />
                <p>E essa é sua área administrativa, utilize um dos menus ou
                    botões abaixo para navegar pelo sistema.
                </p>
            </div>
        )
    }
}

Home.contextType = AuthContext;

export default Home;
