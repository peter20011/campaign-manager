import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function AddFunds() {
  const [balance, setBalance] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleUpdateBalance = (e) => {
    e.preventDefault();

    if (balance <= 0) {
      setError('Balance must be greater than 0.');
      return;
    }

    axios.post('http://localhost:8080/api/account', { initialBalance: balance })
      .then(response => {
        navigate('/'); // Redirect to home after success
      })
      .catch(error => {
        setError('Error updating balance: ' + error.message);
      });
  };

  return (
    <div>
      <h2>Add Funds to Emerald Account</h2>
      <form onSubmit={handleUpdateBalance}>
        <label>
          Enter New Balance:
          <input
            type="number"
            value={balance}
            onChange={(e) => setBalance(e.target.value)}
          />
        </label>
        <button type="submit">Update Balance</button>
      </form>
      {error && <p style={{ color: 'red' }}>{error}</p>}
    </div>
  );
}

export default AddFunds;
