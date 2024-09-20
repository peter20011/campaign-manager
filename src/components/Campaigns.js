import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function Campaigns() {
  const [campaigns, setCampaigns] = useState([]);
  const [balance, setBalance] = useState(0);
  const [selectedCampaign, setSelectedCampaign] = useState(null);
  const [newCampaign, setNewCampaign] = useState({
    campaignName: '',
    keywords: '',
    bidAmount: 0,
    campaignFund: 0,
    status: true,
    town: '',
    radius: 1
  });
  const navigate = useNavigate();

  const cities = ['New York', 'Los Angeles', 'Chicago', 'Houston', 'Phoenix']; // Predefined city list

  useEffect(() => {
    loadCampaigns();
    loadBalance();
  }, []);

  // Load all campaigns
  const loadCampaigns = () => {
    axios.get('http://localhost:8080/api/campaigns/getAll')
      .then(response => {
        setCampaigns(response.data);
      })
      .catch(error => console.error('Error fetching campaigns:', error));
  };

  // Load current account balance
  const loadBalance = () => {
    axios.get('http://localhost:8080/api/account')
      .then(response => {
        setBalance(response.data.balance);
      })
      .catch(error => console.error('Error fetching account balance:', error));
  };

  // Create or update campaign
  const handleCreateOrUpdateCampaign = (e) => {
    e.preventDefault();

    // Prevent campaign creation if there are insufficient funds
    if (balance < newCampaign.campaignFund) {
      alert(`Insufficient funds. Current balance: $${balance}`);
      return;
    }

    if (selectedCampaign) {
      // Update existing campaign
      axios.put(`http://localhost:8080/api/campaigns/${selectedCampaign.id}`, newCampaign)
        .then(() => {
          loadCampaigns();
          resetForm();
          loadBalance();  // Update balance after updating a campaign
        })
        .catch(error => console.error('Error updating campaign:', error));
    } else {
      // Create new campaign and deduct the campaign fund
      axios.post('http://localhost:8080/api/campaigns', newCampaign)
        .then(() => {
          // Deduct the campaign fund from the displayed balance
          setBalance((prevBalance) => prevBalance - newCampaign.campaignFund);
          loadCampaigns();
          resetForm();
        })
        .catch(error => console.error('Error creating campaign:', error));
    }
  };

  // Delete a campaign
  const handleDeleteCampaign = (id) => {
    axios.delete(`http://localhost:8080/api/campaigns/${id}`)
      .then(() => {
        loadCampaigns();
        loadBalance();  // Update balance after deleting a campaign
      })
      .catch(error => console.error('Error deleting campaign:', error));
  };

  // Edit an existing campaign
  const handleEditCampaign = (campaign) => {
    setSelectedCampaign(campaign);
    setNewCampaign(campaign);
  };

  // Reset the form for creating a new campaign
  const resetForm = () => {
    setSelectedCampaign(null);
    setNewCampaign({
      campaignName: '',
      keywords: '',
      bidAmount: 0,
      campaignFund: 0,
      status: true,
      town: '',
      radius: 1
    });
  };

  return (
    <div>
      <h2>Manage Campaigns</h2>

      <h3>Current Balance: ${balance}</h3>

      <h3>{selectedCampaign ? 'Edit Campaign' : 'Create a New Campaign'}</h3>
      <form onSubmit={handleCreateOrUpdateCampaign} style={{ display: 'flex', flexDirection: 'column', width: '400px' }}>
        <input
          type="text"
          placeholder="Enter Campaign Name"
          value={newCampaign.campaignName}
          onChange={(e) => setNewCampaign({ ...newCampaign, campaignName: e.target.value })}
          required
        />
        <input
          type="text"
          placeholder="Enter Keywords"
          value={newCampaign.keywords}
          onChange={(e) => setNewCampaign({ ...newCampaign, keywords: e.target.value })}
          required
        />
        <input
          type="number"
          placeholder="Enter Bid Amount ($)"
          value={newCampaign.bidAmount}
          onChange={(e) => setNewCampaign({ ...newCampaign, bidAmount: parseFloat(e.target.value) })}
          required
        />
        <input
          type="number"
          placeholder="Enter Campaign Fund ($)"
          value={newCampaign.campaignFund}
          onChange={(e) => setNewCampaign({ ...newCampaign, campaignFund: parseFloat(e.target.value) })}
          required
        />
        <select
          value={newCampaign.town}
          onChange={(e) => setNewCampaign({ ...newCampaign, town: e.target.value })}
          required
        >
          <option value="">Select a Town</option>
          {cities.map((city) => (
            <option key={city} value={city}>{city}</option>
          ))}
        </select>
        <input
          type="number"
          placeholder="Enter Radius (km)"
          value={newCampaign.radius}
          onChange={(e) => setNewCampaign({ ...newCampaign, radius: parseInt(e.target.value) })}
          required
        />
        <button type="submit">{selectedCampaign ? 'Update Campaign' : 'Create Campaign'}</button>
        {selectedCampaign && <button onClick={resetForm}>Cancel Edit</button>}
      </form>

      <h3>All Campaigns</h3>
      <div style={{ marginTop: '20px' }}>
        {campaigns.length > 0 ? (
          campaigns.map((campaign) => (
            <div key={campaign.id} style={{ border: '1px solid #ccc', padding: '10px', marginBottom: '10px' }}>
              <p><strong>Campaign Name:</strong> {campaign.campaignName}</p>
              <p><strong>Keywords:</strong> {campaign.keywords}</p>
              <p><strong>Bid Amount:</strong> ${campaign.bidAmount}</p>
              <p><strong>Campaign Fund:</strong> ${campaign.campaignFund}</p>
              <p><strong>Status:</strong> {campaign.status ? 'Active' : 'Inactive'}</p>
              <p><strong>Town:</strong> {campaign.town}</p>
              <p><strong>Radius:</strong> {campaign.radius} km</p>
              <button onClick={() => handleEditCampaign(campaign)}>Edit</button>
              <button onClick={() => handleDeleteCampaign(campaign.id)} style={{ marginLeft: '10px' }}>Delete</button>
            </div>
          ))
        ) : (
          <p>No campaigns available.</p>
        )}
      </div>
    </div>
  );
}

export default Campaigns;
