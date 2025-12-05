import React from "react";
import { Outlet } from "react-router-dom";
import Logo from "../components/Logo";

const RootLayout = () => {
  return (
    <>
      <div>
        <Logo />
      </div>
      <Outlet />
    </>
  );
};

export default RootLayout;
