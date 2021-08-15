import './App.css';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import { Home } from './pages/Home';
import { Noteboard } from './pages/Noteboard';
import { AddNote } from './pages/AddNote';

function App() {
  return (
    <Router>
      <Switch>
        <Route path='/' exact component={Home}/>
        <Route path='/noteboard' component={Noteboard}/>
        <Route path='/add-note' component={AddNote}/>
      </Switch>
    </Router>
  );
}

export default App;
