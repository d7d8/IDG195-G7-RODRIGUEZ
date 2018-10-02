import React, { Component } from 'react';
import { StyleSheet, Text, FlatList, View } from 'react-native';


export default class App extends Component {
  render() {
    return (
      <View style={styles.container}>
        <Text>Colores</Text>
        <FlatList
          data = {[
            {key: 'Rojo'},
            {key: 'Negro'},
            {key: 'Blanco'},
            {key: 'Verde'},
            {key: 'Amarillo'},
            {key: 'Azul'},
          ]}
          renderItem={({item}) => <Text style={styles.item}>{item.key}</Text>}
        />
      </View>
        
    );
  }
  
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingTop: 22
   },
   item: {
     padding: 10,
     fontSize: 18,
     height: 44,
   },
});
