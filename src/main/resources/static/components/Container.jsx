import React from "react";
import ReactDOM from "react-dom";
import Message from "./Message.jsx";
import ButtonForm from "./ButtonForm.jsx";
import $ from 'jquery';

export default class ContainerDiv extends React.Component {
  constructor(props) {
    super(props);
    this.emmitEvents = this.emmitEvents.bind(this);
    this.state = {
      data: []
    };
  }

  componentDidMount() {
    let component = this;
    $.ajax({
      url: 'http://localhost:8080/print',
      dataType: 'json',
      success: function (data) {
        component.setState({data});
      }
    });
  }

  emmitEvents() {
    $.get('http://localhost:8080/emitDirectly');
  }

  emmitEventsBroker() {
    $.get('http://localhost:8080/emitExchange');
  }

  render() {
    let messages = this.state.data.map((m) => <Message id={m} message={m}/>);
    return <div className="row">
      <h3>RabbitMQ Spring Boot Demo App</h3>
      <ButtonForm buttonText="Send Messages Directly to Queue" emmitEvents={this.emmitEvents}/>
      <ButtonForm buttonText="Send Messages to Exchange Broker" emmitEvents={this.emmitEventsBroker}/>
      <div>
        {messages}
      </div>
    </div>;
  }
}

ReactDOM.render(
  <ContainerDiv/>,
  document.getElementById('container')
);