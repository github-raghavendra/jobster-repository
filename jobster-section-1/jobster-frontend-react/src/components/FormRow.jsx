const FormRow = ({ type, name, value, handleOnChange, labelText }) => {
  return (
    <div className="form-row">
      <label htmlFor={name} className="form-label">
        {labelText || name}
      </label>

      <input
        type={type}
        value={value}
        name={name}
        onChange={handleOnChange}
        className="form-input"
      />
    </div>
  );
};

export default FormRow;
