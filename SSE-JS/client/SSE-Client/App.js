import React from "react";
import { StyleSheet, Text, View} from 'react-native';
import MapApp from "./views/Map";
import Products from "./views/Products";


export default function App() {
  return (
    <View style={styles.container}> 
    
    </View>
  );

}

const styles = StyleSheet.create({
  container: {
    ...StyleSheet.absoluteFillObject,
    flex: 1, //the container will fill the whole screen.
    justifyContent: "flex-end",
    alignItems: "center",
  },
  map: {
    ...StyleSheet.absoluteFillObject,
  },
});



