import request from "./request";

// 根据email获取验证码
export function getEmailCode(email) {
  return request({
    url: "auth/emailCode?email=" + email,
    method: "post",
  });
}
