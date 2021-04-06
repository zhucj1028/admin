import request from '@/utils/request'



export function getInfo(token) {
  return request({
    url: '/valuenet/api/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/valuenet/logout',
    method: 'post'
  })
}
