import React from "react";
import Header from "./component/Header";
import Login from "./pages/Login";
import Profile from "./pages/Profile";
import Roles from "./pages/Roles";
import Users from "./pages/Users";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom"


const App = () => {
    return (
        <div>
            <Router>
                <Header />
                <Switch>
                    <Route exact={true} path="/" component={Login} />
                    <Route path="/users" component={Users} />
                    <Route path="/role" component={Roles} />
                    <Route path="/profile" component={Profile} />
                    <Route path="/login" component={Login} />
                </Switch>
            </Router>
        </div>
    );
};

export default App;
