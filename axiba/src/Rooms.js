import React, {Component} from "react";
import {Button, Divider, Icon, Layout, Modal, Table, Tooltip} from "antd";
import "./App.css";
import Axios from "axios";
import CreateRoom from "./CreateRoom";

const {Header, Footer, Content} = Layout;

class Room extends React.Component {

    constructor() {
        super();
        this.state = {
            rooms: [],
            createRoom: false,
            columns: [{
                title: 'Name',
                dataIndex: 'name',
                key: 'name',
            }, {
                title: 'Player1',
                dataIndex: 'player1',
                key: 'player1',
            }, {
                title: 'Player2',
                dataIndex: 'player2',
                key: 'player2',
            }, {
                title: 'Action',
                key: 'action',
                render: (text, record) => (
                    <span>
                        <Tooltip placement="topLeft" title="Enter room">
                            <Button type="primary" onClick={this.handleRoomEnter} value={record.name}>
                                <Icon type="login"/>
                            </Button>
                        </Tooltip>
                    </span>
                ),
            }],
        };
    }

    componentDidMount() {
        this.fetch();
        this._interval_fetch = setInterval(() => {
            this.fetch();
        }, 1000);
    }

    componentDidUnmount() {
        clearInterval(this._interval_fetch);
    }

    fetch() {
        // todo
        if (this.props.userName === "admin") {
            let rooms = [];
            let room1 = {
                key: 1,
                name: "axiba",
                player1: "xiaowangzi",
                player2: "aguang",
            };
            let room2 = {
                key: 2,
                name: "lililala",
                player1: "shiguangji",
            };
            rooms.push(room1);
            rooms.push(room2);
            this.setState({
                rooms: rooms,
            });
            return;
        }
        Axios
            .get(`/rooms/`)
            .then((ret) => {
                let dataList = ret.data.rooms;
                let rooms = [];
                for (let i = 0; i < dataList.length; ++i) {
                    let data = dataList[i];
                    let room = {
                        key: i,
                        name: data.name,
                        player1: data.playerNames !== null && data.playerNames.length > 0 ? data.playerNames[0] : null,
                        player2: data.playerNames !== null && data.playerNames.length > 1 ? data.playerNames[1] : null,
                    };
                    rooms.push(room);
                }
                this.setState({
                    rooms: rooms,
                })
            });
    }

    handleCreateRoom = () => {
        this.setState({
            createRoom: true,
        });
    };

    handleRoomEnter = (e) => {
        const roomName = e.target.value;
        Axios
            .get(`/rooms/enter?userName=${this.props.userName}&roomName=${roomName}`)
            .then((ret) => {
                if (ret.data === true) {
                    this.props.onEnterRoom(roomName);
                    return;
                }
                Modal.error({
                    title: 'Enter room failed',
                    content: 'Room does not exist anymore or room is full.',
                });
            });
    };

    handleRoomCreateCancel = () => {
        this.setState({
            createRoom: false,
        });
    };

    handleRoomCreateSuccess = (roomName) => {
        this.props.onEnterRoom(roomName);
    };

    render() {
        return (
            <div>
                {this.state.createRoom ?
                    <CreateRoom onCancel={this.handleRoomCreateCancel} userName={this.props.userName}
                                onSuccess={this.handleRoomCreateSuccess}/> : null}
                <Layout>
                    <Header>
                        <Tooltip placement="topLeft" title="Create new room">
                            <Button type="primary" onClick={this.handleCreateRoom}>
                                <Icon type="plus"/>
                            </Button>
                        </Tooltip>
                    </Header>
                    <Content>
                        <Table columns={this.state.columns} dataSource={this.state.rooms}/>
                    </Content>
                </Layout>
            </div>
        );
    }
}

export default Room;