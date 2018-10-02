import React, { Component } from 'react';
import { StyleSheet, Text, View, Image } from 'react-native';

class Greeting extends Component {
  render() {
    return (
      <Text style = {styles.smallgreen}> Buenos dias {this.props.saludo}!</Text>
    );
  }
}

export default class App extends Component {
  render() {
    let pic = {
      uri: 'https://upload.wikimedia.org/wikipedia/commons/d/de/Bananavarieties.jpg'
    };
    return (
      <View style={{alignItems: 'center'}}>
        <Greeting saludo='Roxanne' />
        <Greeting saludo='Henry' />
        <Greeting saludo='Maria' />
        <Image source={pic} style={{width: 193, height: 140}}/>
        <Text style = {styles.yellow}>Mira las bananas arriba</Text>
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
