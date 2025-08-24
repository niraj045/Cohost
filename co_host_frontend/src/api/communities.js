import api from './config';

export const communitiesAPI = {
  getAllCommunities: async () => {
    const response = await api.get('/communities');
    return response.data;
  },

  getCommunityById: async (id) => {
    const response = await api.get(`/communities/${id}`);
    return response.data;
  },

  createCommunity: async (communityData) => {
    const response = await api.post('/communities', communityData);
    return response.data;
  },

  updateCommunity: async (id, communityData) => {
    const response = await api.put(`/communities/${id}`, communityData);
    return response.data;
  },

  deleteCommunity: async (id) => {
    const response = await api.delete(`/communities/${id}`);
    return response.data;
  },

  joinCommunity: async (communityId) => {
    const response = await api.post(`/communities/${communityId}/join`);
    return response.data;
  },

  leaveCommunity: async (communityId) => {
    const response = await api.post(`/communities/${communityId}/leave`);
    return response.data;
  }
};