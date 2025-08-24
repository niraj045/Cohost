import api from './config';

export const logisticsAPI = {
  getEventLogistics: async (eventId) => {
    const response = await api.get(`/events/${eventId}/logistics`);
    return response.data;
  },

  createLogisticsItem: async (eventId, itemData) => {
    const response = await api.post(`/events/${eventId}/logistics`, itemData);
    return response.data;
  },

  updateLogisticsItem: async (itemId, itemData) => {
    const response = await api.put(`/logistics/${itemId}`, itemData);
    return response.data;
  },

  deleteLogisticsItem: async (itemId) => {
    const response = await api.delete(`/logistics/${itemId}`);
    return response.data;
  },

  claimLogisticsItem: async (itemId, claimData) => {
    const response = await api.post(`/logistics/${itemId}/claim`, claimData);
    return response.data;
  },

  updateLogisticsClaim: async (claimId, claimData) => {
    const response = await api.put(`/logistics/claims/${claimId}`, claimData);
    return response.data;
  },

  deleteLogisticsClaim: async (claimId) => {
    const response = await api.delete(`/logistics/claims/${claimId}`);
    return response.data;
  }
};