import './App.css';
import React, { useMemo, useState } from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import { Home } from './pages/Home';
import { Noteboard } from './pages/Noteboard';
import { AddNote } from './pages/AddNote';
import { EditNote } from './pages/EditNote';
import { Signup } from './pages/Signup';
import { AccountVerification } from './pages/AccountVerification';
import { SignIn } from './pages/Signin';
import { Test } from './pages/Test';

export const JWTContext = React.createContext({
  JWTData:{
    refreshToken:'',
    accessToken:''
  },
  setJWTData: () => {}
});

function App() {

  const [JWTData, setJWTData] = useState({
    refreshToken:'',
    accessToken: ''
  });
  
  const JWTValues = useMemo(() => ({JWTData, setJWTData}), [JWTData]);

  return (

    <JWTContext.Provider value={JWTValues}>


      <Router>
        <Switch>
          <Route path='/' exact component={Home}/>
          <Route path='/test' component={Test}/>
          <Route path='/noteboard' component={Noteboard}/>
          <Route path='/add-note' component={AddNote}/>
          <Route path='/edit-note/:id' component={EditNote}/>
          <Route path='/sign-up' component={Signup}/>
          <Route path='/sign-in' component={SignIn}/>
          <Route path='/account-verification' component={AccountVerification}/>
        </Switch>
      </Router>

    </JWTContext.Provider>
  );
}

export default App;
