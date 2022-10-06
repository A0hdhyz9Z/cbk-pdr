import request from "./request";


// 用户注册api接口
export function register(user) {
  console.log('register_api')
  return request({
    url: '/user/register/'+user.username+'/'+user.password+'/'+user.password+'/'+user.email,
    method: 'post',
  })
}

// 用户登录api接口
export function login(user) {
  console.log('login_api')
  return request({
    url: '/user/login/'+user.username+'/'+user.password,
    method: 'post',
  })
}