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
  console.log('sendCode_api')
  return request({
    url: '/user/forget/'+data.username+'/'+data.email,
    method: 'post',
  })
}

//核对验证码
export function checkCode(data) {
  console.log('checkCode_api')
  return request({
    url: '/user/verify/'+data.username+'/'+data.code,
    method: 'post',
  })
}

//修改密码
export function modifyPwd(data) {
  console.log('modifyPwd_api')
  return request({
    url: '/user/update/'+data.password+'/'+data.password,
    method: 'put',
  })
}

//上传文件
export function upload(data) {
  console.log('upload_api')
  return request({
    url: '/order/upload',
    method: 'post',
    data:data,
  })
}

//提交订单
export function order(data) {
  console.log('order_api')
  return request({
    url: '/order/commit?orderName='+data.orderName+'&orderDescription='+data.orderDescription+'&orderData='+data.orderDataset+'&orderAlg='+data.orderAlg,
    method: 'post',
  })
}

//SelectAll
export function selectAll() {
  console.log('selectAll_api')
  return request({
    url: '/order/record',
    method: 'post',
  })
}

//SelectById（展示图表）
export function selectById(id) {
  console.log('selectById_api')
  return request({
    url: '/order/detail/'+id,
    method: 'post',
  })
}

//预测
export function predict(data) {
  console.log('predict_api')
  return request({
    url: '/order/predict',
    method: 'post',
    data:data,
    // file:
    // orderId:
    // alg:1/2/3
  })
}