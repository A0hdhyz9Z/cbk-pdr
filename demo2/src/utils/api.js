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

//发送验证码
export function sendCode(data) {
  console.log('login_api')
  return request({
    url: '/user/forget/'+data.username+'/'+data.email,
    method: 'post',
  })
}

//核对验证码
export function checkCode(data) {
  console.log('login_api')
  return request({
    url: '/user/verify/'+data.username+'/'+data.code,
    method: 'post',
  })
}

//修改密码
export function modifyPwd(data) {
  console.log('login_api')
  return request({
    url: '/user/update/'+data.password+'/'+data.password,
    method: 'put',
  })
}