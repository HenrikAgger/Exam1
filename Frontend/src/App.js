import React, { useState, useEffect } from "react";
import facade from "./apiFacade";

function Login(props) {
  const [user, setUser] = useState({ username: "", password: "" });

  const login = evt => {
    evt.preventDefault();
    console.log(user);

    props.login(user.username, user.password);
  };

  const onChange = evt => {
    setUser({ ...user, [evt.target.id]: evt.target.value });
  };

  return (
    <div>
      <h2>Login</h2>
      <form onSubmit={login} onChange={onChange}>
        <input placeholder="User Name" id="username" />
        <input placeholder="Password" id="password" />
        <button>Login</button>
      </form>
    </div>
  );
}

function LoggedIn() {
  const [data, setData] = useState({ msg: "Fetching!!" });

  useEffect(() => {
    facade.fetchData().then(res => setData(res));
  }, []);

  return (
    <div>
      <h2>Data Received from server</h2>
      <h3>{data.msg}</h3>
    </div>
  );
}

function App() {
  const [loggedIn, setLoggedIn] = useState(false);

  const logout = () => {
    facade.logout();
    setLoggedIn(false);
  };
  const login = (user, pass) => {
    facade.login(user, pass).then(res => setLoggedIn(true));
  };

  return (
    <div>
      <AllPersons />
      <FindPersons />
    </div>
  );
}

function AllPersons() {
  const [persons, setPersons] = useState([]);

  useEffect(() => {
    facade.getAllPersons().then(res => setPersons(res));
  }, []);

  return (
    <li>
      {persons.map(person => (
        <ul>{person.email}</ul> // email ?
      ))}
    </li>
  );
}

function FindPersons() {
  const [id, setId] = useState(0);
  const [person, setPerson] = useState({});

  const onChange = evt => { 
    setId(evt.target.value);
  };

  const onSubmit = evt => {
    evt.preventDefault();
    facade.findPerson(id).then(res => setPerson(res));
  };

  return (// email
    <form onSubmit={onSubmit}> 
      <input type="number" email="id" placeholder="Id" onChange={onChange} />
      {person.email}
    </form>
  );
}

export default App;
