import request from '@/utils/request'

// 查询菜单列表
export function listRes(query) {
  return request({
    url: '/valuenet/api/permission/findAll',
    method: 'get',
    params: query
  })
}

// 查询菜单详细
export function getRes(id) {
  return request({
    url: '/valuenet/api/permission/' + id,
    method: 'get'
  })
}

// 查询菜单下拉树结构
export function treeselect() {
  return request({
    url: '/valuenet/api/menu/treeselect',
    method: 'get'
  })
}

// 根据角色ID查询菜单下拉树结构
export function roleMenuTreeselect(roleId) {
  return request({
    url: '/valuenet/api/menu/roleMenuTreeselect/' + roleId,
    method: 'get'
  })
}

// 新增菜单
export function addRes(data) {
  return request({
    url: '/valuenet/api/permission',
    method: 'post',
    data: data
  })
}

// 修改菜单
export function updateRes(data) {
  return request({
    url: '/valuenet/api/permission',
    method: 'put',
    data: data
  })
}

// 删除菜单
export function delRes(resId) {
  return request({
    url: '/valuenet/api/permission/' + resId,
    method: 'delete'
  })
}
