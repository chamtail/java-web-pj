import React, {Component} from "react";
import "./App.css";
import Login from "./Login";
import Rooms from "./Rooms";
import Room from "./Room";

class App extends Component {

    constructor() {
        super();
        this.state = {
            loginVisible: true,
            roomsVisible: false,
            userName: null,
            roomName: null,
            roomVisible: false,
        }
    }

    handleLoginFinish = (userName) => {
        this.setState({
            loginVisible: false,
            userName: userName,
            roomsVisible: true,
        });
    };

    handleEnterRoom = (roomName) => {
        this.setState({
            roomsVisible: false,
            roomName: roomName,
            roomVisible: true,
        })
    };

    render() {
        return (
            <div>
                {this.state.loginVisible ? <Login onFinish={this.handleLoginFinish}/> : null}
                {this.state.roomsVisible ?
                    <Rooms userName={this.state.userName} onEnterRoom={this.handleEnterRoom}/> : null}
                {this.state.roomVisible ? <Room/> : null}
            </div>
        );
    }
}

export default App;