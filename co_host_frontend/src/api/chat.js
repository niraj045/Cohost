import api from './config';

export const chatAPI = {
  getEventMessages: async (eventId) => {
    const response = await api.get(`/events/${eventId}/messages`);
    return response.data;
  },

  sendMessage: async (eventId, messageData) => {
    const response = await api.post(`/events/${eventId}/messages`, messageData);
    return response.data;
  },

  deleteMessage: async (messageId) => {
    const response = await api.delete(`/messages/${messageId}`);
    return response.data;
  }
};