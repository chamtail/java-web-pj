import React from "react";
import {HashRouter, Route} from "react-router-dom";
import PageLogin from "./page/login";

export default class Application extends React.Component {

    constructor() {
        super();
    }

    componentDidMount() {

    }

    componentWillUnmount() {
    }

    render() {
        return (
            <div>
                <HashRouter>
                    <div className="container">
                        <div className="content">
                            <Route exact path="/" component={PageLogin}/>
                        </div>
                    </div>
                </HashRouter>
            </div>
        );
    }
}
