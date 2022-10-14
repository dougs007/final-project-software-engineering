import React from 'react';

import Login from '../pages/login';
import Home from '../pages/home';

import Unitys from '../pages/unitys/lista-unitys';
import Plans from '../pages/plans/list-plans';

import CadastroUnidades from '../pages/unitys/cadastro-unity';
import CadastroPlans from '../pages/plans/cadastro-plans';
import LandingPage from '../pages/landingPage';
import { AuthConsumer } from '../main/provedorAutenticacao';

import { Route, Switch, BrowserRouter, Redirect } from 'react-router-dom';

function RotaAutenticada( { component: Component, isUsuarioAutenticado, ...props } ) {
    return (
        <Route exact {...props} render={ (componentProps) => {
            if (isUsuarioAutenticado) {
                return (
                    <Component {...componentProps} />
                )
            } else {
                return(
                    <Redirect
                        to={{ pathname : '/login', state : { from: componentProps.location }}}
                    />
                )
            }
        }}  />
    )
}

function Rotas(props) {
    return (
        <BrowserRouter>
            <Switch>
                <Route
                    exact
                    path="/"
                    component={LandingPage}
                />
                <Route
                    exact
                    path="/login"
                    component={Login}
                />
                {/* <Route
                    exact
                    path="/cadastro-usuarios"
                    component={CadastroUsuario}
                /> */}

                <RotaAutenticada
                    isUsuarioAutenticado={props.isUsuarioAutenticado}
                    path="/home"
                    component={Home}
                />
                <RotaAutenticada
                    isUsuarioAutenticado={props.isUsuarioAutenticado}
                    path="/unitys"
                    component={Unitys}
                />
                <RotaAutenticada
                    isUsuarioAutenticado={props.isUsuarioAutenticado}
                    path="/unity/:id?"
                    component={CadastroUnidades}
                />

                {/*
                <RotaAutenticada
                    isUsuarioAutenticado={props.isUsuarioAutenticado}
                    path="/coaches"
                    component={Coaches}
                />
                <RotaAutenticada
                    isUsuarioAutenticado={props.isUsuarioAutenticado}
                    path="/coach/:id?"
                    component={CadastroCoach}
                />
                <RotaAutenticada
                    isUsuarioAutenticado={props.isUsuarioAutenticado}
                    path="/students"
                    component={Students}
                />
                <RotaAutenticada
                    isUsuarioAutenticado={props.isUsuarioAutenticado}
                    path="/student/:id?"
                    component={CadastroStudent}
                />
                */}

                <RotaAutenticada
                    isUsuarioAutenticado={props.isUsuarioAutenticado}
                    path="/plans"
                    component={Plans}
                />
                <RotaAutenticada
                    isUsuarioAutenticado={props.isUsuarioAutenticado}
                    path="/plan/:id?"
                    component={CadastroPlans}
                />
            </Switch>
        </BrowserRouter>
    )
}

export default () => (
    <AuthConsumer>
        { (context) => (<Rotas isUsuarioAutenticado={context.isAutenticado} />) }
    </AuthConsumer>
)
