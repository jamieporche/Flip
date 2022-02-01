<template>
  <div id="register-container">
    <div id="register" class="text-center">
      <form class="form-register" @submit.prevent="register">
        <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
        <div class="alert alert-danger" role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
        </div>
        <div id="username-container">
          <label for="username" class="sr-only">Username</label>
          <input
            type="text"
            id="username"
            class="form-control"
            placeholder="Username"
            v-model="user.username"
            required
            autofocus
          />
        </div>
        <div id="password-container">
          <label for="password" class="sr-only">Password</label>
          <input
            type="password"
            id="password"
            class="form-control"
            placeholder="Password"
            v-model="user.password"
            required
          />
          <input
            type="password"
            id="confirmPassword"
            class="form-control"
            placeholder="Confirm Password"
            v-model="user.confirmPassword"
            required
          />
          <router-link :to="{ name: 'login' }" id="login"
            >Have an account?</router-link
          >
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">
          Create Account
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scope>
body {
  background: url("../assets/pexels-fauxels-3184464.jpg");
  background-size: 100vw;
  background-repeat: no-repeat;
  margin: 0px;
}
h1 {
  text-align: center;
  font-size: 7vh;
}
label {
  font-size: 2.5vh;
}
div#register-container {
  display: flex;
  justify-content: center;
  width: 100%;
  height: 100%;
  margin: 50px 0px 0px 0px;
}
div#register {
  border-radius: 20px;
  border: none;
  margin: auto;
  width: 60%;
  background-image: linear-gradient(to bottom, rgb(240, 240, 230), #9c9c9c);
}
#username-container {
  display: flex;
  flex-direction: column;
  gap: 5px;
}
#password-container {
  display: flex;
  flex-direction: column;
  gap: 5px;
}
input {
  padding: 1vh;
  border: none;
}
#login {
  align-self: end;
  text-decoration: none;
  font-size: 2vh;
}
form {
  display: flex;
  flex-direction: column;
  padding: 0px 50px;
  gap: 20px;
}
button {
  margin-bottom: 50px;
  padding: 1vh;
  color: white;
  background-color: rgb(49, 92, 49);
  font-size: 2.5vh;
  border: none;
}
</style>