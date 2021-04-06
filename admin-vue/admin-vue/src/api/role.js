import request from '@/utils/request'
import { praseStrEmpty } from '@/utils/common';

// 查询角色列表
export function listRole(query) {
  return request({
    url: '/valuenet/api/role/findByPage',
    method: 'get',
    params: query
  })
}
// 查询角色列表
export function findAllRole(query) {
  return request({
    url: '/valuenet/api/role/findAll',
    method: 'get',
    params: query
  })
}
// 新增角色
export function addRole(data) {
  return request({
    url: '/valuenet/api/role',
    method: 'post',
    data: data
  })
}

// 查询角色详细
export function getRole(roleId) {
  return request({
    url: '/valuenet/api/role/' + praseStrEmpty(roleId),
    method: 'get'
  })
}

// 修改角色
export function updateRole(data) {
  return request({
    url: '/valuenet/api/role',
    method: 'put',
    data: data
  })
}

// 修改角色
export function impower(data) {
  return request({
    url: '/valuenet/api/role/impower',
    method: 'post',
    data: data
  })
}


// 删除角色
export function delRole(roleId) {
  return request({
    url: '/valuenet/api/role/' + roleId,
    method: 'delete'
  })
}
export function roleMenuTreeselect(roleId) {
  return request({
    url: '/valuenet/api/role/getRes/' + roleId,
    method: 'get'
  })
}

