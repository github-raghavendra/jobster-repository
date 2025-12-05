import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import http from "../../utils/axios";
import { toast } from "react-toastify";
import { addUserToLocalStorage } from "../../utils/localStorage";

const initialState = {
  isLoading: false,
  user: null,
};

// 1st parameter is the name of the action
// 2nd parameter is the async callback function
export const registerUser = createAsyncThunk(
  "user/registerUser",
  async (user, thunkAPI) => {
    console.log(`Register user: ${JSON.stringify(user)}`);
    try {
      const response = await http.post("/register", user);
      console.log(response);
      return response.data;
    } catch (error) {
      console.log(error);
      return thunkAPI.rejectWithValue(error.response.data.msg);
    }
  }
);

export const loginUser = createAsyncThunk(
  "user/loginUser",
  async (user, thunkAPI) => {
    console.log(`Login user: ${JSON.stringify(user)}`);
    try {
      const response = await http.post("/login", user);
      console.log(response);
      return response.data;
    } catch (error) {
      console.log(error);
      return thunkAPI.rejectWithValue(error.response.data.msg);
    }
  }
);

const userSlice = createSlice({
  name: "user",
  initialState,
  extraReducers: (builder) => {
    builder
      .addCase(registerUser.pending, (state) => {
        state.isLoading = true;
      }) //action.payload is de-structured below
      .addCase(registerUser.fulfilled, (state, { payload }) => {
        // Again object de-structuring example
        const { user } = payload;
        state.isLoading = false;
        state.user = user;
        // Test also on sessionStorage file
        addUserToLocalStorage(user);
        toast.success(`Hello There ${user.name}`);
      })
      .addCase(registerUser.rejected, (state, { payload }) => {
        state.isLoading = false;
        toast.error(payload);
      })
      .addCase(loginUser.pending, (state) => {
        state.isLoading = true;
      })
      .addCase(loginUser.fulfilled, (state, { payload }) => {
        const { user } = payload;
        state.isLoading = false;
        state.user = user;
        addUserToLocalStorage(user);
        toast.success(`Welcome Back ${user.name}`);
      })
      .addCase(loginUser.rejected, (state, { payload }) => {
        state.isLoading = false;
        toast.error(payload);
      });
  },
});

export default userSlice.reducer;
