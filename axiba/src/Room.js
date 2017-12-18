import React, {Component} from "react";
import "./App.css";
class Room extends Component {

    constructor() {
        super();
        this.state = {}
    }

    render() {
        return (
            <div>
                {this.props.roomName}
            </div>
        );
    }
}

export default Room;