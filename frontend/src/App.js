import './App.css';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import { Home } from './pages/Home';
import { Noteboard } from './pages/Noteboard';

function App() {
  return (
    <Router>
      <Switch>
        <Route path='/' exact component={Home}/>
        <Route path='/noteboard' component={Noteboard}/>
      </Switch>
    </Router>
  );
}

export default App;
