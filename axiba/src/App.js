import React, { Component } from 'react';
import Button from 'antd/lib/button';
import './App.css';
import Login from './Login';

class App extends Component {

    constructor() {
        super();
    }

    render() {
        return (
            <Login/>
        );
    }
}

export default App;