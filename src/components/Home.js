import React, { useEffect, useState } from 'react';
import axios from 'axios';

function Home() {
  const [balance, setBalance] = useState(0);

  useEffect(() => {
    axios.get('http://localhost:8080/api/account')
      .then(response => {
        setBalance(response.data.balance);
      })
      .catch(error => console.error('Error fetching account balance:', error));
  }, []);

  return (
    <div>
      <h2>Emerald Account Balance: ${balance}</h2>
      <p>Navigate to different sections to manage your campaigns or update your balance.</p>
    </div>
  );
}

export default Home;
