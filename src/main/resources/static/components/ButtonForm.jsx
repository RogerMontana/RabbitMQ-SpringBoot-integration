import React from 'react';

export default class ButtonForm extends React.Component {
  constructor(props) {
    super(props);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleSubmit() {
    this.props.emmitEvents();
  }

  render() {
    return <form onSubmit={this.handleSubmit}>
      <div className="row">
        <div className="small-2 columns">
          <button className="hollow button">{this.props.buttonText}</button>
        </div>
      </div>
    </form>;
  }
}


