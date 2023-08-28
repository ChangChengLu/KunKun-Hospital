import myAxios from "@/plugins/MyAxios";

export async function userRegisterUsingPOST(
  body: API.UserRegisterRequest
): Promise<API.BaseResponseBoolean> {
  return myAxios.post("/user/manager/register", body);
}

export async function userLoginUsingPOST(
  body: API.UserLoginRequest
): Promise<API.BaseResponseUserLoginVO> {
  return myAxios.post("/user/manager/login", body);
}

export async function addUserUsingPOST(
  body: API.UserAddRequest
): Promise<API.BaseResponseLong> {
  return myAxios.post("/user/add", body);
}

export async function deleteUserUsingPOST(
  body: API.DeleteRequest
): Promise<API.BaseResponseBoolean> {
  return myAxios.post("/user/delete", body);
}

export async function getUserByIdUsingGET(
  params: number
): Promise<API.BaseResponseUser> {
  return myAxios.get("/user/get" + params);
}

export async function getUserVOByIdUsingGET(
  params: number
): Promise<API.BaseResponseUserVO> {
  return myAxios.get("/user/get/vo/" + params);
}

export async function userLogoutUsingPOST(): Promise<API.BaseResponseBoolean> {
  return myAxios.post("/user/logout", {});
}
