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
      <AllRecipes />
      <FindRecipes />
    </div>
  );
}

function AllRecipes() {
  const [recipes, setRecipes] = useState([]);

  useEffect(() => {
    facade.getAllRecipes().then(res => setRecipes(res));
  }, []);

  return (
    <li>
      {recipes.map(recipe => (
        <ul>{recipe.ingredientList}</ul>
      ))}
    </li>
  )
}

function FindRecipes() {
  const [id, setId] = useState(0);
  const [recipe, setRecipe] = useState({});

  const onChange = evt => { 
    setId(evt.target.value);
  };

  const onSubmit = evt => {
    evt.preventDefault();
    facade.findRecipe(id).then(res => setRecipe(res));
  };

  return (
    <form onSubmit={onSubmit}> 
      <input type="number" ingredientList="id" placeholder="Id" onChange={onChange} />
      {recipe.ingredientList}
    </form>
  );
}

export default App;
