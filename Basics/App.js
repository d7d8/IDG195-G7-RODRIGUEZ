import React, { Component } from 'react';
import { StyleSheet, Text, View, Image, TextInput, Button, Alert } from 'react-native';

class Greeting extends Component {
  render() {
    return (
      <Text style = {styles.smallgreen}> Buenos dias {this.props.saludo}!</Text>
    );
  }
}

class Blink extends Component {
    constructor(props) {
    super(props);
    this.state = {isShowingText: true};

    // Toggle the state every second
    setInterval(() => {
      this.setState(previousState => {
        return { isShowingText: !previousState.isShowingText };
      });
    }, 1000);
  }

  render() {
    let display = this.state.isShowingText ? this.props.text : ' ';
    return (
      <Text>{display}</Text>
    );
  }
}


export default class App extends Component {
  
  constructor(props) {
    super(props);
    this.state = {text: ''};
  }
 
  render() {
    let pic = {
      uri: 'https://upload.wikimedia.org/wikipedia/commons/d/de/Bananavarieties.jpg'
    };
    return (
      <View style={{flex: 1, flexDirection: 'column', justifyContent: 'space-between',}}>
        <Greeting saludo='Roxanne' />
        <Greeting saludo='Henry' />
        <Greeting saludo='Maria' />
        <Button
          onPress={() => {
          Alert.alert('Alerta desbloqueada!');
          }}
          title="Presioname"
        />
        <TextInput
          style={{height: 40}}
          placeholder="Traductor pizzero"
          onChangeText={(text) => this.setState({text})}
        />
        <Image source={pic} style={{width: 350, height: 200}}/>
        <Text style={{padding: 10, fontSize: 42}}>
          {this.state.text.split(' ').map((word) => word && '🍕').join(' ')}
        </Text>
        <Text style = {styles.yellow}>Mira las bananas arriba</Text>
        <Blink text = 'Yo parpadeo'/>
      </View>   
    );
  }
  
}

const styles = StyleSheet.create({
  smallgreen: {
    color: 'green',
    fontSize: 10,
  },
  yellow: {
    color: 'yellow',
  },
});
