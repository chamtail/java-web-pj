import React, {Component} from "react";
import "./App.css";
import {Button, Checkbox, Form, Icon, Input, Modal} from "antd";
import Axios from "axios";

const FormItem = Form.Item;

class NormalRegisterForm extends Component {

    constructor() {
        super();
        this.state = {
            confirmDirty: false,
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
            Axios
                .get(`/users/register?userName=${userName}&password=${password}`)
                .then((ret) => {
                    if (ret.data === true) {
                        Modal.success({
                            title: 'Register successfully',
                            content: (
                                <div>
                                    <p>You will be redirected to login page.</p>
                                </div>
                            )
                        });
                        this.props.onFinish();
                        return;
                    }
                    Modal.error({
                        title: 'Register failed',
                        content: 'User name already taken.',
                    });
                });
        });
    };

    handleConfirmBlur = (e) => {
        const value = e.target.value;
        this.setState({confirmDirty: this.state.confirmDirty || !!value});
    };

    checkPassword = (rule, value, callback) => {
        const form = this.props.form;
        if (value && value !== form.getFieldValue('password')) {
            callback('Two passwords that you enter is inconsistent!');
        } else {
            callback();
        }
    };

    checkConfirm = (rule, value, callback) => {
        const form = this.props.form;
        if (value && this.state.confirmDirty) {
            form.validateFields(['confirm'], {force: true});
        }
        callback();
    };

    render() {
        const {getFieldDecorator} = this.props.form;
        const formItemLayout = {
            labelCol: {
                xs: {span: 24},
                sm: {span: 8},
            },
            wrapperCol: {
                xs: {span: 24},
                sm: {span: 16},
            },
        };
        return (
            <Modal visible={true}
                   closable={true}
                   onCancel={this.props.onFinish}
                   footer={null}>
                <div>
                    <Form onSubmit={this.handleSubmit} className="login-form">
                        <FormItem {...formItemLayout}
                                  label="User name">
                            {getFieldDecorator('userName', {
                                rules: [{
                                    required: true, message: 'Please input your user name!',
                                }],
                            })(
                                <Input />
                            )}
                        </FormItem>
                        <FormItem{...formItemLayout}
                                 label="Password">
                            {getFieldDecorator('password', {
                                rules: [{
                                    required: true, message: 'Please input your password!',
                                }, {
                                    validator: this.checkConfirm,
                                }],
                            })(
                                <Input type="password"/>
                            )}
                        </FormItem>
                        <FormItem{...formItemLayout}
                                 label="Confirm Password">
                            {getFieldDecorator('confirm', {
                                rules: [{
                                    required: true, message: 'Please confirm your password!',
                                }, {
                                    validator: this.checkPassword,
                                }],
                            })(
                                <Input type="password" onBlur={this.handleConfirmBlur}/>
                            )}
                        </FormItem>
                        <FormItem>
                            <Button type="primary" htmlType="submit" className="register-form-button">Register</Button>
                        </FormItem>
                    </Form>
                </div>
            </Modal>
        );
    }
}

export default Form.create()(NormalRegisterForm);