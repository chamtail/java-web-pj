import React from 'react';
import Loading from '../base/loading';

export default class Login extends React.Component {

    constructor() {
        super();
        this.state = {
            loading: true,
            loginUserName: "",
			loginPassword: "",
			registerUserName: "",
			registerPassword: "",
        };
    }

    render() {
        return (
			<div>
                {this.state.loading ? (
                    <Loading />
                ) : null}
			</div>
        );
    }

}

