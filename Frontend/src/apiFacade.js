//const URL = "http://localhost:8080/restaurant";
const URL = "https://henriksdomainname.dk/exam1";

function handleHttpErrors(res) {
  if (!res.ok) {
    return Promise.reject({ status: res.status, fullError: res.json() });
  }
  return res.json();
}

function ApiFacade() {
  //Insert utility-methods from a latter step (d) here

  const makeOptions = (method, addToken, body) => {
    var opts = {
      method: method,
      headers: {
        "Content-type": "application/json",
        Accept: "application/json"
      }
    };
    if (addToken && loggedIn()) {
      opts.headers["x-access-token"] = getToken();
    }
    if (body) {
      opts.body = JSON.stringify(body);
    }
    return opts;
  };
  const setToken = token => {
    localStorage.setItem("jwtToken", token);
  };
  const getToken = token => {
    return localStorage.getItem("jwtToken");
  };
  const loggedIn = () => {
    const loggedIn = getToken() != null;
    return loggedIn;
  };
  const logout = () => {
    localStorage.removeItem("jwtToken");
  };

  const login = (user, pass) => {
    const options = makeOptions("POST", true, {
      username: user,
      password: pass
    });
    return fetch(URL + "/api/login", options)
      .then(handleHttpErrors)
      .then(res => {
        setToken(res.token);
      });
  };
  const fetchData = () => {
    const options = makeOptions("GET", true);
    return fetch(URL + "/api/info/user", options).then(handleHttpErrors);
  };

  const getAllRecipes = () => {
    return fetch(URL + "/api/recipe/all").then(handleHttpErrors);
  };

  const findRecipe = (id) => {
    return fetch(URL + "/api/recipe/id/" + id).then(handleHttpErrors);
  };

  const createRecipe = (recipe) => {
    return fetch(URL + "/api/recipe/create", makeOptions("POST", false, recipe)).then(handleHttpErrors);
  };

  const editRecipe = (recipe) => {
    return fetch(URL + "/api/recipe/edit", makeOptions("PUT", false, recipe)).then(handleHttpErrors);
  };  

  const deleteRecipe = (id) => {
    return fetch(URL + "/api/recipe/delete/" + id).then(handleHttpErrors);
  };  


  return {
    logout,
    login,
    fetchData,
    getAllRecipes,
    findRecipe,
    createRecipe,
    editRecipe,
    deleteRecipe,
  };
}

const facade = new ApiFacade();
export default facade;
