import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { RootLayout, Dashboard, Landing, Error, Register } from "./pages";
import { ToastContainer } from "react-toastify";

const router = createBrowserRouter([
  {
    path: "/",
    element: <RootLayout />,
    children: [
      { index: true, element: <Dashboard /> },
      { path: "landing", element: <Landing /> },
      { path: "register", element: <Register /> },
      { path: "*", element: <Error /> },
    ],
  },
]);


function App() {
  return (
    <>
      <ToastContainer position="top-center" />
      <RouterProvider router={router} />
    </>
  );
}

export default App;
