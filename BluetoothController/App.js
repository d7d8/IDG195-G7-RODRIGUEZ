import React from 'react';
import { StyleSheet, Text, View, TextInput } from 'react-native';

export default class App extends React.Component {
  render() {
    return (
      <View style={{padding: 20}}>
        <TextInput
          style = {{ height: 60 }}
          placeholder = "Lista de dispositivos"
          onChangeText = {(password) => this.setState({password})}
        />
        <TextInput
          style = {{ height: 60 }}
          placeholder = "Lista de dispositivos"
          onChangeText = {(usuario) => this.setState({usuario})}
        />
        <View style = {styles.buttonContainer}>
          <Button
            onPress = {this._onPressButton}
            title = "Ingresar" 
          />
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#afff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
