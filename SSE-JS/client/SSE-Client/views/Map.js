import React from 'react';
import { StyleSheet, Text, View} from 'react-native';
import MapView, { Marker } from "react-native-maps";

const MapApp = () => {
  
  const [currentLocation, setCurrentLocation] = React.useState({
    latitude:40.715219,
    longitude: -74.014249,
    longitude: -74.010685,
    latitudeDelta: 0.0091,
    longitudeDelta: 0.011,
  })
  const [isPaused, setPause] = React.useState(false);
  const ws = React.useRef(null);
  
    React.useMemo(() => {
        ws.current = new WebSocket("ws://localhost:9090/dm1");
        ws.current.onopen = () => console.log("ws opened");
        ws.current.onclose = () => console.log("ws closed");
  
        const wsCurrent = ws.current;
  
        return () => {
            wsCurrent.close();
        };
    }, []);
  
    React.useMemo(() => {
        if (!ws.current) return;
  
        ws.current.onmessage = message => {
            if (isPaused) return;
            if(message) {
              let location = message.data.split(",")
              console.log(location)
              setCurrentLocation({latitude: parseFloat(location[0]), longitude: parseFloat(location[1])})
            }
        };
    }, [isPaused]);

  return (
    currentLocation ? 
      <MapView
        style={styles.map}
        region={{
          latitude: currentLocation.latitude,
          longitude: currentLocation.longitude,
          latitudeDelta: 0.0091,
          longitudeDelta: 0.011,
        }}
      >
      <Marker coordinate={{
          latitude: currentLocation.latitude,
          longitude: currentLocation.longitude,
          title: 'Foo Place',
          subtitle: '1234 Foo Drive'
  }} />
        </MapView>
  : null
  );
};

//create our styling code:
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

export default MapApp;