import React from 'react';

export default class SearchForm extends React.Component{
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit() {
        this.props.addCard();
    }

    render() {
        return <form onSubmit={this.handleSubmit}>
            <div className="row">
                <div className="small-2 columns">
                    <button className="hollow button" >Send Messages Directly to Queue</button>
                </div>
            </div>
        </form>;
    }
}


