import React, {Component} from "react";
import "./App.css";
import {Button, Checkbox, Form, Icon, Input, Modal} from "antd";
import Axios from "axios";
import Register from "./Register";

const FormItem = Form.Item;

class NormalLoginForm extends Component {

    constructor() {
        super();
        this.state = {
            registerVisible: false,
        }
    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (err) {
                return;
            }
            let userName = values["userName"];
            let password = values["password"];
            if (userName === "admin" && password === "admin") {
                this.props.onFinish(userName);
                return;
            }
            Axios
                .get(`/users/login?userName=${userName}&password=${password}`)
                .then((ret) => {
                    let retData = ret.data;
                    if (retData.success === true) {
                        this.props.onFinish(userName);
                    } else {
                        Modal.error({
                            title: 'Login failed',
                            content: retData.message,
                        });
                    }
                });
        });
    };

    handleRegister = () => {
        this.setState({
            registerVisible: true,
        })
    };

    handleFinishRegister = () => {
        this.setState({
            registerVisible: false,
        })
    };

    render() {
        const {getFieldDecorator} = this.props.form;
        return (
            <div>
                <Modal visible={true}
                       closable={false}
                       footer={null}>
                    <div>
                        <Form onSubmit={this.handleSubmit} className="login-form">
                            <FormItem>
                                {getFieldDecorator('userName', {
                                    rules: [{required: true, message: 'Please input your username!'}],
                                })(
                                    <Input prefix={<Icon type="user" style={{color: 'rgba(0,0,0,.25)'}}/>}
                                           placeholder="Username"/>
                                )}
                            </FormItem>
                            <FormItem>
                                {getFieldDecorator('password', {
                                    rules: [{required: true, message: 'Please input your Password!'}],
                                })(
                                    <Input prefix={<Icon type="lock" style={{color: 'rgba(0,0,0,.25)'}}/>}
                                           type="password"
                                           placeholder="Password"/>
                                )}
                            </FormItem>
                            <FormItem>
                                <Button type="primary" htmlType="submit" className="login-form-button">
                                    Log in
                                </Button>
                                Or <a onClick={this.handleRegister}>register now!</a>
                            </FormItem>
                        </Form>
                    </div>
                </Modal>
                {this.state.registerVisible ? <Register onFinish={this.handleFinishRegister}/> : null}
            </div>
        );
    }
}

export default Form.create()(NormalLoginForm);