import React from 'react';

export default class Loading extends React.Component {
    
    render() {
        return (
            <div className="loading">
                <div className="spinner">
                    <div className="rect1" />
                    <div className="rect2" />
                    <div className="rect3" />
                    <div className="rect4" />
                    <div className="rect5" />
                </div>
            </div>
        );
    }

}

