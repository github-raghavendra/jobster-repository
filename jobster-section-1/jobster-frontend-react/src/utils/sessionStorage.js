export const addUserToLocalStorage = (user) => {
  sessionStorage.setItem('user', JSON.stringify(user));
};

export const removeUserFromLocalStorage = () => {
  sessionStorage.removeItem('user');
};

export const getUserFromLocalStorage = () => {
  const result = sessionStorage.getItem('user');
  const user = result ? JSON.parse(result) : null;
  return user;
};
