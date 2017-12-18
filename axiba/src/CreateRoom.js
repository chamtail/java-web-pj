import React, {Component} from "react";
import {Input, Modal} from "antd";
import "./App.css";
import Axios from "axios";

class Room extends Component {

    constructor() {
        super();
        this.state = {}
    }

    handleSubmit = (e) => {
        let roomName = e.target.value;
        Axios
            .get(`/rooms/create?roomName=${roomName}&userName=${this.props.userName}`)
            .then((ret) => {
                if (ret.data === true) {
                    this.props.onSuccess(roomName);
                    return;
                }
                Modal.error({
                    title: 'Create room failed',
                    content: 'Room name already taken.',
                });
            });
    };

    render() {
        return (
            <div>
                <Modal visible={true}
                       footer={null}
                       onCancel={this.props.onCancel}>
                    <Input placeholder="Enter room name"
                           onPressEnter={this.handleSubmit}
                           className="create-form-input"/>
                </Modal>
            </div>
        );
    }
}

export default Room;