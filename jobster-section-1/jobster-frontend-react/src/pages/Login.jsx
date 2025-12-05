import React, { useState } from "react";
import Wrapper from "../assets/wrappers/RegisterPage";
import FormRow from "../components/FormRow";
import { toast } from "react-toastify";
import { useDispatch, useSelector } from "react-redux";
import { loginUser, registerUser } from "../feature/user/userSlice";

const initialState = {
  name: "",
  email: "",
  password: "",
  isMember: true,
};

const Login = () => {
  const [values, setValues] = useState(initialState);
  const { user, isLoading } = useSelector((store) => store.user);
  const dispatch = useDispatch();

  const handleOnChange = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setValues({ ...values, [name]: value });
  };

  const onSubmit = (event) => {
    event.preventDefault();
    const { name, email, password, isMember } = values;
    if (!email || !password || (!isMember && !name)) {
      toast.error("Please fill out all fields");
      return;
    }

    if (isMember) {
      dispatch(loginUser({ email: email, password: password }));
      return;
    }

    dispatch(registerUser({ name: name, email: email, password: password }));
    return;
  };

  const toggleMember = () => {
    setValues({ ...values, isMember: !values.isMember });
  };

  return (
    <Wrapper>
      <form className="form" onSubmit={onSubmit}>
        <h3> {values.isMember ? "Login" : "Register"} </h3>
        {/** name field */}

        {/** if isMember is false we will show the name field */}
        {!values.isMember && (
          <FormRow
            type="text"
            name="name"
            value={values.name}
            handleOnChange={handleOnChange}
          />
        )}

        {/** email field */}
        <FormRow
          type="email"
          name="email"
          value={values.email}
          handleOnChange={handleOnChange}
        />
        {/** password field */}
        <FormRow
          type="password"
          name="password"
          value={values.password}
          handleOnChange={handleOnChange}
        />

        <button type="submit" className="btn btn-block" disabled={isLoading}>
          submit
        </button>
        <p>
          {!values.isMember ? " Already a member ? " : " Not a member yet ? "}
          {/* while creating button inside form always provide type="button" mandatory
          neithe your form will auto submit when you click on the button
          means onSubmit will be called! loop hole in react app 3 hours wasted */}
          <button type="button" className="btn" onClick={toggleMember}>
            {!values.isMember ? " Login" : " Register"}
          </button>
        </p>
      </form>
    </Wrapper>
  );
};


export default Login;
