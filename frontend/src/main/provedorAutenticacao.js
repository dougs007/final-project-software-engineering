import React from 'react';

import AuthService from '../app/service/authService';
import jwt from 'jsonwebtoken';

export const AuthContext = React.createContext();
export const AuthConsumer = AuthContext.Consumer;

const AuthProvider = AuthContext.Provider;

class ProvedorAutenticacao extends React.Component {

    state = {
        usuarioAutenticado: null,
        roleId: null,
        isAutenticado: false,
        isLoading: true
    }

    iniciarSessao = (tokenDTO) => {
        const token = tokenDTO.token
        const claims = jwt.decode(token)
        const usuario = {
            id: claims.userid,
            nome: claims.nome,
            roleId: claims.roleid,
        }
        
        AuthService.logar(usuario, token);
        this.setState({
            isAutenticado: true,
            usuarioAutenticado: usuario,
            roleId: usuario.roleId,
        })
    }

    encerrarSessao = () => {
        AuthService.removerUsuarioAutenticado();
        this.setState({ isAutenticado: false, usuarioAutenticado: null, roleId: 0})
    }

    async componentDidMount() {
        const isAutenticado = AuthService.isUsuarioAutenticado()
        if (isAutenticado) {
            const usuario = await AuthService.refreshSession()
            this.setState({
                isAutenticado: true,
                usuarioAutenticado: usuario,
                isLoading: false,
                roleId: usuario.roleid,
            })
        } else {
            this.setState( previousState => {
                return {
                    ...previousState,
                    isLoading: false
                }
            })
        }
    }

    render() {

        if (this.state.isLoading) {
            return null;
        }

        const contexto = {
            usuarioAutenticado: this.state.usuarioAutenticado,
            isAutenticado: this.state.isAutenticado,
            roleId: this.state.roleId,
            iniciarSessao: this.iniciarSessao,
            encerrarSessao: this.encerrarSessao,
        }

        return(
            <AuthProvider value={contexto} >
                {this.props.children}
            </AuthProvider>
        )
    }
}

export default ProvedorAutenticacao;
