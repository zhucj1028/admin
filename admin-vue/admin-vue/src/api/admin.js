import request from '@/utils/request'
import { praseStrEmpty } from '@/utils/common';

// 查询用户列表
export function listAdmin(query) {
  return request({
    url: '/valuenet/api/user/findByPage',
    method: 'get',
    params: query
  })
}
// 新增用户
export function addAdmin(data) {
  return request({
    url: '/valuenet/api/user',
    method: 'post',
    data: data
  })
}

// 查询用户详细
export function getAdmin(adminId) {
  return request({
    url: '/valuenet/api/user/' + praseStrEmpty(adminId),
    method: 'get'
  })
}

// 修改用户
export function updateAdmin(data) {
  return request({
    url: '/valuenet/api/user',
    method: 'put',
    data: data
  })
}

// 删除用户
export function delAdmin(roleId) {
  return request({
    url: '/valuenet/api/user/' + roleId,
    method: 'delete'
  })
}

// 登录
export function login(data) {
  return request({
    url: '/valuenet/login',
    method: 'post',
    data
  })
}

// 获取登录数据
export function loginList(data) {
  return request({
    url: '/valuenet/api/user/logins',
    method: 'get',
    data: data
  })
}

// 获取日志数据
export function logList(data) {
  return request({
    url: '/valuenet/api/log/selectLog',
    method: 'get',
    data: data
  })
}
