import React, {Component} from "react";
import "./App.css";
import Axios from "axios";
import {Avatar, Button, Icon, Layout, Modal, notification, Spin, Tag} from "antd";

const {Header, Content, Footer} = Layout;

class Room extends Component {

    constructor() {
        super();
        this.state = {
            horizontal: [],
            vertical: [],
            square: [],
            player1Name: null,
            player2Name: null,
            currentPlayer: null,
            over: false,
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
        if (this.props.roomName === "lililala") {
            this.setState({
                horizontal: [[true, true, true], [true, true, true], [true, false, false], [true, false, false]],
                vertical: [[true, true, true, true], [true, true, false, true], [false, true, true, true]],
                square: [[2, 1, 1], [2, 0, 0], [0, 0, 0]],
                player1Name: "xiaowangzi",
                player2Name: "admin",
                currentPlayer: "admin",
            });
            return;
        }
        Axios
            .get(`/rooms/display?roomName=${this.props.roomName}`)
            .then((ret) => {
                let data = ret.data;
                this.setState({
                    horizontal: data.horizontal,
                    vertical: data.vertical,
                    square: data.square,
                    player1Name: data.player1Name,
                    player2Name: data.player2Name,
                    currentPlayer: data.currentPlayer,
                });
                if (data.winnerName !== null && !this.state.over) {
                    this.openGameOver(this.props.userName === data.winnerName);
                    this.setState({
                        over: true,
                    });
                }
            });
    }

    handleLeave = () => {
        // todo
        if (this.props.userName === "admin") {
            this.props.onLeave();
            return;
        }
        Axios
            .get(`/rooms/leave?roomName=${this.props.roomName}&userName=${this.props.userName}`)
            .then(() => {
                this.props.onLeave();
            });
    };

    handleStart = () => {
        Axios
            .get(`/rooms/start?roomName=${this.props.roomName}`)
            .then(() => {
                this.fetch();
            });
    };

    openGameOver = (win) => {
        notification.open({
            message: 'Game Over',
            description: win ? "You win!!!" : "You lose...",
            icon: <Icon type={win ? "smile-circle" : "frown-circle"} style={{color: '#108ee9'}}/>,
            btn: null,
            onClose: this.setState({
                over: false,
            })
        });
    };

    render() {
        return (
            <div>
                <Modal visible={this.state.currentPlayer === null || this.state.currentPlayer !== this.props.userName}
                       closable={false} footer={null}>
                    <Spin tip="Waiting for the opponent..."
                          style={{display: "block"}}/>
                </Modal>
                <Layout>
                    <Header>
                    <span>
                        <Tag color="geekblue">Room {this.props.roomName}</Tag>
                    </span>
                        <span style={{float: "right"}}>
                        <Avatar style={{backgroundColor: "red", verticalAlign: 'middle'}} size="large">
                            {this.state.player1Name === null ? "" : this.state.player1Name}
                        </Avatar>
                        <label style={{color: "white", paddingLeft: "10px", paddingRight: "10px"}}>vs.</label>
                        <Avatar style={{backgroundColor: "yellow", verticalAlign: 'middle', color: "black"}}
                                size="large">
                            {this.state.player2Name === null ? "" : this.state.player2Name}
                        </Avatar>
                    </span>
                    </Header>
                    <Content>
                        <div>
                            {[0, 1, 2, 3].map((rowIndex) =>
                                [0, 1, 2, 3].map((colIndex) =>
                                    <div className="game-dot"
                                         style={{
                                             top: (15 + rowIndex * 25) + "%",
                                             left: (12 + colIndex * 25) + "%"
                                         }}/>
                                )
                            )}
                            {this.state.horizontal.map((row, rowIndex) =>
                                row.map((stroke, colIndex) =>
                                    <div className={stroke ? "game-edge-horizontal-stuffed" : "game-edge-horizontal"}
                                         onClick={() => {
                                             let userName = this.props.userName;
                                             let currentPlayer = this.state.currentPlayer;
                                             if (userName === currentPlayer || currentPlayer === null) {
                                                 return;
                                             }
                                             Axios
                                                 .get(`/rooms/connect?roomName=${this.props.roomName}&userName=${userName}` +
                                                     `&isHorizontal=true&row=${rowIndex}&col=${colIndex}`)
                                                 .then((ret) => {
                                                     this.fetch();
                                                 });
                                         }}
                                         style={{
                                             top: (15 + rowIndex * 25) + "%", left: (12 + colIndex * 25) + "%"
                                         }}/>)
                            )}
                            {this.state.vertical.map((row, rowIndex) =>
                                row.map((stroke, colIndex) =>
                                    <div className={stroke ? "game-edge-vertical-stuffed" : "game-edge-vertical"}
                                         onClick={() => {
                                             let userName = this.props.userName;
                                             let currentPlayer = this.state.currentPlayer;
                                             if (userName === currentPlayer || currentPlayer === null) {
                                                 return;
                                             }
                                             Axios
                                                 .get(`/rooms/connect?roomName=${this.props.roomName}&userName=${userName}` +
                                                     `&isHorizontal=false&row=${rowIndex}&col=${colIndex}`)
                                                 .then((ret) => {
                                                     this.fetch();
                                                 });
                                         }}
                                         style={{
                                             left: (12 + colIndex * 25) + "%", top: (15 + rowIndex * 25) + "%"
                                         }}/>)
                            )}
                            {this.state.square.map((row, rowIndex) =>
                                row.map((fill, colIndex) =>
                                    <div className="game-edge-square"
                                         style={{
                                             left: (12 + colIndex * 25) + "%",
                                             top: (15 + rowIndex * 25) + "%",
                                             background: fill === 0 ? "white" : (fill === 1 ? "red" : "yellow")
                                         }}/>)
                            )}
                        </div>
                    </Content>
                    <Footer>
                        <Button type="primary" onClick={this.handleLeave}>
                            <Icon type="left"/>Leave
                        </Button>
                        <Button type="primary" onClick={this.handleStart}>
                            Start<Icon type="caret-right"/>
                        </Button>
                    </Footer>
                </Layout>
            </div>
        );
    }
}

export default Room;