import request from "./request";

// 根据email获取验证码
export function getEmailCode(email) {
  return request({
    url: "auth/emailCode?email=" + email,
    method: "post",
  });
}
// 用户注册api接口
export function register(user) {
  console.log('register_api')
  return request({
    url: '/user/register/'+user.username+'/'+user.password+'/'+user.password+'/'+user.email,
    method: 'post',
    data
  })
}