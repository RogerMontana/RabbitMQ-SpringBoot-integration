import React from 'react';


export default class Message extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return <div className="panel callout radius">
      <h3>{this.props.message}</h3>
    </div>;
  }
}

