import axios, { AxiosInstance } from "axios";

const instance: AxiosInstance = axios.create({
  baseURL: "http://localhost:8202/api/",
  timeout: 100000,
  headers: {},
});

instance.interceptors.response.use(
  function (response) {
    const data = response.data;
    if (data.code) {
      return data.data;
    }
    console.error("request error: ", data);
    return response;
  },
  function (error) {
    return Promise.reject(error);
  }
);

export default instance;
