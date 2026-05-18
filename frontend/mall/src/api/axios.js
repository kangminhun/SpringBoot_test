import axios from 'axios';

//아니 뭔데 왜 안되는건데
const api = axios.create({
  baseURL:"http://localhost:8080",
});

export default api;