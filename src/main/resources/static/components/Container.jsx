import React from "react";
import ReactDOM from "react-dom";
import Card from "./Card.jsx";
import SearchForm from "./SearchForm.jsx";
import $ from 'jquery';

export default class ContainerDiv extends React.Component{
    constructor(props) {
        super(props);
        this.addCard = this.addCard.bind(this);
        this.state = {
          data: []
        };
    }

    addCard() {
       $.get('http://localhost:8080/emitDirectly');
        let data = $.ajax({
          url: 'http://localhost:8080/print',
          dataType: 'json',
          success: function (data) {
            return data;
          }
        });
      console.log(data);
      this.setState({data});
    }

    render() {
        let cardId = 1;
        let cards = this.state.data.map(function (d) {
            return <Card id = {cardId++} message={d} />
        });
        return <div className="row">
            <h3>RabbitMQ Spring Boot Demo App</h3>
            <SearchForm addCard={this.addCard}/>
            <div>
                {cards}
            </div>
        </div>;
    }
}

ReactDOM.render(
    <ContainerDiv/>,
    document.getElementById('container')
);