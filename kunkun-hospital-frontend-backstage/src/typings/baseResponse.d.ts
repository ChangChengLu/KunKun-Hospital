declare namespace API {
  type BaseResponseBoolean = {
    code?: number;
    data?: boolean;
    message?: string;
  };

  type BaseResponseLong = {
    id?: number;
  };

  type BaseResponseUserLoginVO = {
    code?: number;
    data?: API.LoginUserVO;
    message?: string;
  };

  type BaseResponseUser = {
    code?: number;
    data?: API.User;
    message?: string;
  };

  type BaseResponseUserVO = {
    code?: number;
    data?: API.UserVO;
    message?: string;
  };
}
