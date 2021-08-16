import './App.css';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import { Home } from './pages/Home';
import { Noteboard } from './pages/Noteboard';
import { AddNote } from './pages/AddNote';
import { EditNote } from './pages/EditNote';

function App() {
  return (
    <Router>
      <Switch>
        <Route path='/' exact component={Home}/>
        <Route path='/noteboard' component={Noteboard}/>
        <Route path='/add-note' component={AddNote}/>
        <Route path='/edit-note/:id' component={EditNote}/>
      </Switch>
    </Router>
  );
}

export default App;
